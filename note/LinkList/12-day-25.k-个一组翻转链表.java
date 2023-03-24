/*
 * @lc app=leetcode.cn id=25 lang=java
 *
 * [25] K 个一组翻转链表
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
    // 递归 + 迭代
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return null;
        // 区间 [a, b) 包含 k 个待反转元素
        ListNode a, b;
        a = b = head;
        for (int i = 0; i < k; i++) {
            // 不足 k 个，不需要反转，base case
            if (b == null)
                return head;
            b = b.next;
        }
        // 反转前 k 个元素
        ListNode newHead = reverse(a, b);
        // 递归反转后续链表并连接起来
        a.next = reverseKGroup(b, k);
        return newHead;
    }

    /* 反转区间 [a, b) 的元素，注意是左闭右开 */
    ListNode reverse(ListNode a, ListNode b) {
        ListNode pre, cur, nxt;
        pre = null;
        cur = a;
        nxt = a;
        // while 终止的条件改一下就行了
        while (cur != b) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        // 返回反转后的头结点
        return pre;
    }
}

class Solution {
    // 迭代解法
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        // 定义一个假的节点。
        ListNode dummy = new ListNode(0);
        // 假节点的next指向head。
        // dummy->1->2->3->4->5
        dummy.next = head;
        // 初始化pre和end都指向dummy。pre指每次要翻转的链表的头结点的上一个节点。end指每次要翻转的链表的尾节点
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            // 循环k次，找到需要翻转的链表的结尾,这里每次循环要判断end是否等于空,因为如果为空，end.next会报空指针异常。
            // dummy->1->2->3->4->5 若k为2，循环2次，end指向2
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            // 如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
            if (end == null) {
                break;
            }
            // 先记录下end.next,方便后面链接链表
            ListNode next = end.next;
            // 然后断开链表
            end.next = null;
            // 记录下要翻转链表的头节点
            ListNode start = pre.next;
            // 翻转链表,pre.next指向翻转后的链表。1->2 变成2->1。 dummy->2->1
            pre.next = reverse(start);
            // 翻转后头节点变到最后。通过.next把断开的链表重新链接。
            start.next = next;
            // 将pre换成下次要翻转的链表的头结点的上一个节点。即start
            pre = start;
            // 翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end = start;
        }
        return dummy.next;
    }

    // 链表翻转
    // 例子： head： 1->2->3->4
    public ListNode reverse(ListNode head) {
        // 单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null) {
            return head;
        }
        // 前一个节点指针
        ListNode preNode = null;
        // 当前节点指针
        ListNode curNode = head;
        // 下一个节点指针
        ListNode nextNode = null;
        while (curNode != null) {
            // nextNode 指向下一个节点,保存当前节点后面的链表。
            nextNode = curNode.next;
            // 将当前节点next域指向前一个节点 null<-1<-2<-3<-4
            curNode.next = preNode;
            // preNode 指针向后移动。preNode指向当前节点。
            preNode = curNode;
            // curNode指针向后移动。下一个节点变成当前节点
            curNode = nextNode;
        }
        return preNode;
    }
}
// @lc code=end
