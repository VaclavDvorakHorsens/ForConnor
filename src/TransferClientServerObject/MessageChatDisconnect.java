package TransferClientServerObject;

/**
 * Transfer class between client and server, implements Message Creates messages
 * for the function Disconnect Chat
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class MessageChatDisconnect implements Message
{

   private MessageType messageType;
   private String userName;

   /**
    * Constructor of the class that create the fucntion based on the user name
    * 
    * @param userName
    */

   public MessageChatDisconnect(String userName)
   {

      this.userName = userName;
      this.messageType = Message.MessageType.CHATDISCONNECT;
   }

   @Override
   public MessageType returnMessageType()
   {
      return messageType;
   }

   @Override
   public String returnUserName()
   {
      return userName;
   }

}
