package plus.naomi.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Naomi
 * @description
 * @date 2021/11/16 15:45
 */
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * @author Naomi
 */
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        inorder(root, res);
        return res;
    }

    void inorder(TreeNode treeNode, List<Integer> res) {
        if (treeNode == null) {
            return;
        }
        inorder(treeNode.left, res);
        res.add(treeNode.val);
        inorder(treeNode.right, res);
    }

}
