/*****************************************************************************
 *                              Surgeon.java                                 *
 *****************************************************************************/
/*This code was generated using the UMPLE 1.33.0.6934.a386b0a58 modeling language!*/
// Example from: https://cruise.umple.org/umple/, Dr. Timothy Lethbridge et al., Downloaded 2024-01-22


/**
 * Specialized doctor who performs advanced procedures on patients.
 */
// line 50 "model.ump"
// line 84 "model.ump"
public class Surgeon extends Doctor
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Surgeon(String aName, int aId, int aSalary, Hospital aHospital, Ward... allWards)
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