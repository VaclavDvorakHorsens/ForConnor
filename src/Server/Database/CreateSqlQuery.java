package Server.Database;

import java.util.ArrayList;

import TransferClientServerObject.Message;
import TransferClientServerObject.MessageCreateProfile;
import TransferClientServerObject.MessageLogIn;
import TransferClientServerObject.MessageManageProfileDetails;
import TransferClientServerObject.hobbyType;

/**
 * A class that creates a sql query message for Database
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class CreateSqlQuery
{
   private String sqlQuery;

   /**
    * Constructor for creating the sql query message
    * 
    * @param clientMessageProfile,
    *           databaseSchema
    */

   public String registerProfileInDatabaseQuery(Message clientMessageProfile,
         String databaseSchema)
   {
      sqlQuery = "INSERT INTO " + databaseSchema + ".Profile VALUES (nextval('"
            + databaseSchema + ".userid_seq'),'"
            + ((MessageCreateProfile) clientMessageProfile).returnUserName()
            + "','"
            + ((MessageCreateProfile) clientMessageProfile).returnPassword()
            + "','"
            + ((MessageCreateProfile) clientMessageProfile).returnEmail()
            + "','"
            + ((MessageCreateProfile) clientMessageProfile).returnDateOfBirth()
            + "','" + returnTypeOfSexInProfileMessage(clientMessageProfile)
            + "','" + returnTypeOfUserInProfileMessage(clientMessageProfile)
            + "');";
      return sqlQuery;
   }

   /**
    * method create the sql query message for type of user
    * 
    * @param clientMessageProfile
    */

   private String returnTypeOfUserInProfileMessage(Message clientMessageProfile)
   {
      String typeOfUser = "";
      if (((MessageCreateProfile) clientMessageProfile)
            .returnIsPremiumUser() == true)
      {
         typeOfUser = "PremiumUser";
      }
      else if (((MessageCreateProfile) clientMessageProfile)
            .returnIsPremiumUser() == false)
      {
         typeOfUser = "BasicUser";
      }
      return typeOfUser;
   }

   /**
    * method create the sql query message for type sex of user
    * 
    * @param clientMessageProfile
    */

   private String returnTypeOfSexInProfileMessage(Message clientMessageProfile)
   {
      String sexOfUser = "";
      if (((MessageCreateProfile) clientMessageProfile).returnSex().equals("M"))
      {
         sexOfUser = "M";
      }
      else
      {
         sexOfUser = "F";
      }
      return sexOfUser;
   }

   /**
    * method create the sql query message for userName of user
    * 
    * @param messageProfile,
    *           databaseSchema
    */

   public String returnProfileDetails(Message messageProfile,
         String databaseSchema)
   {
      String sqlQuery = "";
      if (messageProfile instanceof MessageManageProfileDetails)
      {
         sqlQuery = "SELECT * FROM " + databaseSchema + ".profile"
               + " WHERE username='"
               + ((MessageManageProfileDetails) messageProfile).returnUserName()
               + "';";
      }
      return sqlQuery;
   }

   /**
    * method create the sql query message for hobbies of the user
    * 
    * @param messageProfile,
    *           databaseSchema, userId
    */

   public ArrayList<String> registerProfileListOfHobbies(Message MessageProfile,
         String databaseSchema, int userId)
   {
      ArrayList<hobbyType> listOfUserHobbies = new ArrayList<hobbyType>();
      ArrayList<String> listOfSqlQueries = new ArrayList<String>();

      if (MessageProfile instanceof MessageManageProfileDetails)
      {
         listOfUserHobbies = ((MessageCreateProfile) MessageProfile)
               .returnListOfHobbies();
      }

      for (int i = 0; i < listOfUserHobbies.size(); i++)
      {
         listOfSqlQueries.add("INSERT INTO " + databaseSchema
               + ".ProfileHobbies (hobbyid, userid) select hobbyid,'" + userId
               + "' from " + databaseSchema + ".Hobby where hobbyname='"
               + listOfUserHobbies.get(i) + "';");
      }

      return listOfSqlQueries;
   }

   /**
    * method create the sql query message that return the hobbies selected by
    * user
    * 
    * @param databaseSchema,
    *           userName
    */

   public String returnProfileHobbies(String databaseSchema, String userName)
   {
      sqlQuery = "SELECT * FROM " + databaseSchema + ".hobby inner join "
            + databaseSchema
            + ".profilehobbies on hobby.hobbyid=profilehobbies.hobbyid inner join "
            + databaseSchema + ".profile  on " + databaseSchema
            + ".profile.userid=" + databaseSchema
            + ".profilehobbies.userid where " + databaseSchema
            + ".profile.username='" + userName + "';";

      return sqlQuery;
   }

   /**
    * method create the sql query message for checking if log in is valid
    * 
    * @param messageLogin,
    *           databaseSchema
    */

   public String returnIsLogInValid(Message messageLogin, String databaseSchema)
   {
      sqlQuery = "SELECT * FROM " + databaseSchema + ".profile"
            + " WHERE username='"
            + ((MessageLogIn) messageLogin).returnUserName()
            + "' and password='"
            + ((MessageLogIn) messageLogin).returnPassword() + "';";

      return sqlQuery;
   }

   /**
    * method create the sql query message wich return type of user
    * 
    * @param message,
    *           databaseSchema
    */

   public String returnTypeOfUser(Message message, String databaseSchema)
   {
      sqlQuery = "SELECT * FROM " + databaseSchema + ".profile"
            + " WHERE username='" + ((MessageLogIn) message).returnUserName()
            + "' and password='" + ((MessageLogIn) message).returnPassword()
            + "';";
      return sqlQuery;
   }

   /**
    * method create the sql query message for checking if user name is available
    * 
    * @param message,
    *           databaseSchema
    */

   public String returnIsUserNameAlreadyTaken(Message message,
         String databaseSchema)
   {
      sqlQuery = "SELECT * FROM " + databaseSchema + ".profile"
            + " WHERE username='"
            + ((MessageCreateProfile) message).returnUserName()
            + "' and password='"
            + ((MessageCreateProfile) message).returnPassword() + "';";
      return sqlQuery;
   }

}
