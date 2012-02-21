public class Random {

    void binarySearch(int[] array, int lowerbound, int upperbound, int key) {
        int position;

        // To start, find the subscript of the middle position.
        position = (lowerbound + upperbound) / 2;

        while ((array[position] != key) && (lowerbound <= upperbound)) {
            if (array[position] > key) // If the number is > key, ..
            { // decrease position by one.
                upperbound = position - 1;
            } else { // Else, increase position by one.
                lowerbound = position + 1;
            }
            position = (lowerbound + upperbound) / 2;
        }
        if (lowerbound <= upperbound) {
            // cout<< "The number was found in array subscript "<<
            // position<<endl<<endl;
            // cout<< "The binary search found the number after " <<
            // comparisonCount
            // << " comparisons.\n";
            // printing the number of comparisons is optional
        } else
            // cout<<
            // "Sorry, the number is not in this array. The binary search made "
            // <<comparisonCount << " comparisons.";
            return; // you may also consider returning the subscript
    }
}