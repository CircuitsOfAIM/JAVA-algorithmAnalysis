package algoana;

import java.util.Scanner;




public class Sorter {
    // This attribute will keep track of the
    // number of operations made by the
    // given sorting algorithm.
    public long nrOperations = 0;

    public Sorter(){
        nrOperations = 0;
    }



    /**
     * This algorithm performs insertion sort onto an input array of integers.
     * It goes linearly trough the array and switches the element at index with the value previous to that, if it is smaller than the previous element, for as long as it is smaller as all previous elements.
     * @param values Array of integers that the sort should perform on.
     */
    public void insertionSort(int[] values){
        int index = 0;
        for(int arrayIndex = 0; arrayIndex < values.length -1; arrayIndex++ ) {
            index = arrayIndex;

            while(values[index] > values[index + 1]){
                nrOperations++;
                int placeholder = values[index];
                values[index] = values[index + 1];
                values[index + 1] = placeholder;
                nrOperations++;

                if(index!= 0){
                    index--;
                }

            }
            nrOperations++;

        }
    }





    public void mergeSort(int[] values){
        int[] targetArray = new int[values.length];


        int size = 1;
        while (size < values.length){
                int count = 0;
                int countLeftArray = 1;
                int countRightArray = 1;



                while (count + size < values.length) {
                    if (values[count] > values[count + size]) {
                        int placeholder = values[count];
                        values[count] = values[count + size];
                        values[count + size] = placeholder;
                    }

                    countLeftArray = count + 1;
                    countRightArray = count + size + 1;

                    while(countRightArray < size){

                        if (values[countLeftArray] > values[countRightArray]) {
                            int placeholder = values[countLeftArray];
                            values[countLeftArray] = values[countRightArray];
                            values[countRightArray] = placeholder;
                        }

                        countLeftArray++;
                        countRightArray++;

                    }

                    countLeftArray = 0;
                    countRightArray = 0;

                    count = count + size * 2;
                }
            size = size * 2;
        }
    }
    */



    /**
     * This function swaps two elements at specified indices in a call by refence manner.
     * @param values the array of values on which we need to swap to elements
     * @param indexA the index of the first element
     * @param indexB the index of the second element
     */
    public void swap (int[] values, int indexA, int indexB){
        int temp = values[indexA];
        values[indexA] = values[indexB];
        values[indexB] = temp;

        // Increment the number of operations
        nrOperations++;
    }

    /**
     * This functions parses the types of sorting
     * algorithm given by the user, then applies that
     * sorting algorithm on the provided array.
     * If the sorting algorithm is not defined, the
     * function will state so and do nothing.
     * @param name the name of the algorithm used. By default these are 'Insertion' or 'Merge'.
     * @param values an integer array that contains the values to sort.
     */
    public void sort(String name, int[] values) {
        switch (name) {
            case "Insertion":
                insertionSort(values);
                return;
            case "Merge":
                return;
            default:
                System.out.println("The sorting algorithm '" + name + "' does not exist.");
                System.out.println("Try adding the function, or correct the spelling");
        }
    }
}
