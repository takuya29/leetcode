class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        
        Set<Integer> pacific = new HashSet<>();
        Set<Integer> atlantic = new HashSet<>();
        
        for (int i = 0; i < m; i++) {
            addReachable(i, 0, heights, pacific);
            addReachable(i, n - 1, heights, atlantic);
        }
        
        for (int j = 0; j < n; j++) {
            addReachable(0, j, heights, pacific);
            addReachable(m - 1, j, heights, atlantic);
        }
        
        pacific.retainAll(atlantic);
        return pacific.stream()
            .map(val -> Arrays.asList(val / n, val % n))
            .toList();
    }
                 
    private void addReachable(int i,
                              int j,
                              int[][] heights,
                              Set<Integer> visited) {
        
        int m = heights.length;
        int n = heights[0].length;
        
        if (visited.contains(i * n + j)) return;      
        
        Queue<Integer> queue = new LinkedList<>();
        visited.add(i * n + j);
        queue.offer(i * n + j);
        
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};  
        
        while (!queue.isEmpty()) {
            int val = queue.poll();
            int row = val / n;
            int col = val % n;
            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];
                int newVal = newRow * n + newCol;
                
                if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                    continue;
                }
                if (visited.contains(newVal)) {
                    continue;
                }
                if (heights[row][col] > heights[newRow][newCol]) {
                    continue;
                }
                visited.add(newVal);
                queue.offer(newVal);
            }
        }
    }
}
