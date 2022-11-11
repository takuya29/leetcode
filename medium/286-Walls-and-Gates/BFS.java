class Solution {
    private static final long INF = 2147483647;
    
    public void wallsAndGates(int[][] rooms) {
        int m = rooms.length;
        int n = rooms[0].length;
        
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(i * n + j);
                }
            }
        }
        
        int steps = 1;
        int[][] directions = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        
        while (!queue.isEmpty()) {
            int numIter = queue.size();
            for (int i = 0; i < numIter; i++) {
                int val = queue.poll();
                int r = val / n;
                int c = val % n;
                for (int[] dir: directions) {
                    int nextR = r + dir[0];
                    int nextC = c + dir[1];
                    if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n) {
                        continue;
                    }
                    if (rooms[nextR][nextC] != INF) {
                        continue;
                    }
                    rooms[nextR][nextC] = steps;
                    queue.offer(nextR * n + nextC);
                }
            }
            ++steps;
        }
    }
}
