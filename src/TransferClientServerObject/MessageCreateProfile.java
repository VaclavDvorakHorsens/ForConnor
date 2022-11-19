package TransferClientServerObject;

import java.util.ArrayList;


/**
 * Transfer class for Profile between client and server, implements Message
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class MessageCreateProfile extends MessageManageProfileDetails
{

   private boolean isUserNameAlreadyTaken;

   /**
    * constructor accepts and declares
    * messageType,userName,password,sex,email,isPremiumUser
    * dateOfBirth,listOfHobbies parameters
    */
   public MessageCreateProfile(MessageType messageType, String userName,
         String password, String sex, String email, boolean isPremiumUser,
         String dateOfBirth, ArrayList<hobbyType> listOfHobbies,
         boolean isUserNameAlreadyTaken)
   {
      super(messageType, userName, password, sex, email, isPremiumUser,
            dateOfBirth, listOfHobbies);

      this.isUserNameAlreadyTaken = false;
   }

   /**
    * method set the value for isUserNameAlreadyTaken
    * 
    * @param isUserNameAlreadyTaken
    */

   public void setIsUserNameAlreadyTaken(boolean isUserNameAlreadyTaken)
   {
      this.isUserNameAlreadyTaken = isUserNameAlreadyTaken;
   }

   /**
    * method return the value for isUserNameAlreadyTaken
    */
   public boolean returnIsUserNameAlreadyTaken()
   {
      return isUserNameAlreadyTaken;
   }

}
