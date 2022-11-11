class Solution:
    INF = 2147483647

    def wallsAndGates(self, rooms: List[List[int]]) -> None:
        """
        Do not return anything, modify rooms in-place instead.
        """
        m = len(rooms)
        n = len(rooms[0])

        queue = deque()
        for i in range(m):
            for j in range(n):
                if rooms[i][j] == 0:
                    queue.append((i, j))

        steps = 1
        while queue:
            n_iter = len(queue)
            for _ in range(n_iter):
                i, j = queue.popleft()
                for di, dj in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
                    next_i = i + di
                    next_j = j + dj
                    if next_i < 0 or next_i >= m or next_j < 0 or next_j >= n:
                        continue
                    if rooms[next_i][next_j] != self.INF:
                        continue
                    rooms[next_i][next_j] = steps
                    queue.append((next_i, next_j))
            steps += 1
