package TransferClientServerObject;

/**
 * Transfer class between client and server, implements Message Creates messages
 * for Chat
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class MessageChatSendMessage implements Message
{

   private MessageType messageType;
   private String userName;
   private String message;

   /**
    * Constructor of the class that creates the message for Chat
    * 
    * @param message,
    *           userName
    */

   public MessageChatSendMessage(String userName, String message)
   {

      this.userName = userName;
      this.message = message;
      this.messageType = Message.MessageType.CHATMESSAGE;
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
    * method return value of userName
    */

   @Override
   public String returnUserName()
   {
      return userName;
   }

   /**
    * method return value of message
    */

   public String returnMessage()
   {
      return message;
   }

}
