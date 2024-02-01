package com.thinking.machines.admin.bl.interfaces.managers;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;

public interface StudentManagerInterface
{
public void addStudent(StudentInterface student) throws BLException;
public void updateStudent(StudentInterface student) throws BLException;
public void deleteStudent(String studentId) throws BLException;
public StudentInterface getByEnrollmentNumber(String enrollmentNumber) throws BLException;
public StudentInterface getByAadharCardNumber(String aadharCardNumber) throws BLException;
public StudentInterface getByStudentId(String studentId) throws BLException;
public boolean studentIdExists(String studentId);
public boolean aadharCardNumberExists(String aadharCardNumber);
public boolean enrollmentNumberExists(String enrollmentNumber);
public Set<StudentInterface> getAll() throws BLException;
public Set<StudentInterface> getByCourseCode(int courseCode) throws BLException;
public int getCount();
public int getCountByCourseCode(int courseCode)throws BLException;
public boolean isCourseAlloted(int courseCode)throws BLException;
}
