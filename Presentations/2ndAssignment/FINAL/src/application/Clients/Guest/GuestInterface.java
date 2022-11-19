package application.Clients.Guest;

import java.rmi.RemoteException;

public interface GuestInterface extends RemoteClient
{
void setGuestName(String guestName) throws RemoteException;
void getBurger() throws RemoteException, InterruptedException;

}
