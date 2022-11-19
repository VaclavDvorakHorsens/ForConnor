package application.Clients.Chef;

import java.rmi.RemoteException;

import application.Clients.Guest.RemoteClient;
import application.RecipeRelatedStuff.RecipeProvider;

public interface ChefClientInterface extends RemoteClient
{

   void addBurger() throws Exception;
   void setChefName(String chefName) throws RemoteException;



}
