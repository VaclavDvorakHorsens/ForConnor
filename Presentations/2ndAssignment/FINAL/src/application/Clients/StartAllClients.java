package application.Clients;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import application.Clients.ClientsMainMethods.BurgerBarManager_Main;
import application.Clients.ClientsMainMethods.Chef_Main;
import application.Clients.ClientsMainMethods.Guest_Main;
import javafx.application.Application;

public class StartAllClients
{

   
   //starts Guest_Main,Chef_Main and BurgerBarManager_Main classes
   public static void main(String[] args) throws IOException, NotBoundException
   {
   
    new Thread(()  -> 
      {
      System.out.println("Manager opens burgerBar");  
      Application.launch(BurgerBarManager_Main.class);
         
       
      }).start();
      
  
      Guest_Main.main(args);
      Chef_Main.main(args);
            
  
     
   }

}
