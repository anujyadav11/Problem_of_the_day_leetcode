/*********************************************** JAVA **************************************************/

Optimal Solution - Repeatedly merge the adjacent pair with the minimum sum until the array becomes non-decreasing.
                  “I simulate the process greedily by always merging the smallest adjacent pair until the sequence becomes non-decreasing.”

class Solution {
    public int minimumPairRemoval(int[] nums) {
        // Convert array to list for easy removal and updates
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : nums) {
            list.add(num);
        }
        int operations = 0;
        // Continue until the array becomes non-decreasing
        while (!isNonDecreasing(list)) {
            int minSum = Integer.MAX_VALUE;
            int index = 0;
            // Find adjacent pair with minimum sum
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    index = i;
                }
            }
            // Replace the pair with their sum
            list.set(index, minSum);
            // Remove the second element of the pair
            list.remove(index + 1);
            operations++;
        }
        return operations;
    }
    // Checks if the list is non-decreasing
    private boolean isNonDecreasing(ArrayList<Integer> list) {
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) < list.get(i - 1)) {
                return false;
            }
        }
        return true;
    }
}

Time Complexity :- O(n^2).
Space Complexity :- O(n).
