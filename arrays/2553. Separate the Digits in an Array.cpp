/*********************************************** JAVA **************************************************/

// Optimal Solution - Separates array numbers into ordered digits using string conversion and character-to-integer mapping, collecting results into a flat integer array.
/* "String conversion is the cleanest approach for digit extraction in order — no reversal needed unlike the % 10 method. ch - '0' is the standard char-to-digit conversion in Java. 
    The only tradeoff is string allocation per number — for very large arrays the % 10 approach with reversal avoids string overhead but adds code complexity." */

class Solution {
    public int[] separateDigits(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) {
            // convert number to string to extract digits in order
            for (char ch : String.valueOf(num).toCharArray())
                list.add(ch - '0');
        }
        // convert list to primitive array
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++)
            result[i] = list.get(i);
        return result;
    }
}

// Time Complexity :- O(n * d).
// Space Complexity :- O(n * d).
