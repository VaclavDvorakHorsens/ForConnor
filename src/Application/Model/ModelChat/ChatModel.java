package Application.Model.ModelChat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import Application.ClientNetworking.ClientInterface;
import TransferClientServerObject.MessageChatConnect;
import TransferClientServerObject.MessageChatDisconnect;
import TransferClientServerObject.MessageChatSendMessage;
import TransferClientServerObject.MessageChatUpdateClientList;
import javafx.application.Platform;

/**
 * A class serving as logIn model
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class ChatModel implements ChatModelInterface
{

   private PropertyChangeListener chatUpdateClientList;
   private PropertyChangeListener returnChatMessage;
   private String wholeChatConversation = "";
   private PropertyChangeSupport changeSupport;
   private ClientInterface client;
   private String userName;

   /**
    * constructor initializing client, setting PropertyChangeListener and
    * listener for client
    */

   public ChatModel(ClientInterface client)
   {
      this.changeSupport = new PropertyChangeSupport(this);
      this.client = client;
      this.chatUpdateClientList = (evt) -> chatUpdateClientList(evt);
      client.addPropertyChangeListener("returnMessageChatUpdateClientList",
            chatUpdateClientList);

      this.returnChatMessage = (evt) -> chatReturnChatMessage(evt);

      client.addPropertyChangeListener("returnMessageChatSendMessage",
            returnChatMessage);

   }

   /**
    * listens for client and if client receives Message, fires event for above
    * class(ChatWindowViewModel)
    * 
    * @param evt
    *           PropertyChangeEvent
    */

   private void chatReturnChatMessage(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         wholeChatConversation += ((MessageChatSendMessage) evt.getNewValue())
               .returnUserName();
         wholeChatConversation += ": ";
         wholeChatConversation += ((MessageChatSendMessage) evt.getNewValue())
               .returnMessage();
         wholeChatConversation += "\n";
         changeSupport.firePropertyChange("returnMessageChatSendMessage", null,
               wholeChatConversation);
      });

   }

   /**
    * update the Client List with the new Client
    * 
    * @param evt
    *           PropertyChangeEvent
    */

   private void chatUpdateClientList(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         List<String> returnUserNames = ((MessageChatUpdateClientList) evt
               .getNewValue()).returnUserNames();
         changeSupport.firePropertyChange("returnMessageChatUpdateClientList",
               null, returnUserNames);
      });
   }

   /**
    * sends message into chat to another user
    * 
    * @param userName
    *           String, chatMessageText String
    */

   @Override
   public void sendChatMessage(String userName, String chatMessageText)
   {
      client.sendMessage(new MessageChatSendMessage(userName, chatMessageText));

   }

   /**
    * disconnects the user from the chat and close the chat
    * 
    * @param userName
    *           String
    */

   @Override
   public void disconnectFromChat(String userName)
   {
      client.sendMessage(new MessageChatDisconnect(userName));

   }

   /**
    * connect the user to the chat and open the chat
    * 
    * @param userName
    *           String
    */

   @Override
   public void connectToChat(String userName)
   {
      client.sendMessage(new MessageChatConnect(userName));
   }

   /**
    * Add a property-change listener for a specific property - name
    * 
    * @param name
    *           String, listener PropertyChangeListener
    */

   @Override
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);
   }

   /**
    * return user name
    */

   @Override
   public String returnUserName()
   {
      return userName;
   }

   /**
    * set user name
    * 
    * @param userName
    *           String
    */

   @Override
   public void setUserName(String userName)
   {
      this.userName = userName;
   }

}
