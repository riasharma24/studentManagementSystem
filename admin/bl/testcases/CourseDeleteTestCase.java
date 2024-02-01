import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.exceptions.*;

public class CourseDeleteTestCase
{
public static void main(String gg[])
{
CourseManagerInterface courseManager;
try{
courseManager=CourseManager.getCourseManager();
courseManager.deleteCourse(Integer.parseInt(gg[0]));
System.out.println("Course deleted");
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}
