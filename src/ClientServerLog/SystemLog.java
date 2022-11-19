package ClientServerLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A class that logs other classes activities into .txt file
 * 
 * @author Vaclav Dvorak
 * @version 1.0
 */
public class SystemLog
{
   private File systemLog;
   private static SystemLog instance;
   private static Lock lock = new ReentrantLock();
   private DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

   /**
    * constructor defining .txt file path
    */
   private SystemLog() throws IOException
   {
      systemLog = new File("C:\\Vasek\\catalogue.txt");
   }

   /**
    * returns instance of log keeps it locked as long
    * 
    * @param index
    *           the position in the list of the Student object
    * @return the Student at index if one exists, else null
    */
   public static SystemLog getInstance()
   {
      if (instance == null)
      {
         synchronized (lock)
         {
            if (instance == null)
               try
               {
                  instance = new SystemLog();
               }
               catch (IOException e)
               {
                  e.printStackTrace();
               }
         }
      }
      return instance;
   }

   /**
    * logs String to .txt file
    * 
    * @param String
    *           to be written into .txt file
    */
   public void log(String txt)
   {
      try
      {
         String time = dateFormat.format(Calendar.getInstance().getTime());
         Writer out = new BufferedWriter(new FileWriter(systemLog, true));
         System.out.println(time + ":" + txt);
         out.append(time + ":" + txt);
         out.flush();
         out.close();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }

}