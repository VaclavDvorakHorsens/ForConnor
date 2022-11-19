package Application.ViewModel.ViewModelLogIn;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import Application.Model.ModelLogIn.LogInModelInterface;
import TransferClientServerObject.MessageLogIn;
import javafx.application.Platform;

/**
 * A class implementing middle layer between Controlling class and logInModel on
 * client side
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class LogInWindowViewModel
{
   private PropertyChangeSupport changeSupport;
   private LogInModelInterface logInModel;


   /**
    * constructor accepting LogInModelInterface and initializing
    * PropertyChangeSupport,LogInModelInterface,boolean adds listener on
    * logInModel
    */
   public LogInWindowViewModel(LogInModelInterface logInModel)
   {
      this.logInModel = logInModel;
      this.changeSupport = new PropertyChangeSupport(this);
      addProfileModelListener();
   }

   /**
    * forwards logIn parameters:userName,password to logInModel
    * 
    * @param String
    *           userName,password
    */
   public void logIn(String userName, String password)
   {
      logInModel.logIn(userName, password);
   }

   /**
    * adds listeners(above layer class: LogInWindowController)
    * 
    * @param String,PropertyChangeListener
    */
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);
   }

   /**
    * remove listeners that listens on THIS
    */
   public void removeListener(String name,
         PropertyChangeListener logInOKlistener)
   {
      changeSupport.removePropertyChangeListener(name, logInOKlistener);
   }

   /**
    * reacts on PropertyChangeEvent(new response logInMessage from server) by
    * assigning incomeing value to variable and calling isLogInOk();
    * 
    * @param evt
    */
   private void returnLogInOK(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         MessageLogIn returnMessage = (MessageLogIn) evt.getNewValue();
         changeSupport.firePropertyChange("logInOK", null, returnMessage);
      });
   }

   /**
    * deals with result of trying to login and fires property for above layer
    * class:LogInWindowController
    */
   private void returnLogInNOK(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         changeSupport.firePropertyChange("logInNOK", null, true);
      });
   }

   /**
    * adds listeners on logInModel
    */
   private void addProfileModelListener()
   {
      logInModel.addPropertyChangeListener("logInOK",
            evt -> returnLogInOK(evt));
      logInModel.addPropertyChangeListener("logInNOK",
            evt -> returnLogInNOK(evt));
   }

}