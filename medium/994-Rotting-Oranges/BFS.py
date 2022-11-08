class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        m = len(grid)
        n = len(grid[0])

        queue = deque()
        freshes = set()
        for i in range(m):
            for j in range(n):
                if grid[i][j] == 1:
                    freshes.add((i, j))
                elif grid[i][j] == 2:
                    queue.append((i, j))

        counter = 0
        while queue and freshes:
            n_iter = len(queue)
            for _ in range(n_iter):
                i, j = queue.popleft()
                for di, dj in [(0, 1), (1, 0), (-1, 0), (0, -1)]:
                    adjacent = (i + di, j + dj)
                    if adjacent in freshes:
                        freshes.remove(adjacent)
                        queue.append(adjacent)
            counter += 1

        return counter if len(freshes) == 0 else -1
