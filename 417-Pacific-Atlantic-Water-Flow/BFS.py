class Solution:
    def pacificAtlantic(self, heights: List[List[int]]) -> List[List[int]]:
        m, n = len(heights), len(heights[0])

        def reachable(i: int, j: int, visited: set[tuple[int, int]]) -> None:
            if (i, j) in visited:
                return
            visited.add((i, j))

            queue = deque([(i, j)])
            while queue:
                r, c = queue.popleft()
                for dr, dc in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
                    new_r, new_c = r + dr, c + dc
                    if new_r < 0 or new_r >= m or new_c < 0 or new_c >= n:
                        continue
                    if heights[new_r][new_c] < heights[r][c]:
                        continue
                    if (new_r, new_c) in visited:
                        continue
                    visited.add((new_r, new_c))
                    queue.append((new_r, new_c))
            return

        pacific = set()
        atlantic = set()

        for i in range(m):
            reachable(i, 0, pacific)
            reachable(i, n - 1, atlantic)

        for j in range(n):
            reachable(0, j, pacific)
            reachable(m - 1, j, atlantic)

        return [[i, j] for i, j in pacific.intersection(atlantic)]
