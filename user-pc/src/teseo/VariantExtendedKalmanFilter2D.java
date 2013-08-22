/* ExtendedKalmanFilter 
 * 
 * Created on 7 agosto 2008, 11.36
 * @author Filippo Zanella
 */
package teseo;

import Jama.Matrix;
import java.util.Vector;

public class VariantExtendedKalmanFilter2D implements Constants
{
    private int t;           // indices of filter matrices
    private long timeStep;  // step of the filter
    private Matrix A; // 2x2
    private Matrix H; // ix1 where i = # nodes in the estimation set at time t (H is time variant)
    private Matrix R; // ixi where i = # nodes in the estimation set at time t (R is time variant)
    private Matrix Q; // 2x2
      
    private Matrix DH; // ix2

    private Vector<Matrix> psi = new Vector<Matrix>(); // 2x1
    private Vector<Matrix> p = new Vector<Matrix>(); // 2x2
    
    private int MAX_SIZE_VECTOR = 1000;
    
    private Channel ch;
    
    public VariantExtendedKalmanFilter2D()
    {
        // ----- Static matrix of the model
        double[][] _A = {{1, 0}, {0, 1}};
        A = new Matrix(_A);
                      
        // ------ Initial conditions ------
        this.setInitialCondition();
    }

    public void setInitialCondition()
    {
        // Init at time t0 = 0
        t = 0;
        timeStep = 0;
          
        // TODO: Init of the state (Tuning with trilateration!!!) psi(0|-1)
        double[][] _PSI0 = {{.0}, {.0}};
        Matrix PSI0 = new Matrix(_PSI0);
        psi.insertElementAt(PSI0, ZERO);

        // Init of covariance of prediction error P(0|-1)
        double[][] _P = {{1, 0}, {0, 1}};
        Matrix P0 = new Matrix(_P);
        p.insertElementAt(P0, ZERO);
        
        // Variance of model error (Init of sampled covariance)
        /* --- The value of Q shoud be tuned with the Bartlett test --- */
        // The Bolt variance is lower than 0.38 [m^2]
        double[][] _Q = {{VAR_W, .0}, {.0, VAR_W}};
        Q = new Matrix(_Q);
    }
    
    // NB: check that it receives a column vector
    public double[][] update(double[][] rss, double[][] coordinates, Channel ch) 
    {
        // Variable channel
        this.ch = ch;

        Matrix PHI;         // RSS
        Matrix COOR;        // fixed nodes coordinates

        Matrix PSI;         // state (x,y,z)
        Matrix P;           // matrix P

        // It's ok if I have more than 3 measurements... otherwise the measure that
        // it is considered is that one of the previous step
        // rss.lenth must be <=3 for the observability
        if (rss.length >=3) 
        {
            // Built the matrices of the measurements
            PHI = new Matrix(rss);  // y(t) ix1

            // Built the matrices of fixed nodes positions
            COOR = new Matrix(coordinates);  // psi note ix2

            int DimRowPhi = PHI.getRowDimension();
            int DimColumnPhi = 2; // A.getColumnDimension();
            /* ---- Initial state is t0 = 0 with PSI0 = psi(0|-1) = PSI0 and P0 = P(0|-1) = I.
            Mobile node sends first message at time t0 that is the first broadcast and
             the estimatation is computed after Tc [s], which is a range of time
             useful to collect a non empty set of measure ---- */


            // 1) -------- Forward estimates --------

            // \hat{x}(t|t-1)
            Matrix PSI_ = psi.elementAt(t);
            // P(t|t-1)
            Matrix P_ = p.elementAt(t);

            // Update of matrices of the model
            // Matrix DH, that contains the derivates of the nonlinear elements in H
            double[][] _DH = new double[DimRowPhi][DimColumnPhi];
            // Matrix H
            double[][] _H = new double[DimRowPhi][1];
            for (int i = 0; i < DimRowPhi; i++) 
            {
                // I have the vector of estimates \hat{x}(t|t-1) 2x1 and the matrices of fixed nodes positions COOR ix2
                double distance = ch.get2Dnorm(PSI_.get(0, 0), COOR.get(i, 0), PSI_.get(1, 0), COOR.get(i, 1));
                // Valore di h(\hat{x}(t|t-1))[i][0]
                _H[i][0] = ch.getPower(distance);
                // Values of partial derivative h(.)[i][j] computed in \hat{x}(t|t-1) and the coordinates of node i
                for (int j = 0; j < DimColumnPhi; j++) 
                {
                    _DH[i][j] = ch.getPartialDerivativePower(PSI_.get(j, 0), COOR.get(i, j), distance);
//                    System.out.println("_D[" + i + "][" + j  + "] = " + _DH[i][j]);
                }
            }
            H = new Matrix(_H);
            DH = new Matrix(_DH);

            // TODO: Variance of measure error
            double[][] _R = new double[DimRowPhi][DimRowPhi];
            for (int i=0; i < DimRowPhi; i++) 
            {
                for (int j=0; j < DimRowPhi; j++) 
                {
                    if (i==j) 
                        _R[i][j] = VAR_V;      // +-6 [dB] 

                    else 
                        _R[i][j] = 0;                     
                }
            }
            R = new Matrix(_R);

            // Estimates
            // DELTA(t) = DH P(t|t-1) DH' + R
            Matrix DELTA = ((DH.times(P_)).times(DH.transpose())).plus(R);
            // L(t) = P(t|t-1) DH' DELTA(t)^{-1}
            Matrix L = (P_.times(DH.transpose())).times(DELTA.inverse());
            // \hat{x}(t|t) = \hat{x}(t|t-1) + L(t) [y(t) - h(\hat{x}(t|t-1))]
            PSI = PSI_.plus(L.times(PHI.minus(H)));
            psi.insertElementAt(PSI, t);
            // P(t|t) = P(t|t-1) - P(t|t-1) DH' DELTA(t)^{-1} DH P(t|t-1) 
            P = P_.minus((((P_.times(DH.transpose())).times(DELTA.inverse())).times(DH)).times(P_));
            p.insertElementAt(P, t);


            // 2) Forward estimates

            // \hat{x}(t+1|t) = A \hat{x}(t|t)
            Matrix Xplus = A.times(PSI);
            psi.insertElementAt(Xplus, t+1);
            // P(t+1|t) = A P(t|t) A' + Q    
            Matrix Pplus = ((A.times(P)).times(A.transpose())).plus(Q);
            p.insertElementAt(Pplus, t+1);            
        } 
        else 
        {
            // \hat{x}(t|t-1)
            Matrix PSI_ = psi.elementAt(t);
            // Prelevo P(t|t-1)
            Matrix P_ = p.elementAt(t);

            // \hat{x}(t|t) = \hat{x}(t|t-1)
            PSI = PSI_;
            psi.insertElementAt(PSI, t);
            // P(t|t) = P(t|t-1) 
            P = P_;
            p.insertElementAt(P, t);

            // \hat{x}(t+1|t) = A \hat{x}(t|t)
            Matrix Xplus = A.times(PSI);
            psi.insertElementAt(Xplus, t+1);
            // P(t+1|t) = A P(t|t) A' + Q    
            Matrix Pplus = ((A.times(P)).times(A.transpose())).plus(Q);
            p.insertElementAt(Pplus, t+1);

        //Toolkit.getDefaultToolkit().beep();     // Beep (it takes a long time!!! 200 [ms])
        }

        t++;
        timeStep++;

        if (t > MAX_SIZE_VECTOR) {
            resetFilter();
        }
        return PSI.getArray();
    }

    public void resetFilter()
    {
        psi.insertElementAt(psi.elementAt(t-1), ZERO);
        p.insertElementAt(p.elementAt(t-1), ZERO);
        t = 0;
    }

    public long getTimeStep() {return timeStep;}

    public Vector<Matrix> getP() {return p;}

    public Vector<Matrix> getX() {return psi;}    
}
