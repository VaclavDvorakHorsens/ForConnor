package Application.Model.ModelChat;

import java.beans.PropertyChangeListener;

/**
 * A class serving as Chat model
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public interface ChatModelInterface
{

   void disconnectFromChat(String userName);

   void connectToChat(String userName);

   void addPropertyChangeListener(String name, PropertyChangeListener listener);

   void sendChatMessage(String userName, String chatMessageText);

   String returnUserName();

   void setUserName(String userName);
}
