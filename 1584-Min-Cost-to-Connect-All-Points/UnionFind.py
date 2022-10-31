from heapq import heapify, heappop

class Solution:
    def minCostConnectPoints(self, points: List[List[int]]) -> int:
        n = len(points)
        if n == 1:
            return 0

        edges = []
        for i in range(n):
            for j in range(i + 1, n):
                distance = abs(points[i][0] - points[j][0]) + abs(points[i][1] - points[j][1])
                edges.append((distance, i, j))
        heapify(edges)
        
        uf = UnionFind(n)
        count = 0
        result = 0
        
        while count < n - 1:
            dist, i, j = heappop(edges)
            if uf.merge(i, j):
                result += dist
                count += 1
        
        return result
        

        
class UnionFind:
    def __init__(self, n: int) -> None:
        self.parent = list(range(n))
        self.size = [1] * n
    
    def root(self, i: int) -> int:
        if (self.parent[i] != i):
            self.parent[i] = self.root(self.parent[i])
        return self.parent[i]
    
    def merge(self, i: int, j: int) -> bool:
        i, j = self.root(i), self.root(j)
        
        if (i == j):
            return False
        
        if (self.size[i] < self.size[j]):
            i, j = j, i
        
        self.parent[j] = i
        self.size[i] += self.size[j]
        return True
