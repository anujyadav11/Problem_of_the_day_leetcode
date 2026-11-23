/*********************************************** JAVA **************************************************/

// Optimal Solution - Implemented prefix products with zero-reset handling to support O(1) insertion and O(1) product queries.
/* "I maintain a list of prefix products instead of calculating each query from scratch. When a new nonzero number arrives, I multiply it by the previous prefix product and store the result. 
    When zero arrives, I clear the list because every product containing that zero must be zero. To answer a query, I divide the latest prefix product by the prefix product immediately before the requested range. 
    This makes both add and getProduct operations O(1)." */

class ProductOfNumbers {
    // Stores prefix products of all numbers added since the last zero.
    List<Integer> list;
    public ProductOfNumbers() {
        // Initialize the prefix product list.
        list = new ArrayList<>();
    }
    public void add(int num) {
        // If the incoming number is zero,
        // all previous products involving this number become zero.
        // Clear the list to start a new prefix product sequence.
        if (num == 0) {
            list.clear();
            return;
        }
        // Get the previous prefix product.
        // If the list is empty, use 1 as the initial product.
        int prev = (list.size() == 0) ? 1 : list.get(list.size() - 1);
        // Calculate and store the new prefix product.
        list.add(prev * num);
    }
    public int getProduct(int k) {
        // Get the number of elements stored since the last zero.
        int s = list.size();
        // If fewer than k numbers exist after the last zero,
        // the last k numbers must contain a zero.
        if (s < k)
            return 0;
        // If exactly k numbers exist,
        // the last prefix product is the required product.
        else if (s == k)
            return list.get(s - 1);
        // Otherwise, divide the latest prefix product
        // by the prefix product before the last k numbers.
        else
            return (list.get(s - 1) / list.get(s - 1 - k));
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(n).
