package Application.Model.ModelLogIn;

/**
* An interface for LogInModel class
* @author Vaclav Dvorak
* @version 1.0
*/
import java.beans.PropertyChangeListener;

/**
 * An interface for LogInModel class
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */

public interface LogInModelInterface
{
   public void logIn(String userName, String password);

   public void addPropertyChangeListener(String name,
         PropertyChangeListener listener);
}