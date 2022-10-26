class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        boolean[][] visited = new boolean[m][n];
        
        int ans = 0;
        Queue<Pair> queue = new LinkedList<>();
        
        List<Pair<Integer, Integer>> directions = new ArrayList();
        directions.add(new Pair(0, 1));
        directions.add(new Pair(1, 0));
        directions.add(new Pair(0, -1));
        directions.add(new Pair(-1, 0));
        
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0' || visited[i][j]) continue;
                ans++;
                visited[i][j] = true;
                queue.offer(new Pair(i, j));
                while (!queue.isEmpty()) {
                    Pair<Integer, Integer> crr = queue.poll();
                    for (Pair<Integer, Integer> dir : directions) {
                        int newx = crr.getKey() + dir.getKey();
                        int newy = crr.getValue() + dir.getValue();
                        if (newx < 0 || newx >= m || newy < 0 || newy >= n) continue;
                        if (grid[newx][newy] == '0' || visited[newx][newy]) continue;
                        visited[newx][newy] = true;
                        queue.offer(new Pair(newx, newy));
                    }
                }
                    
            }
        }
        
        return ans;
    }
}
