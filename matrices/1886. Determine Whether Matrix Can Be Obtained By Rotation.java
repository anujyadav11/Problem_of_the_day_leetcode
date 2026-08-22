/*********************************************** JAVA **************************************************/

// Optimal Solution - Checks if a matrix matches a target by simulating all four 90° clockwise rotations using the standard index mapping (i,j)→(j, r-1-i).
/* "There are exactly 4 distinct rotations for any matrix — checking all four with the 90° clockwise formula (i,j)→(j, r-1-i) covers every case. 
    The trick is to rotate in-place iteratively rather than hardcoding each rotation separately." */

class Solution {
    public boolean findRotation(int[][] mat, int[][] target) {
        // try all 4 rotations: 0°, 90°, 180°, 270°
        for (int n = 0; n < 4; n++) {
            // check if current rotation matches target
            if (Arrays.deepEquals(target, mat))
                return true;
            // rotate mat 90° clockwise for next iteration
            mat = rotate(mat);
        }
        // no rotation matched target
        return false;
    }
    public int[][] rotate(int[][] mat) {
        // size of the square matrix
        int r = mat.length;
        // allocate new matrix for rotated result
        int[][] rotated = new int[r][r];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                // 90° clockwise rotation formula: (i,j) → (j, r-1-i)
                rotated[j][r - 1 - i] = mat[i][j];
            }
        }
        // return the rotated matrix
        return rotated;
    }
}

// Time Complexity :- O(n^2).
// Space Complexity :- O(n^2).
