package com.thinking.machines.admin.bl.interfaces.pojo;

public interface CourseInterface extends java.io.Serializable,Comparable<CourseInterface>
{
public int getCode();
public void setCode(int code);
public String getTitle();
public void setTitle(String title);
}