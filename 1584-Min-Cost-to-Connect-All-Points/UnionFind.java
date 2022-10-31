class Solution {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        
        if (n == 1){
            return 0;
        }
        
        ArrayList<List<Integer>> edges = new ArrayList<>(n * (n - 1));
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int dist = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
                edges.add(Arrays.asList(dist, i, j));
            }
        }
        
        Collections.sort(edges, (o1, o2) -> Integer.compare(o1.get(0), o2.get(0)));
        
        int counter = 0;
        int ans = 0;
        
        UnionFind unionFind = new UnionFind(n);
        int idx = 0;
        
        while (counter < n - 1) {
            List<Integer> edge = edges.get(idx);
            if (unionFind.join(edge.get(1), edge.get(2))) {
                ++counter;
                ans += edge.get(0);
            }
            ++idx;
        }
        
        return ans;
        
    }
}

class UnionFind {
    
    int[] parent;
    int[] size;
    
    public UnionFind(int num) {
        parent = new int[num];
        size = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }
    
    private int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }
    
    public boolean join(int i, int j) {
        int iRoot = find(i);
        int jRoot = find(j);
        
        if (iRoot == jRoot) {
            return false;
        }
        
        if (size[iRoot] < size[jRoot]) {
            int tmp = jRoot;
            jRoot = iRoot;
            iRoot = tmp;
        }
        
        parent[jRoot] = iRoot;
        size[iRoot] += size[jRoot];
        return true;
    }
}
