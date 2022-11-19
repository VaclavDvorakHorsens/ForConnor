package Application.ViewModel.ViewModelProfile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;
import Application.Model.ModelProfile.ProfileModelInterface;
import TransferClientServerObject.MessageManageProfileDetails;
import TransferClientServerObject.hobbyType;
import javafx.application.Platform;

/**
 * A class serving as middle layer between Controlling class and profileModel on
 * client side
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class ProfileWindowViewModel
{
   private PropertyChangeSupport changeSupport;
   private ProfileModelInterface profileModel;

   /**
    * constructor initializing profileModel, adding property listeners on
    * profileModel
    */
   public ProfileWindowViewModel(ProfileModelInterface profileModel)
   {

      this.profileModel = profileModel;
      this.changeSupport = new PropertyChangeSupport(this);

      addProfileModelListeners();
   }

   /**
    * forwards parameters for registering new user to profileModel
    * 
    * @param String:
    *           userName, password,sex,email,dateofBirth
    *           boolean:isPremiumUser,isUserNameAlreadyTaken
    *           ArrayList<hobbyType> listOfHobbies
    */
   public void registerProfile(ProfileDetailsData profileDetailsData)

   {
      if (profileModel.checkIfAllMandatoryFieldsFilled(profileDetailsData)==true)
         
      {
         profileModel.registerProfile(profileDetailsData);
      }
      else
      {
         changeSupport.firePropertyChange("mandatoryFieldsFilledIncorrect",
               null, true);
      }

   }

   /**
    * sends parameters for registering new user to profileModel
    * 
    * @return String userName
    */
   public void returnProfileDetails()
   {
      profileModel.returnProfileDetails();
   }

   /**
    * adds listeners(for above layer class: ProfileWindowController)
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
   public void removeListener(String name, PropertyChangeListener listener)
   {
      changeSupport.removePropertyChangeListener(name, listener);
   }

   /**
    * gets profile details return message from profileModel
    * 
    * @param evt
    *           PropertyChangeEvent
    */
   private void getProfileDetailsMessageResponse(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {
         MessageManageProfileDetails returnMessage = (MessageManageProfileDetails) evt
               .getNewValue();

         returnProfileDetailsToProfileWindowController(returnMessage);

      });
   }

   
   /**
    * forwards userName to model
    * 
    * @param userName
    *           
    */
   public void setUserName(String userName)
   {
      profileModel.setUserName(userName);
   }

   
   /**
    * returns userName from model
    * 
    * @return userName
    *           
    */
   public String getUserName()
   {
      return profileModel.returnUserName();
   }
   
   
   
   /**
    * return the details selecter by the user
    * 
    * @param returnMessage
    */

   private void returnProfileDetailsToProfileWindowController(
         MessageManageProfileDetails returnMessage)
   {

      boolean soccer = false;
      boolean dancing = false;
      boolean movies = false;
      boolean swimming = false;
      boolean running = false;
      boolean reading = false;

      for (int i = 0; i < returnMessage.returnListOfHobbies().size(); i++)
      {
         if (returnMessage.returnListOfHobbies().get(i)
               .equals(hobbyType.SOCCER))
         {
            soccer = true;
         }
         if (returnMessage.returnListOfHobbies().get(i)
               .equals(hobbyType.DANCING))
         {
            dancing = true;
         }
         if (returnMessage.returnListOfHobbies().get(i)
               .equals(hobbyType.MOVIES))
         {
            movies = true;
         }
         if (returnMessage.returnListOfHobbies().get(i)
               .equals(hobbyType.SWIMMING))
         {
            swimming = true;
         }
         if (returnMessage.returnListOfHobbies().get(i)
               .equals(hobbyType.RUNNING))
         {
            running = true;
         }
         if (returnMessage.returnListOfHobbies().get(i)
               .equals(hobbyType.READING))
         {
            reading = true;
         }
      }

      ProfileDetailsData profileDetailsData = new ProfileDetailsData(
            returnMessage.returnUserName(), returnMessage.returnPassword(),
            returnMessage.returnEmail(),

            returnMessage.returnIsPremiumUser(),
            LocalDate.parse(returnMessage.returnDateOfBirth()),

            (returnMessage.returnSex().equals("M")),
            (returnMessage.returnSex().equals("F")), soccer, dancing, movies,
            swimming, running, reading, false);

      changeSupport.firePropertyChange("returnProfileDetails", null,
            profileDetailsData);

   }

   /**
    * fires properties for above layer Class ProfileWindowController
    */
   private void registerProfileOK(PropertyChangeEvent evt)
   {

      changeSupport.firePropertyChange("registerProfileOK", null, true);

   }

   /**
    * fires properties for above layer Class ProfileWindowController
    */
   private void returnRegistrationProfileNOK(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {

         changeSupport.firePropertyChange("registerProfileNOK", null, false);

      });
   }
   
   /**
    * adds listeners on profileModel
    */
   private void addProfileModelListeners()
   {
      profileModel.addPropertyChangeListener("registerProfileNOK",
            evt -> returnRegistrationProfileNOK(evt));
      profileModel.addPropertyChangeListener("registerProfileOK",
            evt -> registerProfileOK(evt));
      profileModel.addPropertyChangeListener("returnMessageProfileDetails",
            evt -> getProfileDetailsMessageResponse(evt));
   }

   


  

}
