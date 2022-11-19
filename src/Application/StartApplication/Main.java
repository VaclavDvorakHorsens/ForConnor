package Application.StartApplication;

import java.io.IOException;

import Application.View.GUIrequisites.ViewHandler;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A main class that extends Application and initialize ViewHandler()
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class Main extends Application
{

   public static void main(String[] args)
   {
      launch(args);
   }

   /**
    * initialize ViewHadler() and calls openGUI for it for logIn GUI
    * 
    * @param student
    *           the student to add to the list
    * @throws IOException
    */
   @Override
   public void start(Stage primaryStage)
   {
      ViewHandler viewHandler = new ViewHandler(primaryStage);
      viewHandler.openGUI("logIn", null);

   }

}
