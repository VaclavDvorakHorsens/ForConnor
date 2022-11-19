package Server.Model.ModelLogIn;

import TransferClientServerObject.Message;

/**
 * A interface between ServerSocketHandler and ServerLogInModel
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public interface ServerLogInModelInterface
{
   public Message logIn(Message message);
}