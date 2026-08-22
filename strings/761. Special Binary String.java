/*********************************************** JAVA **************************************************/

Optimal Solution - Recursively splits special binary substrings and sorts them greedily to form lexicographically largest string.
                   Treat each balanced substring like parentheses, recursively optimize inner parts, then sort in descending order for maximum lexicographical result.

class Solution {
    public String makeLargestSpecial(String s) {
        // List to store all top-level special substrings
        List<String> specials = new ArrayList<>();
        // Count keeps track of balance between '1' and '0'
        int count = 0;
        // Marks start of current special substring
        int start = 0;
        // Traverse string
        for (int i = 0; i < s.length(); i++) {
            // Increase count for '1', decrease for '0'
            count += (s.charAt(i) == '1') ? 1 : -1;
            // When balance becomes 0 → found a valid special substring
            if (count == 0) {
                // Extract inner substring (excluding outer 1 and 0)
                String inner = s.substring(start + 1, i);
                // Recursively process inner part
                String processed = "1" + makeLargestSpecial(inner) + "0";
                // Add processed substring to list
                specials.add(processed);
                // Move start pointer
                start = i + 1;
            }
        }
        // Sort substrings in descending lexicographical order
        Collections.sort(specials, Collections.reverseOrder());
        // Combine sorted substrings
        StringBuilder result = new StringBuilder();
        for (String str : specials) {
            result.append(str);
        }
        return result.toString();
    }
}

Time Complexity :- O(N^2).
Space Complexity :- O(N).
