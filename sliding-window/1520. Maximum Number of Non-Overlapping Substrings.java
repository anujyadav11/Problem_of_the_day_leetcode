/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds the maximum number of non-overlapping substrings by expanding character ranges and applying earliest-finish-time greedy selection.

/* “First, I find the first and last occurrence of every character. For each character, I try to construct the smallest substring containing all occurrences of every character inside it. 
    While scanning the interval, if I find a character whose first occurrence is before the current left boundary, the interval is invalid. Otherwise, 
    I expand the right boundary to include all occurrences of that character.
    After generating all valid intervals, the problem becomes selecting the maximum number of non-overlapping intervals. 
    I sort them by ending position and greedily choose the interval that finishes earliest whenever it doesn’t overlap the previously selected interval. 
    This leaves the most room for future substrings.” */

class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();
        int[] start = new int[26];
        int[] end = new int[26];
        Arrays.fill(start, -1);
        // Find first and last occurrence of every character
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            if (start[c] == -1) {
                start[c] = i;
            }
            end[c] = i;
        }
        List<int[]> intervals = new ArrayList<>();
        // Build valid intervals for each character
        for (int c = 0; c < 26; c++) {
            if (start[c] == -1)
                continue;
            int left = start[c];
            int right = end[c];
            boolean valid = true;
            for (int i = left; i <= right; i++) {
                int ch = s.charAt(i) - 'a';
                // This character starts before our interval
                if (start[ch] < left) {
                    valid = false;
                    break;
                }
                // Expand interval if necessary
                right = Math.max(right, end[ch]);
            }
            if (valid) {
                intervals.add(new int[] { left, right });
            }
        }
        // Select maximum number of non-overlapping intervals
        intervals.sort(Comparator.comparingInt(a -> a[1]));
        List<String> result = new ArrayList<>();
        int lastEnd = -1;
        for (int[] interval : intervals) {
            if (interval[0] > lastEnd) {
                result.add(s.substring(interval[0], interval[1] + 1));
                lastEnd = interval[1];
            }
        }
        return result;
    }
}

// Time Complexity :- O(n). 
// Space Complexity :- O(n) - for the result/string output; auxiliary work is O(1) apart from the interval list.
