package Server.Model.ModelProfile;


import Server.Database.DatabaseReadWriteAccess;
import TransferClientServerObject.Message;

/**
 * A class accepting Message from ServerSocketHandler, implements
 * ServerProfileModelInterface
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ServerProfileModel implements ServerProfileModelInterface
{

   private DatabaseReadWriteAccess databaseReadWriteAccess;

   /**
    * No-argument constructor initializing databaseInterface
    */
   public ServerProfileModel(DatabaseReadWriteAccess databaseReadWriteAccess)
   {
      this.databaseReadWriteAccess = databaseReadWriteAccess;
   }

   /**
    * sends Message to databaseInterface and sends back reply Message
    * 
    * @param profile
    *           Message
    * @return profile Message response
    */
   @Override
   public Message registerProfile(Message message)
   {

      databaseReadWriteAccess.acquireWrite();
      Message returnMessage = databaseReadWriteAccess.registerProfile(message);
      databaseReadWriteAccess.releaseWrite();
      return returnMessage;
   }

   /**
    * sends Message to databaseInterface and sends back reply Message with
    * profile details
    * 
    * @param profile
    *           Message
    */

   @Override
   public Message returnProfileDetails(Message message)
   {
      databaseReadWriteAccess.acquireRead();
      Message returnMessage = databaseReadWriteAccess
            .returnProfileDetails(message);
      databaseReadWriteAccess.releaseRead();
      return returnMessage;
   }

}
