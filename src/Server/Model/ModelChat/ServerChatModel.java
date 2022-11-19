package Server.Model.ModelChat;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Server.ServerNetWorking.ServerSocketHandler;
import TransferClientServerObject.Message;
import TransferClientServerObject.MessageChatConnect;
import TransferClientServerObject.MessageChatDisconnect;
import TransferClientServerObject.MessageChatSendMessage;
import TransferClientServerObject.MessageChatUpdateClientList;

/**
 * A class accepting Message from ServerSocketHandler, implements
 * ServerChatModelInterface
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class ServerChatModel implements ServerChatModelInterface
{
   private static ServerChatModel instance = null;
   private static Lock lock = new ReentrantLock();
   private Map<String, ServerSocketHandler> clients = new HashMap<>();

   
   /**
    * static method that initializes ServerChatModel and returns it as static
    */

   public static ServerChatModel getInstance()
   {
      if (instance == null)
      {
         synchronized (lock)
         {
            if (instance == null)
            {
               instance = new ServerChatModel();
            }
         }

      }
      return instance;
   }

   /**
    * No-argument constructor
    * 
    */
   private ServerChatModel()
   {

   }

   /**
    * connect the client to the chat via socket
    * 
    * @param message,
    *           client
    */

   @Override
   public void connectToChat(MessageChatConnect message,
         ServerSocketHandler client)
   {
      clients.put(message.returnUserName(), client);
      Message returnMessage = new MessageChatUpdateClientList(clients.keySet());
      for (ServerSocketHandler serverSocketHandler : clients.values())
      {
         serverSocketHandler.returnMessage(returnMessage);
      }
   }

   /**
    * disconnect the client from the chat via socket
    * 
    * @param message
    */

   @Override
   public void disconnectFromChat(MessageChatDisconnect message)
   {
      clients.remove(message.returnUserName());
      Message returnMessage = new MessageChatUpdateClientList(clients.keySet());
      for (ServerSocketHandler serverSocketHandler : clients.values())
      {
         serverSocketHandler.returnMessage(returnMessage);
      }
   }

   /**
    * send the message to the chat via socket
    * 
    * @param message
    */

   @Override
   public void sendChatMessage(MessageChatSendMessage message)
   {
      for (ServerSocketHandler serverSocketHandler : clients.values())
      {
         serverSocketHandler.returnMessage(message);
      }
   }

}
