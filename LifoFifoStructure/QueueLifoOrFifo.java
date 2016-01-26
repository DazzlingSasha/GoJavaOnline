package LifoFifoStructure;

import java.util.*;

public class QueueLifoOrFifo{

    private Deque<String> queueMail = new ArrayDeque<>();

    public Deque<String> getQueueMail() {
        return queueMail;
    }
    public void addQueueMailLIFO(String textMail) {
        queueMail.offerFirst(textMail);
    }
    public void addQueueMailFIFO(String textMail) {
        queueMail.offer(textMail);
    }
    public void send(){
        System.out.println(Colors.ANSI_CYAN + "Connecting to the server. The message was sent"+ Colors.ANSI_RESET);
        System.out.println(Colors.ANSI_CYAN + queueMail.poll() + Colors.ANSI_RESET);
    }
    public void sendAll(){
        System.out.println(Colors.ANSI_CYAN + "Connecting to the server. The message was sent" + Colors.ANSI_RESET);
        while (!getQueueMail().isEmpty()) {
            System.out.println(Colors.ANSI_CYAN + queueMail.poll() + Colors.ANSI_RESET);
        }
    }

}
