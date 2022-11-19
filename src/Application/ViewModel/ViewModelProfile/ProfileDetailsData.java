package Application.ViewModel.ViewModelProfile;

import java.time.LocalDate;

/**
 * the class that handles profile details
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class ProfileDetailsData
{
   public ProfileDetailsData(String userName, String password, String email,
         boolean isPremiumUser, LocalDate dateOfBirth, boolean male,
         boolean female, boolean soccer, boolean dancing, boolean movies,
         boolean swimming, boolean running, boolean reading,
         boolean isUserNameTaken)
   {
      this.userName = userName;
      this.password = password;
      this.email = email;
      this.dateOfBirth = dateOfBirth;
      this.isPremiumUser = isPremiumUser;
      this.male = male;
      this.female = female;
      this.soccer = soccer;
      this.dancing = dancing;
      this.swimming = swimming;
      this.running = running;
      this.reading = reading;
      this.movies = movies;
      this.isUserNameTaken = isUserNameTaken;

   }

   private String userName;
   private String password;
   private boolean male;
   private boolean female;
   private String email;
   private boolean isPremiumUser;
   private LocalDate dateOfBirth;
   private boolean soccer;
   private boolean dancing;
   private boolean movies;
   private boolean swimming;
   private boolean reading;
   private boolean running;
   private boolean isUserNameTaken = false;

   /**
    * Method return the value of the field userName
    */

   public String getUserName()
   {
      return userName;
   }

   /**
    * Method set the value of the field userName
    * 
    * @param userName
    */

   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   /**
    * Method return the value of the field password
    */

   public String getPassword()
   {
      return password;
   }

   /**
    * Method set the value of the field password
    * 
    * @param password
    */

   public void setPassword(String password)
   {
      this.password = password;
   }

   /**
    * Method return the value of the field male
    */

   public boolean isMale()
   {
      return male;
   }

   /**
    * Method set the value of the field male
    * 
    * @param male
    */

   public void setMale(boolean male)
   {
      this.male = male;
   }

   /**
    * Method return the value of the field female
    */

   public boolean isFemale()
   {
      return female;
   }

   /**
    * Method set the value of the field female
    * 
    * @param female
    */

   public void setFemale(boolean female)
   {
      this.female = female;
   }

   /**
    * Method return the value of the field email
    */

   public String getEmail()
   {
      return email;
   }

   /**
    * Method set the value of the field email
    * 
    * @param email
    */

   public void setEmail(String email)
   {
      this.email = email;
   }

   /**
    * Method return the value of the field isPremiumUser
    */

   public boolean isPremiumUser()
   {
      return isPremiumUser;
   }

   /**
    * Method set the value of the field isPremiumUser
    * 
    * @param isPremiumUser
    */

   public void setPremiumUser(boolean isPremiumUser)
   {
      this.isPremiumUser = isPremiumUser;
   }

   /**
    * Method return the value of the field getDateOfBirth
    */

   public LocalDate getDateOfBirth()
   {
      return dateOfBirth;
   }

   /**
    * Method set the value of the field dateOfBirth
    * 
    * @param datOfBirth
    */

   public void setDateOfBirth(LocalDate dateOfBirth)
   {
      this.dateOfBirth = dateOfBirth;
   }

   /**
    * Method return the value of the field soccer
    */

   public boolean isSoccer()
   {
      return soccer;
   }

   /**
    * Method set the value of the field soccer
    * 
    * @param soccer
    */

   public void setSoccer(boolean soccer)
   {
      this.soccer = soccer;
   }

   /**
    * Method return the value of the field dancing
    */

   public boolean isDancing()
   {
      return dancing;
   }

   /**
    * Method set the value of the field dancing
    * 
    * @param dancing
    */

   public void setDancing(boolean dancing)
   {
      this.dancing = dancing;
   }

   /**
    * Method return the value of the field movie
    */

   public boolean isMovies()
   {
      return movies;
   }

   /**
    * Method set the value of the field movies
    * 
    * @param movies
    */

   public void setMovies(boolean movies)
   {
      this.movies = movies;
   }

   /**
    * Method return the value of the field swimming
    */

   public boolean isSwimming()
   {
      return swimming;
   }

   /**
    * Method set the value of the field swimming
    * 
    * @param swimming
    */

   public void setSwimming(boolean swimming)
   {
      this.swimming = swimming;
   }

   /**
    * Method return the value of the field reading
    */

   public boolean isReading()
   {
      return reading;
   }

   /**
    * Method set the value of the field reading
    * 
    * @param reading
    */

   public void setReading(boolean reading)
   {
      this.reading = reading;
   }

   /**
    * Method return the value of the field running
    */

   public boolean isRunning()
   {
      return running;
   }

   /**
    * Method set the value of the field running
    * 
    * @param running
    */

   public void setRunning(boolean running)
   {
      this.running = running;
   }

   /**
    * Method return the value of the field isUserNameTaken
    */

   public boolean isUserNameTaken()
   {
      return isUserNameTaken;
   }

   /**
    * Method set the value of the field isUserNameTaken
    * 
    * @param isUserNaemTaken
    */

   public void setUserNameTake(boolean isUserNameTaken)
   {
      this.isUserNameTaken = isUserNameTaken;
   }

}
