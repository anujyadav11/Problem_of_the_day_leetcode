/*********************************************** JAVA **************************************************/

// Optimal Solution - Rotates linked list right by k by forming a circular list, finding the new tail at position length-k, then breaking the circle at that point.
/* "The circular list trick avoids array conversion — connect tail to head, find the new break point, split. The k % length handles cases where k > length — rotating by length is a no-op. 
    New tail is at position length - k from original head, new head is the node right after it." */

class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        // empty or single node — no rotation needed
        if (head == null || head.next == null) return head;
        // find length and tail node
        int length = 1;
        ListNode tail = head;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        // form circular list
        tail.next = head;
        // effective rotation — find new tail position
        k = k % length;
        k = length - k;
        // traverse to new tail
        while (k > 0) {
            tail = tail.next;
            k--;
        }
        // new head is just after new tail
        head = tail.next;
        // break circular link
        tail.next = null;
        return head;
    }
}

// Time Complexity :- O(n).
// Space Complexity :- O(1).
