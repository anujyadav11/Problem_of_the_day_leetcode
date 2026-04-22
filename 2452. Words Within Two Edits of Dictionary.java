/*********************************************** JAVA **************************************************/

// Optimal Solution - Filters queries within 2 character edits of any dictionary word using brute-force position-wise comparison with early termination on excess mismatches.
/* "All words have equal length so position-wise comparison works directly — no insertions or deletions to handle, only substitutions. 
    The two early exits are key for efficiency: diff > 2 skips the rest of the current word, and break after finding a valid match skips remaining dictionary words for that query. For large inputs, BK-tree or trie-based approaches can improve lookup time." */

class Solution {
    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        List<String> res = new ArrayList<>();
        for (String query : queries) {
            for (String word : dictionary) {
                int diff = 0;
                // count character mismatches at each position
                for (int i = 0; i < query.length(); i++) {
                    if (query.charAt(i) != word.charAt(i))
                        diff++;
                    // early exit — already exceeds 2 edits
                    if (diff > 2)
                        break;
                }
                if (diff <= 2) {
                    // query is within 2 edits of this dictionary word — add and move on
                    res.add(query);
                    break;
                }
            }
        }
        return res;
    }
}

// Time Complexity :- O(q × d × L). - q queries, d dictionary words, L word length per comparison
// Space Complexity :- O(q). 
