package Server.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import ClientServerLog.SystemLog;
import TransferClientServerObject.Message;
import TransferClientServerObject.MessageLogIn;

/**
 * A class that takes care of communication between physical database and
 * DatabaseInterface
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class DatabaseAdapter implements DatabaseInterface
{
   private CreateMessagesFromDatabase createMessagesFromDatabase;
   private CreateSqlQuery createSqlQuery;

   private SystemLog systemLog;
   private Connection databaseConnection;
   private Statement sqlStatement;
   private ResultSet resultSet;
   private String sqlQuery;
   private String databaseSchema;

   /**
    * constructor initializing database connection and SystemLog(keeps track on
    * sql queries)
    */
   public DatabaseAdapter()
   {
      this.createSqlQuery = new CreateSqlQuery();
      this.createMessagesFromDatabase = new CreateMessagesFromDatabase();
      this.systemLog = SystemLog.getInstance();

      connecToDatabase();

   }

   /**
    * method create the connection between the database and application
    */

   private void connecToDatabase()
   {
      try
      {
         Class.forName("org.postgresql.Driver");
         databaseConnection = DriverManager.getConnection(
               "jdbc:postgresql://localhost:5432/postgres", "postgres",
               "password");
         sqlStatement = databaseConnection.createStatement();
         this.databaseSchema = "sep2_ver05";
      }
      catch (Exception e)
      {
         systemLog.log(e.getClass().getName() + ": " + e.getMessage());
         System.exit(0);
      }

   }

   /**
    * Accepts Message from DatabaseInterface and calls isLogInOk() and based on
    * that returns Message
    * 
    * @param Message
    *           from client
    * @return respond Message to client
    */
   @Override
   public Message logIn(Message message)
   {

      boolean isLogInOk = isLogInOk((MessageLogIn) message);
      String typeOfUser = typeOfUser((MessageLogIn) message);
      return createMessagesFromDatabase.createMessageLoginResponse(isLogInOk,
            typeOfUser, message);

   }

   /**
    * method return the message with profile details
    * 
    * @param message
    */

   @Override
   public Message returnProfileDetails(Message message)
   {
      return getProfileDetails(message);

   }

   /**
    * method return the message with registered profile
    * 
    * @param message
    */

   @Override
   public Message registerProfile(Message message)
   {

      Message returnProfileMessage = createProfile(message);
      return returnProfileMessage;

   }

   /**
    * method creates the profile in the database
    * 
    * @param message
    */

   private Message createProfile(Message message)
   {
      boolean isUserNameAlreadyTaken = isUserNameAlreadyTaken(message);
      if (isUserNameAlreadyTaken == false)
      {
         registerProfileInDatabaseWithHobbies(message);
      }
      return createMessagesFromDatabase
            .createMessageNewProfileResponse(isUserNameAlreadyTaken, message);
   }

   /**
    * method register the hobbies for the user in the profile
    * 
    * @param messageCreateNewProfile
    */

   private void registerProfileInDatabaseWithHobbies(
         Message messageCreateNewProfile)
   {

      try
      {
         sqlQuery = createSqlQuery.registerProfileInDatabaseQuery(
               messageCreateNewProfile, databaseSchema);
         sqlStatement.executeUpdate(sqlQuery);

         systemLog.log(sqlQuery);

         int userId = returnUserIdFromDatabase(messageCreateNewProfile);

         ArrayList<String> sqlProfileHobbiesQueries = createSqlQuery
               .registerProfileListOfHobbies(messageCreateNewProfile,
                     databaseSchema, userId);

         for (int i = 0; i < sqlProfileHobbiesQueries.size(); i++)
         {
            sqlStatement.executeUpdate(sqlProfileHobbiesQueries.get(i));
            systemLog.log(sqlQuery);
         }
      }

      catch (SQLException e)
      {
         systemLog.log(e.getClass().getName() + ": " + e.getMessage());
      }
   }

   /**
    * method return the user Id from the database
    * 
    * @param messageCreateNewProfile
    */

   private int returnUserIdFromDatabase(Message messageCreateNewProfile)
   {
      int userId = 0;
      sqlQuery = createSqlQuery.returnProfileDetails(messageCreateNewProfile,
            databaseSchema);
      try
      {
         resultSet = sqlStatement.executeQuery(sqlQuery);
         systemLog.log(sqlQuery);
         while (resultSet.next())
         {
            userId = resultSet.getInt("userid");
         }
      }
      catch (SQLException e)
      {
         systemLog.log(e.getMessage());
      }

      return userId;
   }

   /**
    * method return from database the message with profile details
    * 
    * @param message
    */

   private Message getProfileDetails(Message message)
   {

      String userName = "";
      String password = "";
      String dateOfBirth = "";
      String email = "";
      String sex = "";
      String tempTypeOfUser = "";
      Boolean isPremiumUser = false;

      sqlQuery = createSqlQuery.returnProfileDetails(message, databaseSchema);
      try
      {
         resultSet = sqlStatement.executeQuery(sqlQuery);
         systemLog.log(sqlQuery);

         while (resultSet.next())
         {
            userName = resultSet.getString("username");
            password = resultSet.getString("password");
            dateOfBirth = resultSet.getString("dateofbirth");
            email = resultSet.getString("email");
            sex = resultSet.getString("sex");
            tempTypeOfUser = resultSet.getString("typeOfUser");
         }
      }
      catch (SQLException e)
      {
         systemLog.log(e.getMessage());
      }

      ArrayList<String> listOfUserHobbies = returnProfileHobbies(userName);

      sqlQuery = createSqlQuery.returnProfileHobbies(
             databaseSchema, userName);

      return createMessagesFromDatabase.createMessageGetProfileDetailsResponse(
            userName, password, sex, email, isPremiumUser, tempTypeOfUser,
            dateOfBirth, listOfUserHobbies);

   }

   /**
    * method return the message with profile hobbies of the user
    * 
    * @param message
    */

   private ArrayList<String> returnProfileHobbies(String userName)
   {
      ArrayList<String> listOfUserHobbies = new ArrayList<String>();
      sqlQuery = createSqlQuery.returnProfileHobbies(
            /* listOfUserHobbies, */ databaseSchema, userName);

      try
      {
         resultSet = sqlStatement.executeQuery(sqlQuery);
         systemLog.log(sqlQuery);
         while (resultSet.next())
         {
            listOfUserHobbies.add(resultSet.getString("hobbyname"));
         }
      }
      catch (SQLException e)
      {
         systemLog.log(e.getMessage());
      }
      return listOfUserHobbies;
   }

   /**
    * Accepts Message and translates it to sql query and calls database
    * 
    * @param Message
    *           from client
    * @return boolean if userName and password fit, based on sql query
    */
   private boolean isLogInOk(Message messageLogin)
   {

      String userName = "";
      String password = "";
      this.sqlQuery = createSqlQuery.returnIsLogInValid(messageLogin,
            databaseSchema);

      try
      {
         resultSet = sqlStatement.executeQuery(sqlQuery);
         systemLog.log(sqlQuery);

         while (resultSet.next())
         {
            userName = resultSet.getString("username");
            password = resultSet.getString("password");
         }
      }
      catch (Exception e)
      {
         systemLog.log(e.getClass().getName() + ": " + e.getMessage());
      }

      return returnIsLogInValid(messageLogin, userName, password);
   }

   /**
    * Accepts Message and translates it to sql query and calls database
    * 
    * @param Message
    *           from client
    * @return String typeOfUser based on sql query
    */
   private String typeOfUser(Message messageLogin)
   {
      String databaseTypeOfUser = "";
      this.sqlQuery = createSqlQuery.returnTypeOfUser(messageLogin,
            databaseSchema);

      try
      {
         resultSet = sqlStatement.executeQuery(sqlQuery);
         systemLog.log(sqlQuery);

         while (resultSet.next())
         {
            databaseTypeOfUser = resultSet.getString("typeofuser");
         }

      }
      catch (Exception e)
      {
         systemLog.log(e.getClass().getName() + ": " + e.getMessage());
      }

      return databaseTypeOfUser;

   }

   /**
    * checks if there is already a user with same userName in database
    * 
    * @param Message
    *           from client
    * @return boolean isUserNameAlreadyTaken based on sql query
    */
   private boolean isUserNameAlreadyTaken(Message messageProfile)
   {
      boolean isUserNameAlreadyTaken = false;
      this.sqlQuery = createSqlQuery
            .returnIsUserNameAlreadyTaken(messageProfile, databaseSchema);

      try
      {
         resultSet = sqlStatement.executeQuery(sqlQuery);
         systemLog.log(sqlQuery);

         while (resultSet.next())
         {
            String databaseUserName = resultSet.getString("username");
            if (databaseUserName.equals(messageProfile.returnUserName()))
            {
               isUserNameAlreadyTaken = true;
               break;
            }
         }
      }
      catch (Exception e)
      {
         systemLog.log(e.getClass().getName() + ": " + e.getMessage());
      }
      return isUserNameAlreadyTaken;

   }

   /**
    * method return the message and check if the LogIn
    * 
    * @param message,
    *           userName, password
    */

   private boolean returnIsLogInValid(Message message, String userName,
         String password)
   {
      boolean sqlIsLogInOk = false;

      if (userName.equals(((MessageLogIn) message).returnUserName())
            && password.equals(((MessageLogIn) message).returnPassword()))
      {
         sqlIsLogInOk = true;
      }
      return sqlIsLogInOk;
   }

}
