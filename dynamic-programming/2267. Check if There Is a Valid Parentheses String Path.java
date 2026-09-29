/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks for a valid parentheses path using bottom-up 3D DP over grid position and unmatched-parenthesis balance.
/* “I use a 3D DP where the state contains the current grid position and the number of unmatched opening parentheses. Moving onto an opening parenthesis increases the balance,
    while a closing parenthesis decreases it. A balance can never become negative, and at the destination it must be zero. 
    I process the grid bottom-up and transition to the down and right neighbours using the updated balance.” */

class Solution {
    int m, n;
    // t[i][j][open] =
    // whether there exists a valid path from (i, j)
    // to the bottom-right when the current balance is 'open'.
    boolean[][][] t;
    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        // Maximum possible path length is m + n - 1.
        t = new boolean[101][101][201];
        // A valid parentheses string must have even length.
        if ((m + n - 1) % 2 == 1) {
            return false;
        }
        // The path must start with '('
        // and end with ')'.
        if (grid[0][0] != '(' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        // Process cells from bottom-right towards top-left.
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                // Current balance cannot be greater than
                // the number of cells already processed on the path.
                for (int open = 0; open <= i + j + 1; open++) {
                    // At the destination, the parentheses must
                    // be completely balanced.
                    if (i == m - 1 && j == n - 1) {
                        t[i][j][open] = (open == 0);
                        continue;
                    }
                    // Try moving down.
                    if (i + 1 < m) {
                        int newOpen =(grid[i + 1][j] == '(')? open + 1: open - 1;
                        // The balance can never become negative.
                        if (newOpen >= 0 && t[i + 1][j][newOpen]) {
                            t[i][j][open] = true;
                        }
                    }
                    // Try moving right.
                    if (j + 1 < n && !t[i][j][open]) {
                        int newOpen =(grid[i][j + 1] == '(')? open + 1: open - 1;
                        // The balance can never become negative.
                        if (newOpen >= 0 && t[i][j + 1][newOpen]) {
                            t[i][j][open] = true;
                        }
                    }
                }
            }
        }
        // The starting '(' contributes one opening parenthesis,
        // so we begin with balance = 1.
        return t[0][0][1];
    }
}

// Time Complexity :- O(m × n × (m + n)).
// Space Complexity :- O(m × n × (m + n)).
