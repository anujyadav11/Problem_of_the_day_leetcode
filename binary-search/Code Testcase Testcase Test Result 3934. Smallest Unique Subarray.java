/*********************************************** JAVA **************************************************/

// Optimal Solution - Implemented binary search on the answer with power-of-two block encoding, coordinate compression, and sorted window identifiers to detect unique subarrays in O(n log² n) time and O(n log n) auxiliary space.
/* "I need to find the smallest subarray length for which at least one subarray occurs exactly once. I use binary search on the candidate length because uniqueness is monotonic with increasing window length. 
    To compare windows efficiently, I build identifiers for power-of-two blocks. Each window is represented by two overlapping blocks whose identifiers together cover the complete window. 
    I sort these encoded representations and check whether any occurs exactly once. This avoids directly comparing entire subarrays." */

class Solution {
    public int smallestUniqueSubarray(int[] nums) {
        // Get the length of the input array.
        int n = nums.length;
        // names.get(k)[i] represents the unique ID of the block
        // of length 2^k starting at index i.
        //
        // Equal blocks of the same length receive the same ID.
        List<int[]> names = new ArrayList<>();
        // Level 0: blocks of length 1.
        // Each element is its own block ID.
        names.add(nums);
        // Build identifiers for blocks of length:
        // 2, 4, 8, 16, ...
        //
        // half represents the length of each half-block.
        for (int half = 1; 2 * half <= n; half *= 2) {
            // Combine two adjacent blocks of length 'half'
            // to represent a block of length '2 * half'.
            long[] pairs = pairs(names.get(names.size() - 1), half);
            // Sort all encoded pairs and remove duplicates.
            // Each distinct pair represents a unique block.
            long[] ids = Arrays.stream(pairs)
                               .sorted()
                               .distinct()
                               .toArray();
            // Convert every encoded pair into its compressed ID.
            // Equal pairs receive the same ID.
            names.add(
                Arrays.stream(pairs)
                      .mapToInt(pair -> Arrays.binarySearch(ids, pair))
                      .toArray()
            );
        }
        // Binary search for the smallest valid window length.
        // The answer lies between 1 and n.
        int lo = 1, hi = n;
        while (lo < hi) {
            // Check the middle candidate length.
            int mid = (lo + hi) / 2;
            // If a unique window exists at this length,
            // try smaller lengths.
            if (found(names, mid)) {
                hi = mid;
            } else {
                // Otherwise, search for a larger length.
                lo = mid + 1;
            }
        }
        // Return the smallest length that contains
        // at least one unique subarray.
        return lo;
    }
    // Checks whether at least one window of the given length
    // occurs exactly once in the entire array.
    private boolean found(List<int[]> names, int length) {
        // Find the largest power of two not exceeding length.
        //
        // k = floor(log2(length))
        // 2^k is the size of the block used for comparison.
        int k = 31 - Integer.numberOfLeadingZeros(length);
        // Encode two overlapping blocks:
        // 1. First block starts at i.
        // 2. Second block starts at i + length - 2^k.
        //
        // Together, they cover the complete window.
        long[] pairs = pairs(
            names.get(k),
            length - (1 << k)
        );
        // Sort encoded windows so identical windows
        // become adjacent.
        Arrays.sort(pairs);
        // Check whether any encoded window occurs exactly once.
        for (int i = 0; i < pairs.length; i++) {
            // A window is unique if it differs from both
            // its previous and next sorted neighbors.
            if ((i == 0 || pairs[i - 1] != pairs[i]) &&
                (i + 1 == pairs.length || pairs[i] != pairs[i + 1])) {

                return true;
            }
        }
        // Every window occurred at least twice.
        return false;
    }
    // Encodes every pair of blocks:
    // (block[i - shift], block[i])
    //
    // Each pair is packed into one long value.
    private long[] pairs(int[] block, int shift) {
        // Number of valid pairs that can be constructed.
        long[] pairs = new long[block.length - shift];
        for (int i = shift; i < block.length; i++) {
            // Store the first block ID in the upper 32 bits
            // and the second block ID in the lower 32 bits.
            //
            // This gives every ordered pair a unique encoding.
            pairs[i - shift] =
                (long) block[i - shift] << 32 | block[i];
        }
        return pairs;
    }
}

// Time Complexity :- O(n log^2 n).
// Space Complexity :- O(n log n).
