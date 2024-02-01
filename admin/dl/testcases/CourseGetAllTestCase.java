import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import java.util.*;

public class CourseGetAllTestCase
{
public static void main(String gg[])
{
Set<CourseDTOInterface> courses;
CourseDAOInterface courseDAO=new CourseDAO();
try{
courses=courseDAO.getAll();

courses.forEach((courseDTO)->{
System.out.println("Course name : "+courseDTO.getTitle());
System.out.println("Course code : "+courseDTO.getCode());
});
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}

}
}