package Testing_JUnit;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

import Server.Database.DatabaseAdapter;
import Server.Database.DatabaseInterface;
import TransferClientServerObject.Message;
import TransferClientServerObject.MessageCreateProfile;
import TransferClientServerObject.MessageLogIn;
import TransferClientServerObject.hobbyType;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DatabaseTest
{

   private DatabaseInterface databaseAdapter;
   private Connection databaseConnection;
   private Statement sqlStatement;
   private String deleteSqlQuery;
   private Message message;
   private String databaseSchema;
   private String userName;
   private String password;


   @Before
   public void setup() throws ClassNotFoundException, IOException, SQLException
   {
    
      this.userName="testUser"; 
      this.password="testPassword"; 
      this.databaseAdapter = new DatabaseAdapter();
      Class.forName("org.postgresql.Driver");
      this.databaseConnection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/postgres", "postgres", "password");
      this.sqlStatement = databaseConnection.createStatement();
      this.databaseSchema="sep2_ver05";
   }

  
   public void delete() throws SQLException
   {
      
      this.deleteSqlQuery="DELETE FROM "+databaseSchema+".ProfileHobbies WHERE userid=(select userid from "+ databaseSchema+".profile where username='"+userName+"')";
 
      sqlStatement.execute(deleteSqlQuery);
      this.deleteSqlQuery = "DELETE FROM "+databaseSchema+".Profile WHERE username='"+userName+"'";
  
      sqlStatement.execute(deleteSqlQuery);  
   }
   
   @org.junit.Test
   // Register Profile-success
   public void test1_RegisterProfileSuccess() throws IOException, ClassNotFoundException, SQLException
   {

   
   ArrayList<hobbyType> listOfHobbies=new ArrayList<hobbyType>();
   listOfHobbies.add(hobbyType.SOCCER);
   listOfHobbies.add(hobbyType.DANCING);
   
   this.message = new MessageCreateProfile(Message.MessageType.REGISTERPROFILE, userName,
         password, "M","testemail@gmail.com",true,"1982-01-01",listOfHobbies,false);
   MessageCreateProfile successRegisterProfile = (MessageCreateProfile) databaseAdapter.registerProfile(message);

   assertEquals(successRegisterProfile.returnIsUserNameAlreadyTaken(), false);
  
   }
   
   
   
   @org.junit.Test
   // LogIn successful
   public void test2_LogInSuccess() throws IOException, ClassNotFoundException, SQLException
   {
     
      this.message = new MessageLogIn(Message.MessageType.LOGIN, userName,
            password, false, "BasicUser");
      MessageLogIn successLogIn = (MessageLogIn) databaseAdapter.logIn(message);

      assertEquals(successLogIn.returnIsLogInOK(), true);
   }

   
   
   @org.junit.Test
   // LogIn non-successful, wrong userName
   public void test3_LogInWrongUserName() throws IOException, ClassNotFoundException, SQLException
   {
      
      this.message = new MessageLogIn(Message.MessageType.LOGIN, "wrongUser",
            password, false, "BasicUser");
      MessageLogIn nonSuccessLogIn = (MessageLogIn) databaseAdapter
            .logIn(message);
      assertEquals(nonSuccessLogIn.returnIsLogInOK(), false);
   }

   
   
   @org.junit.Test
   // LogIn non-successful, wrong password
   public void test4_LogInWrongPassword() throws IOException, ClassNotFoundException, SQLException
   {
      
      this.message = new MessageLogIn(Message.MessageType.LOGIN, userName,
            "wrongPassword", false, "BasicUser");
      MessageLogIn nonSuccessLogIn = (MessageLogIn) databaseAdapter.logIn(message);
      assertEquals(nonSuccessLogIn.returnIsLogInOK(), false);   
   }

   
   
   @org.junit.Test
   // Register Profile-userName already taken
   public void test5_RegisterProfileUserNameTaken() throws IOException, ClassNotFoundException, SQLException
   {
   
   ArrayList<hobbyType> listOfHobbies=new ArrayList<hobbyType>();
   listOfHobbies.add(hobbyType.SOCCER);
   listOfHobbies.add(hobbyType.DANCING);
   
   this.message = new MessageCreateProfile(Message.MessageType.REGISTERPROFILE, userName,
         password, "M","testemail@gmail.com",true,"1982-01-01",listOfHobbies,false);
   MessageCreateProfile nonSuccessRegisterProfile = (MessageCreateProfile) databaseAdapter.registerProfile(message);
   assertEquals(nonSuccessRegisterProfile.returnIsUserNameAlreadyTaken(), true);
  
   delete();
   
   
   }

   
}
