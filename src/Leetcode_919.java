import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Leetcode_919 {

    public static void main(String[] args) {

    }
}

class CBTInserter{
    Queue<TreeNode> candidate = new ArrayDeque<>();
    TreeNode root;

    public CBTInserter(TreeNode root) {
        this.root = root;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode t = queue.poll();
            if (t.left != null) queue.offer(t.left);
            if (t.right != null) queue.offer(t.right);
            if (t.left == null || t.right == null) {
                candidate.offer(t);
            }
        }
     }

    public int insert(int val) {
        TreeNode x = new TreeNode(val);
        TreeNode node = candidate.peek();
        int ret = node.val;
        if (node.left == null) {
            node.left = x;
        }
        else if (node.right == null) {
            node.right = x;
            candidate.poll();
        }
        candidate.offer(x);
        return ret;
    }

    public TreeNode get_root() {
        return root;
    }
}
