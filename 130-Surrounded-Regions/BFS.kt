class Solution {
    fun solve(board: Array<CharArray>): Unit {
        val m = board.size
        val n = board[0].size
        
        for (i in 0 until m) {
            markReachable(i, 0, board)
            markReachable(i, n - 1, board)
        }
        
        for (j in 0 until n) {
            markReachable(0, j, board)
            markReachable(m - 1, j, board)
        }
        
        for (i in 0 until m) {
            for (j in 0 until n) {
                when (board[i][j]) {
                    'O' -> {board[i][j] = 'X'}
                    '#' -> {board[i][j] = 'O'}
                    else -> {}
                }
            }
        } 
    }
    
    fun markReachable(i: Int, j: Int, board: Array<CharArray>) {
        if (board[i][j] != 'O') {
            return
        }
        
        val m = board.size
        val n = board[0].size
        
        val queue = ArrayDeque<Pair<Int, Int>>()
        board[i][j] = '#'
        queue.offer(i to j)
        
        val delta = arrayOf(1 to 0, 0 to 1, -1 to 0, 0 to -1)
        
        while (!queue.isEmpty()) {
            val (x, y) = queue.poll()
            for ((dx, dy) in delta) {
                val newX = x + dx
                val newY = y + dy
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue
                }
                if (board[newX][newY] != 'O') {
                    continue
                }
                board[newX][newY] = '#'
                queue.offer(newX to newY)
            }
        }
    }
}
