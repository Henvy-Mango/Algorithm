/*
 * @lc app=leetcode.cn id=141 lang=java
 *
 * [141] 环形链表
 */

// @lc code=start
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
        ListNode p1 = head,p2 = head;

        while(p1!=null&&p1.next!=null){
            p1= p1.next.next;

            p2 = p2.next;

            if(p1==p2){
                return true;
            }

        }
        
        return false;
    }
}
// @lc code=end

