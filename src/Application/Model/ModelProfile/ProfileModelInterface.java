package Application.Model.ModelProfile;

import java.beans.PropertyChangeListener;
import Application.ViewModel.ViewModelProfile.ProfileDetailsData;

/**
 * An interface for ProfileModel class
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public interface ProfileModelInterface
{

   void addPropertyChangeListener(String name, PropertyChangeListener listener);

   void returnProfileDetails();

   boolean checkIfAllMandatoryFieldsFilled(
         ProfileDetailsData profileDetailsData);

   void registerProfile(ProfileDetailsData profileDetailsData);

   String returnUserName();

   void setUserName(String userName);

}
