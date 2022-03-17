import java.util.LinkedList;


public class NodeBored {
    Bored bored;
    String action;
    int RacineNodeNum;


    public NodeBored(Bored bored, String action, int RacineNodeNum) {
        this.bored = bored;
        this.action = action;
        this.RacineNodeNum = RacineNodeNum;
    }


    public void updateNode(Bored bored, String action, int RacineNodeNum) {
        this.bored = bored;
        this.action = action;
        this.RacineNodeNum = RacineNodeNum;
    }


    public void printNode() {
        bored.printBored();
        System.out.println("Last Action: " + action);
        System.out.println("Racine node num: " + RacineNodeNum + "\n");
    }


    public static void clearConsole() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    
    // end of game
    public static boolean gameEnd(LinkedList<NodeBored> nodeList) {
        if (nodeList.peekLast().bored.isSolved()) {
            clearConsole();
            nodeList.peekFirst().printNode();
            nodeList.peekLast().printNode();
            System.out.println("Solved! ^-^\n\n");
            int index = nodeList.peekLast().RacineNodeNum;

            LinkedList<String> actionList = new LinkedList<String>();
            actionList.add(nodeList.peekLast().action);
            while (index != 0) {
                actionList.addFirst(nodeList.get(index).action);
                index = nodeList.get(index).RacineNodeNum;
            }

            for (int i = 0; i < actionList.size(); i++) {
                System.out.print("Action " + (i + 1) + " : " + actionList.get(i) + "\n");
            }

            return true;
        }
        return false;
    }
}
