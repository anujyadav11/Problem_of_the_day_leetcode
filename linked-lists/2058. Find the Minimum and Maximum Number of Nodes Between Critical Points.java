/*********************************************** JAVA **************************************************/

// Optimal Solution - Find minimum and maximum distances between critical points using a single linked-list traversal.
/* “I traverse the linked list once while maintaining the previous, current, and next nodes to identify local minima and maxima. For each critical point, I track its index. 
    The distance from the previous critical point gives the minimum distance, while the distance from the first critical point to the latest critical point gives the maximum distance. 
    If fewer than two critical points exist, I return [-1, -1].” */

class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // First and previous critical point indices
        int firstCpi = -1;
        int prevCpi = -1;
        // Current node's index
        int currIndex = 1;
        ListNode curr = head.next;
        ListNode prev = head;
        // res[0] = minimum distance
        // res[1] = maximum distance
        int res[] = new int[2];
        res[0] = Integer.MAX_VALUE;
        // Last node cannot be a critical point
        while (curr.next != null) {
            ListNode nextNode = curr.next;
            // Check if current node is a local min or max
            if ((curr.val < nextNode.val && curr.val < prev.val) ||
                (curr.val > nextNode.val && curr.val > prev.val)) {
                // First critical point
                if (prevCpi == -1) {
                    firstCpi = currIndex;
                    prevCpi = currIndex;
                } else {
                    // Distance from previous critical point
                    res[0] = Math.min(res[0], currIndex - prevCpi);
                    prevCpi = currIndex;
                }
            }
            prev = prev.next;
            curr = curr.next;
            currIndex++;
        }
        // Calculate maximum distance using first and last points
        if (firstCpi != -1 && res[0] != Integer.MAX_VALUE) {
            res[1] = prevCpi - firstCpi;
        } else {
            // Fewer than two critical points
            res[0] = -1;
            res[1] = -1;
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
