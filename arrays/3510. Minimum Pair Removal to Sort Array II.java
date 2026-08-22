/*********************************************** JAVA **************************************************/

Optimal Solution - Greedily merge the adjacent pair with minimum sum using a TreeSet and linked list until the array becomes non-decreasing.
                    I optimized the greedy solution by maintaining adjacent pair sums in a TreeSet and dynamically updating a linked list, reducing the complexity to O(n log n).

class Solution {
    // Node represents an element in a doubly linked list
    // and also stores sum with its next neighbor
    class Node implements Comparable<Node> {
        int idx;        // original index (tie-breaker)
        long val;       // current value after merges
        long sum;       // val + next.val
        Node prev, next;
        public Node(int idx, long val) {
            this.idx = idx;
            this.val = val;
        }
        // TreeSet ordering:
        // 1) smaller adjacent sum first
        // 2) if tie, smaller index first
        @Override
        public int compareTo(Node other) {
            if (this.next == null || other.next == null) {
                return this.next == null ? 1 : -1;
            }
            long diff = this.sum - other.sum;
            return diff != 0 ? (diff < 0 ? -1 : 1)
                             : (this.idx - other.idx);
        }
    }
    public int minimumPairRemoval(int[] nums) {
        TreeSet<Node> set = new TreeSet<>();
        int descents = 0;        // count of decreasing adjacent pairs
        int n = nums.length;
        Node prev = null;
        // Build doubly linked list + TreeSet
        for (int i = 0; i < n; i++) {
            Node cur = new Node(i, nums[i]);
            if (prev != null) {
                if (cur.val < prev.val) descents++;
                prev.next = cur;
                cur.prev = prev;
                prev.sum = prev.val + cur.val;
                set.add(prev);
            }
            prev = cur;
        }
        // Add last node
        set.add(prev);
        int operations = 0;
        // Keep merging until array becomes non-decreasing
        while (descents > 0) {
            operations++;
            // Get adjacent pair with minimum sum
            Node first = set.pollFirst();
            Node second = first.next;
            // Update descent count
            if (second.val < first.val) descents--;
            // Merge nodes
            first.val += second.val;
            first.sum = first.val + (second.next == null ? 0 : second.next.val);
            first.next = second.next;
            if (second.next != null) {
                if (second.next.val < second.val) descents--;
                second.next.prev = first;
                if (first.val > second.next.val) descents++;
            }
            set.remove(second);
            set.add(first);
            // Fix previous node relations
            Node p = first.prev;
            if (p != null) {
                set.remove(p);
                if (p.val > p.sum - p.val) descents--;
                if (p.val > first.val) descents++;
                p.sum = p.val + first.val;
                p.next = first;
                set.add(p);
            }
        }
        return operations;
    }
}

Time Complexity :- O(n log n).
Space Complexity :- O(n).
