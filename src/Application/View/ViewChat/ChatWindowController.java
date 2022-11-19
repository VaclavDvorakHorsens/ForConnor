package Application.View.ViewChat;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

import Application.ViewModel.ViewModelChat.ChatWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * A class controlling FXML
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class ChatWindowController
{
   private ChatWindowViewModel chatWindowViewModel;
   private PropertyChangeSupport changeSupport;
   @FXML
   private Label ReturnToProfileSection;
   @FXML
   private Button sendChatMessage;
   @FXML
   private Button disconnect;
   @FXML
   private TextField userName = new TextField();
   @FXML
   private Label chatWindowLabel;
   @FXML
   private TextArea chatArea = new TextArea();
   @FXML
   private Label connectedUsersListLabel;
   @FXML
   private TextField sendMessageField = new TextField();
   @FXML
   private TextArea connectedUsersList = new TextArea();

   /**
    * assign ChatWindowViewModel and define itself as observable object
    * 
    * @param chatWindowViewModel
    */

   public void init(ChatWindowViewModel chatWindowViewModel)
   {
      this.changeSupport = new PropertyChangeSupport(this);
      this.chatWindowViewModel = chatWindowViewModel;
      this.connectedUsersList.textProperty()
            .bind(chatWindowViewModel.returnConnectedUsersList());
      this.chatArea.textProperty()
            .bind(chatWindowViewModel.returnChatMessage());

      chatWindowViewModel.connectToChat();
   }

   /**
    * sends message from GUI fields to ChatWindowViewModel
    * 
    * @param actionEvent,reacts
    *           to button
    */

   public void sendChatMessage(ActionEvent actionEvent) throws IOException
   {
      chatWindowViewModel.sendChatMessage(sendMessageField.getText());
      sendMessageField.setText("");

   }

   /**
    * disconnect from the chat on pressing button
    * 
    * @param actionEvent,reacts
    *           to button
    */

   public void disconnectFromChat(ActionEvent actionEvent) throws IOException
   {
      chatWindowViewModel.disconnectFromChat();
      changeSupport.firePropertyChange("profile", null,
            chatWindowViewModel.getUserName());

   }

   /**
    * adds listeners on THIS(above layer class: ViewHandler)
    * 
    * @param String,PropertyChangeListener
    */
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);
   }

}
