import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;

public class CourseGetByTitleTestCase
{
public static void main(String gg[])
{
String title=gg[0];
try{
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
CourseDTOInterface courseDTO=courseDAO.getByTitle(title);
System.out.println("Title : "+courseDTO.getTitle());
System.out.println("Code : "+courseDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}

}