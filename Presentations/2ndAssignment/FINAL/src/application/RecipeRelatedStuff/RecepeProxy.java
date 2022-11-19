package application.RecipeRelatedStuff;

import java.util.ArrayList;

public class RecepeProxy implements RecipeProvider
{
private ArrayList<Recipe> cacheRecipeList;
private RecipeReader recipeReader;


//constructor, set recipe file to read recipes from recipes.txt
//and recipeList as a cache
public RecepeProxy()
{
   this.cacheRecipeList=new ArrayList<Recipe>();
   recipeReader=new RecipeReader("recipes.txt");
}


//get recipe by id from cache or, if its not there, then from recipes.txt
//and it adds it to the cache
   
   public Recipe getRecipeById(String id) throws Exception
   {
      Recipe tempRecipe=new Recipe("","","");
      int index=Integer.parseInt(id);
      boolean isRecipeInCache=false;
      
         for(int i=0;i<cacheRecipeList.size();i++)
         {  
           
           if(cacheRecipeList.get(i).getId().equals(id))
               {
               tempRecipe=cacheRecipeList.get(i);
               System.out.println("recipe for burger:"+cacheRecipeList.get(i).getName()+" no recipe needed");
               isRecipeInCache=true;
               break;
               }
           
         
          } 
         if(isRecipeInCache==false)
         {
            tempRecipe=recipeReader.getRecipeById(id);
            cacheRecipeList.add(tempRecipe); 
            System.out.println("recipe for burger:"+tempRecipe.getName()+" had to be taken from RecipeProvider");
          
         }
         return tempRecipe; 
       } 
      
    
     
    
   

     


   
 //get recipe by name from cache or, if its not there, then from recipes.txt
 //and it adds it to the cache
  
   public Recipe getRecipeByName(String name) throws Exception
   {
      Recipe tempRecipe=new Recipe("","","");
      boolean isRecipeInCache=false;
      
         for(int i=0;i<cacheRecipeList.size();i++)
         {  
           
           if(cacheRecipeList.get(i).getName().equals(name))
               {
               tempRecipe=cacheRecipeList.get(i);
               System.out.println("recipe for burger:"+cacheRecipeList.get(i).getName()+" no recipe needed");
               isRecipeInCache=true;
               break;
               }
           
         
          } 
         if(isRecipeInCache==false)
         {
            tempRecipe=recipeReader.getRecipeByName(name);
            cacheRecipeList.add(tempRecipe); 
            System.out.println("recipe for burger:"+tempRecipe.getName()+" had to be taken from RecipeProvider");
          
         }
         return tempRecipe; 
       } 
      

   
  //i am not really calling this method  
   
   public void updateRecipe(Recipe recipe) throws Exception
   {
      recipeReader.updateRecipe(recipe); 
      
   }

}
