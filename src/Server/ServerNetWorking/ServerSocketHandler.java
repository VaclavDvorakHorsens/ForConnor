package Server.ServerNetWorking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import ClientServerLog.SystemLog;
import Server.Database.DatabaseReadWriteAccess;
import Server.Model.ModelChat.ServerChatModel;
import Server.Model.ModelChat.ServerChatModelInterface;
import Server.Model.ModelLogIn.ServerLogInModel;
import Server.Model.ModelLogIn.ServerLogInModelInterface;
import Server.Model.ModelProfile.ServerProfileModel;
import Server.Model.ModelProfile.ServerProfileModelInterface;
import TransferClientServerObject.Message;
import TransferClientServerObject.MessageChatConnect;
import TransferClientServerObject.MessageChatDisconnect;
import TransferClientServerObject.MessageChatSendMessage;

/**
 * A class that takes care of communication between client and server via
 * sockets
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ServerSocketHandler implements Runnable
{
   private SystemLog systemLog;
   private ObjectInputStream inFromClient;
   private ObjectOutputStream outToClient;
   private ServerLogInModelInterface serverLogInModelInterface;
   private ServerProfileModelInterface serverProfileModelInterface;
   private ServerChatModelInterface serverChatModelInterface;

   /**
    * constructor initializing and setting socket communication and accepting
    * server sockets and initializing appropriate Server models
    */
   public ServerSocketHandler(Socket socket,
         DatabaseReadWriteAccess databaseReadWriteAccess)
   {
      this.systemLog = SystemLog.getInstance();
      this.serverLogInModelInterface = new ServerLogInModel(
            databaseReadWriteAccess);
      this.serverProfileModelInterface = new ServerProfileModel(
            databaseReadWriteAccess);
      this.serverChatModelInterface = ServerChatModel.getInstance();
      try
      {
         this.inFromClient = new ObjectInputStream(socket.getInputStream());
         this.outToClient = new ObjectOutputStream(socket.getOutputStream());
      }
      catch (IOException e)
      {
         systemLog.log(e.getMessage());
      }

   }

   /**
    * waiting for Message via socket, implements Runnable and runs in separate
    * Thread
    */
   @Override
   public void run()
   {
      try
      {
         while (true)
         {
            Message message = (Message) inFromClient.readObject();
            Message returnMessage = null;
            switch (message.returnMessageType())
            {
               case LOGIN:
                  returnMessage = serverLogInModelInterface.logIn(message);
                  break;
               case REGISTERPROFILE:
                  returnMessage = serverProfileModelInterface
                        .registerProfile(message);
                  break;
               case PROFILEDETAILS:
                  returnMessage = serverProfileModelInterface
                        .returnProfileDetails(message);
                  break;
               case CHATCONNECT:
                  serverChatModelInterface
                        .connectToChat((MessageChatConnect) message, this);
                  break;
               case CHATDISCONNECT:
                  serverChatModelInterface
                        .disconnectFromChat((MessageChatDisconnect) message);
                  break;
               case CHATMESSAGE:
                  serverChatModelInterface
                        .sendChatMessage((MessageChatSendMessage) message);
                  break;

               default:
                  break;
            }
            if (returnMessage != null)
            {
               returnMessage(returnMessage);
            }
         }
      }
      catch (ClassNotFoundException e)
      {
         systemLog.log(e.getMessage());
      }
      catch (IOException e)
      {
         systemLog.log(e.getMessage());
      }

   }

   /**
    * sends Message to client via socket
    * 
    * @param Message
    */
   public void returnMessage(Message message)
   {
      try
      {
         outToClient.writeObject(message);
      }
      catch (IOException e)
      {
         systemLog.log(e.getMessage());
      }
   }

}