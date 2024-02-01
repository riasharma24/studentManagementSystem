import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;
import java.text.*;

public class StudentUpdateTestCase
{
public static void main(String gg[])
{
StudentInterface student=new Student();
student.setStudentId(gg[0]);
student.setName(gg[1]);
student.setGender(gg[2].charAt(0));
student.setEnrollmentNumber(gg[3]);
student.setAadharCardNumber(gg[4]);
try{
student.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse(gg[5]));
}catch(ParseException pe)
{
System.out.println(pe.getMessage());
}
student.setIsIndian(Boolean.parseBoolean(gg[6]));
student.setCourseCode(Integer.parseInt(gg[7]));
try{
StudentManagerInterface studentManager;
studentManager=StudentManager.getStudentManager();
studentManager.updateStudent(student);
System.out.println("Student updated");
}catch(BLException blException)
{
if(blException.hasPropertyException())
{
List<String> properties=blException.getProperties();
for(String property : properties)
{
System.out.println(blException.getPropertyException(property));
}
}
else
{
System.out.println(blException.getGenericException());
}
}
}
}