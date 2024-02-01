import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;

public class StudentGetCountTestCase
{
public static void main(String gg[])
{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
System.out.println("Total students : "+studentDAO.getCount());
}
}