import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;

public class StudentEnrollmentNumberExistsTestCase
{
public static void main(String gg[])
{
try{
StudentManagerInterface studentManager;
studentManager=StudentManager.getStudentManager();
if(studentManager.enrollmentNumberExists(gg[0]))System.out.println("Enrollment number : "+gg[0]+" exists.");
else System.out.println("Enrollment number : "+gg[0]+" does not exist.");
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