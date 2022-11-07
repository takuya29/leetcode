class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        
        int m = grid.length;
        int n = grid[0].length;
        
        List<Pair<Integer, Integer>> directions = new ArrayList<>(4);
        directions.add(new Pair(0, 1));
        directions.add(new Pair(0, -1));
        directions.add(new Pair(1, 0));
        directions.add(new Pair(-1, 0));
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited.add(new Pair(i, j))) continue;
                int area = 0;
                Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
                queue.offer(new Pair(i, j));
                while (queue.size() > 0) {
                    Pair<Integer, Integer> pair = queue.poll();
                    if (grid[pair.getKey()][pair.getValue()] == 0) continue;
                    area++;
                    for (Pair<Integer, Integer> dir: directions) {
                        int nx = pair.getKey() + dir.getKey();
                        int ny = pair.getValue() + dir.getValue();
                        if (nx < 0 || nx >=m || ny < 0 || ny >= n) continue;
                        if (!visited.add(new Pair(nx, ny))) continue;
                        queue.add(new Pair(nx, ny));
                    }
                }
                result = Math.max(result, area);
            }
        }
        
        return result;
    }
}
