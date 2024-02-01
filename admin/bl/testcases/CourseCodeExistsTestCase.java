import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;

public class CourseCodeExistsTestCase
{
public static void main(String gg[])
{
CourseInterface course;
CourseManagerInterface courseManager;
try{
courseManager=CourseManager.getCourseManager();
if(courseManager.courseCodeExists(Integer.parseInt(gg[0])))System.out.println("Code exists.");
else System.out.println("Code does not exist.");
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}