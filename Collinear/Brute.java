/*************************************************************************
 * Name: Zak Stern
 * Email: 
 *
 * Compilation:  javac Brute.java
 * Execution:
 * Dependencies: Point.java
 *
 * Description: examines 4 points at a time and checks whether they all lie on the same line segment, 
 * printing out any such line segments to standard output and drawing them using standard drawing.
 *
 *************************************************************************/

public class Brute {
    
    public static boolean inBounds(int i) {
        if (i > 0 && i < 32768) return true;
        return false; 
    }
    
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // number of points
        
// rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(.01);
        StdDraw.show();
        
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            if (inBounds(x) && inBounds(y)) {
                
                Point p = new Point(x,y);
                //StdOut.println(p.toString());
                p.draw();
                StdDraw.show();
            }
        }
        
        // display to screen all at once
        //StdDraw.show(0);
           
    }
}