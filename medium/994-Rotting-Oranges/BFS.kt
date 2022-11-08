class Solution {
    fun orangesRotting(grid: Array<IntArray>): Int {
        val m = grid.size
        val n = grid[0].size
        
        val queue = ArrayDeque<Pair<Int, Int>>()
        val freshes = HashSet<Pair<Int, Int>>()
        
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (grid[i][j] == 1) freshes.add(i to j)
                else if (grid[i][j] == 2) queue.offer(i to j)
            }
        }
        
        var counter = 0
        val directions = listOf(0 to 1, 1 to 0, -1 to 0, 0 to -1)
        while(!queue.isEmpty() && !freshes.isEmpty()) {
            repeat(queue.size) {
                val (i, j) = queue.poll()
                for ((di, dj) in directions) {
                    val adjacent = (i + di to j + dj)
                    if (adjacent in freshes) {
                        freshes.remove(adjacent)
                        queue.offer(adjacent)
                    }
                }
            }
            ++counter
        }
        
        return if (freshes.isEmpty()) counter else -1
    }
}
