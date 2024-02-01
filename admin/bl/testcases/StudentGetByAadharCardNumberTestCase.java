import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.text.*;

public class StudentGetByAadharCardNumberTestCase
{
public static void main(String gg[])
{
StudentManagerInterface studentManager;
try{
studentManager=StudentManager.getStudentManager();
StudentInterface student;
student=studentManager.getByAadharCardNumber(gg[0]);
System.out.println("Name : "+student.getName());
System.out.println("Enrollment number : "+student.getEnrollmentNumber());
System.out.println("Aadhar card number : "+student.getAadharCardNumber());
System.out.println("Student id : "+student.getStudentId());
System.out.println("Date of birth : "+new SimpleDateFormat("dd/MM/yyyy").format(student.getDateOfBirth()));
System.out.println("Is indian : "+student.getIsIndian());
System.out.println("Gender : "+student.getGender());
System.out.println("Course code : "+student.getCourseCode());
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}