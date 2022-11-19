package Application.ViewModel.ViewModelChat;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import Application.Model.ModelChat.ChatModelInterface;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * A class implementing middle layer between Controlling class and ChatModel on
 * client side
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class ChatWindowViewModel
{

   private ChatModelInterface chatModel;
   private PropertyChangeListener chatUpdateClientList;
   private PropertyChangeListener returnMessageChatSendMessage;
   private StringProperty connectedUsersList;
   private StringProperty chatArea;

   /**
    * constructor accepting chatModel and connects the users, messages
    * 
    * @param chatModel
    */

   public ChatWindowViewModel(ChatModelInterface chatModel)
   {
      this.chatArea = new SimpleStringProperty();
      this.connectedUsersList = new SimpleStringProperty();
      this.chatModel = chatModel;
      this.returnMessageChatSendMessage = (evt) -> returnChatMessage(evt);
      this.chatUpdateClientList = (evt) -> chatUpdateClientList(evt);

      chatModel.addPropertyChangeListener("returnMessageChatUpdateClientList",
            chatUpdateClientList);
      chatModel.addPropertyChangeListener("returnMessageChatSendMessage",
            returnMessageChatSendMessage);
   }

   /**
    * reacts on PropertyChangeEvent(new response ChatMessage from server) gives
    * the message in the chat
    * 
    * @param evt
    */

   private void returnChatMessage(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
  
         chatArea.setValue((String) evt.getNewValue());
      });
   }

   /**
    * returns the users that are connected
    */

   public StringProperty returnConnectedUsersList()
   {
      return connectedUsersList;
   }

   /**
    * returns the message sent
    */

   public StringProperty returnChatMessage()
   {
      return chatArea;
   }

   /**
    * reacts on PropertyChangeEvent(new response ChatMessage from server) gives
    * the users that are in chat
    * 
    * @param evt
    */

   private void chatUpdateClientList(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {

         String user = "";
         List<String> listOfClients = (List<String>) evt.getNewValue();

         for (String userName : listOfClients)
         {
            user += userName + "\n";
         }
         connectedUsersList.setValue(user);
      });

   }

   /**
    * display the text sent by user display the user wich sent the message
    * 
    * @param chatMessageText
    */

   public void sendChatMessage(String chatMessageText)
   {
      chatModel.sendChatMessage(chatModel.returnUserName(), chatMessageText);
   }

   /**
    * disconnect the user from the chat
    */

   public void disconnectFromChat()
   {
      chatModel.disconnectFromChat(chatModel.returnUserName());
   }

   /**
    * connect the user to the chat
    */

   public void connectToChat()
   {
      chatModel.connectToChat(chatModel.returnUserName());
   }

 

   /**
    * set the user to the chat
    * 
    * @param userName
    */
   public void setUserName(String userName)
   {
      chatModel.setUserName(userName);
   }

   /**
    * return the user for the chat
    */

   public String getUserName()
   {
      return chatModel.returnUserName();
   }

}
