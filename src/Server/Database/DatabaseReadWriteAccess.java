package Server.Database;

import TransferClientServerObject.Message;

/**
 * A class controlling and locking database
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class DatabaseReadWriteAccess
{

   private DatabaseInterface databaseAdapter;
   private int activeReaders = 0, waitingReaders = 0, writers = 0;

   /**
    * constructor
    * 
    * @param databaseAdapter
    */
   public DatabaseReadWriteAccess(DatabaseInterface databaseAdapter)
   {
      this.databaseAdapter = databaseAdapter;
   }

   /**
    * locking access to database synchronized method
    */
   public synchronized void acquireRead()
   {
      waitingReaders++;
      while (writers > 0)
      {
         try
         {
            wait();
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
      waitingReaders--;
      activeReaders++;
   }

   /**
    * notify that there is no more reading in database synchronized method
    */
   public synchronized void releaseRead()
   {
      activeReaders--;
      if (activeReaders == 0)
      {
         notifyAll();
      }
   }

   /**
    * notify that there is no more writing in database synchronized method
    */
   public synchronized void releaseWrite()
   {
      writers--;
      notifyAll();
   }

   /**
    * locking access to database synchronized method
    */
   public synchronized void acquireWrite()
   {
      while (activeReaders > 0 || writers > 0 || waitingReaders > 0)
      {
         try
         {
            wait();
         }
         catch (InterruptedException e)
         {
            e.printStackTrace();
         }
      }
      writers++;
   }

   /**
    * send log in message to database
    * 
    * @param message
    * @return messsage
    */
   public Message logIn(Message message)
   {
      return databaseAdapter.logIn(message);
   }

   /**
    * send message to database asking for profile details
    * 
    * @param message
    * @return messsage
    */
   public Message returnProfileDetails(Message message)
   {
      return databaseAdapter.returnProfileDetails(message);
   }

   /**
    * send register profile message to database
    * 
    * @param message
    * @return messsage
    */
   public Message registerProfile(Message message)
   {
      return databaseAdapter.registerProfile(message);
   }

}
