import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import com.thinking.machines.admin.dl.interfaces.dao.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import java.text.*;

public class StudentUpdateTestCase
{
public static void main(String gg[])
{
StudentDTOInterface studentDTO;
studentDTO=new StudentDTO();
studentDTO.setStudentId(gg[0]);
studentDTO.setName(gg[1]);
studentDTO.setCourseCode(Integer.parseInt(gg[2]));
SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
try{
studentDTO.setDateOfBirth(simpleDateFormat.parse(gg[3]));
}catch(ParseException pe)
{
System.out.println("Unable to parse date.");
}
studentDTO.setIsIndian(Boolean.parseBoolean(gg[4]));
studentDTO.setAadharCardNumber(gg[5]);
studentDTO.setEnrollmentNumber(gg[6]);
studentDTO.setGender(gg[7].charAt(0));

try{
StudentDAOInterface studentDAO;
studentDAO=new StudentDAO();
studentDAO.update(studentDTO);
System.out.println("Student updated with ID : "+studentDTO.getStudentId());
}catch(DAOException daoException)
{
System.out.println(daoException);
}

}
}