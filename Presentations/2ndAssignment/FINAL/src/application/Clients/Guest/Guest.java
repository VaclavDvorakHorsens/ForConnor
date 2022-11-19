package application.Clients.Guest;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;

import application.BurgerAndHisArrayList.Burger;
import application.Server.ServerRemoteObjects.RemoteServerBounderQueue;

public class Guest implements GuestInterface,Runnable
{
   private String guestName;
   private RemoteServerBounderQueue remoteObject;
   private int counterOfEatenBurgers;
   private boolean shallGuestEatBurgers;
   
   
 //constructor, receives serverObject and calls addYourselfToTheServer()
   public Guest() throws RemoteException, NotBoundException
   {
      shallGuestEatBurgers=true;
      counterOfEatenBurgers=0;
      
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
   
   //method is called by server  
   @Override
   public void DisconnectFromServer() throws RemoteException
   {
      shallGuestEatBurgers=false; 
      System.out.println("guest name: "+guestName+" is going home");
      
   }
   
 //sends THIS to server
   @Override
   public void addYourSelfToServerList() throws RemoteException
   {
      remoteObject.addClientToServerList(this);
   }
   
   
 //keeps running and calling getBurger() till shallGuestEatBurgers==true
   public void run()
   {
         try
         {
            while(shallGuestEatBurgers==true)
            {
            getBurger();  
            }
         }
         catch (Exception e)
         {
            e.printStackTrace();
         }  
   }
   
 //call getBurger on serverObject and then wait randomTime and raise counterOfEaten burgers
   @Override
   public void getBurger() throws RemoteException, InterruptedException
   {
 
      Random random=new Random();
      int randomWaitingTime=random.nextInt(10000)+5000;
      Thread.sleep(randomWaitingTime);
      Burger burger=(Burger) remoteObject.getBurger(0);
      counterOfEatenBurgers++;
      System.out.println("guest name: "+guestName+" has just eaten his "+counterOfEatenBurgers+ "burger. It was burger:"+burger.getName());
   }
   
   @Override
   public void setGuestName(String guestName) throws RemoteException
   {
      this.guestName=guestName;
      System.out.println("guest:"+guestName+"enters burgerBar");
   }
   
   
   
}
