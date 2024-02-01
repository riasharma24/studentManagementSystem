package com.thinking.machines.admin.dl.interfaces.dto;
import java.util.*;

public interface StudentDTOInterface extends java.io.Serializable,Comparable<StudentDTOInterface>
{
public void setName(String name);
public String getName();
public void setStudentId(String studentId);
public String getStudentId();
public void setEnrollmentNumber(String enrollmentNumber);
public String getEnrollmentNumber();
public void setAadharCardNumber(String aadharCardNumber);
public String getAadharCardNumber();
public void setGender(char gender);
public char getGender();
public void setIsIndian(boolean isIndian);
public boolean getIsIndian();
public void setDateOfBirth(Date dateOfBirth);
public Date getDateOfBirth();
public void setCourseCode(int code);
public int getCourseCode();
}