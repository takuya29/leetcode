class Solution {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int[] edge: edges) {
            uf.merge(edge[0], edge[1]);
        }
        
        int numComp = 0;
        for (int i = 0; i < n; i++) {
            if (uf.isRoot(i)) ++numComp;
        }
        return numComp;
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
    
    public boolean isRoot(int i) {
        return i == parent[i];
    }
    
    public int root(int i) {
        if (!isRoot(i)) {
            parent[i] = root(parent[i]);
        }
        return parent[i];
    }
    
    public void merge(int i, int j) {
        i = root(i);
        j = root(j);
        
        if (i == j) return;
        
        if (size[i] < size[j]) {
            int tmp = j;
            j = i;
            i = tmp;
        }
        
        parent[j] = i;
        size[i] += size[j];
    }
}
