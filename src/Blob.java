import java.util.*;

//This class will make our Blobs
public class Blob {
    //the center x in a blob
    public double cx;
    // the y center in a blob
    public double cy;
    //the amount of pixels in a blob
    private int mass;

    //creates an empty blob
    public Blob(){
        cx = 0.0;
        cy = 0.0;
        mass = 0;
    }

    //adds pixels (x, y) to this blob (gets the blob's new center and size)
    public void add(int x, int y){
        cx = (cx * mass + x) / (mass + 1);
        cy = (cy * mass + y) / (mass + 1);
        mass++;
    }

    //shows the number of pixels in the blob
    public int mass(){
        return mass;
    }

    //calculates the distance between the center of two blobs
    public double distanceTo(Blob that){
        return Math.sqrt(Math.pow(cx - that.cx, 2) + Math.pow(cy - that.cy, 2));
    }

    //String representation of this blob
    public String toString(){
        return mass + "(" + cx + ", " + cy + ")";
    }

    //tests this class by directly calling all instant methods
    public static void main (String[] args){
        Blob b1 = new Blob();
        b1.add(0, 0);
        StdOut.println(b1.toString());
        Blob b2 = new Blob();
        b2.add(1, 0);
        StdOut.println(b2.toString());
        StdOut.println(b1.distanceTo(b2));
    }

}

