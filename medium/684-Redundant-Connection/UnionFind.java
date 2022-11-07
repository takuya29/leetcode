class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length);
        for (int[] edge: edges) {
            if (!unionFind.merge(edge[0] - 1, edge[1] - 1)) {
                return edge;
            }
        }
        return new int[]{-1, -1};
    }
}

class UnionFind {
    private int[] par;
    private int[] size;
    
    public UnionFind(int num) {
        par = new int[num];
        for (int i = 0; i < num; i++) {
            par[i] = i;
        }
        
        size = new int[num];
        for (int i = 0; i < num; i++) {
            size[i] = 1;
        }
    }
    
    public int root(int i) {
        if (i != par[i]) {
            par[i] = root(par[i]);
        }
        return par[i];
    }
    
    public boolean merge(int i, int j) {
        i = root(i);
        j = root(j);
        if (i == j) {
            return false;
        }
        else {
            if (size[i] < size[j]) {
                int tmp = j;
                j = i;
                i = tmp;
            }
            par[j] = i;
            size[i] += size[j];
            return true;
        }
    }
}
