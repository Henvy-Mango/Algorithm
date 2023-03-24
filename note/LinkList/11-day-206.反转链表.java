/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution1 {
    // 递归解法
    // reverse 递归函数定义是这样的：输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头结点。
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 以 head.next 为起点的链表反转，并返回反转之后的头结点。
        ListNode last = reverseList(head.next);

        // 取最栈底的情况，即最开始的时候
        // 1 -> reverse(2 -> 3) ，括号里当成一个整体，符合递归函数定义
        // 1 -> ( 2 <- 3 )

        // 1 -> 2 <- 3
        //   <-
        head.next.next = head;

        // 1 <- 2 <- 3
        head.next = null;
        return last;
    }
}

class Solution {
    // 迭代解法
    public ListNode reverseList(ListNode head) {
        ListNode pre, cur;
        pre = null;
        cur = head;

        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        // 返回反转后的头结点
        return pre;
    }
}
// @lc code=end

