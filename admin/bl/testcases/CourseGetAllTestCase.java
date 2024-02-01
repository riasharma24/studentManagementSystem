import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;
import java.util.*;

public class CourseGetAllTestCase
{
public static void main(String gg[])
{
CourseManagerInterface courseManager;
try{
courseManager=CourseManager.getCourseManager();
Set<CourseInterface> courses=courseManager.getAll();
for(CourseInterface course : courses)
{
System.out.println("Course code : "+course.getCode());
System.out.println("Course title : "+course.getTitle());
System.out.println("***********************************************");
}
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}