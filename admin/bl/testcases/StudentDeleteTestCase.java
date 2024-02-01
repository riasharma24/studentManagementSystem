import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;
import java.text.*;

public class StudentDeleteTestCase
{
public static void main(String gg[])
{
try{
StudentManagerInterface studentManager;
studentManager=StudentManager.getStudentManager();
studentManager.deleteStudent(gg[0]);
System.out.println("Student deleted");
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