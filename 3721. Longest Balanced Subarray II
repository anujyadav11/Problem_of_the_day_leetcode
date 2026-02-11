/*********************************************** JAVA **************************************************/

Optimal Solution - Prefix-balance tracking with lazy segment tree to compute the longest balanced subarray in O(n log n).
                   Convert the problem into a prefix balance equality problem and use a segment tree to efficiently handle range updates and earliest balance lookup.

class Solution {
    // Node structure for Segment Tree
    static class Node {
        int l, r;     // segment range [l, r]
        int mn, mx;   // minimum and maximum value in this segment
        int lazy;     // lazy propagation value
    }
    // Segment Tree supporting range add and range min/max tracking
    static class SegmentTree {
        Node[] tr;
        SegmentTree(int n) {
            // Allocate 4*n nodes (safe size for segment tree)
            tr = new Node[n << 2];
            for (int i = 0; i < tr.length; i++) {
                tr[i] = new Node();
            }
            // Build tree for range [0, n]
            build(1, 0, n);
        }
        // Build the tree structure
        void build(int u, int l, int r) {
            tr[u].l = l;
            tr[u].r = r;
            // Initialize values
            tr[u].mn = tr[u].mx = 0;
            tr[u].lazy = 0;
            // Leaf node
            if (l == r) return;
            int mid = (l + r) >> 1;
            // Build left and right children
            build(u << 1, l, mid);
            build(u << 1 | 1, mid + 1, r);
        }
        // Range add operation
        void modify(int u, int l, int r, int v) {
            // If fully covered, apply lazy update
            if (tr[u].l >= l && tr[u].r <= r) {
                apply(u, v);
                return;
            }
            // Push lazy before going deeper
            pushdown(u);
            int mid = (tr[u].l + tr[u].r) >> 1;
            if (l <= mid) modify(u << 1, l, r, v);
            if (r > mid) modify(u << 1 | 1, l, r, v);
            // Update current node after children update
            pushup(u);
        }
        // Query to find first position where target lies in range
        int query(int u, int target) {
            // If leaf node, return its index
            if (tr[u].l == tr[u].r) {
                return tr[u].l;
            }
            pushdown(u);
            int left = u << 1;
            int right = u << 1 | 1;
            // Prefer searching in left subtree if target fits range
            if (tr[left].mn <= target && target <= tr[left].mx) {
                return query(left, target);
            }
            return query(right, target);
        }
        // Apply lazy update to node
        void apply(int u, int v) {
            tr[u].mn += v;
            tr[u].mx += v;
            tr[u].lazy += v;
        }
        // Update current node from children
        void pushup(int u) {
            tr[u].mn = Math.min(tr[u << 1].mn, tr[u << 1 | 1].mn);
            tr[u].mx = Math.max(tr[u << 1].mx, tr[u << 1 | 1].mx);
        }
        // Push lazy values down to children
        void pushdown(int u) {
            if (tr[u].lazy != 0) {
                apply(u << 1, tr[u].lazy);
                apply(u << 1 | 1, tr[u].lazy);
                tr[u].lazy = 0;
            }
        }
    }
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        // Segment tree over prefix range
        SegmentTree st = new SegmentTree(n);
        // Map to track last occurrence of each number
        Map<Integer, Integer> last = new HashMap<>();
        int now = 0;   // current prefix balance
        int ans = 0;   // maximum balanced length
        for (int i = 1; i <= n; i++) {
            int x = nums[i - 1];
            // +1 for odd, -1 for even
            int det = (x & 1) == 1 ? 1 : -1;
            // If number seen before, rollback its previous effect
            if (last.containsKey(x)) {
                st.modify(1, last.get(x), n, -det);
                now -= det;
            }
            // Update last occurrence
            last.put(x, i);
            // Apply current contribution
            st.modify(1, i, n, det);
            now += det;
            // Find earliest prefix position with same balance
            int pos = st.query(1, now);
            ans = Math.max(ans, i - pos);
        }
        return ans;
    }
}

Time Complexity :- O().
Space Complexity :- O().
