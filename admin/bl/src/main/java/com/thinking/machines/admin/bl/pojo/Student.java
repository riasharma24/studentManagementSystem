package com.thinking.machines.admin.bl.pojo;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import java.util.*;

public class Student implements StudentInterface
{
private String name;
private String studentId;
private String aadharCardNumber;
private String enrollmentNumber;
private Date dateOfBirth;
private boolean isIndian;
private char gender;
private int courseCode;

public Student()
{
this.name="";
this.studentId="";
this.aadharCardNumber="";
this.enrollmentNumber="";
this.dateOfBirth=null;
this.isIndian=false;
this.gender=' ';
this.courseCode=0;
}

public void setStudentId(String studentId)
{
this.studentId=studentId;
}

public String getStudentId()
{
return this.studentId;
}

public void setAadharCardNumber(String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}

public String getAadharCardNumber()
{
return this.aadharCardNumber;
}

public void setEnrollmentNumber(String enrollmentNumber)
{
this.enrollmentNumber=enrollmentNumber;
}

public String getEnrollmentNumber()
{
return this.enrollmentNumber;
}

public void setDateOfBirth(Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}

public Date getDateOfBirth()
{
return this.dateOfBirth;
}

public void setGender(char gender)
{
this.gender=gender;
}

public char getGender()
{
return this.gender;
}

public void setIsIndian(boolean isIndian)
{
this.isIndian=isIndian;
}

public boolean getIsIndian()
{
return this.isIndian;
}

public void setName(String name)
{
this.name=name;
}

public String getName()
{
return this.name;
}

public void setCourseCode(int courseCode)
{
this.courseCode=courseCode;
}

public int getCourseCode()
{
return this.courseCode;
}

public int hashCode()
{
return this.studentId.toUpperCase().hashCode();
}

public int compareTo(StudentInterface other)
{
return this.studentId.compareTo(other.getStudentId());
}

public boolean equals(Object other)
{
if(!(other instanceof StudentInterface))return false;
StudentInterface student=(StudentInterface)other;
return student.getStudentId().equalsIgnoreCase(this.studentId);
}

}