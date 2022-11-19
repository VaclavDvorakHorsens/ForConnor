package Application.Model.ModelSearch;

import Application.ClientNetworking.ClientInterface;

/**
 * A class serving as searching model
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class SearchModel implements SearchModelInterface
{

   private ClientInterface client;

   /**
    * constructor initializing client
    */
   public SearchModel(ClientInterface client)
   {
      this.client = client;
   }

}
