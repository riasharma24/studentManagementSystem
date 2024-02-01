import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import java.text.*;

public class StudentAddTestCase
{
public static void main(String gg[])
{
StudentDTOInterface studentDTO;
studentDTO=new StudentDTO();
studentDTO.setName(gg[0]);
studentDTO.setCourseCode(Integer.parseInt(gg[1]));
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try{
studentDTO.setDateOfBirth(simpleDateFormat.parse(gg[2]));
}catch(ParseException pe)
{
System.out.println("Unable to parse date.");
}
studentDTO.setIsIndian(Boolean.parseBoolean(gg[3]));
studentDTO.setAadharCardNumber(gg[4]);
studentDTO.setEnrollmentNumber(gg[5]);
studentDTO.setGender(gg[6].charAt(0));

try{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
studentDAO.add(studentDTO);
System.out.println("Student added with ID : "+studentDTO.getStudentId());
}catch(DAOException daoException)
{
System.out.println(daoException);
}

}
}