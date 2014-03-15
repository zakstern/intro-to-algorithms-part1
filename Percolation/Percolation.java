public class Percolation {
 
   private int gridSize;    // number of sites in grid
   private int gridEdgeLength; //square root of gridSize 
   private WeightedQuickUnionUF uf;
   private int[] status;  //keeps track of open/closed status.
   
   public Percolation(int N)  {  // create N-by-N grid, with all sites blocked
       if (N < 1) {
           throw new IllegalArgumentException();   //invalid grid-size
       }
       else {
           gridSize = N*N;
           //(0 is for virtual top, N+2 is for virtual bottom)
           uf = new WeightedQuickUnionUF(gridSize+2); 
           gridEdgeLength = N;
           status = new int[gridSize+1]; 
           for (int i = 1; i < gridSize+1; i++) {
              status[i] = 0;
           }
           connectVirtualSites();
       }
   }
   
   private int xyTo1D(int x, int y) {
       //index 0 is reserved for the virtual "top".  Hence, (1,1) returns 1.
       return (gridEdgeLength*x)-(gridEdgeLength-y); 
   }
   
   private void indexOutOfBounds(int i, int j) {
       if (i < 1 || j < 1 || i > gridSize || j > gridSize) {
           throw new IndexOutOfBoundsException();
       }
   }
   
   private boolean hasTopNeighbor(int i, int j) {
       return i > 1;
   }
   
   private boolean hasBottomNeighbor(int i, int j) {
       return i < gridEdgeLength;
   }
   
   private boolean hasLeftNeighbor(int i, int j) {
       return j > 1;
   }

   private boolean hasRightNeighbor(int i, int j) {
       return j < gridEdgeLength;
   }
   
   private int getTopNeighbor(int i, int j) { 
       return xyTo1D(i , j) - gridEdgeLength;
   }
   
   private int getBottomNeighbor(int i, int j) {
       return xyTo1D(i , j) + gridEdgeLength;
   }
   
   private int getLeftNeighbor(int i, int j) {
       return xyTo1D(i , j) - 1;
   }
   
   private int getRightNeighbor(int i, int j) {
       return xyTo1D(i , j) + 1;
   }
   
   private void connectOpenNeighbors(int i, int j) {
       if (hasTopNeighbor(i , j) && isOpen(i-1 , j)) {
           uf.union(xyTo1D(i , j) , getTopNeighbor(i , j));
       }
       if (hasBottomNeighbor(i , j) && isOpen(i+1 , j)) {
           uf.union(xyTo1D(i , j) , getBottomNeighbor(i , j));
       }
       if (hasLeftNeighbor(i , j) && isOpen(i , j-1)) {
           uf.union(xyTo1D(i , j) , getLeftNeighbor(i , j));
       }
       if (hasRightNeighbor(i , j) && isOpen(i , j+1)) {
           uf.union(xyTo1D(i , j) , getRightNeighbor(i , j));
       }
   }
   
   //connects the top and bottom virtual sites to the first and last row
   private void connectVirtualSites() {  
       int i = 1;
       while (i < gridEdgeLength+1) {
           uf.union(0 , i);
           i++;
       }
       i = gridSize;
       while (i > gridSize-gridEdgeLength) {
           uf.union(gridSize+1 , i);
           i--;
       }
   }

   /*
    *open site (row i, column j) if it is not already
    */
   public void open(int i, int j) { 
       indexOutOfBounds(i , j);  
       if (status[xyTo1D(i , j)] != 1) {
           status[xyTo1D(i , j)] = 1;
           connectOpenNeighbors(i , j);
       }
   }
   
   public boolean isOpen(int i, int j) {    // is site (row i, column j) open?
       indexOutOfBounds(i , j);              
       return (status[xyTo1D(i , j)] == 1);    
   }
       
   public boolean isFull(int i, int j) {    // is site (row i, column j) full?
       indexOutOfBounds(i , j);
       if (isOpen(i , j)) {
           return uf.connected(0 , xyTo1D(i , j));
       }
       return false;
   }
   
   public boolean percolates() {           // does the system percolate?
       return uf.connected(0 , gridSize+1);
   }
   
   public static void main(String[] args) {     
   }
}

   