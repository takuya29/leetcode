class Solution {
    fun numIslands(grid: Array<CharArray>): Int {
        val m = grid.size
        val n = grid[0].size
        
        var ans = 0
        var visited = Array(m) {BooleanArray(n) {false}}
        
        for (i in 0..(m - 1)) {
            for (j in 0..(n - 1)) {
                if ((grid[i][j] == '0') || visited[i][j]) continue;
                ++ans
                var queue = ArrayDeque<Int>()
                queue.offer(i * n + j)
                
                while (!queue.isEmpty()) {
                    val crr = queue.poll()
                    val x = crr / n
                    val y = crr % n
                    
                    if ((x > 0) && (grid[x - 1][y] == '1') && !visited[x - 1][y]) {
                        visited[x - 1][y] = true
                        queue.offer((x - 1) * n + y)
                    }
                    
                    if ((x < m - 1) && (grid[x + 1][y] == '1') && !visited[x + 1][y]) {
                        visited[x + 1][y] = true
                        queue.offer((x + 1) * n + y)
                    }
                    
                    if ((y > 0) && (grid[x][y - 1] == '1') && !visited[x][y - 1]) {
                        visited[x][y - 1] = true
                        queue.offer(x * n + y - 1)
                    }
                    
                    if ((y < n - 1) && (grid[x][y + 1] == '1') && !visited[x][y + 1]) {
                        visited[x][y + 1] = true
                        queue.offer(x * n + y + 1)
                    }

                }
            }
        }
        
        return ans
        
    }
}
