package Application.ViewModel.ViewModelAdmin;

import Application.Model.ModelAdmin.AdminModelInterface;
import Application.Model.ModelProfile.ProfileModelInterface;
import Application.Model.ModelSearch.SearchModelInterface;

/**
 * A class implementing middle layer between Controlling class and AdminModel on
 * client side
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public class AdminWindowViewModel
{

   private AdminModelInterface adminModel;
   private ProfileModelInterface profileModel;
   private SearchModelInterface searchModel;

   /**
    * constructor accepting AdminModelInterface, searchModel, profileModel
    * 
    * @param adminModel,
    *           profileModel, searchModel
    */

   public AdminWindowViewModel(AdminModelInterface adminModel,
         SearchModelInterface searchModel, ProfileModelInterface profileModel)
   {

      this.adminModel = adminModel;
      this.profileModel = profileModel;
      this.searchModel = searchModel;

   }

}
