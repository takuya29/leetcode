class UnionFind:
    def __init__(self, num: int) -> None:
        self.parent = list(range(num))
        self.size = [1] * num

    def root(self, i: int) -> int:
        if i != self.parent[i]:
            self.parent[i] = self.root(self.parent[i])
        return self.parent[i]

    def merge(self, i: int, j: int) -> None:
        i, j = self.root(i), self.root(j)
        if i == j:
            return
        if self.size[i] < self.size[j]:
            i, j = i, j
        self.parent[j] = i
        self.size[i] += self.size[j]


class Solution:
    def countComponents(self, n: int, edges: List[List[int]]) -> int:
        unionFind = UnionFind(n)
        for a, b in edges:
            unionFind.merge(a, b)
        return sum(unionFind.parent[i] == i for i in range(n))
