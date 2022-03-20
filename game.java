public class game {
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("\n" + color.TEXT_RED + "Please input a string of numbers" + color.TEXT_RESET + "\n");
            System.out.println(color.TEXT_CYAN + "Usage: " + color.TEXT_RESET + "java game --array <array> --output <output>");
            System.out.println(color.TEXT_CYAN + "Usage: " + color.TEXT_RESET + "java game -a <array> -o <output>\n");
            System.out.println(color.TEXT_GREEN + "Example: " + color.TEXT_RESET + "java game --array 402138657 -o 012345678\n");
            System.exit(1);
        }

        String table = args[1];
        String output = args[3];
        if (table.length() != 9 || output.length() != 9) {
            System.out.println("\n" + color.TEXT_RED + "Error: array length is not 9" + color.TEXT_RESET);
            System.out.println(color.TEXT_GREEN + "Example: " + color.TEXT_RESET + "Exemple: java game -a 402138657 -o 012345678\n");
            System.exit(1);
        }


        // create Thread to calculate time of gameLoop
        Thread thread = new Thread(new Runnable() {
            public void run() {
                long startTime = System.currentTimeMillis();
                gameLoop.StartDfs(table, output);
                long endTime = System.currentTimeMillis();
                System.out.println("\n\nTime: " + (float)((endTime - startTime)) / 1000 + "s\n");
            }
        });

        thread.start();
    }
}
