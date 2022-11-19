package Server.StartServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import ClientServerLog.SystemLog;
import Server.Database.DatabaseAdapter;
import Server.Database.DatabaseInterface;
import Server.Database.DatabaseReadWriteAccess;
import Server.ServerNetWorking.ServerSocketHandler;

/**
 * A class starting server 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class StartServer
{

   /**
    * starting server sockets and assigning ServerSocketHandler in separate
    * Threads to any calling client socket
    */
   public static void main(String[] args)

   {
      SystemLog systemLog= SystemLog.getInstance();;
      System.out.println("starting server");
      try
      {
         ServerSocket welcomeSocket = new ServerSocket(2234);
         
         DatabaseInterface databaseAdapter=new DatabaseAdapter();
         DatabaseReadWriteAccess  databaseReadWriteAccess= new DatabaseReadWriteAccess(databaseAdapter);
         while (true)
         {
            Socket connectionSocket = welcomeSocket.accept();
            ServerSocketHandler serverSocketHandler = new ServerSocketHandler(
                  connectionSocket,databaseReadWriteAccess);
            Thread newServerHandlerThread = new Thread(serverSocketHandler);
            newServerHandlerThread.start();
         }

      }
      catch (IOException e)
      {
         systemLog.log(e.getMessage());
      }
   }

}

