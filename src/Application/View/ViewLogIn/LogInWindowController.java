package Application.View.ViewLogIn;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import Application.ViewModel.ViewModelLogIn.LogInWindowViewModel;
import TransferClientServerObject.MessageLogIn;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 * A class controlling FXML
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class LogInWindowController
{
   @FXML
   private Label goToRegisterNewProfileLabel;
   @FXML
   private Button goToRegisterNewProfile;
   @FXML
   private Label userNameLabel;
   @FXML
   private TextField userName = new TextField();
   @FXML
   private Label passwordLabel;
   @FXML
   private PasswordField password = new PasswordField();
   @FXML
   private Button logIn;
   @FXML
   private Button exit;
   private LogInWindowViewModel logInWindowViewModel;
   private PropertyChangeSupport changeSupport;
   private PropertyChangeListener logInOKlistener;
   private PropertyChangeListener logInNOKlistener;

   /**
    * assign logInWindowViewModel and define itself as observable object
    * 
    * @param logInWindowViewModel
    */
   public void init(LogInWindowViewModel logInWindowViewModel)
   {
      this.changeSupport = new PropertyChangeSupport(this);
      this.logInWindowViewModel = logInWindowViewModel;
      setLogInWindowViewModelListeners();
      addLogInWindowViewModelListeners();
   }

   /**
    * sends userName and password from GUI fields to logInWindowViewModel
    * 
    * @param actionEvent,reacts
    *           to LogIn button
    */
   public void logIn(ActionEvent actionEvent)
   {

      logInWindowViewModel.logIn(userName.getText(), password.getText());
   }

   /**
    * adds listeners on THIS(for above layer class: ViewHandler)
    * 
    * @param String,PropertyChangeListener
    */
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);
   }

   /**
    * remove listeners (for below layer class: LogInWindowViewModel)
    */
   public void removeListener()
   {
      logInWindowViewModel.removeListener("logInOK", logInOKlistener);
      logInWindowViewModel.removeListener("logInNOK", logInNOKlistener);
   }

   /**
    * fires property(for above layer class: ViewHandler)
    * 
    * @param reacts
    *           to createNewProfile button
    */
   public void createNewProfile(ActionEvent actionEvent)
   {
      removeListener();
      changeSupport.firePropertyChange("openNewProfileGUI", null, true);
   }

   /**
    * fires PropertyChange for listeners(above layer class: ViewHandler)
    * 
    * @param reacts
    *           on PropertyChangeEvent
    */
   private void switchToProfile(PropertyChangeEvent evt)
   {
      removeListener();
      MessageLogIn message = (MessageLogIn) evt.getNewValue();
      changeSupport.firePropertyChange("logInOKOpenProfileGUI", null,
            new LogInData(message.returnUserName(),
                  message.returnTypeOfUser()));
   }

   /**
    * shows validation if userName or password is incorrect
    * 
    * @param reacts
    *           on PropertyChangeEvent
    */
   private void showLogInNoSuccessValidation(PropertyChangeEvent evt)
   {
      Alert alert1 = new Alert(AlertType.ERROR,
            "UserName or Password is incorrect", ButtonType.YES);
      alert1.setTitle("Error");
      alert1.setHeaderText(null);
      alert1.showAndWait();
   }

   /**
    * adds listeners on logInWindowViewModel
    */
   private void addLogInWindowViewModelListeners()
   {
      logInWindowViewModel.addPropertyChangeListener("logInOK",
            logInOKlistener);
      logInWindowViewModel.addPropertyChangeListener("logInNOK",
            logInNOKlistener);
   }

   /**
    * define listeners reactions
    */
   private void setLogInWindowViewModelListeners()
   {
      this.logInOKlistener = (evt) -> switchToProfile(evt);
      this.logInNOKlistener = (evt) -> showLogInNoSuccessValidation(evt);
   }

   /**
    * closes application
    * 
    * @param actionEvent,
    *           click on ExitApplication button
    */
   public void exitApplication(ActionEvent actionEvent)
   {
      System.exit(0);
   }
   
}
