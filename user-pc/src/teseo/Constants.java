/* Constants.java
 * 
 * Created on 18 giugno 2007, 22.32
 * @author Filippo Zanella
 */

package teseo;

import java.awt.Color;

public interface Constants
{    
    // --- Teseo constants  
    int FIX_SCALE_POINT = 3;        // numbers after the comma to approssimate the mobile node position      
    long AUDIT_TIME = 60;           // [ms] frequency of estimation (after the mobile node has received a ping_client_msg)
    int K_T_U = 6;
    int K_TAU_U = 20;
            
    // --- Shared TinyOs constants !!!PAY ATTENTION!!!  
    //int TIMER_BROADCAST = 250;          // [ms] transmission frequency of ping_client_msg by the mobile node
    byte START = 0xA;                     // Start 
    byte STOP = 0xB;                      // Stop 
    
    // --- MapPanel constants   
    int REFRESH_TIME = 360;         // [ms] refresh of MapPanel
    //int MIN_TRACE_SIZE = 30;        // size of tracing that indicates when it should start to SAVE the trace
    int MIN_TRACE_SIZE = 30000000;        // size of tracing that indicates when it should start to DELETE the trace
    
    // --- VEKF constants
    double VAR_W = .0144;
    double VAR_V = 36;
    
    // --- General useful constants   
    int ZERO = 0;
//    double M2CM = 100;
//    double CM2M = 0.01;    
    Color BROWN = new Color(150, 50, 5);
    Color BLUE = new Color(0, 0, 255);
    Color RED = new Color(255, 0, 0);
    Color YELLOW = Color.YELLOW;
    Color GREEN = new Color(0, 255, 0);
    Color GREEN_2 = new Color(70, 185, 50);
//    Color GREEN_3 = new Color(120, 155, 5);
    public static final String COPYRIGHT = "\u00a9";
//    public static final String REGISTERED = "\u00ae";
//    public static final String EURO = "\u20ac";
}
