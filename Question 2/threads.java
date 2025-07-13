public class threads {
    private static int sharedVariable = 0;

    public static void main(String[] args) {
        Thread adder = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronized (threads.class) {
                    sharedVariable += i;
                    System.out.println("Adder added " + i + ", sharedVariable: " + sharedVariable);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });

        Thread subtractor = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                synchronized (threads.class) {
                    sharedVariable -= i;
                    System.out.println("Subtractor subtracted " + i + ", sharedVariable: " + sharedVariable);
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                }
            }
        });

        adder.start();
        subtractor.start();

        try {
            adder.join();
            subtractor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value of sharedVariable: " + sharedVariable);
    }
}