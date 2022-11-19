package Application.Model.ModelAdmin;

import Application.ClientNetworking.ClientInterface;


/**
* A class serving as Admin model
* @author Vaclav Dvorak
* @version 1.0
*/

public class AdminModel implements AdminModelInterface
{

private ClientInterface client;



/**
* constructor initializing client
* 
*@param client ClientInterface
*/    
  
  public AdminModel(ClientInterface client)
  {
    this.client=client; 
  }


}
