/* Node.java
 * 
 * Created on 14 giugno 2007, 15.01
 * @author Filippo Zanella
 */

package teseo;

public class Node
{
    private double x;
    private double y;
    private double z;
    private int nodeId;
//    private int channel;
//    private int power;
    
    public Node()
    {
        x = Double.NaN; 
        y = Double.NaN;
        z = Double.NaN;
        nodeId = -1;
    }

    public Node(int nodeId, double x, double y, double z)
    {
        this.nodeId = nodeId;
        this.x = x; 
        this.y = y;
        this.z = z;
    }
  
    public int getNodeId() {return nodeId;}
    
    public void setNodeId(int _nodeId) {nodeId = _nodeId;}
       
    public void setX(double x) { this.x = x;}
    
    public double getX() {return this.x;}
    
    public void setY(double y) {this.y = y;}
        
    public double getY() {return this.y;}

    public void setZ(double z) { this.z = z;}
    
    public double getZ() {return this.z;}
       
//    public int getChannel() {return channel;}
//    public void setChannel(int _channel) {channel = _channel;}
//    public int getPower() {return power;}
//    public void setPower(int _power) {power = _power;}
}
