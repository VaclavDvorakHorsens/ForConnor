package application.Clients.Chef;

import java.lang.reflect.InvocationTargetException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import application.BurgerAndHisArrayList.Burger;
import application.RecipeRelatedStuff.RecepeProxy;
import application.RecipeRelatedStuff.RecipeProvider;
import application.Server.ServerRemoteObjects.RemoteServerBounderQueue;

public class Chef implements ChefClientInterface, Runnable
{
   private String chefName;
   private RemoteServerBounderQueue remoteObject;
   private boolean shallChefMakeBurgers;
   private RecipeProvider recipeProxy;
   
   
   //constructor, receives serverObject and calls addYourselfToServerList()
   public Chef() throws NotBoundException, RemoteException
   {
      this.recipeProxy=new RecepeProxy();
      this.shallChefMakeBurgers=true;
      UnicastRemoteObject.exportObject(this,0);
      Registry registry=LocateRegistry.getRegistry("localhost",1099);
      try
      {
         remoteObject=(RemoteServerBounderQueue) registry.lookup("remoteObject");  
      }
      catch(RemoteException e)
      {
      e.printStackTrace();   
      }
      
      addYourSelfToServerList();
   }
   
   
   //sends THIS to server
   public void addYourSelfToServerList() throws RemoteException
   {
      remoteObject.addClientToServerList(this);
   }
   
   
   
 //keeps running and calling addBurger() till shallChefMakeBurger==true
   
   public void run()
   {
         try
         {
            while(shallChefMakeBurgers==true)
            {
            addBurger();
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }     
   }
   
 //call getBurger on RecipeProvider(proxy), which has 2 methods how to create burger, 
 // so they are called randomly with random index/id and with random time delay
 //once serverObject delivers burger, it is added then to serverObject 
   @Override   
   public void addBurger() throws Exception
   {
    
         Random random=new Random();
         int randomWaitingTime=random.nextInt(5000)+5000;
         Thread.sleep(randomWaitingTime);
         String[] recipeNames= {"Hamburger","Cheese burger","Veggie burger"};
         Burger burger=null;
         
         int randomMethod=chooseRandomGetRecipeBurgerMethod();
         
         if(randomMethod==1)
            {
            System.out.println("chef: "+chefName+" cookes based on burger index");
            burger=recipeProxy.getRecipeById(Integer.toString(chooseRandomBurgerToCook())).createBurger();
            }
         else if(randomMethod==2)
         {
            int randomBurger=chooseRandomBurgerToCook()-1;
            System.out.println("chef: "+chefName+" cookes based on burger name");
            burger=recipeProxy.getRecipeByName(recipeNames[randomBurger]).createBurger();
             
         }
         remoteObject.addBurger(burger);
         System.out.println("chef name: "+chefName+" made a burger: "+burger.getName());
  
   }
   
   //is called by addBurger()
   private int chooseRandomGetRecipeBurgerMethod()
   {
      Random randomRecipeNumber=new Random();
      int randomAddBurgerMethod=randomRecipeNumber.nextInt(2)+1;
      return randomAddBurgerMethod;
   }
   
   //is called by addBurger()
   private int chooseRandomBurgerToCook()
   {
      Random randomRecipeNumber=new Random();
      int randomBurger=randomRecipeNumber.nextInt(3)+1;
      return randomBurger; 
   }


  //method is called by server
   @Override
   public void DisconnectFromServer() throws RemoteException,InvocationTargetException
   {
      shallChefMakeBurgers=false;
      System.out.println("chef name: "+chefName+" is going home");  
   }
   
   @Override
   public void setChefName(String chefName) throws RemoteException
   {
      this.chefName=chefName;
      System.out.println("chef:"+chefName+" is starting cooking burgers");
   }
   
}
