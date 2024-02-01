import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.exceptions.*;

public class StudentGetCountByCourseTestCase
{
public static void main(String gg[])
{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
try{
System.out.println("Total students : "+studentDAO.getCountByCourse(Integer.parseInt(gg[0])));
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}