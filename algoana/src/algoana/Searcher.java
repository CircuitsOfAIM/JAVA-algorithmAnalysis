package algoana;

import java.util.Scanner;




public class Searcher {
    public long nrOperations;

    public Searcher(){
        nrOperations = 0;
    }



    /**
     * This function performs a binary search and returns the position of the searched number or -1 if it is not in the array.
     * Remember, binary search requires a presorted array in ascending order.
     * @param values The int array you want to search the number in.
     * @param target The int value you are searching for in an array.
     * @return returns the index as int of the searched number in the array or -1 if it is not in the array.
     */

    //Could you maybe elaborate if this sort of doc is okey, or if there is a nicer way to not mention both in the description and return statement.


    public int binarySearch(int[] values, int target){
        int upperBound = values.length-1;
        int lowerBound = 0;
        int middle = (lowerBound + upperBound) / 2;

        while(lowerBound != middle) {

            if (values[middle] == target) {
                return values[middle];
            } else if (values[middle] > target) {
                upperBound = middle;
            } else {
                lowerBound = middle;
            }
            middle = (lowerBound + upperBound) / 2;
            nrOperations++;
        }

        if(values[upperBound] == target){
            nrOperations++;
            return upperBound;
        } else {
            return -1;
        }
    }


    /**
     * This function searches through an
     * array in a linear fashion until
     * it finds the first instance of
     * the target value.
     * @param values an integer array through which to search
     * @param target the integer to find
     * @return the index where the target was found.
     * If the target is not in values, it returns -1.
     */
    public int linearSearch(int[] values, int target){
        for (int index = 0; index < values.length; index++) {
            if (values[index] == target)
                return index;
            // Increment number of operations
            // after each comparison
            nrOperations++;
        }
        // If the target is not found, return -1
        return -1;
    }
}
