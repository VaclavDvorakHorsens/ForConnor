package application.Clients.BurgerBarManager;

import java.lang.reflect.InvocationTargetException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import application.Server.ServerRemoteObjects.RemoteServerBounderQueue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class BurgerBarManagerController implements BurgerBarManager
{
 private RemoteServerBounderQueue remoteObject; 
   @FXML private Button closeBar; 
 
 
   
   //constructor, receives serverObject and calls sendYourselfToTheServer()
   public BurgerBarManagerController() throws RemoteException, NotBoundException
   {
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
   
   
  //calls DisconnectFromServer()
   @Override
   public void closeBar(ActionEvent actionEvent) throws RemoteException
   {
    
         DisconnectFromServer();
    
   }

   
   
  

 //calls shutDownTheBar() on serverObject
   @Override
   public void DisconnectFromServer() throws RemoteException
   {
      remoteObject.shutDownTheBar();
     
      System.exit(0);
   }
   
   
 //sends THIS to server 
   @Override
   public void addYourSelfToServerList() throws RemoteException
   {
      remoteObject.addClientToServerList(this);   
   }
   
}
