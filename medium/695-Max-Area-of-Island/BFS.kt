import kotlin.math.max

class Solution {
    fun maxAreaOfIsland(grid: Array<IntArray>): Int {
        var visited = HashSet<Pair<Int, Int>>()
        var result = 0
        
        val m = grid.size
        val n = grid[0].size
        
        val directions = arrayOf(Pair(0, 1), Pair(0, -1), Pair(1, 0), Pair(-1, 0))
        
        for (i in 0..(m - 1)) {
            for (j in 0..(n - 1)) {
                if (!visited.add(Pair(i, j))) continue

                var queue = ArrayDeque<Pair<Int, Int>>()
                var area = 0
                queue.add(Pair(i, j))
                
                while (queue.size > 0) {
                    val (x, y) = queue.removeFirst()
                    if (grid[x][y] == 0) continue
                    area++
                    for ((dx, dy) in directions) {
                        val nx = x + dx
                        val ny = y + dy
                        if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue
                        if (!visited.add(Pair(nx, ny))) continue
                        queue.add(Pair(nx, ny))   
                    }
                }
                result = max(result, area)
            }
        }
        return result
    }
}
