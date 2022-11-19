package Server.Model.ModelLogIn;


import Server.Database.DatabaseReadWriteAccess;
import TransferClientServerObject.Message;

/**
 * A class accepting Message from ServerSocketHandler, implements
 * ServerLogInModelInterface
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ServerLogInModel implements ServerLogInModelInterface
{

   private DatabaseReadWriteAccess databaseReadWriteAccess;

   /**
    * No-argument constructor initializing databaseInterface
    */
   public ServerLogInModel(DatabaseReadWriteAccess databaseReadWriteAccess)
   {
      this.databaseReadWriteAccess = databaseReadWriteAccess;
   }

   /**
    * sends Message to databaseInterface and sends back reply Message
    * 
    * @param logIn
    *           Message
    * @return logIn Message response
    */
   @Override
   public Message logIn(Message message)
   {

      databaseReadWriteAccess.acquireRead();
      Message returnMessage = databaseReadWriteAccess.logIn(message);
      databaseReadWriteAccess.releaseRead();
      return returnMessage;
   }

}
