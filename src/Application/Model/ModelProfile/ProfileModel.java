package Application.Model.ModelProfile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import Application.ClientNetworking.ClientInterface;
import Application.ViewModel.ViewModelProfile.ProfileDetailsData;
import TransferClientServerObject.Message;
import TransferClientServerObject.MessageCreateProfile;
import TransferClientServerObject.MessageManageProfileDetails;
import TransferClientServerObject.hobbyType;
import javafx.application.Platform;

/**
 * A class serving as profile model
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class ProfileModel implements ProfileModelInterface
{

   private PropertyChangeSupport changeSupport;
   private ClientInterface client;
   private String userName;

   /**
    * constructor initializing client
    * 
    * @param client
    */
   public ProfileModel(ClientInterface client)
   {

      this.client = client;
      this.changeSupport = new PropertyChangeSupport(this);
      client.addPropertyChangeListener("returnMessageCreateProfile",
            evt -> isRegistrationOK(evt));
      client.addPropertyChangeListener("returnMessageProfileDetails",
            evt -> getProfileDetails(evt));
   }

   /**
    * creates Message and sends and calls sendMessageToClient(message)
    * 
    * @param Strings:userName,
    *           password, sex, email, dateOfBirth and boolean isPremiumUser and
    *           ArrayList<hobbyType> listOfHobbies
    */
   @Override
   public void registerProfile(ProfileDetailsData profileDetailsData)

   {
      sendMessageToClient(createRegisterProfileMessage(profileDetailsData));

   }

   /**
    * returns profile details selected by user name
    * 
    * @param userName
    */

   @Override
   public void returnProfileDetails(/* String userName */)
   {
      Message message = new MessageManageProfileDetails(
            Message.MessageType.PROFILEDETAILS, userName, "", "", "", false, "",
            new ArrayList<hobbyType>());
      sendMessageToClient(message);
   }

   /**
    * listens for client and if client receives Message, fires event for above
    * class(ProfileWindowViewModel)
    * 
    * @param evet
    *           PropertyChangeEvent
    */
   private void isRegistrationOK(PropertyChangeEvent evt)
   {

      Platform.runLater(() -> {

         MessageCreateProfile returnMessage = (MessageCreateProfile) evt
               .getNewValue();

         if (returnMessage.returnIsUserNameAlreadyTaken() == true)
         {
            changeSupport.firePropertyChange("registerProfileNOK", null,
                  returnMessage);
         }
         else if (returnMessage.returnIsUserNameAlreadyTaken() == false)
         {
            changeSupport.firePropertyChange("registerProfileOK", null,
                  returnMessage);
         }

      });
   }

   /**
    * returns profile details and fires an event
    * 
    * @param evt
    */

   private void getProfileDetails(PropertyChangeEvent evt)
   {
      Platform.runLater(() -> {

         MessageManageProfileDetails returnMessage = (MessageManageProfileDetails) evt
               .getNewValue();

         changeSupport.firePropertyChange("returnMessageProfileDetails", null,
               returnMessage);
      });
   }

   /**
    * sends Message to client
    * 
    * @param message
    *           Message
    */
   private void sendMessageToClient(Message message)
   {
      client.sendMessage(message);
   }

   /**
    * Add a property-change listener for a specific property - name
    * 
    * @param name
    *           String, listener PropertyChangeListener
    */

   @Override
   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener)
   {
      changeSupport.addPropertyChangeListener(name, listener);

   }

   /**
    * Creates a message with the profiles details
    * 
    * @param nprofileDetailsData
    */

   private Message createRegisterProfileMessage(
         ProfileDetailsData profileDetailsData)

   {
      String sex = "";
      ArrayList<hobbyType> listOfHobbies = new ArrayList<hobbyType>();
      if (profileDetailsData.isSoccer() == true)
      {
         listOfHobbies.add(hobbyType.SOCCER);
      }
      if (profileDetailsData.isDancing() == true)
      {
         listOfHobbies.add(hobbyType.DANCING);
      }
      if (profileDetailsData.isMovies() == true)
      {
         listOfHobbies.add(hobbyType.MOVIES);
      }
      if (profileDetailsData.isSwimming() == true)
      {
         listOfHobbies.add(hobbyType.SWIMMING);
      }
      if (profileDetailsData.isRunning() == true)
      {
         listOfHobbies.add(hobbyType.RUNNING);
      }
      if (profileDetailsData.isReading() == true)
      {
         listOfHobbies.add(hobbyType.READING);
      }

      if (profileDetailsData.isMale() == true)
      {
         sex = "M";
      }
      else
      {
         sex = "F";
      }

      Message message = new MessageCreateProfile(
            Message.MessageType.REGISTERPROFILE,
            profileDetailsData.getUserName(), profileDetailsData.getPassword(),
            sex, profileDetailsData.getEmail(),
            profileDetailsData.isPremiumUser(),
            profileDetailsData.getDateOfBirth().toString(), listOfHobbies,
            profileDetailsData.isUserNameTaken());
      return message;
   }

   /**
    * returns user name
    */

   @Override
   public String returnUserName()
   {
      return userName;
   }

   /**
    * set an user name
    * 
    * @param userName
    */

   @Override
   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   /**
    * Checks if all fields for the Profile fields are filled
    * 
    * @param ProfileDetailsData
    */

   @Override
   public boolean checkIfAllMandatoryFieldsFilled(
         ProfileDetailsData profileDetailsData)

   {
      if (profileDetailsData.getUserName().equals("")
            || profileDetailsData.getPassword().equals("")
            || (profileDetailsData.isMale() == false
                  && profileDetailsData.isFemale() == false)
            || (!profileDetailsData.getEmail().contains("@"))
            || profileDetailsData.getEmail().equals("")
            || profileDetailsData.getDateOfBirth() == null
            || (profileDetailsData.isSoccer() == false
                  && profileDetailsData.isDancing() == false
                  && profileDetailsData.isMovies() == false
                  && profileDetailsData.isSwimming() == false
                  && profileDetailsData.isRunning() == false
                  && profileDetailsData.isReading() == false))
      {
         return false;
      }
      return true;
   }

}
