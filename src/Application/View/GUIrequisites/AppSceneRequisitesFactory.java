package Application.View.GUIrequisites;

import java.io.IOException;

import Application.View.ViewAdmin.AdminWindowControler;
import Application.View.ViewChat.ChatWindowController;
import Application.View.ViewLogIn.LogInWindowController;
import Application.View.ViewProfile.ProfileWindowControler;
import Application.View.ViewSearch.SearchWindowControler;
import ClientServerLog.SystemLog;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * A class creating Scene requisities
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class AppSceneRequisitesFactory
{
   private SystemLog systemLog;
   private FXMLLoader loader;
   private Parent root;
   private Object controller;
   private String fxmlLocation;

   /**
    * constructor,get systemLog
    */
   public AppSceneRequisitesFactory()
   {
      this.systemLog = SystemLog.getInstance();
   }

   /**
    * creates loader and sets fxmlLocation
    * @param guiName
    */
   public void setScene(String guiName)
   {
      root = null;
      switch (guiName)
      {
         case "logIn":
            this.loader = new FXMLLoader();
            this.fxmlLocation = "../ViewLogIn/LogInGUI.fxml";
            break;
         case "search":
            this.loader = new FXMLLoader();
            this.fxmlLocation = "../ViewSearch/SearchGUI.fxml";
            break;
         case "profile":
            this.loader = new FXMLLoader();
            this.fxmlLocation = "../ViewProfile/ProfileGUI.fxml";
            break;
         case "admin":
            this.loader = new FXMLLoader();
            this.fxmlLocation = "../ViewAdmin/AdminGUI.fxml";
            break;
         case "chat":
            this.loader = new FXMLLoader();
            this.fxmlLocation = "../ViewChat/ChatGUI.fxml";
            break;
         default:
            System.out.println("no such FXML");
      }
      setFXMLLocation();
      setController(guiName);
   }

   /**
    * returns controller for Scene
    * 
    * @return controller
    */
   public Object returnController()
   {
      return controller;
   }

   /**
    * returns root for Scene
    * 
    * @return root
    */
   public Object returnRoot()
   {
      return root;
   }

   /**
    * creates scene based on root and return it
    * 
    * @return Scene scene
    */
   public Scene returnScene()
   {
      Scene scene = new Scene(root);
      return scene;

   }

   /**
    * sets loader based on FXML location and sets root based on loader
    */
   private void setFXMLLocation()
   {
      loader.setLocation(getClass().getResource(fxmlLocation));
      try
      {
         root = loader.load();
      }
      catch (IOException e)
      {
         systemLog.log(e.getMessage());
      }
   }

   /**
    * creates specific controller based on guiName
    * 
    * @param guiName,
    *           which decides what controller is supposed to be created
    */
   private void setController(String guiName)
   {
      if (guiName.equals("logIn"))
      {
         LogInWindowController logInWindowController = loader.getController();
         this.controller = logInWindowController;
      }
      else if (guiName.equals("search"))
      {
         SearchWindowControler searchWindowController = loader.getController();
         this.controller = searchWindowController;
      }
      else if (guiName.equals("profile"))
      {
         ProfileWindowControler profileWindowController = loader
               .getController();
         this.controller = profileWindowController;
      }
      else if (guiName.equals("admin"))
      {
         AdminWindowControler adminWindowController = loader.getController();
         this.controller = adminWindowController;
      }
      else if (guiName.equals("chat"))
      {
         ChatWindowController chatWindowController = loader.getController();
         this.controller = chatWindowController;
      }
   }

}
