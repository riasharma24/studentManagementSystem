import com.thinking.machines.admin.bl.interfaces.pojo.*;
import com.thinking.machines.admin.bl.interfaces.managers.*;
import com.thinking.machines.admin.bl.pojo.*;
import com.thinking.machines.admin.bl.managers.*;
import com.thinking.machines.admin.bl.exceptions.*;

public class CourseGetByCodeTestCase
{
public static void main(String gg[])
{
CourseInterface course;
CourseManagerInterface courseManager;
try{
courseManager=CourseManager.getCourseManager();
course=courseManager.getCourseByCode(Integer.parseInt(gg[0]));
System.out.println("Title : "+course.getTitle());
System.out.println("Code : "+course.getCode());
}catch(BLException blException)
{
System.out.println(blException.getMessage());
}
}
}