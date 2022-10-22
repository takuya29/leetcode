class Solution:
    def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
        result = 0
        visited = set()

        m = len(grid)
        n = len(grid[0])

        for i in range(m):
            for j in range(n):
                if (i, j) in visited:
                    continue
                visited.add((i, j))
                queue = deque([(i, j)])
                area = 0
                while queue:
                    x, y = queue.popleft()
                    if grid[x][y] == 0:
                        continue
                    area += 1
                    for dx, dy in [(0, 1), (0, -1), (1, 0), (-1, 0)]:
                        nx, ny = x + dx, y + dy
                        if nx < 0 or nx >= m or ny < 0 or ny >= n:
                            continue
                        if (nx, ny) in visited:
                            continue
                        visited.add((nx, ny))
                        queue.append((nx, ny))
                result = max(result, area)
        return result
