# Time-complexity analysis of some Searching and Sorting algorithms
In this code, the linear search and binary search has implemented.This implementations are in `searcher` module.Also,
insertion sort and merge sort implemented on `sorter` module.

Using a `evaluator` which was provided, these algorithms compared of relative time-complexity pairwisely.This evaluation uses
`minVPower`and `maxVPower` as parameters for length of the array (The script will test for every value of (2)*x
, where minVPower ≤ x ≤ maxV Power) and similarly `minSPower`and `maxSPower` as parameters for amount of search on input array.

**NOTE**: this code is an assignment under Programming course in Radboud University.This assignment was done on a two-individual group format.All rights are reserved.

## Report
The hypothesis was that for small arrays (few inputs) linear search beats binary search
because small arrays increase the likelihood to get its best case O(1). While it will lose on
larger arrays and more searches. As for the worst case, linear search has O(N) and binary
search O(log N).

By performing the tests, we see that linear search indeed performs better on small input
arrays than binary search. This is because linear search moves linearly through the array.
While binary search always makes extra steps in splitting the array, which performs poorly
on small arrays.

This means the more input elements we have (larger array) the slower linear search
performs in comparison to binary search.
For small arrays with few inputs the speed goes towards its best case O(1) where it beats
binary search which has the best case O(1), but here it rather depends on the chance that
the index we start at directly hits the number we search for.

As assumed the more inputs we have the more binary search outperforms linear search as it
has the worst case O(log N) in comparison to linear searches O(N).
