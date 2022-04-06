import java.util.List;
import java.sql.Time;
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


    private static void makeGraph(String str, int initDepth) {
        Element root = new Element(str, "init", 0);
        queue.add(root);

        int depth = 0;
        int index = 0;
        List<Integer> childs = new ArrayList<Integer>();

        // start building the graph
        while (depth - 1 != initDepth) {
            Board upBoard = new Board(queue.get(index).getBoard());
            upBoard.moveUp();
            if (!alreadyExists(upBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element up = new Element(upBoard, "up", depth);
                queue.add(up);
                childs.add(queue.size() - 1);
            }


            Board downBoard = new Board(queue.get(index).getBoard());
            downBoard.moveDown();
            if (!alreadyExists(downBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element down = new Element(downBoard, "down", depth);
                queue.add(down);
                childs.add(queue.size() - 1);
            }


            Board leftBoard = new Board(queue.get(index).getBoard());
            leftBoard.moveLeft();
            if (!alreadyExists(leftBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element left = new Element(leftBoard, "left", depth);
                queue.add(left);
                childs.add(queue.size() - 1);
            }


            Board rightBoard = new Board(queue.get(index).getBoard());
            rightBoard.moveRight();
            if (!alreadyExists(rightBoard)) {
                depth = queue.get(index).getDepth() + 1;
                Element right = new Element(rightBoard, "right", depth);
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
                System.out.println("Depth: " + element.getDepth());
                System.out.println("Board: " + element.getBoard().toString());
                return;
            }
        }
        System.out.println("Not solved! at this depth");
    }


    public static void main(String[] argc) {
        String str = "275301468";


        // build the graph
        System.out.println("start building graph");
        makeGraph(str, 15);
        System.out.println("end building graph with " + queue.size() + " nodes");


        // calculate the time it takes to solve the puzzle using BFS
        System.out.println("start BFS");
        Time startTime = new Time(System.currentTimeMillis());
        BFS();
        Time endTime = new Time(System.currentTimeMillis());
        System.out.println("BFS time: " + ((double)(endTime.getTime() - startTime.getTime()) / 1000) + "s");
    }
}
