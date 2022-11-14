class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        
        for (int time[] : times) {
            int src = time[0] - 1;
            int dest = time[1] - 1;
            int cost = time[2];
            
            if (!graph.containsKey(src)) {
                graph.put(src, new ArrayList<>());
            }
            graph.get(src).add(new Pair(dest, cost));
        }
        
        Queue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getKey));
        queue.offer(new Pair(0, k - 1));
        int[] distance = new int[n];
        
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[k - 1] = 0;
        
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            int c = pair.getKey();
            int i =  pair.getValue();
            
            if (c > distance[i] || !graph.containsKey(i)) {
                continue;
            }
            
            for (Pair<Integer, Integer> adj : graph.get(i)) {
                int target = adj.getKey();
                int cost = adj.getValue();
                if (distance[target] > c + cost) {
                    distance[target] = c + cost;
                    queue.offer(new Pair(c + cost, target));
                }
            }
        }
        
        int maxDelay = 0;
        for (int d: distance) {
            maxDelay = Math.max(maxDelay, d);
        }
        
        if (maxDelay == Integer.MAX_VALUE) {
            return -1;
        } else {
            return maxDelay;
        }
    }
}
