class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }
        
        for (int[] pair: prerequisites) {
            graph.get(pair[1]).add(pair[0]);
            inDegree[pair[0]] += 1;
        }
        
        List<Integer> order = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }
        
        int node;
        
        while (!queue.isEmpty()) {
            node = queue.poll();
            order.add(node);
            if (graph.containsKey(node)) {
                for (int adj: graph.get(node)) {
                    if (--inDegree[adj] == 0) queue.offer(adj);
                }
            }
        }
        
        return order.size() == numCourses;   
    }
}
