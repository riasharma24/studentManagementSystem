package com.thinking.machines.admin.dl.interfaces.dto;

public interface CourseDTOInterface extends java.io.Serializable,Comparable<CourseDTOInterface>
{
public void setCode(int code);
public int getCode();
public void setTitle(String title);
public String getTitle();
}