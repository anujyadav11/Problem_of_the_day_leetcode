/*********************************************** JAVA **************************************************/

// Brute Force Solution - Find the shortest substring containing exactly k ones and return the lexicographically smallest valid substring.
/* “I try substring lengths from smallest to largest. For each length, I examine every substring and count its number of ones. If it contains exactly k ones, 
    I keep the lexicographically smallest candidate. As soon as I find a valid substring for a particular length, I return it because we’re processing lengths in increasing order.” */

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String res = "";
        // Try every possible substring length
        for (int len = 1; len <= n; len++) {
            // Check all substrings of this length
            for (int start = 0; start <= n - len; start++) {
                String temp = s.substring(start, start + len);
                // Count number of 1s in the substring
                int count1 = 0;

                for (char ch : temp.toCharArray()) {
                    if (ch == '1') {
                        count1++;
                    }
                }
                // Found a beautiful substring
                if (count1 == k) {
                    // Keep lexicographically smaller substring
                    if (res.length() == 0 || temp.compareTo(res) < 0) {
                        res = temp;
                    }
                }
            }
            // First valid length is the shortest length
            if (!res.isEmpty()) {
                return res;
            }
        }
        // No substring contains exactly k ones
        return "";
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n).


/*********************************************** JAVA **************************************************/

// Optimal Solution - Find the shortest substring containing exactly k ones using a sliding window with lexicographical tie-breaking.
/* “I maintain a sliding window and count the number of ones inside it. Whenever the count exceeds k, I move the left pointer forward. 
    I also remove leading zeros because they don’t contribute to the number of ones and only make the substring longer. Whenever the window contains exactly k ones, 
    I compare it with the current answer based first on length and then lexicographical order.” */

class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        // Initialize the result as an empty string
        String res = "";
        // Initialize two pointers, i and j, for sliding window and a counter for '1's
        int i = 0, j = 0, count = 0, n = s.length();
        // Traverse the string with the right pointer `j`
        while (j < n) {
            // If the current character is '1', increment the counter
            if (s.charAt(j) == '1')
                count++;
            // When the count of '1's equals k, process the window
            if (count == k) {
                // Move the left pointer `i` to find the smallest valid substring
                while (i < n && count == k) {
                    // Extract the current substring from `i` to `j`
                    String s1 = s.substring(i, j + 1);
                    // Update the result if it's empty or the current substring is shorter
                    if (res.isEmpty() || s1.length() < res.length())
                        res = s1;
                    // If lengths are equal, keep the lexicographically smaller one
                    else if (s1.length() == res.length())
                        res = (res.compareTo(s1) < 0) ? res : s1;
                    // If the character at `i` is '1', decrement the counter
                    if (s.charAt(i) == '1')
                        count--;
                    // Move the left pointer `i` forward
                    i++;
                }
            }
            // Move the right pointer `j` forward
            j++;
        }
        // Return the shortest beautiful substring found
        return res;
    }
}


// Time Complexity :- O(n).
// Space Complexity :- O(n).
