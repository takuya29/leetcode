class Solution {
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        Set<Integer> freshes = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    freshes.add(i * n + j);
                }
                else if (grid[i][j] == 2) {
                    queue.offer(i * n + j);
                }
            }
        }
        
        int counter = 0;
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        
        while (!queue.isEmpty() && !freshes.isEmpty()) {
            int numIter = queue.size();
            for (int k = 0; k < numIter; k++) {
                int val = queue.poll();
                int i = val / n;
                int j = val % n;
                for (int[] dir: directions) {
                    int[] newPos = {i + dir[0], j + dir[1]};
                    if (newPos[0] < 0 || newPos[0] >= m || newPos[1] < 0 || newPos[1] >= n) {
                        continue;
                    }                    
                    int newVal = newPos[0] * n + newPos[1];
                    if (freshes.contains(newVal)) {
                        freshes.remove(newVal);
                        queue.offer(newVal);
                    }
                }
            }
            ++counter;
        }
        
        return freshes.isEmpty() ? counter : -1;        
    }
}
