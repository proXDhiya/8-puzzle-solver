import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

public class app {
    private static LinkedList<Element> queue = new LinkedList<Element>();


    private static Boolean alreadyExists(Board board) {
        for (Element element : queue)
            if (board.isEqual(element.getBoard()))
                return true;
        return false;
    }


    private static void printGraph() {
        System.out.println("start printing graph");
        int index = 0;
        for (Element element : queue) {
            System.out.print("index: " + index++ + " ");
            System.out.print("\taction: " + element.getAction() + " ");
            System.out.print("\tdepth: " + element.getDepth() + " ");
            System.out.print("\tfromWho: " + element.getFromWho() + " ");
            System.out.print("\tboard: " + element.getBoard().toString() + "\tchilds: ");
            for (Integer child : element.getChilds())
                System.out.print(child + " | ");
            System.out.println();
        }
    }


    private static void makeGraph(String str, int initDepth) {
        Element root = new Element(str, "init", 0, -1);
        queue.add(root);

        int depth = 0;
        int index = 0;
        List<Integer> childs = new ArrayList<Integer>();

        // start building the graph
        while (depth - 1 != initDepth && index != queue.size()) {
            Board upBoard = new Board(queue.get(index).getBoard());
            upBoard.moveUp();
            if (!alreadyExists(upBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element up = new Element(upBoard, "up", depth, index);
                queue.add(up);
                childs.add(queue.size() - 1);
            }


            Board downBoard = new Board(queue.get(index).getBoard());
            downBoard.moveDown();
            if (!alreadyExists(downBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element down = new Element(downBoard, "down", depth, index);
                queue.add(down);
                childs.add(queue.size() - 1);
            }


            Board leftBoard = new Board(queue.get(index).getBoard());
            leftBoard.moveLeft();
            if (!alreadyExists(leftBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element left = new Element(leftBoard, "left", depth, index);
                queue.add(left);
                childs.add(queue.size() - 1);
            }


            Board rightBoard = new Board(queue.get(index).getBoard());
            rightBoard.moveRight();
            if (!alreadyExists(rightBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element right = new Element(rightBoard, "right", depth, index);
                queue.add(right);
                childs.add(queue.size() - 1);
            }

            
            queue.get(index).setChilds(childs);
            childs.clear();
            index++;
        }
    }


    private static void BFS() {
        while (!queue.isEmpty()) {
            Element element = queue.remove();
            if (element.getBoard().isSolved()) {
                System.out.println("Solved!");
                return;
            }
        }
        System.out.println("Not solved! at this depth");
    }


    private static void IDS() {
        int index = 0;
        while (!queue.isEmpty()) {
            Element element = queue.get(index);
            if (element.getChilds().length != 0) {
                index = element.getChilds()[0];
                element.removeFirstChild();
                continue;
            }

            if (element.getBoard().isSolved()) {
                System.out.println("Solved!");
                return;
            } else {
                index = element.getFromWho();
                if (index == -1) {
                    System.out.println("Not solved! at this depth");
                    return;
                }
            }
        }
        System.out.println("Not solved! at this depth");
    }


    private static void StartBuildingGraph(String str, int initDepth) {
        queue.clear();
        System.out.print("Start building graph! with depth: " + initDepth);
        makeGraph(str, initDepth);
        System.out.print("\r                                              ");
    }


    private static void calculateTime(String Algorithm) {
        long startTime = System.currentTimeMillis();
        if (Algorithm.equals("BFS"))
            BFS();
        if (Algorithm.equals("IDS"))
            IDS();
        long endTime = System.currentTimeMillis();
        System.out.println("Algorithm " + Algorithm + " Time: " + (endTime - startTime) + " ms\n");
    }


    public static void main(String[] argc) {
        List<String> boardStr = new ArrayList<String>();
        boardStr.add("012345678");
        boardStr.add("142035678");
        boardStr.add("142358607");
        boardStr.add("032418657");
        boardStr.add("371405628");


        List<Integer> depthList = new ArrayList<Integer>();
        depthList.add(2);
        depthList.add(5);
        depthList.add(10);
        depthList.add(15);
        depthList.add(17);
        

        for (String str : boardStr) {
            System.out.println("------------Board: " + str + "-------------");
            for (Integer depth : depthList) {
                // Build and calculate using BFS
                StartBuildingGraph(str, depth);
                System.out.print("\nDepth: " + depth + " \n");
                calculateTime("BFS");

                // Build and calculate using IDFS
                StartBuildingGraph(str, depth);
                System.out.print("\nDepth: " + depth + " \n");
                calculateTime("IDS");
            }
            System.out.println("\n-----------------------------------------");
        }
    }
}
