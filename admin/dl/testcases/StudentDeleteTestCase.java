import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import java.text.*;

public class StudentDeleteTestCase
{
public static void main(String gg[])
{
try{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
studentDAO.delete(gg[0]);
System.out.println("Student with ID : "+gg[0]+" deleted.");
}catch(DAOException daoException)
{
System.out.println(daoException);
}

}
}