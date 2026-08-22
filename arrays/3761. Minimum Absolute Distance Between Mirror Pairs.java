/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds minimum distance between mirror pairs by mapping each number's reverse to its index and checking if the current number was previously stored as a reverse.
/* "The key insight is storing reverse(nums[i]) as the key — so when a future number nums[j] is looked up, a hit means nums[j] == reverse(nums[i]), confirming a mirror pair. 
    This avoids reversing both numbers for comparison and keeps each lookup O(1). Always store the latest index to minimize distance." */

class Solution {
    // reverse digits of a number — O(log10(n))
    public int getReverse(int n) {
        int rev = 0;
        while (n > 0) {
            int rem = n % 10;
            rev = rev * 10 + rem;
            n /= 10;
        }
        return rev;
    }
    public int minMirrorPairDistance(int[] nums) {
        int n = nums.length;
        // maps reversed value to its most recent index
        HashMap<Integer, Integer> mp = new HashMap<>();
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // check if reverse of nums[i] was seen before
            if (mp.containsKey(nums[i])) {
                // found a mirror pair — update minimum distance
                result = Math.min(result, i - mp.get(nums[i]));
            }
            // store reverse of current number mapped to current index
            mp.put(getReverse(nums[i]), i);
        }
        // return -1 if no mirror pair found
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}

// Time Complexity :- O(n × log10(maxVal)).
// Space Complexity :- O(n).
