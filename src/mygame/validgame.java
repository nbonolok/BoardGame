/*
 * validgame.java
 *
 * Created on January 14, 2010, 10:43 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package mygame;
import java.util.Calendar;


/**
 *
 * @author BKNyoni
 */
public class validgame {
    
    /** Creates a new instance of validgame */
    public validgame() {
    }
    /**
     *
     * @return
     */
    public boolean getStatus()
   {
      Calendar rightNow = Calendar.getInstance();

       int mon= rightNow.get(Calendar.MONTH);
       int d =rightNow.get(Calendar.DATE);
       int y=rightNow.get(Calendar.YEAR);        
       //System.out.println(d+" "+mon+" "+y);
       //if(mon <= 12 && y==2010)
       //{
        //   return true;
       //}
       //return false;
      return true;
   }
}
