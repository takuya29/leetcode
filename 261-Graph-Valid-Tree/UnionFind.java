class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        
        UnionFind unionFind = new UnionFind(n);
        
        for (int[] edge: edges) {
            if (!unionFind.merge(edge[0], edge[1])) {
                return false;
            }
        }
        
        return true;
        
    }
}

class UnionFind {
    private int[] parent;
    private int[] size;
    
    public UnionFind(int num) {
        parent = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    private int root(int node) {
        if (node != parent[node]) {
            parent[node] = root(parent[node]);
        }
        return parent[node];
    }
    
    public boolean merge(int i, int j) {
        int iRoot = root(i);
        int jRoot = root(j);
        
        if (iRoot == jRoot) {
            return false;
        }
        
        if (parent[iRoot] < parent[jRoot]) {
            int tmp = jRoot;
            jRoot = iRoot;
            iRoot = tmp;
        }
        
        parent[jRoot] = iRoot;
        size[iRoot] += size[jRoot];
        
        return true;
    }
}
