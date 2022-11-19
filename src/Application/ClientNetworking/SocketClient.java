package Application.ClientNetworking;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import TransferClientServerObject.Message;
import TransferClientServerObject.MessageChatSendMessage;
import TransferClientServerObject.MessageChatUpdateClientList;
import TransferClientServerObject.MessageCreateProfile;
import TransferClientServerObject.MessageLogIn;
import TransferClientServerObject.MessageManageProfileDetails;

/**
 * A class that implements Client interface and represents client side
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class SocketClient implements ClientInterface
{

   private PropertyChangeSupport changeSupport;
   private ClientSocketHandler socketHandler;

   /**
    * No-argument constructor initializing observer pattern for upper
    * layer(LogInModel) and creates ClientSocketHandler and starts Thread for it
    */
   public SocketClient()
   {
      this.changeSupport = new PropertyChangeSupport(this);
      Socket socket;
      try
      {
         socket = new Socket("localhost", 2234);
         this.socketHandler = new ClientSocketHandler(
               new ObjectOutputStream(socket.getOutputStream()),
               new ObjectInputStream(socket.getInputStream()), this);
         Thread thread = new Thread(socketHandler);
         thread.start();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

   /**
    * implements Client interface method,sends Message further to
    * ClientSocketHandler
    * 
    * @param Message,
    *           that includes communication between client and server
    */
   @Override
   public void sendMessage(Message message)

   {
      socketHandler.sendMessage(message);
   }

   /**
    * adds listeners(upper layer->LogInModel) that observe THIS
    * 
    * @param propertyName
    *           and listener
    */
   @Override
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);
   }

   /**
    * returns Message to upper layer(LogInModel) via Observer Pattern
    * 
    * @param Message,
    *           that includes communication between client and server
    */
   public void returnServerMessage(Message message)
   {

      if (message instanceof MessageLogIn)
      {
         changeSupport.firePropertyChange("returnMessageLogIn", null, message);
      }
      if (message instanceof MessageCreateProfile)
      {
         changeSupport.firePropertyChange("returnMessageCreateProfile", null,
               message);
      }
      if (message instanceof MessageManageProfileDetails)
      {
         changeSupport.firePropertyChange("returnMessageProfileDetails", null,
               message);
      }

      if (message instanceof MessageChatUpdateClientList)
      {
         changeSupport.firePropertyChange("returnMessageChatUpdateClientList",
               null, message);
      }

      if (message instanceof MessageChatSendMessage)
      {
         changeSupport.firePropertyChange("returnMessageChatSendMessage", null,
               message);
      }

   }

}
