import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;

public class CourseGetCountTestCase
{
public static void main(String gg[])
{
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
System.out.println("Number of courses : "+courseDAO.getCount());
}
}