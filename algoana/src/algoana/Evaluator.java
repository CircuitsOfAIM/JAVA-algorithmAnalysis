package algoana;

import java.util.Random;

import static helperFunctions.GeneralFunctions.*;


public class Evaluator {

    public static void main(String[] args) {

        // The type of sorting algorithm used
        String name = "Insertion";

        // These parameters determine the length of the array to sort/search
        int minVPower = 2;
        int maxVPower = 10;

        // These parameters determine how many searches are done by both strategies
        int minSPower = 2;
        int maxSPower = 10;

        // Set this parameter to true if you want
        // the details of each test
        boolean detailedInformation = true;

        // Run the simulation
        runSimulations(name, minVPower, maxVPower, minSPower, maxSPower, detailedInformation);
    }
}
