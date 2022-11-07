"""
# Definition for a Node.
class Node:
    def __init__(self, val = 0, neighbors = None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []
"""


class Solution:
    def cloneGraph(self, node: 'Node') -> 'Node':
        if node is None:
            return None

        cloned = {}
        stack = [node]
        cloned[node.val] = Node(node.val)

        while stack:
            orig = stack.pop()
            for adj in orig.neighbors:
                if adj.val not in cloned:
                    stack.append(adj)
                    cloned[adj.val] = Node(adj.val)
                cloned[orig.val].neighbors.append(cloned[adj.val])

        return cloned[node.val]
