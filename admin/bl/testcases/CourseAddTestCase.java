import com.thinking.machines.admin.bl.exceptions.*;
import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import java.util.*;

public class CourseAddTestCase
{
public static void main(String gg[])
{
CourseInterface course;
course=new Course();
course.setCode(0);
course.setTitle(gg[0]);
try{
CourseManagerInterface courseManager;
courseManager=CourseManager.getCourseManager();
courseManager.addCourse(course);
System.out.println("Course added with code : "+course.getCode());
}catch(BLException blException)
{
if(blException.hasGenericException())
{
System.out.println(blException.getGenericException());
}
if(blException.hasPropertyException())
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