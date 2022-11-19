package Application.View.ViewSearch;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import Application.ViewModel.ViewModelSearch.SearchWindowViewModel;
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
public class SearchWindowControler
{
   private PropertyChangeSupport changeSupport;
   private SearchWindowViewModel searchWindowViewModel;
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
   private Button goToProfile;
   @FXML
   private Button goToChat;
   @FXML
   private Button exit;

   /**
    * initialize searchWindowViewModel
    * 
    * @param SearchWindowViewModel
    */
   public void init(SearchWindowViewModel searchWindowViewModel)
   {
      this.searchWindowViewModel = searchWindowViewModel;
      this.changeSupport = new PropertyChangeSupport(this);
   }

   public void exitApplication(ActionEvent actionEvent)
   {
      System.exit(0);

   }

   /**
    * opens the GUI Window for serching by user name
    * 
    * @param actionEvent
    *           (press Submit button)
    */

   public void goToProfile(ActionEvent actionEvent)
   {
      changeSupport.firePropertyChange("openProfile", null,
            searchWindowViewModel.getUserName());
   }

   /**
    * opens the GUI Window for chat
    * 
    * @param actionEvent
    *           (press chat button)
    */

   public void goToChat(ActionEvent actionEvent)
   {
      System.out.print("volam");
      changeSupport.firePropertyChange("chat", null,
            searchWindowViewModel.getUserName());
   }

   /**
    * adds listeners(above layer class: ViewHandler)
    * 
    * @param String,PropertyChangeListener
    */
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);

   }
}
