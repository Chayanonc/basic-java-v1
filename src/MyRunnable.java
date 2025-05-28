public class MyRunnable implements Runnable {
    private final String text;

    MyRunnable(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            try {
                Thread.sleep(1000);
//                System.out.println(Thread.currentThread().getName() + " " + i);
                System.out.println(this.text);
            } catch (InterruptedException e) {
                System.out.println("Thread was interrupted");
            }

//            # for Chapter 42
//            if (i == 5) {
//                System.out.println("Time is up");
//                System.exit(0);
//            }
        }

    }
}
