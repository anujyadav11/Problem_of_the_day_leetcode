/*********************************************** JAVA **************************************************/

// Optimal Solution - Evaluates bracketed keys using a HashMap lookup while scanning the string in linear time.
/* “I first store all knowledge pairs in a HashMap for constant-time lookup. Then I scan the string from left to right. When I encounter an opening parenthesis, 
    I find the closing parenthesis, extract the key, and replace it with its mapped value or ? if the key doesn’t exist. Normal characters are appended directly.” */

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        int n = s.length();
        // Store key -> value mappings from knowledge.
        Map<String, String> map = new HashMap<>();
        for (List<String> vec : knowledge) {
            map.put(vec.get(0), vec.get(1));
        }
        StringBuilder res = new StringBuilder();
        int i = 0;
        // Process the string from left to right.
        while (i < n) {
            // Found the beginning of a bracket pair.
            if (s.charAt(i) == '(') {
                // Find the corresponding closing bracket.
                int j = s.indexOf(")", i + 1);
                // Extract the key between '(' and ')'.
                String temp = s.substring(i + 1, j);
                // Replace the key with its mapped value.
                // If the key doesn't exist, use '?'.
                res.append(map.getOrDefault(temp, "?"));
                // Move to the closing bracket.
                // The i++ at the bottom moves past it.
                i = j;
            } else {
                // Normal character: append it directly.
                res.append(s.charAt(i));
            }
            // Move to the next character.
            i++;
        }
        return res.toString();
    }
}v

// Time Complexity :- O(n + m).
// Space Complexity :- O(m).
