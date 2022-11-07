class Solution {
    fun validTree(n: Int, edges: Array<IntArray>): Boolean {
        if (edges.size != n - 1) {
            return false
        }
        
        val unionFind = UnionFind(n)
        for (edge in edges) {
            if (!unionFind.merge(edge[0], edge[1])) {
                return false
            }
        }
        
        return true
        
    }
}

class UnionFind(num: Int) {
    private val parent: IntArray
    private val size: IntArray
    
    init {
        parent = IntArray(num) {i -> i}
        size = IntArray(num) {1}
    }
    
    private fun root(node: Int): Int {
        if (node != parent[node]) {
            parent[node] = root(parent[node])
        }
        return parent[node]
    }
    
    fun merge(i: Int, j: Int): Boolean {
        var iRoot = root(i)
        var jRoot = root(j)
        
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
