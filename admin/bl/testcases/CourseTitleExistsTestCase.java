import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;

public class CourseTitleExistsTestCase
{
public static void main(String gg[])
{
CourseInterface course;
CourseManagerInterface courseManager;
try{
courseManager=CourseManager.getCourseManager();
if(courseManager.courseTitleExists(gg[0]))System.out.println("Title exists.");
else System.out.println("Title does not exist.");
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}