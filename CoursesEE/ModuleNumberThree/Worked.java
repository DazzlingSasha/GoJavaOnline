package CoursesEE.ModuleNumberThree;

public class Worked {
    MySemaphore mySemaphore;
    private int count = 0;
    public Worked(int count ) {
        this.count = count;
    }

    public void test() throws InterruptedException {
        mySemaphore = new MySemaphore(count);

        for (int i = 0; i < 10; i++) {
            if (mySemaphore.getAvailablePermits() >= 0) {
                new Thread(new RunWorked()).start();
            }
        }
    }

    public class RunWorked implements Runnable {

        @Override
        public void run() {
            try {
                System.out.println("The " + Thread.currentThread().getName() + " start");
                mySemaphore.acquire();
                mySemaphore.release();
                System.out.println("The " + Thread.currentThread().getName() + " is ending work");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            new Worked(5).test();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}