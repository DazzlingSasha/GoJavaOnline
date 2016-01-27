package LifoFifoStructure;

public class Lifo {
    String[] arr;

    public Lifo(String[] arr) {
        this.arr = arr;
    }

    public static void main(String[] args) {
        String[] arr = new String[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1 + "";
            System.out.println(arr[i]);
        }
        System.out.println(arr.length);
        Lifo s = new Lifo(arr);
        System.out.println("---------------");
        System.out.println(s.size() + " size");
        System.out.println(s.pop() + " show first element and delete");
        System.out.println(s.size() + " size");
        System.out.println(s.pop() + " show first element and delete");
        System.out.println(s.front() + " show first element");
        System.out.println(s.size() + " size");
        System.out.println(s.empty() + "empty array or not");
        System.out.println("add elements with 11 to 16");
        s.push("11");
        s.push("12");
        s.push("13");
        s.push("14");
        s.push("15");
        s.push("16");
        System.out.println(s.pop() + " show first element and delete");
        s.print();
        System.out.println("clear array");
        s.clear();

        System.out.println(s.size());
        System.out.println("---------------");
        s.print();
        System.out.println(s.empty() + "  empty array or not");
    }

    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public int size() {
        int elements = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) {
                elements++;
            }
        }
        return elements;
    }

    public String pop() {
        String lastElement = null;
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] != null) {
                lastElement = arr[i];
                arr[i] = null;
                break;
            }

        }
        return lastElement;
    }

    public String front() {
        return arr[size() - 1];
    }

    public void push(String element) {
        if (size() < arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) {
                    arr[i] = element;
                    break;
                }
            }
        } else {
            String[] arrNew = new String[(int) (arr.length * 1.5)];
            for (int i = 0; i < arrNew.length; i++) {
                if (i < arr.length) {
                    arrNew[i] = arr[i];
                }
                if (arrNew[i] == null) {
                    arrNew[i] = element;
                    arr = arrNew;
                    break;
                }
            }

        }
    }

    public void clear() {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            } else {
                arr[i] = null;
            }
        }
    }

    public boolean empty() {
        if (size() == 0) {
            return false;
        } else {
            return true;
        }
    }
}
