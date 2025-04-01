/*****************************************************************************
 *                              Privilege.java                               *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22



/**
 * Various privileges and roles that the employees have.
 */
// line 38 "model.ump"
// line 106 "model.ump"
public class Privilege
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Privilege Attributes
  private String privilege;

  //Privilege Associations
  private Employee employee;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Privilege(String aPrivilege, Employee aEmployee)
  {
    privilege = aPrivilege;
    boolean didAddEmployee = setEmployee(aEmployee);
    if (!didAddEmployee)
    {
      throw new RuntimeException("Unable to create privilege due to employee. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setPrivilege(String aPrivilege)
  {
    boolean wasSet = false;
    privilege = aPrivilege;
    wasSet = true;
    return wasSet;
  }

  public String getPrivilege()
  {
    return privilege;
  }
  /* Code from template association_GetOne */
  public Employee getEmployee()
  {
    return employee;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setEmployee(Employee aEmployee)
  {
    boolean wasSet = false;
    //Must provide employee to privilege
    if (aEmployee == null)
    {
      return wasSet;
    }

    if (employee != null && employee.numberOfPrivileges() <= Employee.minimumNumberOfPrivileges())
    {
      return wasSet;
    }

    Employee existingEmployee = employee;
    employee = aEmployee;
    if (existingEmployee != null && !existingEmployee.equals(aEmployee))
    {
      boolean didRemove = existingEmployee.removePrivilege(this);
      if (!didRemove)
      {
        employee = existingEmployee;
        return wasSet;
      }
    }
    employee.addPrivilege(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Employee placeholderEmployee = employee;
    this.employee = null;
    if(placeholderEmployee != null)
    {
      placeholderEmployee.removePrivilege(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "privilege" + ":" + getPrivilege()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "employee = "+(getEmployee()!=null?Integer.toHexString(System.identityHashCode(getEmployee())):"null");
  }
}