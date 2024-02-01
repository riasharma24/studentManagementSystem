import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;

public class CourseAddTestCase
{
public static void main(String gg[])
{
String title=gg[0];
CourseDTOInterface courseDTO=new CourseDTO();
courseDTO.setTitle(title);
CourseDAOInterface courseDAO;
courseDAO=new CourseDAO();
try{
courseDAO.add(courseDTO);
System.out.println("Course added with title : "+courseDTO.getTitle()+", and code : "+courseDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}

}