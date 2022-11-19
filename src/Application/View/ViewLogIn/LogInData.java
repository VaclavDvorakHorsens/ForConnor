package Application.View.ViewLogIn;

/**
 * A class serving as Transfer object between ViewHandler and Controllers
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class LogInData
{
   private String userName;
   private String typeOfUser;

   /**
    * consturctor that initializing the userName and typeOfUser
    * 
    * @param userName,
    *           typeOfUser
    */

   public LogInData(String userName, String typeOfUser)
   {

      this.userName = userName;
      this.typeOfUser = typeOfUser;
   }

   /**
    * method that return the userName
    */
   public String getUserName()
   {
      return userName;
   }

   /**
    * method that set the userName
    */
   public String getTypeOfUser()
   {
      return typeOfUser;
   }
}
