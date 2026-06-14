/*********************************************** JAVA **************************************************/

// Optimal Solution - Finds maximum twin sum by reversing the first half in-place during fast/slow pointer traversal, then pairing nodes from both halves.
/*  "Combining midpoint finding with in-place reversal in one pass is the O(1) space trick — instead of storing values in an array. 
    The fast pointer termination fast != null && fast.next != null is the standard even-length list check — always verify both before accessing .next.next. When fast exits, slow lands exactly at the second half start." */

class Solution {
    public int pairSum(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        // use fast pointer for termination with null safety
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            // reverse first half while finding midpoint
            ListNode temp = slow.next;
            slow.next = prev;
            prev = slow;
            slow = temp;
        }
        // slow is now at second half start, prev is reversed first half
        int res = 0;
        while (slow != null) {
            res = Math.max(res, prev.val +  slow.val);
            prev = prev.next;
            slow = slow.next;
        }
        return res;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
