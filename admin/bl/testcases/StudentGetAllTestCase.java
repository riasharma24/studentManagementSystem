import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;
import java.text.*;

public class StudentGetAllTestCase
{
public static void main(String gg[])
{
Set<StudentInterface> students;
try{
StudentManagerInterface studentManager;
studentManager=StudentManager.getStudentManager();
students=studentManager.getAll();
for(StudentInterface student : students)
{
System.out.println("Name : "+student.getName());
System.out.println("Enrollment number : "+student.getEnrollmentNumber());
System.out.println("Aadhar card number : "+student.getAadharCardNumber());
System.out.println("Student id : "+student.getStudentId());
System.out.println("Date of birth : "+new SimpleDateFormat("dd/MM/yyyy").format(student.getDateOfBirth()));
System.out.println("Gender : "+student.getGender());
System.out.println("Is indian : "+student.getIsIndian());
System.out.println("*********************************************");
}
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
else
{
List<String> properties=blException.getProperties();
for(String property : properties)
{
System.out.println(blException.getPropertyException(property));
}
}
}
}
}