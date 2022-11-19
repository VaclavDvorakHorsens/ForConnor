package Application.Model.ModelLogIn;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import Application.ClientNetworking.ClientInterface;
import TransferClientServerObject.Message;
import TransferClientServerObject.MessageLogIn;
import javafx.application.Platform;

/**
 * A class serving as logIn model
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class LogInModel implements LogInModelInterface
{
   private PropertyChangeSupport changeSupport;
   private ClientInterface client;

   /**
    * constructor initializing client, islogInOk boolean and setting
    * PropertyChangeSupport for above class(LogInWindowViewModel) and listener
    * for client
    */
   public LogInModel(ClientInterface client)
   {
      this.client = client;
      client.addPropertyChangeListener("returnMessageLogIn",
            evt -> getMessageLogIn(evt));
      this.changeSupport = new PropertyChangeSupport(this);
      ;

   }

   /**
    * creates Message and sends and calls sendMessageToClient(message)
    * 
    * @param userName
    *           String, password String
    */
   @Override
   public void logIn(String userName, String password)
   {
      Message message = new MessageLogIn(Message.MessageType.LOGIN, userName,
            password, false, null);

      sendMessageToClient(message);

   }

   /**
    * sends Message to client
    * 
    * @param message
    *           Message
    */
   private void sendMessageToClient(Message message)
   {
      client.sendMessage(message);
   }

   /**
    * listens for client and if client receives Message, fires event for above
    * class(LogInWindowViewModel)
    * 
    * @param evet
    *           PropertyChangeEvent
    */
   private void getMessageLogIn(PropertyChangeEvent evt)
   {

      Platform.runLater(() -> {

         MessageLogIn returnMessage = (MessageLogIn) evt.getNewValue();
         if (returnMessage.returnIsLogInOK() == true)
         {
            changeSupport.firePropertyChange("logInOK", null, returnMessage);

         }
         else
         {
            changeSupport.firePropertyChange("logInNOK", null, returnMessage);
         }

      });

   }

   /**
    * adds listeners(above layer class: LogInWindowViewModel)
    * 
    * @param String,PropertyChangeListener
    */
   @Override
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);

   }

}
