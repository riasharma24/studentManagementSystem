package com.thinking.machines.admin.bl.managers;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.exceptions.*;
import java.util.*;

public class CourseManager implements CourseManagerInterface
{
private Map<String,CourseInterface> titleWiseCoursesMap;
private Map<Integer,CourseInterface> codeWiseCoursesMap;
private Set<CourseInterface> coursesSet;
private static CourseManager courseManager=null;

private CourseManager() throws BLException
{
populateDatastructures();
}

private void populateDatastructures() throws BLException
{
titleWiseCoursesMap=new HashMap<>();
codeWiseCoursesMap=new HashMap<>();
coursesSet=new TreeSet<>();
try{
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
Set<CourseDTOInterface> dlCourses;
dlCourses=courseDAO.getAll();
CourseInterface course;
for(CourseDTOInterface courseDTO : dlCourses)
{
course=new Course();
course.setCode(courseDTO.getCode());
course.setTitle(courseDTO.getTitle());
coursesSet.add(course);
titleWiseCoursesMap.put(course.getTitle().toUpperCase(),course);
codeWiseCoursesMap.put(new Integer(course.getCode()),course);
}
}catch(DAOException daoException)
{
BLException blException=new BLException(daoException.getMessage());
throw blException;
}
}

public static CourseManager getCourseManager() throws BLException
{
if(courseManager==null)courseManager=new CourseManager();
return courseManager;
}

public void addCourse(CourseInterface course) throws BLException
{
BLException blException=new BLException();
if(course==null)
{
blException.setGenericException("Course cannot be null");
throw blException;
}
String title=course.getTitle();
if(title==null)
{
blException.addPropertyException("title","Title cannot be null");
}
title=title.trim();
if(title.length()==0)
{
blException.addPropertyException("title","Title cannot be of zero length");
}
int code=course.getCode();
if(code!=0)
{
blException.addPropertyException("code","Code cannot be null");
}
if(this.titleWiseCoursesMap.containsKey(title))
{
blException.addPropertyException("title","Title already exists");
}
if(blException.hasPropertyException())
{
throw blException;
}
try{
CourseDTOInterface courseDTO;
courseDTO=new CourseDTO();
courseDTO.setTitle(title);
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
courseDAO.add(courseDTO);
course.setCode(courseDTO.getCode());
CourseInterface dsCourse=new Course();
dsCourse.setCode(course.getCode());
dsCourse.setTitle(course.getTitle());
titleWiseCoursesMap.put(dsCourse.getTitle().toUpperCase(),dsCourse);
codeWiseCoursesMap.put(dsCourse.getCode(),dsCourse);
coursesSet.add(dsCourse);
}catch(DAOException daoException)
{
BLException dlException=new BLException(daoException.getMessage());
throw dlException;
}
}

public void updateCourse(CourseInterface course) throws BLException
{
BLException blException;
blException=new BLException();
if(course==null)
{
blException.setGenericException("Course cannot be null");
throw blException;
}
String title=course.getTitle();
int code=course.getCode();
if(title==null)
{
blException.addPropertyException("title","Title cannot be null");
}
if(title.length()==0)
{
blException.addPropertyException("title","Title cannot be null");
}
if(new CourseDAO().codeExists(code)==false)
{
blException.addPropertyException("code","Course code does not exist");
}
if(blException.hasPropertyException())
{
throw blException;
}
try{
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
CourseDTOInterface courseDTO;
courseDTO=new CourseDTO();
courseDTO.setCode(code);
courseDTO.setTitle(title);
courseDAO.update(courseDTO);
codeWiseCoursesMap.remove(code);
titleWiseCoursesMap.remove(courseDAO.getByCode(code).getTitle());
CourseInterface dsCourse;
dsCourse=new Course();
dsCourse.setCode(courseDTO.getCode());
dsCourse.setTitle(courseDTO.getTitle());
codeWiseCoursesMap.put(dsCourse.getCode(),dsCourse);
titleWiseCoursesMap.put(dsCourse.getTitle(),dsCourse);
coursesSet.add(dsCourse);
}catch(DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}

public void deleteCourse(int code) throws BLException
{
BLException blException=new BLException();
if(code==0)
{
blException.setGenericException("Invalid code.");
throw blException;
}
if(new CourseDAO().codeExists(code)==false)
{
blException.setGenericException("Invalid code.");
throw blException;
}
try{
CourseInterface dsCourse=codeWiseCoursesMap.get(code);
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
titleWiseCoursesMap.remove(dsCourse.getTitle());
codeWiseCoursesMap.remove(code);
coursesSet.remove(dsCourse);
CourseDTOInterface courseDTO;
courseDTO=new CourseDTO();
courseDTO.setTitle(dsCourse.getTitle());
courseDTO.setCode(dsCourse.getCode());
courseDAO.delete(courseDTO);
}catch(DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}

public int getCourseCount()
{
return coursesSet.size();
}

public Set<CourseInterface> getAll() throws BLException
{
if(this.coursesSet.size()==0)throw new BLException("No courses present");
Set<CourseInterface> courses=new HashSet<>();
for(CourseInterface course : this.coursesSet)
{
courses.add(course);
}
return courses;
}

public CourseInterface getCourseByTitle(String title) throws BLException
{
BLException blException;
blException=new BLException();
if(title==null)
{
blException.setGenericException("Title cannot be null");
throw blException;
}
title=title.trim();
if(title.length()==0)
{
blException.setGenericException("Title cannot be of zero length");
throw blException;
}
CourseInterface dsCourse;
dsCourse=titleWiseCoursesMap.get(title.toUpperCase());
if(dsCourse==null)
{
blException.setGenericException("Course does not exist.");
throw blException;
}
CourseInterface course;
course=new Course();
course.setCode(dsCourse.getCode());
course.setTitle(dsCourse.getTitle());
return course;
}

public CourseInterface getCourseByCode(int code) throws BLException
{
if(code<=0)throw new BLException("Course code does not exist.");
if(new CourseDAO().codeExists(code)!=false)throw new BLException("Course code does not exist.");
CourseInterface dsCourse;
dsCourse=codeWiseCoursesMap.get(code);
if(dsCourse==null)throw new BLException("Course does not exist.");
CourseInterface course=new Course();
course.setTitle(dsCourse.getTitle());
course.setCode(dsCourse.getCode());
return course;
}

public boolean courseCodeExists(int code)
{
if(codeWiseCoursesMap.containsKey(code))return true;
return false;
}

public boolean courseTitleExists(String title)
{
if(titleWiseCoursesMap.containsKey(title.toUpperCase()))return true;
return false;
}

}