import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;

public class CourseTitleExistsTestCase
{
public static void main(String gg[])
{
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
if(courseDAO.titleExists(gg[0]))System.out.println("Title : "+gg[0] +" exists.");
else System.out.println("Title : "+gg[0]+" does not exist.");
}

}