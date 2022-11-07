class Solution {
    fun minCostConnectPoints(points: Array<IntArray>): Int {
        val n = points.size
        if (n == 1) {
            return 0
        }
        
        val edges = mutableListOf<IntArray>()
        for (i in 0 until n) {
            for (j in i + 1 until n) {
                val dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1])
                edges.add(intArrayOf(dist, i, j))
            }
        }
        
        edges.sortBy {it -> it[0]}

        var result = 0
        var counter = 0
        val unionFind = UnionFind(n)
        
        for (edge in edges) {
            if (unionFind.join(edge[1], edge[2])) {
                result += edge[0]
                ++counter
            }
            if (counter == n - 1) {
                break
            }
        }
        
        return result
        
    }
}

class UnionFind(num: Int) {
    val parent: IntArray
    val size: IntArray
    
    init {
        parent = IntArray(num) {i -> i}
        size = IntArray(num) {1}
    }
    
    fun find(i: Int): Int {
        if (i != parent[i]) {
            parent[i] = find(parent[i])
        }
        return parent[i]
    }
    
    fun join(i: Int, j: Int): Boolean {
        var iRoot = find(i)
        var jRoot = find(j)
        
        if (iRoot == jRoot) {
            return false
        }
        
        if (size[iRoot] < size[jRoot]) {
            val tmp = jRoot
            jRoot = iRoot
            iRoot = tmp
        }
        
        parent[jRoot] = iRoot
        size[iRoot] += size[jRoot]
        return true
    }
    
}
