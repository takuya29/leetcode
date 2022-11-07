/**
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList<Node?>()
 * }
 */

class Solution {
    fun cloneGraph(node: Node?): Node? {
        if (node == null) return null
        
        var stack = ArrayDeque<Node>()
        stack.push(node)
        
        var cloned = HashMap<Int, Node>()
        cloned[node.`val`] = Node(node.`val`)
        
        while (!stack.isEmpty()) {
            val crr = stack.pop()
            for (adj in crr.neighbors) {
                if (adj == null) continue
                if (!(adj.`val` in cloned)) {
                    cloned[adj.`val`] = Node(adj.`val`)
                    stack.push(adj)
                }
                cloned[crr.`val`]?.neighbors?.add(cloned[adj.`val`])
            }
        }
        
        return cloned[node.`val`]
    }
}
