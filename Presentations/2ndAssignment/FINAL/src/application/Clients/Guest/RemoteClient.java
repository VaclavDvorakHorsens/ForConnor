package application.Clients.Guest;

import java.lang.reflect.InvocationTargetException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteClient extends Remote
{
void DisconnectFromServer() throws RemoteException, InvocationTargetException;

void addYourSelfToServerList() throws RemoteException;
}
