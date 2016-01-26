package LifoFifoStructure;

import java.util.*;

public class QueueLifoOrFifo extends Thread{

    private static volatile Deque<String> queueMail = new ArrayDeque<>();

    public static synchronized Queue<String> getQueueMail() {
        return queueMail;
    }

    public synchronized void addQueueMailLIFO(String textMail) {
        queueMail.offerFirst(textMail);
    }
    public synchronized void addQueueMailFIFO(String textMail) {
        queueMail.offer(textMail);
    }
    public void run() {
        try {
            System.out.println("The sending messages will start after 3 seconds. But can writing next messages.");
            while (!queueMail.isEmpty()) {
                sleep(3000);
                System.out.println(Colors.ANSI_CYAN + String.format("The remaining %s posts", queueMail.size())+ Colors.ANSI_RESET);
                System.out.println(Colors.ANSI_CYAN + queueMail.poll() + Colors.ANSI_RESET);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

}
