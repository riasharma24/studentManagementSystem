import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.exceptions.*;

public class StudentEnrollmentNumberExistsTestCase
{
public static void main(String gg[])
{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
if(studentDAO.enrollmentNumberExists(gg[0]))System.out.println("Enrollment number : "+gg[0]+" exists.");
else System.out.println("Enrollment number : "+gg[0]+" does not exist.");
}
}