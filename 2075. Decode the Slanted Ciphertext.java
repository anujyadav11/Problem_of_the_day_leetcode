/*********************************************** JAVA **************************************************/

// Optimal Solution - Decodes cipher text by reading diagonals of the encoded matrix, stepping by (columns+1) per diagonal and stripping trailing padding spaces.
/* "The key insight is that diagonal traversal in a 2D matrix maps to a fixed step size of columns + 1 in the flattened 1D string — one column right plus one row down. 
    Starting each diagonal at col (row 0) and iterating all columns start points reconstructs the original reading order." */

class Solution {
    public String decodeCiphertext(String encodedText, int rows) {
        int l = encodedText.length();
        // number of columns = total length / rows
        int columns = l / rows;
        StringBuilder originalText = new StringBuilder();
        // each diagonal starts at a different column index
        for (int col = 0; col < columns; col++) {
            // traverse diagonal: each step moves one row down and one column right
            // in flattened index that means jumping by (columns + 1)
            for (int j = col; j < l; j += (columns + 1))
                originalText.append(encodedText.charAt(j));
        }
        // remove trailing spaces added during encoding padding
        while (originalText.length() > 0 &&
                originalText.charAt(originalText.length() - 1) == ' ')
            originalText.deleteCharAt(originalText.length() - 1);
        // return the decoded original text
        return originalText.toString();
    }
}

// Time Complexity :- O(l). length of given string.
// Space Complexity :- O(1).
