/*********************************************** JAVA **************************************************/

// Optimal Solution - Counts zigzag arrays using matrix exponentiation on a state transition matrix encoding valid up/down value transitions between consecutive elements.
/* "Matrix exponentiation converts 'count sequences of length n' to O(m³ log n) — ideal when n is huge but state space m is small. The zero-skip optimization if (A[row][mid] == 0) continue significantly speeds up sparse matrices. 
    Compared to the DP approach, this scales better for large n but worse for large value ranges." */

class Solution {
    private static final long MOD = 1_000_000_007L;

    // matrix multiplication with modulo — O(size³)
    private long[][] multiply(long[][] A, long[][] B) {
        int size = A.length;
        long[][] result = new long[size][size];
        for (int row = 0; row < size; row++) {
            for (int mid = 0; mid < size; mid++) {
                if (A[row][mid] == 0) continue;
                long cur = A[row][mid];
                for (int col = 0; col < size; col++) {
                    if (B[mid][col] == 0) continue;
                    result[row][col] = (result[row][col] + cur * B[mid][col]) % MOD;
                }
            }
        }
        return result;
    }

    public int zigZagArrays(int n, int l, int r) {
        int valueCount = r - l + 1;
        // states: [0, valueCount) = up states, [valueCount, 2*valueCount) = down states
        int stateSize = 2 * valueCount;
        long[][] transition = new long[stateSize][stateSize];
        for (int x = 0; x < valueCount; x++) {
            // from up-state x: can go to down-state y where y > x
            for (int y = x + 1; y < valueCount; y++)
                transition[x][valueCount + y] = 1;
            // from down-state x: can go to up-state y where y < x
            for (int y = 0; y < x; y++)
                transition[valueCount + x][y] = 1;
        }
        // identity matrix for matrix exponentiation
        long[][] result = new long[stateSize][stateSize];
        for (int i = 0; i < stateSize; i++)
            result[i][i] = 1;
        // compute transition^(n-1) via fast matrix exponentiation
        long power = n - 1;
        while (power > 0) {
            if ((power & 1) == 1)
                result = multiply(result, transition);
            transition = multiply(transition, transition);
            power >>= 1;
        }
        // sum all reachable states from any starting state
        long answer = 0;
        for (int row = 0; row < stateSize; row++) {
            long rowSum = 0;
            for (int col = 0; col < stateSize; col++)
                rowSum = (rowSum + result[row][col]) % MOD;
            answer = (answer + rowSum) % MOD;
        }
        return (int) answer;
    }
}

// Time Complexity :- O(m³ log n). — m = 2×valueCount; matrix multiply is O(m³), done O(log n) times
// Space Complexity :- O(m²).— two matrices of size m×m
