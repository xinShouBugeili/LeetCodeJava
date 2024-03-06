/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {


    public boolean hasCycle(ListNode head) {
    
        // check if input is null
        if (head == null) {
            return false;
        }

        //快慢指针的原点应该是head，然后各自有自己的变量自增
        ListNode slow = head;
        ListNode fast = head;

        while (slow !=null && fast != null ) {
            slow = slow.next;
            fast = fast.next;
            if (fast == null) {
                return false;
            }
            fast = fast.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
