package TransferClientServerObject;



/**
 * Transfer class between client and server, implements Message
 * Creates messages for the function Connect Chat
 * @author Vaclav Dvorak
 * @version 1.0
 */


public class MessageChatConnect implements Message
{

  
   private MessageType messageType;
   private String userName;
   

 /**
 * Constructor of the class that create the fucntion based on the user name
* @param userName
 */
   
   public MessageChatConnect(String userName)
   {
      
      this.userName = userName;
      this.messageType=Message.MessageType.CHATCONNECT;
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
 * return value of the userNames
 */


   @Override
   public String returnUserName()
   {
      return userName;
   }

}

