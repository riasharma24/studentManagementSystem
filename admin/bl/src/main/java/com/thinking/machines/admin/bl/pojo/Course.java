package com.thinking.machines.admin.bl.pojo;
import com.thinking.machines.admin.bl.interfaces.pojo.*;

public class Course implements CourseInterface
{
private int code;
private String title;

public Course()
{
this.code=0;
this.title="";
}

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

public int compareTo(CourseInterface other)
{
return this.code-other.getCode();
}

public boolean equals(Object other)
{
if(!(other instanceof CourseInterface))return false;
CourseInterface course=(CourseInterface)other;
return course.getCode()==this.code;
}

public int hashCode()
{
return code;
}

}