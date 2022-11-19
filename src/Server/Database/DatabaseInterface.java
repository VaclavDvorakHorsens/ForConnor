package Server.Database;

import TransferClientServerObject.Message;

/**
* An interface between DatabaseAdapter class and rest of ServerModels
* @author Vaclav Dvorak
* @version 1.0
*/
public interface DatabaseInterface
   {
   public Message logIn(Message message);
   public Message registerProfile(Message message);
   public Message returnProfileDetails(Message message);
 
   }