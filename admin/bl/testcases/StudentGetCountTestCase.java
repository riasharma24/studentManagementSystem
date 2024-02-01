import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;

public class StudentGetCountTestCase
{
public static void main(String gg[])
{
try{
StudentManagerInterface studentManager;
studentManager=StudentManager.getStudentManager();
System.out.println("Count : "+studentManager.getCount());
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