class Solution {
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val graph = mutableMapOf<Int, MutableSet<Int>>()
        val indegree = IntArray(numCourses) {0}
        
        for (i in 0 until numCourses) {
            graph[i] = mutableSetOf<Int>()
        }
        
        for (pair in prerequisites) {
            graph[pair[1]]!!.add(pair[0])
            ++indegree[pair[0]]
        }
        
        return topSort(graph, indegree)
    }
    
    fun topSort(graph: Map<Int, Set<Int>>, indegree: IntArray): IntArray {
        val stack = Stack<Int>()
        indegree.forEachIndexed {i, v -> if (v == 0) stack.push(i)}
        
        var order = mutableListOf<Int>()
        while (!stack.isEmpty()) {
            val node = stack.pop()
            order.add(node)
            for (adjacent in graph[node]!!) {
                if (--indegree[adjacent] == 0) stack.push(adjacent);
            }
        }
        
        return if (order.size == indegree.size) order.toIntArray() else IntArray(0)
    }
}
