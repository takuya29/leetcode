class Solution:
    def solve(self, board: List[List[str]]) -> None:
        """
        Do not return anything, modify board in-place instead.
        """
        m, n = len(board), len(board[0])

        for i in range(m):
            self.markReachable(i, 0, board)
            self.markReachable(i, n - 1, board)

        for j in range(n):
            self.markReachable(0, j, board)
            self.markReachable(m - 1, j, board)

        for i in range(m):
            for j in range(n):
                if board[i][j] == "O":
                    board[i][j] = "X"
                elif board[i][j] == "#":
                    board[i][j] = "O"

    def markReachable(self, i: int, j: int, board: List[List[str]]) -> None:
        if board[i][j] != "O":
            return

        board[i][j] = "#"

        m, n = len(board), len(board[0])
        queue: deque[Tuple[int]] = deque([(i, j)])
        while queue:
            x, y = queue.popleft()
            for dx, dy in [(1, 0), (0, 1), (-1, 0), (0, -1)]:
                newX = x + dx
                newY = y + dy
                if newX < 0 or newX >= m or newY < 0 or newY >= n:
                    continue
                if board[newX][newY] != "O":
                    continue
                board[newX][newY] = "#"
                queue.append((newX, newY))
