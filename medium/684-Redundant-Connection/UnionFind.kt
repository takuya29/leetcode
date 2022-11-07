class Solution {
    fun findRedundantConnection(edges: Array<IntArray>): IntArray {
        val unionFind = UnionFind(edges.size)
        for (edge in edges) {
            if (!unionFind.merge(edge[0] - 1, edge[1] - 1)) {
                return edge
            }
        }
        return intArrayOf()
    }
}

class UnionFind(num: Int) {
    val parent: MutableList<Int>
    val size: MutableList<Int>
    
    init {
        parent = MutableList<Int>(num) { it }
        size = MutableList<Int>(num) {1}
    }
    
    fun root(i: Int): Int {
        if (i != parent[i]) {
            parent[i] = root(parent[i])
        }
        return parent[i]
    }
    
    fun merge(i: Int, j: Int): Boolean {
        var iRoot = root(i)
        var jRoot = root(j)
        if (iRoot == jRoot) {
            return false
        }
        else {
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
}
