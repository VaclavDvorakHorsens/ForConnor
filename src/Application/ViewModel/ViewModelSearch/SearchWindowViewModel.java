package Application.ViewModel.ViewModelSearch;

import Application.Model.ModelSearch.SearchModelInterface;

/**
 * A class implementing middle layer between Controlling class and SearchModel
 * on client side
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class SearchWindowViewModel
{

   private SearchModelInterface searchModel;
   private String userName;

   /**
    * constructor accepting searchModel
    * 
    * @param searchModel
    */

   public SearchWindowViewModel(SearchModelInterface searchModel)
   {
      this.searchModel = searchModel;
   }

   /**
    * method wich set userName
    * 
    * @param userName
    */

   public void setUserName(String userName)
   {
      this.userName = userName;
   }

   /**
    * method wich returns userName
    */

   public String getUserName()
   {
      return userName;
   }

}
