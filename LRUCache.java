// 146. LRU Cache
// https://leetcode.com/problems/lru-cache/description/


/**
 * Time Complexity: O(1) for get() and put()
 * Space Complexity: O(k) where k is capacity to store in map and linkedlist
 */

class LRUCache {

    class Node{
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    Map<Integer, Node> map;
    Node head;
    Node tail;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    private void removeNode(Node node){
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = null;
        node.prev = null;
    }

    private void addNode(Node node){
        head.next.prev = node;
        node.next = head.next;

        head.next = node;
        node.prev = head;
    }
    
    public int get(int key) { // O(1)
        if(!map.containsKey(key)){
            return -1;
        }

        Node node = map.get(key);

        removeNode(node);
        addNode(node);

        return node.value;
    }
    
    public void put(int key, int value) { // O(1)
        if(map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;

            removeNode(node);
            addNode(node);
            return;
        }

        if(map.size() == capacity){
            Node node = tail.prev;
            map.remove(node.key);
            removeNode(node);
        }

        Node node = new Node(key, value);
        map.put(node.key, node);
        addNode(node);
    }
}