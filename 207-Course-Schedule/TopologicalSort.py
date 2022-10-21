from collections import defaultdict, deque

class Solution:
    def canFinish(self, numCourses: int, prerequisites: List[List[int]]) -> bool:
        graph = defaultdict(set)
        indegree = [0] * numCourses
        
        for a, b in prerequisites:
            graph[b].add(a)
            indegree[a] += 1
        
        order = []
        queue = deque()
        
        for i, deg in enumerate(indegree):
            if deg == 0:
                queue.append(i)
        
        while queue:
            node = queue.popleft()
            order.append(node)
            for adj in graph[node]:
                indegree[adj] -= 1
                if indegree[adj] == 0:
                    queue.append(adj)
        
        return len(order) == numCourses
