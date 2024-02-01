import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;

public class CourseDeleteTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
String title=gg[1];
try{
CourseDTOInterface courseDTO=new CourseDTO();
courseDTO.setCode(code);
courseDTO.setTitle(title);

CourseDAOInterface courseDAO=new CourseDAO();
courseDAO.delete(courseDTO);
System.out.println("Course updated.");

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}