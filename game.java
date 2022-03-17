public class game {
    public static void main(String[] args) {
        // game table
        // hard test case 867254301
        // easy test case 402138657
        String table = "402138657";


        // create Thread to calculate time of gameLoop
        Thread thread = new Thread(new Runnable() {
            public void run() {
                long startTime = System.currentTimeMillis();
                gameLoop.Start(table);
                long endTime = System.currentTimeMillis();
                System.out.println("\n\nTime: " + (float)((endTime - startTime)) / 1000 + "s\n");
            }
        });

        thread.start();
    }
}
