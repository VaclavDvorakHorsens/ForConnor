package application.Clients.ClientsMainMethods;


import java.io.IOException;
import java.rmi.NotBoundException;

import application.Clients.BurgerBarManager.BurgerBarManager;
import application.Clients.BurgerBarManager.BurgerBarManagerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class BurgerBarManager_Main extends Application {
   @Override
   public void start(Stage primaryStage) throws IOException, NotBoundException {
      
     
          
            Scene scene=null;
            FXMLLoader loader=new FXMLLoader();
            Parent root =null;
            loader.setLocation(getClass().getResource("../BurgerBarManager/BurgerBarManager.fxml"));
            root = loader.load();
            BurgerBarManager burgerBarManagerController=new BurgerBarManagerController();
            burgerBarManagerController=loader.getController();
            primaryStage.setTitle("BurgerBarManager");
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            
        
     
   
   }
 }
   
  

 
   
 

