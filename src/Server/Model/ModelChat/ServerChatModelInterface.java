package Server.Model.ModelChat;

import Server.ServerNetWorking.ServerSocketHandler;
import TransferClientServerObject.MessageChatConnect;
import TransferClientServerObject.MessageChatDisconnect;
import TransferClientServerObject.MessageChatSendMessage;

/**
 * A interface between ServerSocketHandler and ServerChatModel
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public interface ServerChatModelInterface
{
   void connectToChat(MessageChatConnect message, ServerSocketHandler client);

   void disconnectFromChat(MessageChatDisconnect message);

   void sendChatMessage(MessageChatSendMessage message);
}
