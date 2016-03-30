package LifoFifoStructure;


import java.util.*;

public class SendYourEmails {
    public SendYourEmails() {
        start();
    }

    public void start() {
        final Scanner scanner = new Scanner(System.in);

        int numberMail = 1;
        String textMail;
        boolean isCloseProgram = false;
        boolean isFifoLifo = false;
        System.out.println("What method to send messages? " +
                "Enter: " + Colors.ANSI_CYAN + "1 " + Colors.ANSI_RESET + "if FiFo or " +
                "Enter: " + Colors.ANSI_CYAN + "2 " + Colors.ANSI_RESET + "if LiFo");
        textMail = scanner.nextLine();
        if (("1").equals(textMail)) {
            isFifoLifo = true;
        }
        if (("2").equals(textMail)) {
            isFifoLifo = false;
        }
        QueueLifoOrFifo queueLifoOrFifo = new QueueLifoOrFifo();
        while (!isCloseProgram) {
                System.out.println("The enter the message: Message_" + numberMail++);
                textMail = scanner.nextLine();

                switch (textMail) {
                    case "":
                        numberMail = getNumberMail(numberMail);
                        break;
                    case "show":
                        numberMail = getNumberMail(numberMail);
                        queueLifoOrFifo.getQueueMail().stream().forEach(System.out::println);
                        break;
                    case "send":
                        queueLifoOrFifo.sendAll();
                        break;
                    case "exit":
                        numberMail = getNumberMail(numberMail);
                        isCloseProgram = true;
                        break;
                    default:
                        if (isFifoLifo) {
                            queueLifoOrFifo.addQueueMailFIFO(textMail);
                        } else {
                            queueLifoOrFifo.addQueueMailLIFO(textMail);
                        }
                        System.out.print("The message added in queue. ");
                        System.out.print("For send all messages, enter: " + Colors.ANSI_RED + "\'send\'" + Colors.ANSI_RESET + ". ");
                        System.out.print("For show all messages, enter: " + Colors.ANSI_RED + "\'show\'" + Colors.ANSI_RESET + ". ");
                        System.out.println("For exit, enter: " + Colors.ANSI_RED + "\'exit\'" + Colors.ANSI_RESET + ".");
                        break;
                }
                if(queueLifoOrFifo.getQueueMail().size() == 10){
                    queueLifoOrFifo.send();
                }


        }
    }

    private int getNumberMail(int numberMail) {
        numberMail--;
        return numberMail;
    }

    public static void main(String[] args) {
//        final SendYourEmails sendYourEmails = new SendYourEmails();
        String[] s = new String[10];
        s[4] = "1";
        s[1] = "1";
        s[0] = "1";
        s[2] = "1";
        s[3] = "1";
        s[4] = "1";
        for(int i=s.length-1; i >=0 ; i--)
            if(null != s[i]){
                s[i] = null;
            }
        for(String d : s){
            System.out.println(d);
        }
    }

}
