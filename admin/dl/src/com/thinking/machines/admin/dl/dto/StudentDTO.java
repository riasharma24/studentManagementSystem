package com.thinking.machines.admin.dl.dto;

import com.thinking.machines.admin.dl.interfaces.dto.*;
import java.util.*;

public class StudentDTO implements StudentDTOInterface
{
private String name;
private String enrollmentNumber;
private String aadharCardNumber;
private char gender;
private boolean isIndian;
private Date dateOfBirth;
private String studentId;
private int courseCode;

public StudentDTO()
{
this.name="";
this.enrollmentNumber="";
this.aadharCardNumber="";
this.gender=' ';
this.isIndian=false;
this.dateOfBirth=null;
this.studentId="";
this.courseCode=0;
}

public void setName(String name)
{
this.name=name;
}

public String getName()
{
return this.name;
}

public void setEnrollmentNumber(String enrollmentNumber)
{
this.enrollmentNumber=enrollmentNumber;
}

public String getEnrollmentNumber()
{
return this.enrollmentNumber;
}

public void setAadharCardNumber(String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}

public String getAadharCardNumber()
{
return this.aadharCardNumber;
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

public void setDateOfBirth(Date dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}

public Date getDateOfBirth()
{
return this.dateOfBirth;
}

public void setStudentId(String studentId)
{
this.studentId=studentId;
}

public String getStudentId()
{
return this.studentId;
}

public void setCourseCode(int courseCode)
{
this.courseCode=courseCode;
}

public int getCourseCode()
{
return this.courseCode;
}

public int compareTo(StudentDTOInterface other)
{
return this.studentId.compareToIgnoreCase(other.getStudentId());
}

public boolean equals(Object other)
{
if(!(other instanceof StudentDTOInterface))return false;
StudentDTOInterface studentDTO=(StudentDTOInterface)other;
return studentDTO.getStudentId().equalsIgnoreCase(this.studentId);
}

public int hashCode()
{
return this.studentId.toUpperCase().hashCode();
}

}