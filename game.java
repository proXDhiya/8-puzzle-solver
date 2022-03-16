import java.util.LinkedList;

public class game {

    // game loop
    private static void gameLoop() {
        // create a list to store all the node
        LinkedList<NodeBored> nodeList = new LinkedList<NodeBored>();


        // add first node to list
        nodeList.add(new NodeBored(new Bored("102387465"), "init", -1));

    
        // index
        int index = 0;


        do {
            // create move up node
            Bored boredUp = new Bored(nodeList.get(index).bored);
            boredUp.moveUp();
            if (!boredUp.equals(nodeList.get(index).bored)) {
                nodeList.add(new NodeBored(boredUp, "up", index));
            }
            if (NodeBored.gameEnd(nodeList)) break;


            // create move down node
            Bored boredDown = new Bored(nodeList.get(index).bored);
            boredDown.moveDown();
            if (!boredDown.equals(nodeList.get(index).bored)) {
                nodeList.add(new NodeBored(boredDown, "down", index));
            }
            if (NodeBored.gameEnd(nodeList)) break;


            // create move left node
            Bored boredLeft = new Bored(nodeList.get(index).bored);
            boredLeft.moveLeft();
            if (!boredLeft.equals(nodeList.get(index).bored)) {
                nodeList.add(new NodeBored(boredLeft, "left", index));
            }
            if (NodeBored.gameEnd(nodeList)) break;


            // create move right node
            Bored boredRight = new Bored(nodeList.get(index).bored);
            boredRight.moveRight();
            if (!boredRight.equals(nodeList.get(index).bored)) {
                nodeList.add(new NodeBored(boredRight, "right", index));
            }
            if (NodeBored.gameEnd(nodeList)) break;

            // move to next node
            index++;

        } while(true);
    }


    public static void main(String[] args) {
        // create Thread to calculate time of gameLoop
        Thread thread = new Thread(new Runnable() {
            public void run() {
                long startTime = System.currentTimeMillis();
                gameLoop();
                long endTime = System.currentTimeMillis();
                System.out.println("\n\nTime: " + (float)((endTime - startTime)) / 1000 + "s\n");
            }
        });

        thread.start();
    }
}
