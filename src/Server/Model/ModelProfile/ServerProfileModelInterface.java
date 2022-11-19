package Server.Model.ModelProfile;

import TransferClientServerObject.Message;

/**
 * A interface between ServerSocketHandler and ServerProfileModel
 * @author Vaclav Dvorak
 * @version 1.0
 */
public interface ServerProfileModelInterface
{
   public Message registerProfile(Message message);

   Message returnProfileDetails(Message message);
}
