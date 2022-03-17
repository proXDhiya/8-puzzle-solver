import java.util.LinkedList;

public class gameLoop {
        
    private static boolean isAlredyIn(LinkedList<NodeBored> list, Bored bored) {
        for (NodeBored node : list)
            if (node.bored.equals(bored))
                return true;
        return false;
    }


    private static void moveLogic(LinkedList<NodeBored> list, int index, String move) {
        Bored newBored = new Bored(list.get(index).bored);
        switch (move) {
            case "up":
                newBored.moveUp();
                break;
            case "down":
                newBored.moveDown();
                break;
            case "left":
                newBored.moveLeft();
                break;
            case "right":
                newBored.moveRight();
                break;
        }
        if (!newBored.equals(list.get(index).bored)) {
            if (!isAlredyIn(list, newBored)) {
                list.add(new NodeBored(newBored, move, index));
            }
        }
    }


    // game loop
    public static void Start(String array) {
        // create a list to store all the node
        LinkedList<NodeBored> nodeList = new LinkedList<NodeBored>();


        // add first node to list
        nodeList.add(new NodeBored(new Bored(array), "init", -1));

    
        // index
        int index = 0;


        do {
            // create move up node
            moveLogic(nodeList, index, "up");
            if (NodeBored.gameEnd(nodeList)) break;


            // create move down node
            moveLogic(nodeList, index, "down");
            if (NodeBored.gameEnd(nodeList)) break;


            // create move left node
            moveLogic(nodeList, index, "left");
            if (NodeBored.gameEnd(nodeList)) break;


            // create move right node
            moveLogic(nodeList, index, "right");
            if (NodeBored.gameEnd(nodeList)) break;

            index++;
        } while(true);
    }


}
