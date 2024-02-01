package com.thinking.machines.admin.dl.interfaces.dao;
import com.thinking.machines.admin.dl.exceptions.*;
import com.thinking.machines.admin.dl.dto.*;
import com.thinking.machines.admin.dl.interfaces.dto.*;
import java.util.*;

public interface CourseDAOInterface
{
public void add(CourseDTOInterface courseDTO) throws DAOException;
public void update(CourseDTOInterface courseDTO) throws DAOException;
public void delete(CourseDTOInterface courseDTO) throws DAOException;
public CourseDTOInterface getByCode(int code) throws DAOException;
public CourseDTOInterface getByTitle(String title) throws DAOException;
public Set<CourseDTOInterface> getAll() throws DAOException;
public boolean codeExists(int code);
public boolean titleExists(String name);
public int getCount();
}