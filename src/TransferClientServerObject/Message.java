package TransferClientServerObject;

import java.io.Serializable;

/**
 * A interface providing common methods for transfer objects that go from client
 * to server and the other way around
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public interface Message extends Serializable
{
   public MessageType returnMessageType();

   enum MessageType
   {
      LOGIN, REGISTERPROFILE, PROFILEDETAILS, RETURNPROFILEDETAILS, CHATCONNECT, CHATDISCONNECT, CHATMESSAGE, CHATCLIENTLIST
   }

   String returnUserName();
}
