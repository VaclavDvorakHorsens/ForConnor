package application.Clients.BurgerBarManager;

import java.rmi.Remote;
import java.rmi.RemoteException;

import application.Clients.Guest.RemoteClient;
import javafx.event.ActionEvent;

public interface BurgerBarManager extends RemoteClient
{
   void closeBar(ActionEvent actionEvent) throws RemoteException;
}
