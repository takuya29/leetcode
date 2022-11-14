from heapq import heappush, heappop


class Solution:
    def networkDelayTime(self, times: List[List[int]], n: int, k: int) -> int:
        graph = defaultdict(dict)
        for u, v, w in times:
            graph[u - 1][v - 1] = w

        distances = [float("inf")] * n
        distances[k - 1] = 0
        heap = [(0, k - 1)]
        visited = set()

        while heap and len(visited) < n:
            cost, crr = heappop(heap)
            if distances[crr] < cost:
                continue
            for adj, c in graph[crr].items():
                if distances[adj] > cost + c:
                    distances[adj] = cost + c
                    heappush(heap, (cost + c, adj))
                    visited.add(adj)

        ans = max(distances)
        return ans if ans < float("inf") else -1
