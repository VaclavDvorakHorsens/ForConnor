package Application.ViewModel;

import Application.Model.ModelFactory;
import Application.Model.ModelFactory.ModelType;
import Application.Model.ModelAdmin.AdminModelInterface;
import Application.Model.ModelChat.ChatModelInterface;
import Application.Model.ModelLogIn.LogInModelInterface;
import Application.Model.ModelProfile.ProfileModelInterface;
import Application.Model.ModelSearch.SearchModelInterface;
import Application.ViewModel.ViewModelAdmin.AdminWindowViewModel;
import Application.ViewModel.ViewModelChat.ChatWindowViewModel;
import Application.ViewModel.ViewModelLogIn.LogInWindowViewModel;
import Application.ViewModel.ViewModelProfile.ProfileWindowViewModel;
import Application.ViewModel.ViewModelSearch.SearchWindowViewModel;

/**
 * A class creating and containing WindowViewModels.
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ViewModelFactory
{
   private LogInWindowViewModel logInWindowViewModel;
   private SearchWindowViewModel searchWindowViewModel;
   private ProfileWindowViewModel profileWindowViewModel;
   private AdminWindowViewModel adminWindowViewModel;
   private ChatWindowViewModel chatWindowViewModel;

   /**
    * constructor accepting ModelFactory, creating WindowViewModels
    */
   public ViewModelFactory(ModelFactory modelFactory)
   {
      logInWindowViewModel = new LogInWindowViewModel(
            (LogInModelInterface) modelFactory.getDataModel(ModelType.LOGIN));
      searchWindowViewModel = new SearchWindowViewModel(
            (SearchModelInterface) modelFactory.getDataModel(ModelType.SEARCH));
      profileWindowViewModel = new ProfileWindowViewModel(
            (ProfileModelInterface) modelFactory
                  .getDataModel(ModelType.PROFILE));
      adminWindowViewModel = new AdminWindowViewModel(
            (AdminModelInterface) modelFactory.getDataModel(ModelType.ADMIN),
            (SearchModelInterface) modelFactory.getDataModel(ModelType.SEARCH),
            (ProfileModelInterface) modelFactory
                  .getDataModel(ModelType.PROFILE));
      chatWindowViewModel = new ChatWindowViewModel(
            (ChatModelInterface) modelFactory.getDataModel(ModelType.CHAT));
   }

   /**
    * returns LogInWindowViewModel
    * 
    * @return logInWindowViewModel
    */
   public LogInWindowViewModel getLogInWindowViewModel()
   {
      return logInWindowViewModel;
   }

   /**
    * returns SearchWindowViewModel
    * 
    * @return searchWindowViewModel
    */
   public SearchWindowViewModel getSearchWindowViewModel(String userName)
   {
      searchWindowViewModel.setUserName(userName);
      return searchWindowViewModel;
   }

   /**
    * returns ProfileWindowViewModel
    * 
    * @return profileWindowViewModel
    */
   public ProfileWindowViewModel getProfileWindowViewModel(String userName)
   {
      profileWindowViewModel.setUserName(userName);
      return profileWindowViewModel;
   }

   /**
    * returns AdminWindowViewModel
    * 
    * @return adminWindowViewModel
    */
   public AdminWindowViewModel getAdminWindowViewModel()
   {
      return adminWindowViewModel;
   }

   /**
    * returns ChatWindowViewModel
    * 
    * @return chatWindowViewModel
    */

   public ChatWindowViewModel getChatWindowViewModel(String userName)
   {
      chatWindowViewModel.setUserName(userName);
      return chatWindowViewModel;
   }

}
