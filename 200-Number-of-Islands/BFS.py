class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        visited = set()
        m, n = len(grid), len(grid[0])

        ans = 0

        for i in range(m):
            for j in range(n):
                if grid[i][j] == "0" or (i, j) in visited:
                    continue
                ans += 1
                visited.add((i, j))
                queue = deque([(i, j)])
                while queue:
                    x, y = queue.popleft()
                    for dx, dy in [(0, 1), (1, 0), (-1, 0), (0, -1)]:
                        newx, newy = x + dx, y + dy
                        if newx < 0 or newx >= m or newy < 0 or newy >= n:
                            continue
                        if grid[newx][newy] == "0" or (newx, newy) in visited:
                            continue
                        visited.add((newx, newy))
                        queue.append((newx, newy))

        return ans
