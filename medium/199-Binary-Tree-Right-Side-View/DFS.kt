/**
 * Example:
 * var ti = TreeNode(5)
 * var v = ti.`val`
 * Definition for a binary tree node.
 * class TreeNode(var `val`: Int) {
 *     var left: TreeNode? = null
 *     var right: TreeNode? = null
 * }
 */
class Solution {
    fun rightSideView(root: TreeNode?): List<Int> {
        val values = mutableListOf<Int>()
        if (root == null) {
            return values
        }

        val stack = ArrayDeque<Pair<Int, TreeNode>>();
        stack.push(0 to root)
        
        while (!stack.isEmpty()) {
            val (depth, node) = stack.pop()
            if (values.size == depth) {
                values.add(node.`val`)
            }
            
            if (node.left != null) {
                stack.push(depth + 1 to node.left)
            }
            
            if (node.right != null) {
                stack.push(depth + 1 to node.right)
            }
        }
        
        return values
        
    }
}
