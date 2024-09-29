import java.util.*;

class Q964 {
    // Node class for doubly linked list
    private class Node {
        int count;
        Set<String> keys; // Stores all keys with the same count
        Node prev, next;

        Node(int count) {
            this.count = count;
            this.keys = new HashSet<>();
        }
    }

    private Map<String, Node> keyToNode; // Maps keys to the corresponding node in the doubly linked list
    private Node head, tail; // Dummy head and tail for the doubly linked list

    public Q964() {
        keyToNode = new HashMap<>();
        head = new Node(Integer.MIN_VALUE); // Dummy head node
        tail = new Node(Integer.MAX_VALUE); // Dummy tail node
        head.next = tail;
        tail.prev = head;
    }

    // Helper method to remove a node from the doubly linked list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // Helper method to add a node after a given node
    private void addNodeAfter(Node newNode, Node prevNode) {
        newNode.prev = prevNode;
        newNode.next = prevNode.next;
        prevNode.next.prev = newNode;
        prevNode.next = newNode;
    }

    public void inc(String key) {
        if (keyToNode.containsKey(key)) {
            Node curNode = keyToNode.get(key);
            curNode.keys.remove(key); // Remove key from current node
            int nextCount = curNode.count + 1;

            if (curNode.next.count != nextCount) {
                // Create new node for the next count
                Node newNode = new Node(nextCount);
                addNodeAfter(newNode, curNode);
            }
            // Move key to the next node
            curNode.next.keys.add(key);
            keyToNode.put(key, curNode.next);

            // Remove the current node if it's empty
            if (curNode.keys.isEmpty()) {
                removeNode(curNode);
            }
        } else {
            // If key does not exist, add it with count 1
            if (head.next.count != 1) {
                Node newNode = new Node(1);
                addNodeAfter(newNode, head);
            }
            head.next.keys.add(key);
            keyToNode.put(key, head.next);
        }
    }

    public void dec(String key) {
        Node curNode = keyToNode.get(key);
        curNode.keys.remove(key); // Remove key from current node
        int prevCount = curNode.count - 1;

        if (prevCount > 0) {
            if (curNode.prev.count != prevCount) {
                // Create new node for the previous count
                Node newNode = new Node(prevCount);
                addNodeAfter(newNode, curNode.prev);
            }
            // Move key to the previous node
            curNode.prev.keys.add(key);
            keyToNode.put(key, curNode.prev);
        } else {
            // Remove the key entirely if its count becomes 0
            keyToNode.remove(key);
        }

        // Remove the current node if it's empty
        if (curNode.keys.isEmpty()) {
            removeNode(curNode);
        }
    }

    public String getMaxKey() {
        if (tail.prev == head) {
            return ""; // No elements present
        }
        return tail.prev.keys.iterator().next(); // Return any key with max count
    }

    public String getMinKey() {
        if (head.next == tail) {
            return ""; // No elements present
        }
        return head.next.keys.iterator().next(); // Return any key with min count
    }
}
