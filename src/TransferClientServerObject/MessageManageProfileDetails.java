package TransferClientServerObject;

import java.util.ArrayList;

import TransferClientServerObject.Message.MessageType;

/**
 * Create message for Profile Details between client and server, implements
 * Message
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class MessageManageProfileDetails implements Message
{
   private MessageType messageType;
   private String userName;
   private String password;
   private String sex;
   private String email;
   private boolean isPremiumUser;
   private String dateOfBirth;
   private ArrayList<hobbyType> listOfHobbies;

   /**
    * COnstructor for creating a message with Profile details
    * 
    * @param messageType,
    *           userName, password, sex, email, isPremiumUser, dateOfBirth,
    *           listOfHobbies
    */

   public MessageManageProfileDetails(MessageType messageType, String userName,
         String password, String sex, String email, boolean isPremiumUser,
         String dateOfBirth, ArrayList<hobbyType> listOfHobbies)
   {
      this.messageType = messageType;
      this.userName = userName;
      this.password = password;
      this.sex = sex;
      this.email = email;
      this.isPremiumUser = isPremiumUser;
      this.dateOfBirth = dateOfBirth;
      this.listOfHobbies = listOfHobbies;

   }

   /**
    * method return value of messageType
    */

   @Override
   public MessageType returnMessageType()
   {
      return messageType;
   }

   /**
    * method return value of usernName
    */
   @Override
   public String returnUserName()
   {
      return userName;
   }

   /**
    * method return value of password
    */

   public String returnPassword()
   {
      return password;
   }

   /**
    * method return value of sex
    */

   public String returnSex()
   {
      return sex;
   }

   /**
    * method return value of email
    */

   public String returnEmail()
   {
      return email;
   }

   /**
    * method return value of isPremiumUser
    */
   public boolean returnIsPremiumUser()
   {
      return isPremiumUser;
   }

   /**
    * method return value of dateOfBirth
    */
   public String returnDateOfBirth()
   {
      return dateOfBirth;
   }

   /**
    * method return value of listOfHobbies
    */
   public ArrayList<hobbyType> returnListOfHobbies()
   {
      return listOfHobbies;
   }
}
