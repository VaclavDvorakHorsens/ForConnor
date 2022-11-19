package Server.Database;

import java.util.ArrayList;

import TransferClientServerObject.Message;
import TransferClientServerObject.MessageCreateProfile;
import TransferClientServerObject.MessageLogIn;
import TransferClientServerObject.MessageManageProfileDetails;
import TransferClientServerObject.hobbyType;

/**
 * A class creating Message from Database
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class CreateMessagesFromDatabase
{

   /**
    * Constructor for creating Message from Database
    * 
    * @param isLogInOk,
    *           typeOfUser, message
    */

   public Message createMessageLoginResponse(boolean isLogInOk,
         String typeOfUser, Message message)
   {
      return new MessageLogIn(Message.MessageType.LOGIN,
            ((MessageLogIn) message).returnUserName(),
            ((MessageLogIn) message).returnPassword(), isLogInOk, typeOfUser);
   }

   /**
    * method check if user name is uniq
    * 
    * @param isUserNameAlreadyTaken,
    *           message
    */

   public Message createMessageNewProfileResponse(
         boolean isUserNameAlreadyTaken, Message message)
   {
      Message returnMessageCreateNewProfile = message;
      ((MessageCreateProfile) message)
            .setIsUserNameAlreadyTaken(isUserNameAlreadyTaken);
      return returnMessageCreateNewProfile;
   }

   /**
    * method return the details of the user from database
    * 
    * @param userName,
    *           password, sex, email, isPremiumUser, tempTypeOfUser,
    *           dateOfBirth, listOfUserHobbies
    */

   public Message createMessageGetProfileDetailsResponse(String userName,
         String password, String sex, String email, boolean isPremiumUser,
         String tempTypeOfUser, String dateOfBirth,
         ArrayList<String> listOfUserHobbies)
   {

      if (tempTypeOfUser.equals("BasicUser"))
      {
         isPremiumUser = false;
      }
      else if (tempTypeOfUser.equals("PremiumUser"))
      {
         isPremiumUser = true;
      }

      ArrayList<hobbyType> listOfHobbies = new ArrayList<hobbyType>();

      for (int i = 0; i < listOfUserHobbies.size(); i++)
      {
         listOfHobbies.add(hobbyType.valueOf(listOfUserHobbies.get(i)));

      }

      return new MessageManageProfileDetails(Message.MessageType.PROFILEDETAILS,
            userName, password, sex, email, isPremiumUser, dateOfBirth,
            listOfHobbies);

   }

}
