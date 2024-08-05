public class ConcurrencyDemo {

    public static void main(String[] args) {
        Counter counterUp = new Counter(0, 20, true);
        Counter counterDown = new Counter(20, 0, false);

        Thread thread1 = new Thread(counterUp);
        Thread thread2 = new Thread(counterDown);

        thread1.start();
        try {
            thread1.join(); // Ensure thread1 finishes before starting thread2
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }
}

class Counter implements Runnable {
    private int start;
    private int end;
    private boolean isCountingUp;

    public Counter(int start, int end, boolean isCountingUp) {
        this.start = start;
        this.end = end;
        this.isCountingUp = isCountingUp;
    }

    @Override
    public void run() {
        if (isCountingUp) {
            for (int i = start; i <= end; i++) {
                System.out.println("Counting up: " + i);
                try {
                    Thread.sleep(500); // Sleep for 500 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            for (int i = start; i >= end; i--) {
                System.out.println("Counting down: " + i);
                try {
                    Thread.sleep(500); // Sleep for 500 milliseconds
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
