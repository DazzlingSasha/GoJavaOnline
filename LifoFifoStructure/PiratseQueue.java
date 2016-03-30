package LifoFifoStructure;


public abstract class PiratseQueue {
    private String[] arr = new String[10];

    public String[] getArr() {
        return arr;
    }

    public void print() {
        for (String anArr : arr) {
            System.out.println(anArr);
        }
    }

    public int size() {
        int elements = 0;
        for (String anArr : arr) {
            if (anArr != null) {
                elements++;
            }
        }
        return elements;
    }

    public void clear() {
        arr = new String[10];
    }

    public boolean empty() {
        return size() == 0;
    }

    public abstract String pop();

    public abstract void push(String element);

    public abstract String peek();

    public static void main(String[] args) {
        PiratseQueue p = new Lifo();
        p.push("ss");
        System.out.println(p.empty());
    }
}
