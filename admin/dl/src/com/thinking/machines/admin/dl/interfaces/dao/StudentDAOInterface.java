package com.thinking.machines.admin.dl.interfaces.dao;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.dao.*;
import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import java.util.*;

public interface StudentDAOInterface
{
public void add(StudentDTOInterface studentDTO) throws DAOException;
public void update(StudentDTOInterface studentDTO) throws DAOException;
public void delete(String studentId) throws DAOException;
public Set<StudentDTOInterface> getAll() throws DAOException;
public Set<StudentDTOInterface> getByCourse(int courseCode) throws DAOException;
public StudentDTOInterface getByStudentId(String studentId) throws DAOException;
public StudentDTOInterface getByEnrollmentNumber(String enrollmentNumber) throws DAOException;
public StudentDTOInterface getByAadharCardNumber(String aadharCardNumber) throws DAOException;
public boolean studentIdExists(String studentId);
public boolean enrollmentNumberExists(String enrollmentNumber);
public boolean aadharCardNumberExists(String aadharCardNumber);
public int getCount();
public int getCountByCourse(int courseCode) throws DAOException;
public boolean isCourseAlloted(int courseCode) throws DAOException;
}