/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        
        if (root == null) {
            return values;
        }
        
        Stack<Pair<Integer, TreeNode>> stack = new Stack<>();
        stack.push(new Pair(0, root));
        
        while (!stack.isEmpty()) {
            Pair<Integer, TreeNode> pair = stack.pop();
            int depth = pair.getKey();
            TreeNode node = pair.getValue();
            
            if (values.size() == depth) {
                values.add(node.val);
            }
            
            if (node.left != null) {
                stack.push(new Pair(depth + 1, node.left));
            }
            
            if (node.right != null) {
                stack.push(new Pair(depth + 1, node.right));
            }
        }
        
        return values;
        
    }
}
