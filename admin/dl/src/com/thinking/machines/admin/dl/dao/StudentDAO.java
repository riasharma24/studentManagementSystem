package com.thinking.machines.admin.dl.dao;

import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import java.util.*;
import java.io.*;
import java.text.*;

public class StudentDAO implements StudentDAOInterface
{
private String FILE_NAME="student.dat";

public void add(StudentDTOInterface studentDTO) throws DAOException
{
if(studentDTO==null)throw new DAOException("Student cannot be null.");
String studentId;
String name=studentDTO.getName();
name=name.trim();
if(name.length()==0)throw new DAOException("Student name cannot be null.");
int courseCode=studentDTO.getCourseCode();
if(new CourseDAO().codeExists(courseCode)==false)throw new DAOException("Course code does not exist.");
Date dateOfBirth=studentDTO.getDateOfBirth();
if(dateOfBirth==null)throw new DAOException("Invalid date of birth");
char gender=studentDTO.getGender();
if(gender==' ')throw new DAOException("Gender not set to male/female");
boolean isIndian=studentDTO.getIsIndian();
String enrollmentNumber=studentDTO.getEnrollmentNumber();
if(enrollmentNumber==null)throw new DAOException("Enrollment number cannot be null");
enrollmentNumber=enrollmentNumber.trim();
if(enrollmentNumber.length()==0)throw new DAOException("Enrollment number cannot be null");
String aadharCardNumber=studentDTO.getAadharCardNumber();
if(aadharCardNumber==null)throw new DAOException("Aadhar card number cannot be null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)throw new DAOException("Aadhar card number cannot be null");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
int lastGeneratedCode=10000000;
int recordCount=0;
String lastGeneratedCodeString="";
String recordCountString="";
if(randomAccessFile.length()==0)
{
lastGeneratedCodeString=String.format("%-10d",lastGeneratedCode);
recordCountString=String.format("%-10d",recordCount);
randomAccessFile.writeBytes(recordCountString);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(lastGeneratedCodeString);
randomAccessFile.writeBytes("\n");
}
else
{
recordCountString=randomAccessFile.readLine().trim();
lastGeneratedCodeString=randomAccessFile.readLine().trim();
recordCount=Integer.parseInt(recordCountString);
lastGeneratedCode=Integer.parseInt(lastGeneratedCodeString);
}

boolean enrollmentNumberExists,aadharCardNumberExists;
enrollmentNumberExists=false;
aadharCardNumberExists=false;
String fEnrollmentNumber,fAadharCardNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int i=0;i<6;i++)randomAccessFile.readLine();;
fEnrollmentNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fEnrollmentNumber.equalsIgnoreCase(enrollmentNumber))enrollmentNumberExists=true;
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber))aadharCardNumberExists=true;
if(aadharCardNumberExists && enrollmentNumberExists)break;
}
if(aadharCardNumberExists && enrollmentNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number and enrollment number already exists");
}

if(aadharCardNumberExists)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number exists");
}

if(enrollmentNumberExists)
{
randomAccessFile.close();
throw new DAOException("Enrollment number already exists");
}
studentId="A"+String.format("%-10d",lastGeneratedCode+1);
randomAccessFile.writeBytes(studentId+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(String.valueOf(courseCode)+"\n");
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
randomAccessFile.writeBytes(simpleDateFormat.format(dateOfBirth)+"\n");
randomAccessFile.writeBytes(isIndian+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.writeBytes(enrollmentNumber+"\n");
randomAccessFile.writeBytes(aadharCardNumber+"\n");
randomAccessFile.seek(0);
recordCount++;
lastGeneratedCode++;
recordCountString=String.format("%-10d",recordCount);
lastGeneratedCodeString=String.format("%-10d",lastGeneratedCode);
randomAccessFile.writeBytes(recordCountString+"\n");
randomAccessFile.writeBytes(lastGeneratedCodeString+"\n");
randomAccessFile.close();
studentDTO.setStudentId(studentId);
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void update(StudentDTOInterface studentDTO) throws DAOException
{
if(studentDTO==null)throw new DAOException("Student cannot be null");
String studentId=studentDTO.getStudentId();
if(studentId==null)throw new DAOException("Student id cannot be null");
studentId=studentId.trim();
if(studentId.length()==0)throw new DAOException("Student id cannot be of zero length");
String name=studentDTO.getName();
if(name==null)throw new DAOException("Name cannot be null");
name=name.trim();
if(name.length()==0)throw new DAOException("Name cannot be of zero length");
int courseCode=studentDTO.getCourseCode();
if(courseCode<=0)throw new DAOException("Invalid course code");
if(new CourseDAO().codeExists(courseCode)==false)throw new DAOException("Course code does not exist");
Date dateOfBirth=studentDTO.getDateOfBirth();
if(dateOfBirth==null)throw new DAOException("Date of birth cannot be null");
boolean isIndian=studentDTO.getIsIndian();
char gender=studentDTO.getGender();
if(gender==' ')throw new DAOException("Invalid gender");
String enrollmentNumber=studentDTO.getEnrollmentNumber();
if(enrollmentNumber==null)throw new DAOException("Enrollment number cannot be null");
enrollmentNumber=enrollmentNumber.trim();
if(enrollmentNumber.length()==0)throw new DAOException("Enrollment number cannot be of zero length");
String aadharCardNumber=studentDTO.getAadharCardNumber();
if(aadharCardNumber==null)throw new DAOException("Aadhar card number cannot be null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)throw new DAOException("Aadhar card number cannot be of zero length");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
String fAadharCardNumber;
String fEnrollmentNumber;
String fStudentId;

randomAccessFile.readLine();
randomAccessFile.readLine();
boolean studentIdExists=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine().trim();
if(fStudentId.equalsIgnoreCase(studentId))
{
studentIdExists=true;
break;
}
for(int i=0;i<7;i++)randomAccessFile.readLine();
}

if(studentIdExists==false)
{
randomAccessFile.close();
throw new DAOException("Student id does not exist");
}

randomAccessFile.seek(0);
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine().trim();
for(int i=1;i<=5;i++)randomAccessFile.readLine();
fEnrollmentNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(fEnrollmentNumber.equalsIgnoreCase(enrollmentNumber) && (studentId.equalsIgnoreCase(fStudentId)==false))
{
randomAccessFile.close();
throw new DAOException("Enrollment number already exists against student with id : "+fStudentId);
}
if(fAadharCardNumber.equalsIgnoreCase(aadharCardNumber) && (studentId.equalsIgnoreCase(fStudentId)==false))
{
randomAccessFile.close();
throw new DAOException("Aadhar card number already exists against student with id : "+fStudentId);
}
}

File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");

randomAccessFile.seek(0);
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine().trim();
if(studentId.equalsIgnoreCase(fStudentId))
{
tmpRandomAccessFile.writeBytes(studentId+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(String.valueOf(courseCode)+"\n");
tmpRandomAccessFile.writeBytes(new SimpleDateFormat("dd/MM/yyyy").format(dateOfBirth)+"\n");
tmpRandomAccessFile.writeBytes(isIndian+"\n");
tmpRandomAccessFile.writeBytes(gender+"\n");
tmpRandomAccessFile.writeBytes(enrollmentNumber+"\n");
tmpRandomAccessFile.writeBytes(aadharCardNumber+"\n");
for(int i=0;i<7;i++)randomAccessFile.readLine();
}
else
{
tmpRandomAccessFile.writeBytes(fStudentId+"\n");
for(int i=0;i<7;i++)tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public void delete(String studentId) throws DAOException
{
if(studentId==null)throw new DAOException("Student id cannot be null");
studentId=studentId.trim();
if(studentId.length()==0)throw new DAOException("Student id cannot be of zero length");

try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
String fAadharCardNumber;
String fEnrollmentNumber;
String fStudentId;

File tmpFile=new File("tmp.tmp");
if(tmpFile.exists())tmpFile.delete();
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");

randomAccessFile.readLine();
randomAccessFile.readLine();
boolean studentIdExists=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine().trim();
if(fStudentId.equalsIgnoreCase(studentId))
{
studentIdExists=true;
break;
}
for(int i=0;i<7;i++)randomAccessFile.readLine();
}

if(studentIdExists==false)
{
randomAccessFile.close();
tmpRandomAccessFile.close();
throw new DAOException("Student id does not exist");
}

randomAccessFile.seek(0);
String recordCountString=randomAccessFile.readLine();
int recordCount=Integer.parseInt(recordCountString.trim());
recordCount--;
recordCountString=String.format("%-10d",recordCount);
tmpRandomAccessFile.writeBytes(recordCountString+"\n");
tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine().trim();
if(studentId.equalsIgnoreCase(fStudentId))
{
for(int i=0;i<7;i++)randomAccessFile.readLine();
}
else
{
tmpRandomAccessFile.writeBytes(fStudentId+"\n");
for(int i=0;i<7;i++)tmpRandomAccessFile.writeBytes(randomAccessFile.readLine()+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.close();
randomAccessFile.close();

}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}

public Set<StudentDTOInterface> getAll() throws DAOException
{
try{
Set<StudentDTOInterface> students=new HashSet<>();
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return students;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentDTOInterface studentDTO;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
studentDTO=new StudentDTO();
studentDTO.setStudentId(randomAccessFile.readLine().trim());
studentDTO.setName(randomAccessFile.readLine());
studentDTO.setCourseCode(Integer.parseInt(randomAccessFile.readLine()));
try{
studentDTO.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
}
studentDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
studentDTO.setGender(randomAccessFile.readLine().charAt(0));
studentDTO.setEnrollmentNumber(randomAccessFile.readLine());
studentDTO.setAadharCardNumber(randomAccessFile.readLine());
students.add(studentDTO);
}
randomAccessFile.close();
return students;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}


public Set<StudentDTOInterface> getByCourse(int courseCode) throws DAOException
{
if(courseCode<=0)throw new DAOException("Invalid course code");
if(new CourseDAO().codeExists(courseCode)==false)throw new DAOException("Invalid course code");
try{
Set<StudentDTOInterface> students=new HashSet<>();
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return students;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
StudentDTOInterface studentDTO;
String fStudentId;
String fName;
int fCourseCode=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
if(courseCode==fCourseCode)
{
studentDTO=new StudentDTO();
studentDTO.setStudentId(fStudentId);
studentDTO.setName(fName);
studentDTO.setCourseCode(courseCode);
try{
studentDTO.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(randomAccessFile.readLine()));
}catch(ParseException pe)
{
}
studentDTO.setIsIndian(Boolean.parseBoolean(randomAccessFile.readLine()));
studentDTO.setGender(randomAccessFile.readLine().charAt(0));
studentDTO.setEnrollmentNumber(randomAccessFile.readLine());
studentDTO.setAadharCardNumber(randomAccessFile.readLine());
students.add(studentDTO);
}
else
{
for(int i=0;i<5;i++)randomAccessFile.readLine();
}
}
randomAccessFile.close();
return students;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}


}

public StudentDTOInterface getByStudentId(String studentId) throws DAOException
{
if(studentId==null)throw new DAOException("Student id cannot be null");
studentId=studentId.trim();
if(studentId.length()==0)throw new DAOException("Student id cannot be of zero length");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Student id does not exist.");
}

StudentDTOInterface studentDTO=new StudentDTO();
String fName;
String fEnrollmentNumber;
String fAadharCardNumber;
String fStudentId;
int fCourseCode;
char fGender;
Date fDateOfBirth=null;
boolean fIsIndian;

randomAccessFile.readLine();
randomAccessFile.readLine();

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
try{
fDateOfBirth=new SimpleDateFormat("dd/MM/yyyy").parse(randomAccessFile.readLine());
}catch(ParseException pe)
{
}
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine().charAt(0);
fEnrollmentNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
studentId=studentId.trim();
fStudentId=fStudentId.trim();
if(studentId.equalsIgnoreCase(fStudentId))
{
studentDTO.setStudentId(fStudentId);
studentDTO.setName(fName);
studentDTO.setCourseCode(fCourseCode);
studentDTO.setDateOfBirth(fDateOfBirth);
studentDTO.setIsIndian(fIsIndian);
studentDTO.setGender(fGender);
studentDTO.setEnrollmentNumber(fEnrollmentNumber);
studentDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return studentDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid student id");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public StudentDTOInterface getByEnrollmentNumber(String enrollmentNumber) throws DAOException
{
if(enrollmentNumber==null)throw new DAOException("Enrollment number cannot be null");
enrollmentNumber=enrollmentNumber.trim();
if(enrollmentNumber.length()==0)throw new DAOException("Enrollment number cannot be of zero length");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Enrollment number does not exist.");
}

StudentDTOInterface studentDTO=new StudentDTO();
String fName;
String fEnrollmentNumber;
String fAadharCardNumber;
String fStudentId;
int fCourseCode;
char fGender;
Date fDateOfBirth=null;
boolean fIsIndian;

randomAccessFile.readLine();
randomAccessFile.readLine();

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
try{
fDateOfBirth=new SimpleDateFormat("dd/MM/yyyy").parse(randomAccessFile.readLine());
}catch(ParseException pe)
{
}
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine().charAt(0);
fEnrollmentNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(enrollmentNumber.equalsIgnoreCase(fEnrollmentNumber))
{
studentDTO.setStudentId(fStudentId);
studentDTO.setName(fName);
studentDTO.setCourseCode(fCourseCode);
studentDTO.setDateOfBirth(fDateOfBirth);
studentDTO.setIsIndian(fIsIndian);
studentDTO.setGender(fGender);
studentDTO.setEnrollmentNumber(fEnrollmentNumber);
studentDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return studentDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid enrollment number");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public StudentDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException
{
if(aadharCardNumber==null)throw new DAOException("Aadhar card number cannot be null");
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)throw new DAOException("Aadhar card number cannot be of zero length");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DAOException("Aadhar card number does not exist.");
}

StudentDTOInterface studentDTO=new StudentDTO();
String fName;
String fEnrollmentNumber;
String fAadharCardNumber;
String fStudentId;
int fCourseCode;
char fGender;
Date fDateOfBirth=null;
boolean fIsIndian;

randomAccessFile.readLine();
randomAccessFile.readLine();

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourseCode=Integer.parseInt(randomAccessFile.readLine());
try{
fDateOfBirth=new SimpleDateFormat("dd/MM/yyyy").parse(randomAccessFile.readLine());
}catch(ParseException pe)
{
}
fIsIndian=Boolean.parseBoolean(randomAccessFile.readLine());
fGender=randomAccessFile.readLine().charAt(0);
fEnrollmentNumber=randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(aadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
studentDTO.setStudentId(fStudentId);
studentDTO.setName(fName);
studentDTO.setCourseCode(fCourseCode);
studentDTO.setDateOfBirth(fDateOfBirth);
studentDTO.setIsIndian(fIsIndian);
studentDTO.setGender(fGender);
studentDTO.setEnrollmentNumber(fEnrollmentNumber);
studentDTO.setAadharCardNumber(fAadharCardNumber);
randomAccessFile.close();
return studentDTO;
}
}
randomAccessFile.close();
throw new DAOException("Invalid aadhar card number");
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}

}

public boolean studentIdExists(String studentId)
{
if(studentId==null)return false;
studentId=studentId.trim();
if(studentId.length()==0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}

String fName;
String fEnrollmentNumber;
String fAadharCardNumber;
String fStudentId;
int fCourseCode;
char fGender;
Date fDateOfBirth=null;
boolean fIsIndian;

randomAccessFile.readLine();
randomAccessFile.readLine();

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fStudentId=randomAccessFile.readLine();
for(int i=0;i<7;i++)randomAccessFile.readLine();
studentId=studentId.trim();
fStudentId=fStudentId.trim();
if(studentId.equalsIgnoreCase(fStudentId))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
}
return false;
}

public boolean enrollmentNumberExists(String enrollmentNumber)
{
if(enrollmentNumber==null)return false;
enrollmentNumber=enrollmentNumber.trim();
if(enrollmentNumber.length()==0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}

String fEnrollmentNumber;

randomAccessFile.readLine();
randomAccessFile.readLine();

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int i=0;i<6;i++)randomAccessFile.readLine();
fEnrollmentNumber=randomAccessFile.readLine();
randomAccessFile.readLine();
if(enrollmentNumber.equalsIgnoreCase(fEnrollmentNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
}catch(IOException ioException)
{
}
return false;
}

public boolean aadharCardNumberExists(String aadharCardNumber)
{
if(aadharCardNumber==null)return false;
aadharCardNumber=aadharCardNumber.trim();
if(aadharCardNumber.length()==0)return false;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}

String fAadharCardNumber;

randomAccessFile.readLine();
randomAccessFile.readLine();

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
for(int i=0;i<7;i++)randomAccessFile.readLine();
fAadharCardNumber=randomAccessFile.readLine();
if(aadharCardNumber.equalsIgnoreCase(fAadharCardNumber))
{
randomAccessFile.close();
return true;
}
}
randomAccessFile.close();
return false;
}catch(IOException ioException)
{
}
return false;
}

public int getCount()
{
int recordCount=0;
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
recordCount=Integer.parseInt(randomAccessFile.readLine().trim());
randomAccessFile.close();
return recordCount;
}catch(IOException ioException)
{
}
return recordCount;
}

public int getCountByCourse(int courseCode) throws DAOException
{
if(courseCode<=0)throw new DAOException("Invalid course code");
if(new CourseDAO().codeExists(courseCode)==false)throw new DAOException("Course code does not exist");
try{
File file=new File(FILE_NAME);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return 0;
}
int count=0;
int fCode;
randomAccessFile.readLine();
randomAccessFile.readLine();
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
fCode=Integer.parseInt(randomAccessFile.readLine());
if(courseCode==fCode)count++;
for(int i=0;i<5;i++)randomAccessFile.readLine();
}
return count;
}catch(IOException ioException)
{
throw new DAOException(ioException.getMessage());
}
}

public boolean isCourseAlloted(int courseCode) throws DAOException
{
if(courseCode<=0)throw new DAOException("Invalid course code");
if(new CourseDAO().codeExists(courseCode)==false)throw new DAOException("Invalid course code");
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

while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
randomAccessFile.readLine();
randomAccessFile.readLine();
if(Integer.parseInt(randomAccessFile.readLine().trim())==courseCode)
{
randomAccessFile.close();
return true;
}
for(int i=0;i<5;i++)randomAccessFile.readLine();
}
return false;
}catch(IOException ioException)
{
}
return false;
}




}