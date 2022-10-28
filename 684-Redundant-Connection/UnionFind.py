class UnionFind:
    def __init__(self, n: int):
        self.par = list(range(n))
        self.size = [1] * n

    def root(self, i: int) -> int:
        if i != self.par[i]:
            self.par[i] = self.root(self.par[i])
        return self.par[i]

    def merge(self, i: int, j: int) -> bool:
        i = self.root(i)
        j = self.root(j)
        if i == j:
            return False
        else:
            if self.size[i] < self.size[j]:
                i, j = j, i
            self.par[j] = i
            self.size[i] += self.size[j]
            return True


class Solution:
    def findRedundantConnection(self, edges: List[List[int]]) -> List[int]:
        uf = UnionFind(len(edges))
        for a, b in edges:
            if not uf.merge(a - 1, b - 1):
                return [a, b]
