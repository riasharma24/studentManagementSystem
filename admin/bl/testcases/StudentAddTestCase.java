import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.text.*;
import java.util.*;

public class StudentAddTestCase
{
public static void main(String gg[])
{
StudentInterface student=new Student();
try{
student.setName(gg[0]);
student.setGender(gg[1].charAt(0));
try{
student.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(gg[2]));
}catch(ParseException pe)
{
System.out.println(pe.getMessage());
}
student.setIsIndian(Boolean.parseBoolean(gg[3]));
student.setCourseCode(Integer.parseInt(gg[4]));
student.setAadharCardNumber(gg[5]);
student.setEnrollmentNumber(gg[6]);
StudentManagerInterface studentManager;
studentManager=StudentManager.getStudentManager();
studentManager.addStudent(student);
System.out.println("Student added with student id :"+student.getStudentId());
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
else
{
List<String> l1=blException.getProperties();
for(String property : l1)
{
System.out.println(blException.getPropertyException(property));
}
}
}
}
}