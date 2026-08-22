/*********************************************** JAVA **************************************************/

// Optimal Solution - Maps each word to a letter by summing character weights modulo 26 and converting to the corresponding lowercase letter.
/* "'z' - sum vs 'a' + sum is a direction bug — both compile cleanly but produce reversed mappings. Always verify direction: 'a' + 0 = 'a' and 'a' + 25 = 'z' is the standard forward mapping. 
'z' - 0 = 'z' reverses it. This type of off-by-direction bug is common in character mapping problems — test with sum = 0 to verify immediately." */
class Solution {
    public String mapWordWeights(String[] words, int[] weights) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            int sum = 0;
            // sum weighted values of each character
            for (char ch : word.toCharArray())
                sum += weights[ch - 'a'];
            // map sum to character via modulo 26
            sum = sum % 26;
            sb.append((char) ('a' + sum));
        }
        return sb.toString();
    }
}

// Time Complexity :- O(n * l).
// Space Complexity :- O(n).
