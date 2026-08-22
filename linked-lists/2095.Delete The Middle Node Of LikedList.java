/************************************ JAVA **********************************/

// Optimal Solution:- 

class Solution {
    public ListNode deleteMiddle(ListNode head) {
        // If the list has only one node, return null (deleting the only node)
        if (head.next == null) {
            return null;
        }
        // Initialize two pointers: 'fast' moves twice as fast as 'slow'
        ListNode fast = head.next.next;
        ListNode slow = head;
        // Walk down the list with the fast and slow pointers
        while (fast != null && fast.next != null) {
            fast = fast.next.next;  // Move 'fast' two steps at a time
            slow = slow.next;       // Move 'slow' one step at a time
        }
        // After the loop, 'slow' is at the node before the middle node
        slow.next = slow.next.next; // Remove the middle node by skipping it
        // Return the modified list
        return head;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).