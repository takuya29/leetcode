class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        
        for (int[] pair: prerequisites) {
            if (!graph.containsKey(pair[1])) graph.put(pair[1], new HashSet<>());
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]] += 1;
        }
        
        List<Integer> order = new ArrayList<>();
        Deque<Integer> queue = new ArrayDeque<>();
        
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        
        int node;
        
        while (queue.size() > 0) {
            node = queue.removeFirst();
            order.add(node);
            if (graph.containsKey(node)) {
                for (int adj: graph.get(node)) {
                    if (--inDegree[adj] == 0) queue.add(adj);
                }
            }
        }
        
        return order.size() == numCourses;   
    }
}
