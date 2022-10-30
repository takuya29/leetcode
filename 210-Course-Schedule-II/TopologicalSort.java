class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<Integer>());
        }
        
        int[] indegree = new int[numCourses];
        
        for (int[] preq : prerequisites) {
            graph.get(preq[1]).add(preq[0]);
            ++indegree[preq[0]];
        }
        
        return topSort(graph, indegree);
    }
    
    public int[] topSort(Map<Integer, Set<Integer>> graph, int[] indegree) {
        Stack<Integer> stack = new Stack<>();
        List<Integer> order = new ArrayList<>();
        
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int node = stack.pop();
            order.add(node);
            for (int adjacent: graph.get(node)) {
                if (--indegree[adjacent] == 0) stack.push(adjacent);
            }
        }
        
        return (order.size() == indegree.length) ? order.stream().mapToInt(i -> i).toArray() : new int[0];
    }
}
