package Application.View.ViewAdmin;


import Application.ViewModel.ViewModelAdmin.AdminWindowViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

/**
 * A class controlling FXML
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class AdminWindowControler
{

   private AdminWindowViewModel adminWindowViewModel;
   @FXML
   private Label selectSearchCriteriaLabel;
   @FXML
   private Label sexLabel;
   @FXML
   private CheckBox male;
   @FXML
   private CheckBox female;
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
   private Button search;
   @FXML
   private Button blockDetail;
   @FXML
   private Button blockProfile;
   @FXML
   private Button unblockDetail;
   @FXML
   private Button unblockProfile;
   @FXML
   private Button deleteProfile;
   @FXML
   private Button exit;

   /**
    * initialize adminWindowViewModel
    * 
    * @param AdminWindowViewModel
    */
   public void init(AdminWindowViewModel adminWindowViewModel)
   {
      this.adminWindowViewModel = adminWindowViewModel;
   }

   public void exitApplication(ActionEvent actionEvent)
   {
      System.exit(0);
   }
}
