package com.thinking.machines.admin.bl.interfaces.managers;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;

public interface CourseManagerInterface
{
public void addCourse(CourseInterface course) throws BLException;
public void updateCourse(CourseInterface course) throws BLException;
public void deleteCourse(int code) throws BLException;
public int getCourseCount();
public Set<CourseInterface> getAll() throws BLException;
public CourseInterface getCourseByTitle(String title) throws BLException;
public CourseInterface getCourseByCode(int code) throws BLException;
public boolean courseCodeExists(int code);
public boolean courseTitleExists(String title);
}