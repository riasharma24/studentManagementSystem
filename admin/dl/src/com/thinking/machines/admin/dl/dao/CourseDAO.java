package com.thinking.machines.admin.dl.dao;
import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import java.io.*;
import java.util.*;

public class CourseDAO implements CourseDAOInterface
{
private static String FILE_NAME="courses.dat";

public void add(CourseDTOInterface courseDTO) throws DAOException
{
if(courseDTO==null)throw new DAOException("Course cannot be null.");
String title=courseDTO.getTitle();
title=title.trim();
if(title.length()==0)throw new DAOException("Course cannot be null.");
int recordCount=0;
int lastGeneratedCode=0;
String recordCountString="";
String lastGeneratedCodeString="";

try{

File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
recordCountString="0";
lastGeneratedCodeString="0";
while(recordCountString.length()<10)recordCountString+=" ";
while(lastGeneratedCodeString.length()<10)lastGeneratedCodeString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
}
else
{
recordCountString=randomAccessFile.readLine();
lastGeneratedCodeString=randomAccessFile.readLine();
recordCount=Integer.parseInt(recordCountString.trim());
lastGeneratedCode=Integer.parseInt(lastGeneratedCodeString.trim());
}

int code=lastGeneratedCode+1;
courseDTO.setCode(code);
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException("Course already exists with code "+fCode);
}
}

randomAccessFile.writeBytes(String.valueOf(code));
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(title);
randomAccessFile.writeBytes("\n");

randomAccessFile.seek(0);
recordCount+=1;
lastGeneratedCode=code;
recordCountString=String.valueOf(recordCount);
lastGeneratedCodeString=String.valueOf(lastGeneratedCode);
while(recordCountString.length()<10)recordCountString+=" ";
while(lastGeneratedCodeString.length()<10)lastGeneratedCodeString+=" ";
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public void update(CourseDTOInterface courseDTO) throws DAOException
{
if(courseDTO==null)throw new DAOException("Course cannot be null.");
int code=courseDTO.getCode();
if(code<=0)throw new DAOException("Invalid code.");
String title=courseDTO.getTitle();
if(title==null)throw new DAOException("Invalid title.");
title=title.trim();
if(title.length()==0)throw new DAOException("Invalid title.");
try{
File file=new File(FILE_NAME);
if(!file.exists())throw new DAOException("Invalid course.");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(Integer.parseInt(randomAccessFile.readLine().trim())<=0)
{
randomAccessFile.close();
throw new DAOException("Invalid course.");
}
randomAccessFile.readLine();
int fCode;
String fTitle;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
if(fCode==code)found=true;
randomAccessFile.readLine();
}
if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid course.");
}
randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode!=code && fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
throw new DAOException("Title exists against another course.");
}
}
randomAccessFile.seek(0);
File tmpFile=new File("tmp.dat");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
if(tmpFile.exists())tmpFile.delete();
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
else
{
tmpRandomAccessFile.writeBytes(String.valueOf(code));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(title);
tmpRandomAccessFile.writeBytes("\n");
}
}

randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}

randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
tmpFile.delete();

}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}


}

public void delete(CourseDTOInterface courseDTO) throws DAOException
{
if(courseDTO==null)throw new DAOException("Invalid course.");
int code=courseDTO.getCode();
String title=courseDTO.getTitle();
if(code<=0)throw new DAOException("Invalid code.");
if(title==null)throw new DAOException("Invalid title.");
title=title.trim();
if(title.length()==0)throw new DAOException("Invalid title.");
try{
File file=new File(FILE_NAME);
if(!file.exists())throw new DAOException("Invalid course.");
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)
{
randomAccessFile.close();
throw new DAOException("Invalid course.");
}
randomAccessFile.readLine();
boolean found=false;
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
if(fCode==code)
{
found=true;
break;
}
randomAccessFile.readLine();
}

if(found==false)
{
randomAccessFile.close();
throw new DAOException("Invalid course.");
}
recordCount--;
randomAccessFile.seek(0);
File tmpFile=new File("tmp.dat");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
String recordCountString=String.valueOf(recordCount);
while(recordCountString.length()<10)recordCountString+=" ";

randomAccessFile.readLine();
tmpRandomAccessFile.writeBytes(recordCountString);
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine());
tmpRandomAccessFile.writeBytes("\n");

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode!=code)
{
tmpRandomAccessFile.writeBytes(String.valueOf(fCode));
tmpRandomAccessFile.writeBytes("\n");
tmpRandomAccessFile.writeBytes(fTitle);
tmpRandomAccessFile.writeBytes("\n");
}
}

tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine());
randomAccessFile.writeBytes("\n");
}

randomAccessFile.setLength(tmpRandomAccessFile.length());
randomAccessFile.close();
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
tmpFile.delete();

}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}

public CourseDTOInterface getByCode(int code) throws DAOException
{
if(code<=0)throw new DAOException("Invalid code.");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid code.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
CourseDTOInterface courseDTO;
courseDTO=new CourseDTO();
courseDTO.setCode(fCode);
courseDTO.setTitle(fTitle);
randomAccessFile.close();
return courseDTO;
}
}

randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
catch(NumberFormatException nfe)
{
throw new DAOException(nfe.getMessage());
}
throw new DAOException("Code not found.");

}

public CourseDTOInterface getByTitle(String title) throws DAOException
{
if(title==null)throw new DAOException("Invalid title.");
title=title.trim();
if(title.length()==0)throw new DAOException("Invalid title.");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Invalid title.");
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
CourseDTOInterface courseDTO;
courseDTO=new CourseDTO();
courseDTO.setCode(fCode);
courseDTO.setTitle(fTitle);
randomAccessFile.close();
return courseDTO;
}
}
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
throw new DAOException("Title not found.");
}

public Set<CourseDTOInterface> getAll() throws DAOException
{
Set<CourseDTOInterface> courses=new TreeSet<>();
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)return courses;

int recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
if(recordCount==0)return courses;
randomAccessFile.readLine();
CourseDTOInterface courseDTO;
int code;
String title;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
code=Integer.parseInt(randomAccessFile.readLine());
title=randomAccessFile.readLine();
courseDTO=new CourseDTO();
courseDTO.setCode(code);
courseDTO.setTitle(title);
courses.add(courseDTO);
}
randomAccessFile.close();
return courses;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean codeExists(int code)
{
if(code<=0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fCode==code)
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
catch(NumberFormatException nfe)
{
System.out.println(nfe.getMessage());
}
return false;

}

public boolean titleExists(String title)
{
if(title==null)return false;
title=title.trim();
if(title.length()==0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
int fCode;
String fTitle;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fCode=Integer.parseInt(randomAccessFile.readLine());
fTitle=randomAccessFile.readLine();
if(fTitle.equalsIgnoreCase(title))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
return false;
}

public int getCount()
{
int count=0;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)return 0;
count=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return count;
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
return count;
}


}