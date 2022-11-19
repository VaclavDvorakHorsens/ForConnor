package Application.View.GUIrequisites;

import java.beans.PropertyChangeEvent;
import Application.Model.ModelFactory;
import Application.View.ViewAdmin.AdminWindowControler;
import Application.View.ViewChat.ChatWindowController;
import Application.View.ViewLogIn.LogInData;
import Application.View.ViewLogIn.LogInWindowController;
import Application.View.ViewProfile.ProfileWindowControler;
import Application.View.ViewSearch.SearchWindowControler;
import Application.ViewModel.ViewModelFactory;
import javafx.stage.Stage;

/**
 * A class handeling opening/closing GUI
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ViewHandler
{
   private Stage stage;
   private ViewModelFactory viewModelFactory;
   private AppSceneRequisitesFactory appSceneRequisitesFactory;

   /**
    * constructor accepting primaryStage and initializing viewModelFactory with
    * modelFactory
    */
   public ViewHandler(Stage stage)
   {
      this.appSceneRequisitesFactory = new AppSceneRequisitesFactory();
      this.stage = stage;
      this.viewModelFactory = new ViewModelFactory(new ModelFactory());
   }

   /**
    * opens specific GUI based on argument via AppSceneRequisitesFactory()
    * @param guiName
    */
   public void openGUI(String guiName, String userName)
   {
      appSceneRequisitesFactory.setScene(guiName);
      Object controller = appSceneRequisitesFactory.returnController();

      if (controller instanceof LogInWindowController)
      {
         setLogInController(controller);
      }

      else if (controller instanceof SearchWindowControler)
      {
         setSearchController(controller, userName);
      }

      else if (controller instanceof ProfileWindowControler)
      {
         setProfileController(controller, userName);
      }

      else if (controller instanceof AdminWindowControler)
      {
         setAdminController(controller);
      }

      else if (controller instanceof ChatWindowController)
      {
         setChatController(controller, userName);
      }

      stage.setScene(appSceneRequisitesFactory.returnScene());
      stage.show();
   }

   /**
    * initializes LogInWindowController and adds listeners on it that listen for
    * successful login
    * 
    * @param Object
    *           of type LogInWindowController
    */
   private void setLogInController(Object controller)
   {
      LogInWindowController logInWindowController = (LogInWindowController) controller;

      logInWindowController.init(viewModelFactory.getLogInWindowViewModel());
      logInWindowController.addPropertyChangeListener("logInOKOpenProfileGUI",
            evt -> {
               logInOk(evt);
            });
      logInWindowController.addPropertyChangeListener("openNewProfileGUI",
            evt -> {
               createNewProfile(evt);
            });

      stage.setTitle("LogInWindow");
   }

   /**
    * initializes SearchWindowController
    * 
    * @param Object
    *           of type SearchWindowController
    */
   private void setSearchController(Object controller, String userName)
   {
      SearchWindowControler searchWindowControler = (SearchWindowControler) controller;
      searchWindowControler
            .init(viewModelFactory.getSearchWindowViewModel(userName));
      searchWindowControler.addPropertyChangeListener("openProfile", evt -> {
         openProfile(evt);
      });
      searchWindowControler.addPropertyChangeListener("chat", evt -> {
         goToChat(evt);
         ;
      });
      stage.setTitle("SearchWindow");
   }

   /**
    * initializes ProfileWindowController
    * 
    * @param Object
    *           of type ProfileWindowController
    */
   private void setProfileController(Object controller, String userName)
   {
      ProfileWindowControler profileWindowControler = (ProfileWindowControler) controller;
      profileWindowControler
            .init(viewModelFactory.getProfileWindowViewModel(userName));
      profileWindowControler.addPropertyChangeListener("logIn", evt -> {
         goToLogIn(evt);
      });
      profileWindowControler.addPropertyChangeListener("chat", evt -> {
         goToChat(evt);
         ;
      });
      stage.setTitle("ProfileWindow");
   }

   private void setChatController(Object controller, String userName)
   {
      ChatWindowController chatWindowController = (ChatWindowController) controller;
      chatWindowController
            .init(viewModelFactory.getChatWindowViewModel(userName));
      chatWindowController.addPropertyChangeListener("profile", evt -> {
         openGUI("profile", (String) evt.getNewValue());
      });
      stage.setTitle("ChatWindow");
   }

   /**
    * initializes AdminWindowController
    * 
    * @param Object
    *           of type AdminWindowController
    */
   private void setAdminController(Object controller)
   {
      AdminWindowControler adminWindowControler = (AdminWindowControler) controller;
      adminWindowControler.init(viewModelFactory.getAdminWindowViewModel());
      stage.setTitle("AdminWindow");
   }

   /**
    * calls openGUI() for logIn based on typeOfUser
    * 
    * @param PropertyChangeEvent
    *           evt
    */
   private void logInOk(PropertyChangeEvent evt)
   {
      closeCurrentGUI();
      LogInData loginData = (LogInData) evt.getNewValue();
      String typeOfUser = loginData.getTypeOfUser();

      if (typeOfUser.equals("PremiumUser"))
      {
         openGUI("search", loginData.getUserName());
      }
      else if (typeOfUser.equals("BasicUser"))
      {
         openGUI("profile", loginData.getUserName());
      }
      else if (typeOfUser.equals("Admin"))
      {
         openGUI("admin", null);
      }
   }

   /**
    * closes current GUI
    */
   private void closeCurrentGUI()
   {
      stage.close();
   }

   /**
    * calls for openGUI() for profile
    * 
    * @param PropertyChangeEvent
    *           evt, listening for "newProfile" event from LogInWindowController
    *           class
    */
   private void createNewProfile(PropertyChangeEvent evt)
   {
      openGUI("profile", null);
   }

   private void openProfile(PropertyChangeEvent evt)
   {
      String userName = (String) evt.getNewValue();
      openGUI("profile", userName);
   }

   /**
    * calls for openGUI() for profile
    * 
    * @param PropertyChangeEvent
    *           evt, listening for "logIn" event from ProfileWindowController
    *           class
    */
   private void goToLogIn(PropertyChangeEvent evt)
   {
      openGUI("logIn", null);
   }

   /**
    * opens chat window for the user
    * 
    * @param PropertyChangeEvent
    *           evt,
    */

   private void goToChat(PropertyChangeEvent evt)
   {
      String userName = (String) evt.getNewValue();
      openGUI("chat", userName);
   }

}
