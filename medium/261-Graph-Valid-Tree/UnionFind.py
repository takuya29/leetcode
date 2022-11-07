class Solution:
    def validTree(self, n: int, edges: List[List[int]]) -> bool:
        if len(edges) != n - 1:
            return False
        uf = UnionFind(n)
        for a, b in edges:
            if not uf.merge(a, b):
                return False
        return True


class UnionFind:
    def __init__(self, n: int) -> None:
        self.parent = list(range(n))
        self.size = [1] * n

    def root(self, i: int) -> int:
        if self.parent[i] != i:
            self.parent[i] = self.root(self.parent[i])
        return self.parent[i]

    def merge(self, i: int, j: int) -> bool:
        i, j = self.root(i), self.root(j)
        if i == j:
            return False
        if self.size[i] < self.size[j]:
            i, j = j, i
        self.parent[j] = i
        self.size[i] += self.size[j]
        return True
