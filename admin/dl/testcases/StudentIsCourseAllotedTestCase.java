import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.exceptions.*;

public class StudentIsCourseAllotedTestCase
{
public static void main(String gg[])
{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
try{
if(studentDAO.isCourseAlloted(Integer.parseInt(gg[0])))System.out.println("Yes, course alloted");
else System.out.println("No, course not alloted");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}