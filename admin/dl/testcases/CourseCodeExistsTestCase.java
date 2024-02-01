import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;

public class CourseCodeExistsTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
if(courseDAO.codeExists(code))System.out.println("Code : "+code +" exists.");
else System.out.println("Code : "+code+" does not exist.");
}

}