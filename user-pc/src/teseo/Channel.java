/* Channel.java 
 * 
 * Created on 19 maggio 2008, 08.41
 * @author Filippo Zanella
 */
package teseo;

//  ---------------------------------------------
// |  LEVEL  |  TX POWER [dBm]  |  CURRENT [mA]  |
// |    31   |         0        |      17.4      |
// |    27   |        -1        |      16.5      |
// |    23   |        -3        |      15.2      |
// |    19   |        -5        |      13.9      |
// |    15   |        -7        |      12.5      |
// |    11   |       -10        |      11.2      |
// |     7   |       -15        |       9.9      |
// |     3   |       -25        |       8.5      |
//  ---------------------------------------------

public class Channel
{    
    /* channel parameters */
    //private double d0;      // [m] reference distance
    private int TxPow;      // [dBm] trasmission power (0 [dBm] = max power)
    private double beta;    // [dBm] channel attenuation at 1 [m]
    private double gamma;   // path-loss coefficent

    double DERIVATIVE_COSTANT = -10*StrictMath.log10(StrictMath.E);
    
//    public Channel(double d0, int TxPow, double beta, double gamma)
//    {
//        this.d0 = d0;
//        this.TxPow = TxPow;
//        this.beta = beta;
//        this.gamma = gamma;
//    }

    public Channel(int TxPow, double beta, double gamma)
    {
        this.TxPow = TxPow;
        this.beta = beta;
        this.gamma = gamma;
    }
    
    public void setChannel(double beta, double gamma)
    {
        this.beta = beta;
        this.gamma = gamma;
    }
       
    public int getPower(double d)
    {
        // NB: il cast avviene perchè la potenza è in [dBm], valori interi
        return (int)(this.TxPow + this.beta - 10*gamma*StrictMath.log10(d));
    }

    public double getPartialDerivativePower(double u, double uFix, double d)
    {
        return (DERIVATIVE_COSTANT*gamma*(u-uFix)/StrictMath.pow(d, 2));
    }
    
    public double get2Dnorm(double x, double xFix, double y, double yFix)
    {
        return Math.sqrt(StrictMath.pow((x-xFix), 2) + StrictMath.pow((y-yFix), 2));
    }
    
//    public double get3Dnorm(double x, double xFix, double y, double yFix, double z, double zFix)
//    {
//        return Math.sqrt(StrictMath.pow((x-xFix), 2) + StrictMath.pow((y-yFix), 2) + StrictMath.pow((z-zFix), 2));
//    }
    
    public int getTxPow() {return TxPow;}
    public void setTxPow(int TxPow) {this.TxPow = TxPow;}

    public double getBeta() {return beta;}
    public void setBeta(double beta) {this.beta = beta;}

    //public double getD0(){return d0;}
    //public void setD0(double d0){this.d0 = d0;}
    
    public double getGamma() {return gamma;}
    public void setGamma(double gamma) {this.gamma = gamma;}
}
