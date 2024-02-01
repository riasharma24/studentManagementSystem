import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.exceptions.*;
import java.text.*;
import java.util.*;

public class StudentGetByCourseTestCase
{
public static void main(String gg[])
{
Set<StudentDTOInterface> students=null;
try{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
students=studentDAO.getByCourse(Integer.parseInt(gg[0]));
for(StudentDTOInterface studentDTO:students)
{
System.out.println("Name : "+studentDTO.getName());
System.out.println("Student id : "+studentDTO.getStudentId());
System.out.println("Enrollment number : "+studentDTO.getEnrollmentNumber());
System.out.println("Aadhar card number : "+studentDTO.getAadharCardNumber());
System.out.println("Course code : "+studentDTO.getCourseCode());
System.out.println("Gender : "+studentDTO.getGender());
System.out.println("Is indian : "+studentDTO.getIsIndian());
System.out.println("Date of birth : "+new SimpleDateFormat("dd/MM/yyyy").format(studentDTO.getDateOfBirth()));
System.out.println("***********************************");
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}