package Application.ClientNetworking;

import java.beans.PropertyChangeListener;

import TransferClientServerObject.Message;

/**
 * An interface representing client
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public interface ClientInterface
{
   public void sendMessage(Message message);

   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener);

}
