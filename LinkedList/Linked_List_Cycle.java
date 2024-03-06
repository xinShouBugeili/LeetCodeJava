// https://leetcode.com/problems/linked-list-cycle/
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

/**
 * 假设从head到环入口是a，
 * 环入口到交点是b
 * 交点到环入口是c
 * 因为快指针走了慢指针2倍
 * 所以慢指针a+b
 * 快指针a+b+n*(b+c)
 * a + b + n * (b + c) = 2 (a + b)
 * a = c + (n - 1) * (b + c)
 * 所以一个从head出发，一个从交点出发，总能在走完a步后在环入口相见
 */