/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if (node == null) return null;
        
        HashMap<Integer, Node> cloned = new HashMap<>();
        cloned.put(node.val, new Node(node.val));
        
        Stack<Node> stack = new Stack<>();
        stack.push(node);
        
        while (!stack.isEmpty()) {
            Node crr = stack.pop();
            for (Node adj : crr.neighbors) {
                if (!cloned.containsKey(adj.val)) {
                    cloned.put(adj.val, new Node(adj.val));
                    stack.push(adj);
                }
                cloned.get(crr.val).neighbors.add(cloned.get(adj.val));
            }
        }
        
        return cloned.get(node.val);
    }
}
