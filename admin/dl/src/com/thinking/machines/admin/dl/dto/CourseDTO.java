package com.thinking.machines.admin.dl.dto;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.exceptions.*;

public class CourseDTO implements CourseDTOInterface
{
int code;
String title;
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}

public int compareTo(CourseDTOInterface other)
{
return this.code-other.getCode();
}

public boolean equals(Object other)
{
if(!(other instanceof CourseDTOInterface))return false;
CourseDTOInterface courseDTO;
courseDTO=(CourseDTOInterface)other;
return this.code==courseDTO.getCode();
}
public int hashCode()
{
return this.code;
}
}