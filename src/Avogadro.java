//this class uses the info fund from beadtracker(gets it from the terminal) and calculates the boltzmann and avogadro
public class Avogadro {
    public static void main(String[] args){
        //first part of formula
        //an array to save all distances inside
        double [] distances = new double[0];
        //a constant number that turns pixels to meters
        double P2D = 0.000000175;
        //a boolean to check and see if we've gotten all distances from BeadTracker
        boolean empty = StdIn.isEmpty();
        double temperature = 297.0;
        double weirdN = 0.0009135;
        double r = 0.0000005;
        double constantD;
        double gasConstant = 8.31446;
        double sum = 0;
        double boltzmann;
        double avogadro;

        //if it isn't empty it means we don't have all the distances, so we write a loop which stops when there are no numbers from the input
        while (!empty){
            distances = StdIn.readAllDoubles();
        }

        //a sum of all the distances in meters
        for (int i = 0; i < distances.length; i++) {
            sum += Math.pow(distances[i] * P2D, 2);
        }

        //we got the constant D!
        constantD = sum / (distances.length * 2);

        //calculates Boltzmann
        boltzmann = constantD * 6 * Math.PI * weirdN * r / temperature;
        //calculates avogadro
        avogadro = gasConstant / boltzmann;
        StdOut.println("Boltzmann = " + boltzmann);
        StdOut.println("Avogadro = "+ avogadro);
    }
}

