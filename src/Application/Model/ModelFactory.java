package Application.Model;

import Application.ClientNetworking.ClientInterface;
import Application.ClientNetworking.SocketClient;
import Application.Model.ModelAdmin.AdminModel;
import Application.Model.ModelAdmin.AdminModelInterface;
import Application.Model.ModelChat.ChatModel;
import Application.Model.ModelChat.ChatModelInterface;
import Application.Model.ModelLogIn.LogInModel;
import Application.Model.ModelLogIn.LogInModelInterface;
import Application.Model.ModelProfile.ProfileModel;
import Application.Model.ModelProfile.ProfileModelInterface;
import Application.Model.ModelSearch.SearchModel;
import Application.Model.ModelSearch.SearchModelInterface;

/**
 * A class creating and keeping Models interfaces and client
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ModelFactory
{
   private ProfileModelInterface profileModel;
   private LogInModelInterface logInModel;
   private SearchModelInterface searchModel;
   private AdminModelInterface adminModel;
   private ChatModelInterface chatModel;
   private ClientInterface clientNetworking = new SocketClient();

   /**
    * returns Model based on enum ModelType
    * 
    * @return Model
    */
   public Object getDataModel(ModelType modelType)
   {
      Object model = new Object();
      switch (modelType)
      {
         case LOGIN:
         {
            if (logInModel == null)
            {
               logInModel = new LogInModel(clientNetworking);
            }
            model = logInModel;
            break;
         }
         case SEARCH:
         {
            if (searchModel == null)
            {
               searchModel = new SearchModel(clientNetworking);
            }
            model = searchModel;
            break;
         }
         case PROFILE:
         {
            if (profileModel == null)
            {
               profileModel = new ProfileModel(clientNetworking);
            }
            model = profileModel;
            break;
         }
         case ADMIN:
         {
            if (adminModel == null)
            {
               adminModel = new AdminModel(clientNetworking);
            }
            model = adminModel;
            break;
         }
         case CHAT:
         {
            if (chatModel == null)
            {
               chatModel = new ChatModel(clientNetworking);
            }
            model = chatModel;
            break;
         }

         default:
         {
            System.out.println("such a model does not exist");
         }
      }
      return model;
   }

   /**
    * specify ModelType
    */
   public enum ModelType
   {
      LOGIN, SEARCH, PROFILE, ADMIN, CHAT
   }

}
