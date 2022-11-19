package Application.ClientNetworking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import ClientServerLog.SystemLog;
import TransferClientServerObject.Message;

/**
 * A class that takes care of communication between client and server via
 * sockets
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ClientSocketHandler implements Runnable
{
   private SystemLog systemLog;
   private ObjectOutputStream outToServer;
   private ObjectInputStream inFromServer;
   private Message messageFromServer;
   private SocketClient socketClient;

   /**
    * constructor initializing and setting socket communication and accepting
    * SocketClient
    */
   public ClientSocketHandler(ObjectOutputStream outToServer,
         ObjectInputStream inFromServer, SocketClient socketClient)
   {
      this.systemLog = SystemLog.getInstance();
      this.outToServer = outToServer;
      this.inFromServer = inFromServer;
      this.socketClient = socketClient;
   }

   /**
    * sends Message to server via socket
    * 
    * @param Message
    */
   public void sendMessage(Message message)
   {
      {
         try
         {
            outToServer.writeObject(message);
         }
         catch (IOException e)
         {
            systemLog.log(e.getMessage());
         }
      }
   }

   /**
    * waiting for Message via socket, implements Runnable and runs in separate
    * Thread keeping status of Client being logged in successfully or not
    */
   @Override
   public void run()
   {
      try
      {
         while (true)
         {
            messageFromServer = (Message) inFromServer.readObject();
            socketClient.returnServerMessage(messageFromServer);

         }
      }
      catch (IOException | ClassNotFoundException e)
      {
         systemLog.log(e.getMessage());
      }
   }

}
