class Solution {
    fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
        val m = heights.size
        val n = heights[0].size
        
        val pacific = mutableSetOf<Int>()
        val atlantic = mutableSetOf<Int>()
        
        for (i in 0 until m) {
            addReachable(i, 0, heights, pacific)
            addReachable(i, n - 1, heights, atlantic)
        }
        
        for (j in 0 until n) {
            addReachable(0, j, heights, pacific)
            addReachable(m - 1, j, heights, atlantic)
        }
        
        return pacific.intersect(atlantic)
            .map {i -> listOf(i / n, i % n)}
            .toList()
    }
    
    fun addReachable(i: Int, j: Int, heights: Array<IntArray>, visited: MutableSet<Int>) {
        val m = heights.size
        val n = heights[0].size
        
        if ((i * n + j) in visited) {
            return
        }
        
        visited.add(i * n + j)
        val queue = ArrayDeque<Int>()
        queue.offer(i * n + j)
        
        val directions = listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1)
        
        while (!queue.isEmpty()) {
            val num = queue.poll()
            val row = num / n
            val col = num % n
            
            for ((drow, dcol) in directions) {
                val nextRow = row + drow
                val nextCol = col + dcol
                val nextValue = nextRow * n + nextCol
                if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                    continue
                }
                if (heights[row][col] > heights[nextRow][nextCol]) {
                    continue
                }
                if (nextValue in visited) {
                    continue
                }
                visited.add(nextValue)
                queue.offer(nextValue)
            }
        }
    }
}
