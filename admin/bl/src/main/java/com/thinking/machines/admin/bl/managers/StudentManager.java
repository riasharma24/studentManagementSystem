package com.thinking.machines.admin.bl.managers;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.exceptions.*;
import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import java.util.*;

public class StudentManager implements StudentManagerInterface
{
Map<String,StudentInterface> aadharCardNumberWiseStudentsMap;
Map<String,StudentInterface> enrollmentNumberWiseStudentsMap;
Map<String,StudentInterface> studentIdWiseStudentsMap;
Set<StudentInterface> studentsSet;
private static StudentManagerInterface studentManager=null;

private StudentManager() throws BLException
{
populateDataStructures();
}

private void populateDataStructures() throws BLException
{
this.aadharCardNumberWiseStudentsMap=new HashMap<>();
this.enrollmentNumberWiseStudentsMap=new HashMap<>();
this.studentIdWiseStudentsMap=new HashMap<>();
this.studentsSet=new HashSet<>();

StudentInterface dsStudent;
try{
Set<StudentDTOInterface> students=new StudentDAO().getAll();
for(StudentDTOInterface studentDTO : students)
{
dsStudent=new Student();
dsStudent.setName(studentDTO.getName());
dsStudent.setAadharCardNumber(studentDTO.getAadharCardNumber());
dsStudent.setEnrollmentNumber(studentDTO.getEnrollmentNumber());
dsStudent.setStudentId(studentDTO.getStudentId());
dsStudent.setGender(studentDTO.getGender());
dsStudent.setIsIndian(studentDTO.getIsIndian());
dsStudent.setDateOfBirth(studentDTO.getDateOfBirth());
dsStudent.setCourseCode(studentDTO.getCourseCode());
aadharCardNumberWiseStudentsMap.put(dsStudent.getAadharCardNumber(),dsStudent);
enrollmentNumberWiseStudentsMap.put(dsStudent.getEnrollmentNumber(),dsStudent);
studentIdWiseStudentsMap.put(dsStudent.getStudentId(),dsStudent);
studentsSet.add(dsStudent);
}
}catch(DAOException daoException)
{
throw new BLException(daoException.getMessage());
}
}

public static StudentManagerInterface getStudentManager() throws BLException
{
if(studentManager==null)studentManager=new StudentManager();
return studentManager;
}

public void addStudent(StudentInterface student) throws BLException
{
BLException blException;
blException=new BLException();
if(student==null)
{
blException.setGenericException("Student cannot be null");
throw blException;
}
String name=student.getName();
if(name==null)
{
blException.addPropertyException("name","Name cannot be null.");
}
else{
name=name.trim();
if(name.length()==0)
{
blException.addPropertyException("name","Length of name cannot be zero");
}
}
String aadharCardNumber=student.getAadharCardNumber();
if(aadharCardNumber==null)
{
blException.addPropertyException("aadharCardNumber","Aadhar card number cannot be null.");
}
else{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addPropertyException("Aadhar card number","Length of aadhar card number cannot be zero");
}
}
String enrollmentNumber=student.getEnrollmentNumber();
if(enrollmentNumber==null)
{
blException.addPropertyException("enrollmentNumber","Enrollment number cannot be null.");
}
else{
enrollmentNumber=enrollmentNumber.trim();
if(enrollmentNumber.length()==0)
{
blException.addPropertyException("Enrollment number","Length of enrollment number cannot be null");
}
}
int courseCode=student.getCourseCode();
if(courseCode<=0)
{
blException.addPropertyException("courseCode","Invalid course code");
}
if(new CourseDAO().codeExists(courseCode)==false)
{
blException.addPropertyException("courseCode","Course code does not exist");
}
Date dateOfBirth=student.getDateOfBirth();
if(dateOfBirth==null)
{
blException.addPropertyException("dateOfBirth","Date of birth cannot be null");
}
char gender=student.getGender();
if(gender==' ')
{
blException.addPropertyException("gender","Invalid gender");
}
boolean isIndian=student.getIsIndian();
if(blException.hasPropertyException())
{
throw blException;
}
try{
StudentDTOInterface studentDTO;
studentDTO=new StudentDTO();
studentDTO.setName(name);
studentDTO.setCourseCode(courseCode);
studentDTO.setIsIndian(isIndian);
studentDTO.setGender(gender);
studentDTO.setDateOfBirth(dateOfBirth);
studentDTO.setEnrollmentNumber(enrollmentNumber);
studentDTO.setAadharCardNumber(aadharCardNumber);
new StudentDAO().add(studentDTO);
StudentInterface dsStudent;
dsStudent=new Student();
dsStudent.setName(name);
dsStudent.setStudentId(studentDTO.getStudentId().toUpperCase());
dsStudent.setEnrollmentNumber(enrollmentNumber.toUpperCase());
dsStudent.setAadharCardNumber(aadharCardNumber.toUpperCase());
dsStudent.setGender(gender);
dsStudent.setCourseCode(courseCode);
dsStudent.setDateOfBirth(dateOfBirth);
dsStudent.setIsIndian(isIndian);
enrollmentNumberWiseStudentsMap.put(enrollmentNumber.toUpperCase(),dsStudent);
aadharCardNumberWiseStudentsMap.put(aadharCardNumber.toUpperCase(),dsStudent);
studentIdWiseStudentsMap.put(studentDTO.getStudentId(),dsStudent);
studentsSet.add(dsStudent);
student.setStudentId(studentDTO.getStudentId());
}catch(DAOException daoException)
{
BLException dlException=new BLException();
dlException.setGenericException(daoException.getMessage());
throw dlException;
}
}

public void updateStudent(StudentInterface student) throws BLException
{
BLException blException;
blException=new BLException();
if(student==null)
{
blException.setGenericException("Student cannot be null");
throw blException;
}
String name=student.getName();
if(name==null)
{
blException.addPropertyException("name","Name cannot be null");
}
else
{
name=name.trim();
if(name.length()==0)
{
blException.addPropertyException("name","Name cannot be of zero length");
}
}
String studentId=student.getStudentId();
if(studentId==null)
{
blException.addPropertyException("studentId","Student id cannot be null");
}
else
{
studentId=studentId.trim();
if(studentId.length()==0)
{
blException.addPropertyException("studentId","Student id cannot be of zero length");
}
}
String enrollmentNumber=student.getEnrollmentNumber();
if(enrollmentNumber==null)
{
blException.addPropertyException("enrollmentNumber","Enrollment number cannot be null");
}
else
{
enrollmentNumber=enrollmentNumber.trim();
if(enrollmentNumber.length()==0)
{
blException.addPropertyException("enrollment","Enrollment number cannot be of zero length");
}
}
String aadharCardNumber=student.getAadharCardNumber();
if(aadharCardNumber==null)
{
blException.addPropertyException("aadharCardNumber","Aadhar card number cannot be null");
}
else
{
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)
{
blException.addPropertyException("aadharCardNumber","Aadhar card number cannot be of zero length");
}
}
char gender=student.getGender();
if(gender==' ')
{
blException.addPropertyException("gender","Gender cannot be null");
}
int courseCode=student.getCourseCode();
if(courseCode<=0)
{
blException.addPropertyException("courseCode","Invalid course code");
}
if(new CourseDAO().codeExists(courseCode)==false)
{
blException.addPropertyException("courseCode","Course code does not exist");
}
Date dateOfBirth=student.getDateOfBirth();
if(dateOfBirth==null)
{
blException.addPropertyException("dateOfBirth","Date of birth cannot be null");
}
boolean isIndian=student.getIsIndian();
if(blException.hasPropertyException())
{
throw blException;
}
try{
aadharCardNumberWiseStudentsMap.remove(new StudentDAO().getByStudentId(studentId).getAadharCardNumber());
enrollmentNumberWiseStudentsMap.remove(new StudentDAO().getByStudentId(studentId).getEnrollmentNumber());
studentIdWiseStudentsMap.remove(studentId);
studentsSet.remove(new StudentDAO().getByStudentId(studentId));

StudentDTOInterface studentDTO;
studentDTO=new StudentDTO();
studentDTO.setName(name);
studentDTO.setDateOfBirth(dateOfBirth);
studentDTO.setGender(gender);
studentDTO.setIsIndian(isIndian);
studentDTO.setEnrollmentNumber(enrollmentNumber);
studentDTO.setAadharCardNumber(aadharCardNumber);
studentDTO.setStudentId(studentId);
studentDTO.setCourseCode(courseCode);
new StudentDAO().update(studentDTO);
StudentInterface dsStudent;
dsStudent=new Student();
dsStudent.setName(name);
dsStudent.setStudentId(studentId);
dsStudent.setAadharCardNumber(aadharCardNumber);
dsStudent.setEnrollmentNumber(enrollmentNumber);
dsStudent.setDateOfBirth(dateOfBirth);
dsStudent.setCourseCode(courseCode);
dsStudent.setIsIndian(isIndian);
dsStudent.setGender(gender);
aadharCardNumberWiseStudentsMap.put(aadharCardNumber.toUpperCase(),dsStudent);
enrollmentNumberWiseStudentsMap.put(enrollmentNumber.toUpperCase(),dsStudent);
studentIdWiseStudentsMap.put(studentId.toUpperCase(),dsStudent);
studentsSet.add(dsStudent);
}catch(DAOException daoException)
{
BLException dlException=new BLException();
dlException.setGenericException(daoException.getMessage());
throw dlException;
}
}

public void deleteStudent(String studentId) throws BLException
{
BLException blException;
blException=new BLException();
if(studentId==null)
{
blException.setGenericException("Student id cannot be null");
throw blException;
}
studentId=studentId.trim();
if(studentId.length()==0)
{
blException.addPropertyException("studentId","Student id cannot be of zero length");
}
try{
StudentInterface student=studentIdWiseStudentsMap.get(studentId);
new StudentDAO().delete(studentId.toUpperCase());
studentIdWiseStudentsMap.remove(studentId.toUpperCase());
enrollmentNumberWiseStudentsMap.remove(student.getEnrollmentNumber().toUpperCase());
aadharCardNumberWiseStudentsMap.remove(student.getAadharCardNumber().toUpperCase());
studentsSet.remove(student);
}catch(DAOException daoException)
{
BLException dlException;
dlException=new BLException();
dlException.setGenericException(daoException.getMessage());
throw dlException;
}
}

public StudentInterface getByEnrollmentNumber(String enrollmentNumber) throws BLException
{
if(enrollmentNumber==null)throw new BLException("Enrollment number cannot be null");
if(enrollmentNumberWiseStudentsMap.get(enrollmentNumber)==null)throw new BLException("Enrollment number does not exist");
StudentInterface dsStudent=enrollmentNumberWiseStudentsMap.get(enrollmentNumber);
StudentInterface student=new Student();
student.setName(dsStudent.getName());
student.setStudentId(dsStudent.getStudentId());
student.setEnrollmentNumber(dsStudent.getEnrollmentNumber());
student.setAadharCardNumber(dsStudent.getAadharCardNumber());
student.setGender(dsStudent.getGender());
student.setDateOfBirth(dsStudent.getDateOfBirth());
student.setCourseCode(dsStudent.getCourseCode());
student.setIsIndian(dsStudent.getIsIndian());
return student;
}

public StudentInterface getByAadharCardNumber(String aadharCardNumber) throws BLException
{
if(aadharCardNumber==null)throw new BLException("Aadhar card number cannot be null");
if(aadharCardNumberWiseStudentsMap.get(aadharCardNumber)==null)throw new BLException("Aadhar card number does not exist");
StudentInterface dsStudent=aadharCardNumberWiseStudentsMap.get(aadharCardNumber);
StudentInterface student=new Student();
student.setName(dsStudent.getName());
student.setStudentId(dsStudent.getStudentId());
student.setEnrollmentNumber(dsStudent.getEnrollmentNumber());
student.setAadharCardNumber(dsStudent.getAadharCardNumber());
student.setGender(dsStudent.getGender());
student.setDateOfBirth(dsStudent.getDateOfBirth());
student.setCourseCode(dsStudent.getCourseCode());
student.setIsIndian(dsStudent.getIsIndian());
return student;
}

public StudentInterface getByStudentId(String studentId) throws BLException
{
if(studentId==null)throw new BLException("Student id cannot be null");
if(studentIdWiseStudentsMap.get(studentId)==null)throw new BLException("Student id does not exist");
StudentInterface dsStudent=studentIdWiseStudentsMap.get(studentId);
StudentInterface student=new Student();
student.setName(dsStudent.getName());
student.setStudentId(dsStudent.getStudentId());
student.setEnrollmentNumber(dsStudent.getEnrollmentNumber());
student.setAadharCardNumber(dsStudent.getAadharCardNumber());
student.setGender(dsStudent.getGender());
student.setDateOfBirth(dsStudent.getDateOfBirth());
student.setCourseCode(dsStudent.getCourseCode());
student.setIsIndian(dsStudent.getIsIndian());
return student;
}

public boolean studentIdExists(String studentId)
{
return studentIdWiseStudentsMap.containsKey(studentId.toUpperCase());
}

public boolean aadharCardNumberExists(String aadharCardNumber)
{
return aadharCardNumberWiseStudentsMap.containsKey(aadharCardNumber.toUpperCase());
}

public boolean enrollmentNumberExists(String enrollmentNumber)
{
return enrollmentNumberWiseStudentsMap.containsKey(enrollmentNumber.toUpperCase());
}

public Set<StudentInterface> getAll() throws BLException
{
BLException blException;
blException=new BLException();
if(this.studentsSet.size()==0)
{
blException.setGenericException("No student entries");
throw blException;
}
Set<StudentInterface> students=new HashSet<>();
StudentInterface student;
for(StudentInterface dsStudent : studentsSet)
{
student=new Student();
student.setName(dsStudent.getName());
student.setEnrollmentNumber(dsStudent.getEnrollmentNumber());
student.setAadharCardNumber(dsStudent.getAadharCardNumber());
student.setStudentId(dsStudent.getStudentId());
student.setGender(dsStudent.getGender());
student.setDateOfBirth(dsStudent.getDateOfBirth());
student.setIsIndian(dsStudent.getIsIndian());
student.setCourseCode(dsStudent.getCourseCode());
students.add(student);
}
return students;
}

public Set<StudentInterface> getByCourseCode(int courseCode) throws BLException
{
BLException blException;
blException=new BLException();
if(this.studentsSet.size()==0)
{
blException.setGenericException("No student entries");
throw blException;
}
if(new CourseDAO().codeExists(courseCode)==false)
{
blException.setGenericException("Course code does not exist");
throw blException;
}
Set<StudentInterface> students=new HashSet<>();
StudentInterface student;
for(StudentInterface dsStudent : studentsSet)
{
if(dsStudent.getCourseCode()==courseCode)
{
student=new Student();
student.setName(dsStudent.getName());
student.setStudentId(dsStudent.getStudentId());
student.setEnrollmentNumber(dsStudent.getEnrollmentNumber());
student.setAadharCardNumber(dsStudent.getAadharCardNumber());
student.setDateOfBirth(dsStudent.getDateOfBirth());
student.setGender(dsStudent.getGender());
student.setCourseCode(dsStudent.getCourseCode());
student.setIsIndian(dsStudent.getIsIndian());
students.add(student);
}
}
return students;
}

public int getCount()
{
return studentsSet.size();
}

public int getCountByCourseCode(int courseCode) throws BLException
{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
try{
return studentDAO.getCountByCourse(courseCode);
}catch(DAOException daoException)
{
BLException dlException;
dlException=new BLException();
dlException.setGenericException(daoException.getMessage());
throw dlException;
}
}

public boolean isCourseAlloted(int courseCode) throws BLException
{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
try{
return studentDAO.isCourseAlloted(courseCode);
}catch(DAOException daoException)
{
BLException dlException;
dlException=new BLException();
dlException.setGenericException(daoException.getMessage());
throw dlException;
}
}

}