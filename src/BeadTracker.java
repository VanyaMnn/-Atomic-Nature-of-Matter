import java.util.*;

/*this class tracks all beads by finding the shortest distance in the beads of the next frame and saves that distance,
so it can be used in the next part(Avogadro)*/
public class BeadTracker {


    public static void main(String[] args) {
        //gets the minimum amount of pixels from terminal
        int min_pixel = Integer.parseInt(args[0]);
        //gets tau from terminal
        double tau = Double.parseDouble(args[1]);
        //gets the minimum distance between two blobs from terminal
        double delta = Double.parseDouble(args[2]);


        for (int i = 3; i < args.length - 1; i++) {
            //gets the file's location
            String filename1 = args[i]; //gets the first pathway
            String filename2 = args[i + 1]; //gets the second pathway

            //finds the pictures in the path
            Picture pic1 = new Picture(filename1);  //gets the first picture
            Picture pic2 = new Picture(filename2);  //gets the second picture

            //finds all blobs
            BeadFinder b1 = new BeadFinder(pic1, tau); 
            BeadFinder b2 = new BeadFinder(pic2, tau);

            //finds all beads
            Blob[] blob1 = b1.getBeads(min_pixel);
            Blob[] blob2 = b2.getBeads(min_pixel);


            
            
            for (int j = 0; j < blob2.length; j++) {
                double shortest = 1000000000.0; //set an unbelievably large amount so it can be replaced by the next bead
                for (int k = 0; k < blob1.length; k++) {
                    //calculates the distance between a blob in the 2nd frame with all blobs from the first
                    double distance = blob2[j].distanceTo(blob1[k]);
                    //helps us find the shortest distance of each blob in the 1st frame from the 2nd blob
                    if (distance < shortest) {
                        shortest = distance;
                    }
                }
                if(shortest < delta){
                    StdOut.println(shortest);
                }

            }
            //checks to see if the distance < delta
        
        }
    }
}

