package LifoFifoStructure;


import java.util.*;

public class SendYourEmails extends Thread {
    public SendYourEmails() {
        start();
    }

    public void run() {
        final Scanner scanner = new Scanner(System.in);

        int numberMail = 1;
        String textMail;
        boolean isCloseProgram = false;
        boolean isCloseMail = false;
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

        while (!isCloseProgram) {
            QueueLifoOrFifo queueLifoOrFifo = new QueueLifoOrFifo();

            while (!isCloseMail) {
                System.out.println("The enter the message: Message_" + numberMail++);
                textMail = scanner.nextLine();

                switch (textMail) {
                    case "":
                        numberMail--;
                        break;
                    case "show":
                        numberMail--;
                        QueueLifoOrFifo.getQueueMail().stream().forEach(System.out::println);
                        break;
                    case "send":
                        numberMail--;
                        queueLifoOrFifo.start();
                        isCloseMail = true;
                        break;
                    case "exit":
                        numberMail--;
                        isCloseMail = true;
                        isCloseProgram = true;
                        break;
                    default:
                        if (isFifoLifo) {
                            queueLifoOrFifo.addQueueMailFIFO(textMail);
                        } else {
                            queueLifoOrFifo.addQueueMailLIFO(textMail);
                        }
                        System.out.print("The message added in queue. ");
                        System.out.print("For send messages, enter: " + Colors.ANSI_RED + "\'send\'" + Colors.ANSI_RESET + ". ");
                        System.out.print("For show all messages, enter: " + Colors.ANSI_RED + "\'show\'" + Colors.ANSI_RESET + ". ");
                        System.out.println("For exit, enter: " + Colors.ANSI_RED + "\'exit\'" + Colors.ANSI_RESET + ".");
                        break;
                }

            }
            if (("send").equals(textMail)) {
                isCloseMail = false;
            }
        }
    }

    public static void main(String[] args) {
        final SendYourEmails sendYourEmails = new SendYourEmails();
    }

}
