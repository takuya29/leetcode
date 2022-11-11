class Solution {
    private val INF: Long = 2147483647;
    
    fun wallsAndGates(rooms: Array<IntArray>): Unit {
        val m = rooms.size
        val n = rooms[0].size
        
        val queue = ArrayDeque<Pair<Int, Int>>();
        for (i in 0 until m) {
            for (j in 0 until n) {
                if (rooms[i][j] == 0) {
                    queue.offer(i to j);
                }
            }
        }
        
        val directions = listOf(0 to 1, 1 to 0, -1 to 0, 0 to -1)
        var steps = 1
        
        while (!queue.isEmpty()) {
            val numIter = queue.size
            repeat(numIter) {
                val (r, c) = queue.poll()
                for ((dr, dc) in directions) {
                    val nextR = r + dr
                    val nextC = c + dc
                    if (nextR < 0 || nextR >= m || nextC < 0 || nextC >= n) {
                        continue
                    }
                    if (rooms[nextR][nextC].toLong() != INF) {
                        continue
                    }
                    rooms[nextR][nextC] = steps
                    queue.offer(nextR to nextC)
                }
            }
            ++steps
        }
    }
}
