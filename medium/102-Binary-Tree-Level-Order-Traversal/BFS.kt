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
    fun levelOrder(root: TreeNode?): List<List<Int>> {
        if (root == null) return listOf()
        
        var queue = ArrayDeque<TreeNode>()
        queue.offer(root!!)
        
        var result = arrayListOf<List<Int>>()
        while (!queue.isEmpty()) {
            var values = arrayListOf<Int>()
            repeat(queue.size) {
                val node = queue.poll()
                values.add(node.`val`)
                if (node.left != null) queue.offer(node.left)
                if (node.right != null) queue.offer(node.right)
            }
            result.add(values.toList())
        }
        
        return result
    }
}
