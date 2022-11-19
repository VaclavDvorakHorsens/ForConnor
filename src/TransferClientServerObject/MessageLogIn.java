package TransferClientServerObject;

/**
 * Transfer class for logIn between client and server, implements Message
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class MessageLogIn implements Message
{

   private MessageType messageType;
   private String userName;
   private String password;
   private boolean isLogInOK;
   private String typeOfUser;

   /**
    * constructor accepts and declares messageType,userName,password,isLogInOk
    * parameters
    */
   public MessageLogIn(MessageType messageType, String userName,
         String password, boolean isLogInOK, String typeOfUser)
   {
      this.messageType = messageType;
      this.userName = userName;
      this.password = password;
      this.isLogInOK = isLogInOK;
      this.typeOfUser=typeOfUser;
   }

   /**
    * returns if logIn was successful or not
    * 
    * @return boolean
    */
   public boolean returnIsLogInOK()
   {
      return isLogInOK;
   }

   /**
    * returns userName, implements Message interface
    * 
    * @return String
    */
   @Override
   public String returnUserName()
   {
      return userName;
   }

   /**
    * returns password
    * 
    * @return String
    */
   public String returnPassword()
   {
      return password;
   }
   
   /**
    * returns typeOfUser
    * 
    * @return String
    */
   public String returnTypeOfUser()
   {
      return typeOfUser;
   }

  

   /**
    * returns MessageType, implements Message interface method
    * 
    * @return MessageType
    */
   @Override
   public MessageType returnMessageType()
   {
      return messageType;
   }

}
