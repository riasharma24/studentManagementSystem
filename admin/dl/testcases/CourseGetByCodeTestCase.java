import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;

public class CourseGetByCodeTestCase
{
public static void main(String gg[])
{
int code=Integer.parseInt(gg[0]);
CourseDAOInterface courseDAO=new CourseDAO();
try{
CourseDTOInterface courseDTO=courseDAO.getByCode(code);
System.out.println("Title : "+courseDTO.getTitle());
System.out.println("Code : "+courseDTO.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}

}