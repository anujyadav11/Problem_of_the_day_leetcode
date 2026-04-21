/*********************************************** JAVA **************************************************/

// Optimal Solution - Minimizes Hamming distance by grouping swappable indices with Union-Find and greedily matching source elements to target values within each connected component.
/* "Union-Find is perfect here — transitive swaps form connected components where any permutation is achievable. Grouping source values by component root and greedily matching target values gives the minimum Hamming distance in O(n α(n)). 
    Path compression in find is essential for efficiency — without it, deep chains degrade to O(n) per query." */

class Solution {
    public int minimumHammingDistance(int[] source, int[] target, int[][] allowedSwaps) {
        int N = source.length;
        int[] union = new int[N];
        // initialize each node as its own parent
        for (int i = 0; i < N; i++)
            union[i] = i;
        // union-find: merge indices connected by allowed swaps
        for (int[] swap : allowedSwaps) {
            int parentA = find(union, swap[0]);
            int parentB = find(union, swap[1]);
            // merge two groups if not already connected
            if (parentA != parentB)
                union[parentA] = parentB;
        }
        // group source elements by their union-find root
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int root = find(union, i);
            map.putIfAbsent(root, new HashMap<>());
            Map<Integer, Integer> store = map.get(root);
            // count frequency of each source value per group
            store.put(source[i], store.getOrDefault(source[i], 0) + 1);
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            int root = find(union, i);
            Map<Integer, Integer> store = map.get(root);
            if (store.getOrDefault(target[i], 0) == 0)
                // target[i] not available in this group — hamming distance +1
                res++;
            else
                // consume one occurrence of target[i] from group
                store.put(target[i], store.get(target[i]) - 1);
        }
        return res;
    }
    private int find(int[] union, int node) {
        // path compression — flatten tree for O(α) amortized lookup
        if (union[node] == node) return node;
        return union[node] = find(union, union[node]);
    }
}

// Time Complexity :- O(n α(n)).
// Space Complexity :- O(n).
