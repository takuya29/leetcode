class UnionFind(num: Int) {
    val parent: IntArray
    val size: IntArray
    
    init {
        parent = IntArray(num) { it }
        size = IntArray(num) {1}
    }
    
    fun isRoot(i: Int): Boolean {
        return i == parent[i]
    }
    
    fun root(i: Int): Int {
        if (!isRoot(i)) {
            parent[i] = root(parent[i])
        }
        return parent[i]
    }
    
    fun merge(i: Int, j: Int) {
        var iRoot = root(i)
        var jRoot = root(j)
        
        if (iRoot == jRoot) {
            return
        }
        
        if (size[iRoot] < size[jRoot]) {
            val tmp = jRoot
            jRoot = iRoot
            iRoot = tmp
        }
        
        parent[jRoot] = iRoot
        size[iRoot] += size[jRoot]
    }
}

class Solution {
    fun countComponents(n: Int, edges: Array<IntArray>): Int {
        val unionFind = UnionFind(n)
        
        for (edge in edges) {
            unionFind.merge(edge[0], edge[1])
        }
        
        return (0 until n).filter { unionFind.isRoot(it) }.count()
        
    }
}
