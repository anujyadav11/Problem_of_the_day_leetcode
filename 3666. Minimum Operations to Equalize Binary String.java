/*********************************************** JAVA **************************************************/

// Optimal Solution - Model zero-count transitions as a BFS state graph and use TreeSet range queries to compute minimum operations efficiently.

class Solution {
    public int minOperations(String s, int k) {
        int n = s.length();   // Length of binary string
        int startZeros = 0;   // Count initial number of zeros
        // Count number of '0's in string
        for (char ch : s.toCharArray()) {
            if (ch == '0')
                startZeros++;
        }
        // If no zeros exist, already valid
        if (startZeros == 0)
            return 0;
        
        // operations[i] = minimum operations to reach i zeros
        int[] operations = new int[n + 1];
        Arrays.fill(operations, -1);
        
        // Separate unvisited states by parity
        TreeSet<Integer> evenSet = new TreeSet<>();
        TreeSet<Integer> oddSet = new TreeSet<>();
        
        // Add all possible zero counts (0 → n)
        for (int count = 0; count <= n; count++) {
            if (count % 2 == 0)
                evenSet.add(count);
            else
                oddSet.add(count);
        }
        // BFS queue
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startZeros);
        operations[startZeros] = 0;  // Starting state cost = 0
        // Remove start from available states
        if (startZeros % 2 == 0)
            evenSet.remove(startZeros);
        else
            oddSet.remove(startZeros);
        // BFS traversal
        while (!queue.isEmpty()) {
            int z = queue.poll();   // Current zero count
            // Compute possible range of new zero counts
            int minNewZ = z + k - 2 * Math.min(k, z);
            int maxNewZ = z + k - 2 * Math.max(0, k - n + z);
            // Choose parity set based on minNewZ
            TreeSet<Integer> currSet = (minNewZ % 2 == 0) ? evenSet : oddSet;
            // Find first valid candidate ≥ minNewZ
            Integer val = currSet.ceiling(minNewZ);
            while (val != null && val <= maxNewZ) {
                int newZ = val;
                // Update operations count
                operations[newZ] = operations[z] + 1;
                // If reached zero → return answer
                if (newZ == 0)
                    return operations[newZ];
                queue.offer(newZ);
                currSet.remove(val);  // Mark visited
                val = currSet.ceiling(minNewZ);
            }
        }
        return -1;  // If not reachable
    }
}

// Time Complexity :- O(n log n).
// Space Complexity :- O(n).
