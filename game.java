public class game {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("\n" + color.TEXT_RED + "Please input a string of numbers" + color.TEXT_RESET);
            System.out.println(color.TEXT_CYAN + "Usage: " + color.TEXT_RESET + "java game --array <array>");
            System.out.println(color.TEXT_CYAN + "Usage: " + color.TEXT_RESET + "java game -a <array>");
            System.out.println(color.TEXT_GREEN + "Example: " + color.TEXT_RESET + "java game --array 402138657\n");
            System.exit(1);
        }


        if (args.length > 2) {
            System.out.println("\n" + color.TEXT_RED + "Error: multiple arguments" + color.TEXT_RESET);
            System.out.println(color.TEXT_CYAN + "Usage: " + color.TEXT_RESET + "java game --array <array>");
            System.out.println(color.TEXT_CYAN + "Usage: " + color.TEXT_RESET + "java game -a <array>");
            System.out.println(color.TEXT_GREEN + "Example: " + color.TEXT_RESET + "java game --array 402138657\n");
            System.exit(1);
        }


        String table = args[1];


        if (table.length() != 9) {
            System.out.println("\n" + color.TEXT_RED + "Error: array length is not 9" + color.TEXT_RESET);
            System.out.println(color.TEXT_GREEN + "Example: " + color.TEXT_RESET + "Exemple: java game --array 402138657\n");
            System.exit(1);
        }


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
