package application.Clients.ClientsMainMethods;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import application.Clients.Guest.Guest;
import application.Clients.Guest.GuestInterface;

public class Guest_Main
{

   public static void main(String[] args) throws RemoteException, NotBoundException
   {
     
      GuestInterface guest1=new Guest();
      guest1.setGuestName("guest1");
      GuestInterface guest2=new Guest();
      guest2.setGuestName("guest2");
      GuestInterface guest3=new Guest();
      guest3.setGuestName("guest3");
      GuestInterface guest4=new Guest();
      guest4.setGuestName("guest4");
      
      

      Thread guest1Thread=new Thread((Runnable) guest1);
      Thread guest2Thread=new Thread((Runnable) guest2);
      Thread guest3Thread=new Thread((Runnable) guest3);
      Thread guest4Thread=new Thread((Runnable) guest4);
      
  
      guest1Thread.start();
      guest2Thread.start();
      guest3Thread.start();
      guest4Thread.start();

   }

}
