public class ReverseLinkedList {
    public static void main(String[] args) throws java.lang.Exception {
        LinkedListT a = new LinkedListT();
        a.addFirst("0");
        a.addFirst("1");
        a.addLast("2");
        a.addFirst(53);
        a.addFirst("4yukihj");
        a.addFirst("5");
        a.addLast("6");

        a.iterateBackward();
//        a.reverse();
//        a.display();
    }
}

class Node<E> {
    public E element;
    public Node<E> next;
    public Node<E> prev;

    public Node() {
    }

    public Node(E element, Node<E> next, Node<E> prev) {
        this.element = element;
        this.next = null;
        this.prev = null;
    }
}

class LinkedListT<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public LinkedListT() {
        head = null;
    }

    public void addFirst(E element) {
        Node tmp = new Node(element, head, null);
        if(head != null ) {head.prev = tmp;}
        head = tmp;
        if(tail == null) { tail = tmp;}
        size++;
        System.out.println("adding: "+element);
    }

    public void addLast(E element) {

        Node tmp = new Node(element, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
        System.out.println("adding: "+element);
    }

    public void iterateForward(){

        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.next;
        }
    }

    /**
     * this method walks backward through the linked list
     */
    public void iterateBackward(){

        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.prev;
        }
    }

//    public void add(E element) {
//        Node<E> newNode = new Node(element);
//        newNode.next = null;
//        if(lastNode != null) {
//            newNode.prevt = lastNode;
//            lastNode.next = newNode;
//
//                newNode.next = head;
//                head = newNode;
//
//        } else {
//            lastNode = newNode;
//        }
//    }

    public void reverse() {
        Node<E> current = head;
        Node<E> next = null;
        Node<E> prev = null;

        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        head = prev;
    }

    public void display() {
        Node<E> current = head;
        while (current != null) {
            System.out.println(current.element);
            current = current.next;
        }
    }
}
//public class ReverseLinkedList {
//    public static void main (String[] args) throws java.lang.Exception
//    {
//        LinkedListT a = new LinkedListT();
//        a.add("0");
//        a.add("1");
//        a.add("2");
//        a.add(53);
//        a.add("4yukihj");
//        a.add("5");
//        a.add("6");
//
//        a.display();
//        a.reverse();
//        a.display();
//    }
//}
//class Node<E>{
//    public E element;
//    public Node<E> next;
//    public Node(E data){
//        this.element = data;
//        this.next = null;
//    }
//}
//class LinkedListT<E>{
//    private Node<E> head;
//
//    public LinkedListT(){
//        head=null;
//    }
//
//    public void add(E element){
//        Node<E> newNode = new Node(element);
//        newNode.next = head;
//        head = newNode;
//    }
//
//    public void reverse(){
//        Node<E> current = head;
//        Node<E> next = null;
//        Node<E> prev = null;
//
//        while(current!=null){
//            next = current.next;
//            current.next = prev;
//            prev = current;
//            current = next;
//        }
//        head = prev;
//    }
//
//    public void display(){
//        Node<E> current = head;
//        while(current!=null){
//            System.out.println(current.element);
//            current=current.next;
//        }
//    }
//}