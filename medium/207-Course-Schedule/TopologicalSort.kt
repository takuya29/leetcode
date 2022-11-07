class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        var graph = mutableMapOf<Int, MutableSet<Int>>()
        var indegree = Array<Int>(numCourses) {0}
        
        prerequisites.forEach {
            if (graph[it[1]] == null) graph[it[1]] = mutableSetOf<Int>()
            graph[it[1]]!!.add(it[0])
            indegree[it[0]] += 1
        }
        
        var order = mutableListOf<Int>()
        var queue = ArrayDeque<Int>()
        
        indegree.forEachIndexed {i, e -> if (e == 0) queue.add(i)}
        
        var node: Int
        
        while (queue.size > 0) {
            node = queue.removeFirst()
            order.add(node)
            graph[node]?.forEach {if (--indegree[it] == 0) queue.add(it)}
        }
        
        return order.size == numCourses
    }
}
