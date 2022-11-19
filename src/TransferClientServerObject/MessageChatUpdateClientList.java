package TransferClientServerObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Transfer class between client and server, implements Message Creates messages
 * for Updating Chat
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class MessageChatUpdateClientList implements Message
{

   private MessageType messageType = MessageType.CHATCLIENTLIST;
   private List<String> userNames;

   /**
    * Constructor of the class that creates a list of userNames
    * 
    * @param userNames
    */

   public MessageChatUpdateClientList(Collection<String> userNames)
   {
      this.userNames = new ArrayList<String>(userNames);
   }

   /**
    * method return value of messageType
    */

   @Override
   public MessageType returnMessageType()
   {
      return messageType;
   }

   @Override
   public String returnUserName()
   {
      return null;
   }

   /**
    * return value of the userNames
    */

   public List<String> returnUserNames()
   {
      return userNames;
   }
}
