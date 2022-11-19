package application.Clients.ClientsMainMethods;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import application.Clients.Chef.Chef;
import application.Clients.Chef.ChefClientInterface;
import application.Clients.Guest.Guest;
import application.Clients.Guest.GuestInterface;

public class Chef_Main
{

   public static void main(String[] args) throws RemoteException, NotBoundException
   {
      ChefClientInterface chef1= new Chef();
      ChefClientInterface chef2= new Chef();
      chef1.setChefName("chef1");   
      chef2.setChefName("chef2");
     
      
      
      
      Thread chef1Thread=new Thread((Runnable) chef1);
      Thread chef2Thread=new Thread((Runnable) chef2);
      
      
      chef1Thread.start();
     /* chef2Thread.start();*/
     
   }
   

}
