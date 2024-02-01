import com.thinking.machines.admin.bl.exceptions.*;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;

public class CourseUpdateTestCase
{
public static void main(String gg[])
{
CourseInterface course=new Course();
course.setCode(3);
course.setTitle("Sorcery");
CourseManagerInterface courseManager;
try{
courseManager=CourseManager.getCourseManager();
courseManager.updateCourse(course);
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}