/**
 * Created by Dazzling on 29.09.2016.
 */
public class MYList<E> {

    public class Node{
        public int data;
        public Node next;
        public Node(int data, Node next){
            this.data = data;
            this.next = next;
        }
    }

    private int size;

//    public Node<E> reverseList(Node<E> node) {
//        if (node == null || node.next == null) {
//            return node;
//        }
//        Node<E> currentNode = node;
//        Node<E> previousNode = null;
//        Node<E> nextNode = null;
//
//        while (currentNode != null) {
//            nextNode = currentNode.next;
//            currentNode.next = previousNode;
//            previousNode = currentNode;
//            currentNode = nextNode;
//        }
//        return previousNode;
//    }

//    public void add(E s) {
//    }
//
//    public int size() {
//    }

    public void get() {
    }
}
