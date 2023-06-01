package algoana;

import static helperFunctions.GeneralFunctions.*;




public class Test {
    public static void main (String[] args){
        int[] values = {4, 7, 3, 5, 8, 6, 2, 1}; 


        // Test whether your search algorithm works
        if(testSearch(values, 4))
            System.out.println("Binary search looks good to me!");
        else
            System.out.println("Linear Search and binary search do not return the same index...");

        // Test whether your sorting algorithm works
        String name = "Insertion"; 
        testSort(name, values);
        if (isSorted(values))
            System.out.println(name + " sort looks good to me!");
        else {
            System.out.println("The array is not sorted...");
            System.out.println("Output was:");
            for (int i = 0; i < values.length; i++)
                System.out.print(values[i] + ", ");
        }






    }

    /**
     * @param values the integer array to check
     * @return a boolean that is true if the array
     * values is sorted, and false otherwise.
     */
    public static boolean isSorted(int[] values){
        for(int i = 0; i < values.length-1; i++)
            if (values[i] > values[i+1])
                return false;
        return true;
    }
}
