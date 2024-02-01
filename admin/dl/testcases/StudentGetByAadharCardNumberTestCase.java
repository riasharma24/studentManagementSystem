import java.text.*;
import java.util.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.exceptions.*;

public class StudentGetByAadharCardNumberTestCase
{
public static void main(String gg[])
{
StudentDTOInterface studentDTO=null;
try{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
studentDTO=studentDAO.getByAadharCardNumber(gg[0]);
System.out.println("Name : "+studentDTO.getName());
System.out.println("Student id : "+studentDTO.getStudentId());
System.out.println("Course code : "+studentDTO.getCourseCode());
System.out.println("Gender : "+studentDTO.getGender());
System.out.println("Date : "+(new SimpleDateFormat("dd/MM/yyyy").format(studentDTO.getDateOfBirth())));
System.out.println("Is indian : "+studentDTO.getIsIndian());
System.out.println("Enrollment number : "+studentDTO.getEnrollmentNumber());
System.out.println("Aadhar card number : "+studentDTO.getAadharCardNumber());

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}