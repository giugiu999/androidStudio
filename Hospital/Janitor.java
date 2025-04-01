/*****************************************************************************
 *                              Janitor.java                                *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


/**
 * Employee who maintains the cleanliness of the hospital.
 */
// line 56 "model.ump"
// line 89 "model.ump"
public class Janitor extends Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Janitor(String aName, int aId, int aSalary, Hospital aHospital, Ward... allWards)
  {
    super(aName, aId, aSalary, aHospital, allWards);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public void delete()
  {
    super.delete();
  }

}