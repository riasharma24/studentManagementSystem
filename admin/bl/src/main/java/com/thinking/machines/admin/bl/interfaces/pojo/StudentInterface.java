package com.thinking.machines.admin.bl.interfaces.pojo;
import java.util.*;

public interface StudentInterface extends Comparable<StudentInterface>,java.io.Serializable 
{
public void setStudentId(String studentId);
public String getStudentId();
public void setAadharCardNumber(String aadharCardNumber);
public String getAadharCardNumber();
public void setEnrollmentNumber(String enrollmentNumber);
public String getEnrollmentNumber();
public void setDateOfBirth(Date dateOfBirth);
public Date getDateOfBirth();
public void setGender(char gender);
public char getGender();
public void setIsIndian(boolean isIndian);
public boolean getIsIndian();
public void setName(String name);
public String getName();
public void setCourseCode(int courseCode);
public int getCourseCode();
}