class Solution {
    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;
        
        for (int i = 0; i < m; i++) {
            markReachable(i, 0, board);
            markReachable(i, n - 1, board);
        }
        
        for (int j = 0; j < n; j++) {
            markReachable(0, j, board);
            markReachable(m - 1, j, board);
        }
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }
                else if (board[i][j] == '#') {
                    board[i][j] = 'O';
                }
            }
        }
    }
    
    public void markReachable(int i, int j, char[][] board) {
        if (board[i][j] != 'O') {
            return;
        }
        
        int m = board.length;
        int n = board[0].length;
        
        Queue<Integer> queue = new LinkedList<>();
        board[i][j] = '#';
        queue.offer(i * n + j);
        
        while (!queue.isEmpty()) {
            int v = queue.poll();
            int x = v / n;
            int y = v % n;
            
            int[][] delta = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
            
            for (int[] d: delta) {
                int newX = x + d[0];
                int newY = y + d[1];
                
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue;
                }
                if (board[newX][newY] != 'O') {
                    continue;
                }
                board[newX][newY] = '#';
                queue.offer(newX * n + newY);
            }
        }
        
    }   
}
