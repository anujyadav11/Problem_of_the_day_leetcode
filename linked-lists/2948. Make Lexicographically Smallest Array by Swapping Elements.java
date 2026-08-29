/*********************************************** JAVA **************************************************/

// Optimal Solution - Build value groups from sorted adjacent differences and greedily assign the smallest available value from each group.
/* “I first sort a copy of the array. After sorting, values belong to the same group as long as the difference between consecutive values is at most limit. 
    Each group represents values that can be rearranged among their original positions. I map every value to its group and store the group’s values in sorted queues. 
    Finally, for each original position, I take the smallest available value from its group, producing the lexicographically smallest possible array.” */

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        // Create sorted copy of the array
        int temp[] = new int[n];
        for (int i = 0; i < n; i++) {
            temp[i] = nums[i];
        }
        Arrays.sort(temp);
        // Each group stores its available values
        List<Queue<Integer>> list = new ArrayList<>();
        // Map each value to its group
        Map<Integer, Integer> group = new HashMap<>();
        int groupIndex = 0;
        // First value starts the first group
        list.add(new LinkedList<>());
        list.get(groupIndex).offer(temp[0]);
        group.put(temp[0], groupIndex);
        for (int i = 1; i < n; i++) {
            // Start a new group if adjacent sorted values
            // differ by more than limit
            if (temp[i] - temp[i - 1] > limit) {
                groupIndex++;
                list.add(new LinkedList<>());
            }
            group.put(temp[i], groupIndex);
            list.get(groupIndex).offer(temp[i]);
        }
        // Replace each value with the smallest available
        // value from its group
        for (int i = 0; i < n; i++) {
            int gi = group.get(nums[i]);
            nums[i] = list.get(gi).poll();
        }
        return nums;
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
