package com.thinking.machines.admin.bl.exceptions;
import java.util.*;

public class BLException extends Exception
{
private String genericException;
private Map<String,String> propertyExceptions;

public BLException()
{
genericException=null;
propertyExceptions=new HashMap<>();
}

public BLException(String genericException)
{
this.genericException=genericException;
}

public boolean hasGenericException()
{
if(this.genericException!=null)return true;
else return false;
}

public boolean hasPropertyException()
{
return this.propertyExceptions.size()>0;
}

public int getCount()
{
if(this.genericException!=null)return this.propertyExceptions.size()+1;
else return this.propertyExceptions.size();
}

public void setGenericException(String genericException)
{
this.genericException=genericException;
}

public String getGenericException()
{
return this.genericException;
}

public void addPropertyException(String property,String message)
{
this.propertyExceptions.put(property,message);
}

public List<String> getProperties()
{
List<String> l1=new ArrayList<>();
this.propertyExceptions.forEach((k,v)->{
l1.add(k);
});
return l1;
}

public void removeException(String property)
{
this.propertyExceptions.remove(property);
}

public String getPropertyException(String property)
{
return this.propertyExceptions.get(property);
}

public String getMessage()
{
if(this.genericException==null) return "";
else return this.genericException;
}

}