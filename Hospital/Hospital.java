/*****************************************************************************
 *                              Hospital.java                                *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


import java.util.*;

/**
 * Hospital system - sample UML class diagram in Umple
 */
// line 4 "model.ump"
// line 72 "model.ump"
public class Hospital
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Hospital Associations
  private List<Employee> employees;
  private List<Ward> wards;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Hospital()
  {
    employees = new ArrayList<Employee>();
    wards = new ArrayList<Ward>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }
  /* Code from template association_GetMany */
  public Ward getWard(int index)
  {
    Ward aWard = wards.get(index);
    return aWard;
  }

  public List<Ward> getWards()
  {
    List<Ward> newWards = Collections.unmodifiableList(wards);
    return newWards;
  }

  public int numberOfWards()
  {
    int number = wards.size();
    return number;
  }

  public boolean hasWards()
  {
    boolean has = wards.size() > 0;
    return has;
  }

  public int indexOfWard(Ward aWard)
  {
    int index = wards.indexOf(aWard);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfEmployees()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Employee addEmployee(String aName, int aId, int aSalary, Ward... allWards)
  {
    return new Employee(aName, aId, aSalary, this, allWards);
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    Hospital existingHospital = aEmployee.getHospital();
    boolean isNewHospital = existingHospital != null && !this.equals(existingHospital);
    if (isNewHospital)
    {
      aEmployee.setHospital(this);
    }
    else
    {
      employees.add(aEmployee);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    //Unable to remove aEmployee, as it must always have a hospital
    if (!this.equals(aEmployee.getHospital()))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfWardsValid()
  {
    boolean isValid = numberOfWards() >= minimumNumberOfWards();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfWards()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Ward addWard(String aName, int aCapacity)
  {
    Ward aNewWard = new Ward(aName, aCapacity, this);
    return aNewWard;
  }

  public boolean addWard(Ward aWard)
  {
    boolean wasAdded = false;
    if (wards.contains(aWard)) { return false; }
    Hospital existingHospital = aWard.getHospital();
    boolean isNewHospital = existingHospital != null && !this.equals(existingHospital);

    if (isNewHospital && existingHospital.numberOfWards() <= minimumNumberOfWards())
    {
      return wasAdded;
    }
    if (isNewHospital)
    {
      aWard.setHospital(this);
    }
    else
    {
      wards.add(aWard);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeWard(Ward aWard)
  {
    boolean wasRemoved = false;
    //Unable to remove aWard, as it must always have a hospital
    if (this.equals(aWard.getHospital()))
    {
      return wasRemoved;
    }

    //hospital already at minimum (1)
    if (numberOfWards() <= minimumNumberOfWards())
    {
      return wasRemoved;
    }

    wards.remove(aWard);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addWardAt(Ward aWard, int index)
  {  
    boolean wasAdded = false;
    if(addWard(aWard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWards()) { index = numberOfWards() - 1; }
      wards.remove(aWard);
      wards.add(index, aWard);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveWardAt(Ward aWard, int index)
  {
    boolean wasAdded = false;
    if(wards.contains(aWard))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfWards()) { index = numberOfWards() - 1; }
      wards.remove(aWard);
      wards.add(index, aWard);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addWardAt(aWard, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=employees.size(); i > 0; i--)
    {
      Employee aEmployee = employees.get(i - 1);
      aEmployee.delete();
    }
    for(int i=wards.size(); i > 0; i--)
    {
      Ward aWard = wards.get(i - 1);
      aWard.delete();
    }
  }

}