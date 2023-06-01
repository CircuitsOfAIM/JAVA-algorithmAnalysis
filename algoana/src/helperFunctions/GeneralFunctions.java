package helperFunctions;

import algoana.Searcher;
import algoana.Sorter;

import java.util.Random;

public class GeneralFunctions {

    public static int[] generateRandomValues(Random rand, int nrValues){
        int [] values = new int[nrValues];
        // Fill the array in ascending order
        for (int i = 0; i < nrValues; i++)
            values[i] = i;

        // Swap each element i with another element j > i
        int j;
        for (int i = 0; i < nrValues-1; i++){
            j = (i+1) + rand.nextInt(nrValues-(i+1));
            new Sorter().swap(values, i, j);
        }
        return values;
    }

    public static void printTable(int minSPower, int maxSPower,
                                  int minVPower, int maxVPower,
                                  double[][] results) {


        // Convert each double to a string, and
        // remember how long the longest string is
        String[][] sResults = new String[results.length][results[0].length];
        int resMax = 4;
        for (int i = 0; i < results.length; i++) {
            for (int j = 0; j < results[0].length; j++){
                sResults[i][j] = Double.toString(results[i][j]);
                if (sResults[i][j].length() > resMax)
                    resMax = sResults[i][j].length();
            }
        }

        // Create horizontal line of correct size
        String hline = "\n          -------";
        for (int sp = minSPower; sp <= maxSPower; sp++)
            for (int i = 0; i <= resMax + 3; i++)
                hline += "-";
        hline += "\n";


        // Place the tag 'Searches' above the table
        String table = "";
        for (int i = 0 ; i < hline.length()/2; i++)
            table += " ";
        table += "#Searches\n";

        table += "                |";
        // Add information row on number of searches
        int fore = (resMax-1)/2;
        for (int sp = minSPower; sp <= maxSPower; sp++) {
            for (int i = 0; i < fore; i++)
                table += " ";
            if (sp < 10)
                table += "2^0" + sp;
            else
                table += "2^" + sp;
            for (int i = 0; i < resMax -1 - fore; i++)
                table += " ";
            table += "|";
        }
        table += hline;

        // Print rest of table (lots of spacing-code)
        for (int vp = minVPower; vp <= maxVPower; vp++){
            if (vp == minVPower + (maxVPower-minVPower)/2)
                table += "#Values ";
            else
                table += "        ";

            table += (vp < 10) ? "  2^0" + vp + "  |" : "  2^" + vp + "  |";
            double[] row = results[vp-minVPower];
            String rowString = "";
            for (int i = 0; i < row.length; i++){
                String value = Double.toString(row[i]);
                int spacesFore = (8 - value.length())/2;
                int spacesAft = 8 - value.length() - spacesFore;

                for (int j = 0; j < spacesFore; j++)
                    rowString += " ";
                rowString += value;
                for (int j = 0; j < spacesAft; j++)
                    rowString += " ";
                rowString += "|";
            }
            table += rowString + "\n";
        }
        System.out.println(table);
    }


    public static void runSimulations(String name,
                                      int minVPower, int maxVPower,
                                      int minSPower, int maxSPower,
                                      boolean detailedInformation){
        int[] values;
        int nrValues, nrSearches, toFindIndex;
        long nrOperationsLinear, nrOperationsBinary;
        double ratio;
        Searcher linearSearcher, binarySearcher;
        Sorter sorter;
        Random rand = new Random();
        double[][] results = new double[maxVPower-minVPower+1][maxSPower-minSPower+1];

        System.out.println("Gathering results...");
        for (int vp = minVPower; vp <= maxVPower; vp++) {
            // Calculate how many values there are
            nrValues = (int) Math.pow(2, vp);
            values = generateRandomValues(rand, nrValues);

            for (int sp = minSPower; sp <= maxSPower; sp++) {
                // Calculate how many searches must be done
                nrSearches = (int) Math.pow(2, sp);

                // Create new searcher and perform
                // required amount of linear searches
                linearSearcher = new Searcher();
                for (int i = 0; i < nrSearches; i++) {
                    toFindIndex = rand.nextInt(nrValues);
                    linearSearcher.linearSearch(values, values[toFindIndex]);
                }
                nrOperationsLinear = linearSearcher.nrOperations;

                // Create sort and searcher and perform
                // sorting and binary searches
                binarySearcher = new Searcher();
                sorter = new Sorter();
                sorter.sort(name, values);
                for (int i = 0; i < nrSearches; i++) {
                    toFindIndex = rand.nextInt(nrValues);
                    binarySearcher.binarySearch(values, values[toFindIndex]);
                }
                nrOperationsBinary = sorter.nrOperations + binarySearcher.nrOperations;

                // Calculate ratio to two decimal places
                // and save to result
                ratio = (double) nrOperationsLinear / nrOperationsBinary;
                ratio = (double) Math.round(ratio*100)/100;
                results[vp-minVPower][sp-minSPower] = ratio;

                // Output detailed information if requested
                if (detailedInformation) {
                    System.out.println("For 2^" + vp + " (" + nrValues + ") values and " + nrSearches + " searches:");
                    System.out.println("\tLinear Search - Total Operations = " + nrOperationsLinear);
                    System.out.println("\t" + name + " Sort + Binary Search - Total Operations = " + nrOperationsBinary);
                    if (nrOperationsLinear > nrOperationsBinary) {
                        ratio = (double) nrOperationsLinear / nrOperationsBinary;
                        ratio = (double) Math.round(ratio * 100) / 100;
                        System.out.println(name + " sort + binary search is " + ratio + " times faster than linear search");
                    }
                    else {
                        ratio = (double) nrOperationsBinary / nrOperationsLinear;
                        ratio = (double) Math.round(ratio * 100) / 100;
                        System.out.println("Linear Search is " + ratio + " times faster than " + name + " sort + binary search");
                    }
                }
            }
        }
        System.out.println("Done!\n");

        printTable(minSPower, maxSPower, minVPower, maxVPower, results);
    }

    public static void testSort(String name, int[] values){
        new Sorter().sort(name, values);
    }

    public static boolean testSearch(int[] values, int target){
        int indexLin = new Searcher().linearSearch(values, target);
        int indexBin = new Searcher().binarySearch(values, target);
        return indexLin == indexBin;
    }
}
