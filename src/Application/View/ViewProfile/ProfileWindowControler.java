package Application.View.ViewProfile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import Application.ViewModel.ViewModelProfile.ProfileWindowViewModel;
import Application.ViewModel.ViewModelProfile.ProfileDetailsData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

/**
 * A class controlling FXML (GUI for Profile)
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ProfileWindowControler
{
   private PropertyChangeSupport changeSupport;
   private ProfileWindowViewModel profileWindowViewModel;
   @FXML
   private Label userNameLabel;
   @FXML
   private TextField userName;
   @FXML
   private Label passwordLabel;
   @FXML
   private TextField password;
   @FXML
   private Label emailLabel;
   @FXML
   private TextField email;
   @FXML
   private Label premiumUserLabel;
   @FXML
   private CheckBox isPremiumUser;
   @FXML
   private Label sexLabel;
   @FXML
   private RadioButton male;
   @FXML
   private RadioButton female;
   @FXML
   private ToggleGroup sexGroup;
   @FXML
   private Label dateOfBirthLabel;
   @FXML
   private DatePicker dateOfBirth;
   @FXML
   private Label hobbiesLabel;
   @FXML
   private CheckBox soccer;
   @FXML
   private CheckBox dancing;
   @FXML
   private CheckBox movies;
   @FXML
   private CheckBox swimming;
   @FXML
   private CheckBox running;
   @FXML
   private CheckBox reading;
   @FXML
   private Button submit;
   @FXML
   private Button goToChat;
   @FXML
   private Button goToSearch;
   @FXML
   private Button goToLogIn;
   @FXML
   private Button deleteProfile;
   @FXML
   private Button exit;

   private PropertyChangeListener registerProfileOKListener;
   private PropertyChangeListener registerProfileNOKListener;
   private PropertyChangeListener returnProfileDetailsListener;
   private PropertyChangeListener mandatoryFieldsFilledIncorrectListener;

   /**
    * assign profileWindowViewModel and define itself as observable object and
    * add listeners on profileWindowViewModel
    * 
    * @param profileWindowViewModel
    */
   public void init(ProfileWindowViewModel profileWindowViewModel)
   {
      this.changeSupport = new PropertyChangeSupport(this);
      this.profileWindowViewModel = profileWindowViewModel;
      setProfileWindowViewModelListeners();
      addProfileWindowViewModelListeners();

      if (profileWindowViewModel.getUserName() == null)
      {
         goToSearch.setVisible(false);
         goToChat.setVisible(false);
         deleteProfile.setVisible(false);
      }
      else
      {
         profileWindowViewModel.returnProfileDetails();
      }

   }

   /**
    * sends GUI fields (profile details) to below layer (ProfileWindowViewModel)
    * 
    * @param actionEvent
    *           (press Submit button)
    */
   public void submitProfile(ActionEvent actionEvent)
   {
      ProfileDetailsData profileDetailsData = new ProfileDetailsData(
            userName.getText(), password.getText(), email.getText(),
            isPremiumUser.isSelected(), dateOfBirth.getValue(),
            male.isSelected(), female.isSelected(), soccer.isSelected(),
            dancing.isSelected(), movies.isSelected(), swimming.isSelected(),
            running.isSelected(), reading.isSelected(), false);
      profileWindowViewModel.registerProfile(profileDetailsData);

   }

   /**
    * calls removeListener() and fires property for above listening
    * layer(ViewHandler)
    * 
    * @param actionEvent,GoToLogIn
    *           button
    */
   public void goToLogIn(ActionEvent actionEvent)
   {
      removeListener();
      changeSupport.firePropertyChange("logIn", null, true);
   }

   public void goToChat(ActionEvent actionEvent)
   {

      removeListener();
      changeSupport.firePropertyChange("chat", null, userName.getText());
   }

   /**
    * remove listeners (for below layer class: ProfileWindowViewModel)
    */
   public void removeListener()
   {
      profileWindowViewModel.removeListener("registerProfileOK",
            registerProfileOKListener);
      profileWindowViewModel.removeListener("registerProfileNOK",
            registerProfileNOKListener);
      profileWindowViewModel.removeListener("returnProfileDetails",
            returnProfileDetailsListener);
      profileWindowViewModel.removeListener("mandatoryFieldsFilledIncorrect",
            mandatoryFieldsFilledIncorrectListener);

   }

   /**
    * adds listeners on THIS(above layer class: ViewHandler)
    * 
    * @param String,PropertyChangeListener
    */
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);
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

   private void showFormIncorrectlyFilledValidation()
   {
      Alert alert1 = new Alert(AlertType.ERROR,
            "at least one of following issues has occured:"
                  + "1)All Fields except 'PremiumUser' have to be filled "
                  + "2)At least one hobby has to be filled "
                  + "3)email must contain @ sign ",
            ButtonType.YES);
      alert1.setTitle("Error");
      alert1.setHeaderText(null);
      alert1.showAndWait();
   }

   /**
    * reacts on registerProfileNOKListener listener and shows Registration
    * validation that UserName already exists
    * 
    * @param evt(PropertyChangeEvent)
    */
   private void showRegisterProfileNoSuccessValidation()
   {
      Alert alert1 = new Alert(AlertType.ERROR, "UserName already exists",
            ButtonType.YES);
      alert1.setTitle("Error");
      alert1.setHeaderText(null);
      alert1.showAndWait();
   }

   /**
    * reacts on registerProfileOKListener listener and shows Registration
    * success message
    * 
    * @param evt(PropertyChangeEvent)
    */
   private void showRegisterProfileSuccessMessage()
   {
      Alert alert1 = new Alert(AlertType.CONFIRMATION,
            "Registration was successfull", ButtonType.YES);
      alert1.setTitle("Info");
      alert1.setHeaderText(null);
      alert1.showAndWait();

      eraseRegistrationFormValuesAfterRegistration();
   }

   private void eraseRegistrationFormValuesAfterRegistration()
   {
      userName.setText("");
      password.setText("");
      email.setText("");
      isPremiumUser.setSelected(false);
      male.setSelected(false);
      female.setSelected(false);
      dateOfBirth.setValue(null);
      soccer.setSelected(false);
      dancing.setSelected(false);
      movies.setSelected(false);
      swimming.setSelected(false);
      running.setSelected(false);
      reading.setSelected(false);

   }

   /**
    * adds listeners on profileWindowViewModel
    */
   private void addProfileWindowViewModelListeners()
   {
      profileWindowViewModel.addPropertyChangeListener("registerProfileOK",
            registerProfileOKListener);
      profileWindowViewModel.addPropertyChangeListener("registerProfileNOK",
            registerProfileNOKListener);
      profileWindowViewModel.addPropertyChangeListener("returnProfileDetails",
            returnProfileDetailsListener);

      profileWindowViewModel.addPropertyChangeListener(
            "mandatoryFieldsFilledIncorrect",
            mandatoryFieldsFilledIncorrectListener);

   }

   /**
    * define listeners reactions, as a result profile field values are populated
    * in GUI
    */
   private void setProfileWindowViewModelListeners()
   {
      this.registerProfileOKListener = (
            evt) -> showRegisterProfileSuccessMessage();
      this.registerProfileNOKListener = (
            evt) -> showRegisterProfileNoSuccessValidation();
      this.returnProfileDetailsListener = (evt) -> setProfileFields(evt);
      this.mandatoryFieldsFilledIncorrectListener = (
            evt) -> showFormIncorrectlyFilledValidation();

   }

   private void setProfileFields(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         ProfileDetailsData profileDetailsData = (ProfileDetailsData) evt
               .getNewValue();
         userName.setText(profileDetailsData.getUserName());
         password.setText(profileDetailsData.getPassword());
         email.setText(profileDetailsData.getEmail());
         isPremiumUser.setSelected(profileDetailsData.isPremiumUser());
         male.setSelected(profileDetailsData.isMale());
         female.setSelected(profileDetailsData.isFemale());
         dateOfBirth.setValue(profileDetailsData.getDateOfBirth());
         soccer.setSelected(profileDetailsData.isSoccer());
         dancing.setSelected(profileDetailsData.isDancing());
         movies.setSelected(profileDetailsData.isMovies());
         swimming.setSelected(profileDetailsData.isSwimming());
         running.setSelected(profileDetailsData.isRunning());
         reading.setSelected(profileDetailsData.isReading());

      });
   }

}
