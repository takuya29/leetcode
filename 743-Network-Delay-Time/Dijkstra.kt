class Solution {
    fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
        val graph = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
        for ((u, v, c) in times) {
            if (graph[u - 1] == null) {
                graph[u - 1] = mutableListOf()
            }
            graph[u - 1]!!.add(v - 1 to c)
        }
        
        val dist = IntArray(n) {Int.MAX_VALUE}
        dist[k - 1] = 0
        
        val queue = PriorityQueue<Pair<Int, Int>> {o1, o2 -> o1.first.compareTo(o2.first)}
        queue.offer(0 to k - 1)
        
        while (!queue.isEmpty()) {
            val (crrCost, crrNode) = queue.poll()
            if (crrCost > dist[crrNode] || !graph.containsKey(crrNode)) {
                continue
            }
            for ((target, cost) in graph[crrNode]!!) {
                if (crrCost + cost < dist[target]) {
                    dist[target] = crrCost + cost
                    queue.offer(crrCost + cost to target)
                }
            }
        }
        
        val maxDelay = dist.max()!!
        return if (maxDelay < Int.MAX_VALUE) maxDelay else -1
    }
}
