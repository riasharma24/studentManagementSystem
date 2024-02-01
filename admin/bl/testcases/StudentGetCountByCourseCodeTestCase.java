import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;

public class StudentGetCountByCourseCodeTestCase
{
public static void main(String gg[])
{
try{
StudentManagerInterface studentManager;
studentManager=StudentManager.getStudentManager();
System.out.println("Count : "+studentManager.getCountByCourseCode(Integer.parseInt(gg[0])));
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