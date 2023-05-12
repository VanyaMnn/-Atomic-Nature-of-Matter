import java.util.*;

/*this class has two steps:
  first it finds all blobs in the picture using the dfs method
  second it saves the beads (blobs with mass >= 25) so they can be used in the BeadTracker.java class
*/

public class BeadFinder {
    private Picture pic;
    private double tauLim;
    public int blobs = 0;   //number of blobs
    public int beads = 0;   //number of beads

    ArrayList<Blob> allBlobs = new ArrayList<>();   //this arrayList will save all blobs found within the picture


    //the static below creates a 2-by-2 boolean called checked. this shows us which pixels have been checked and which haven't
    //we first set all the booleans to false, since no pixels have been checked yet
    private static boolean [][] checked(Picture p){
        boolean[][] c = new boolean[p.height()][p.width()];
        for (int i = 0; i < p.height(); i++) {
            for (int j = 0; j < p.width(); j++) {
                c[i][j] = false;
            }

        }
        return c;
    }

    /*the dfs method checks around each pixel with the given coordinates to see whether they fit the conditions of being considered
      blobs or not (being white)
    */
    private void dfs(int i, int j, Blob b){
        //if the pixel is out of the picture's frame or if it's already been checked then don't continue
        if(i < 0 || j < 0 || i > pic.height() - 1 || j > pic.width() - 1 || checked[i][j]){
            return;
        }
        
        checked[i][j] = true;   //the pixel has been checked, so the boolean should be set to true
        if(Luminance.intensity(pic.get(j, i)) >= tauLim){
            checked[i][j] = true;   //just in case :)
            b.add(i,j); //add these coordinates to the blob
            dfs(i - 1, j, b);   //check around said coordinates to see if they're part of the blob
            dfs(i + 1, j, b);
            dfs(i, j - 1, b);
            dfs(i, j + 1, b);
        }
        else{
            return;
        }
    }


    boolean [][] checked;

    //the static BeadFinder helps us find all blobs using the dfs static
    public BeadFinder(Picture picture, double tau){
        pic = picture;
        tauLim = tau;
        int h = picture.height();
        int w = picture.width();
        checked = checked(picture);

        //first we create two for loops to check each pixel
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                /*the if below checks if:
                  1-whether or not the coordinates of that pixels were checked
                  2-whether or not the pixel is considered white
                  if the pixel hasn't been checked and it's white, then the conditions have been fufilled
                */
                if (!checked[i][j] && Luminance.intensity(picture.get(j, i)) >= tau){   //luminance.intensify checks the rgb of any given pixel. if it's within the tau limit we give then its considered white
                    Blob newBlob = new Blob();  //creates a new blob
                    dfs(i, j, newBlob); //calls on the dfs method
                    allBlobs.add(blobs, newBlob);   //adds the blob, which has been checked by the dfs method to the Arraylist
                    blobs += 1; //the number of blobs has increased
                }
                else{
                    //even if the pixel doesn't meet the conditions for dfs to be called, it's still been checked
                    checked[i][j] = true;
                }
                
            }
        }
    }
    
    //this method creates an array which saves useful beads
    public Blob[] getBeads(int min){
        beads = 0;  //the current number of beads found is 0
        int n = 0;  //shows the index that should be filled
        for (int i = 0; i < allBlobs.size(); i++) {
            if(allBlobs.get(i).mass() >= min){
                beads ++;   //checks the arraylist. if a blob's mass is >= 25, it increases the number of found beads by one
            }
            
            Blob[] allBeads = new Blob[beads];  //creates an array with the size of the found beads and saves said beads
        }
        Blob[] allBeads = new Blob[beads];
        for (int i = 0; i < allBlobs.size(); i++) {
            if(allBlobs.get(i).mass() >= min){
                allBeads[n] = allBlobs.get(i);
                n++;
            }
        }
        return allBeads;
    }

    //tests the class
    public static void main(String[] args) {

        int min = Integer.parseInt(args[0]);
        double tau = Double.parseDouble(args[1]);
        Picture myPicture = new Picture(args[2]);
        BeadFinder beadFinder = new BeadFinder(myPicture, tau);
        Blob[] beadList = beadFinder.getBeads(min);

        for (int i = 0; i < beadFinder.beads; i++) {
            StdOut.println(beadList[i]);
        }
        System.out.println(beadFinder.blobs);
        System.out.println(beadFinder.beads);
    }

}
