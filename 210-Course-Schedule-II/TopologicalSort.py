class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        graph: dict[int, set[int]] = defaultdict(set)
        indegree: list[int] = [0] * numCourses

        for a, b in prerequisites:
            graph[b].add(a)
            indegree[a] += 1

        return self.topsort(graph, indegree)

    def topsort(self, graph: dict[int, set[int]], indegree: list[int]):
        stack = [i for i, deg in enumerate(indegree) if deg == 0]
        order = []
        while stack:
            node = stack.pop()
            order.append(node)
            for adjacent in graph[node]:
                indegree[adjacent] -= 1
                if indegree[adjacent] == 0:
                    stack.append(adjacent)
        return order if len(order) == len(indegree) else []
