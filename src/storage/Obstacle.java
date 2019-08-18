package storage;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Obstacle {

    // attributes
    private Rectangle2D rectangle;
    private int xMin, xMax, yMin, yMax;
    private ArrayList<Node> nodeArrayList;

    /**
     * Creates array od Nodes that are obstacles
     * @param s format of string is a,b;c,d;....;x,y
     */
    public Obstacle(String s) {

        // init array list
        nodeArrayList = new ArrayList<>();

        // array of String[]
        String[] strings = s.trim().split(";");

        for (String string : strings) {

            // split into coordinates
            String[] co = string.split(",");

            // add to array list
            nodeArrayList.add(new Node(
                    Integer.parseInt(co[0]),    // x-value
                    Integer.parseInt(co[1])     // y-value
            ));
        }

        // set minimums and maximums
        xMin = Math.min(nodeArrayList.get(0).getX(), nodeArrayList.get(1).getX());
        xMax = Math.max(nodeArrayList.get(0).getX(), nodeArrayList.get(1).getX());
        yMin = Math.min(nodeArrayList.get(0).getY(), nodeArrayList.get(1).getY());
        yMax = Math.max(nodeArrayList.get(0).getY(), nodeArrayList.get(1).getY());

        // set up rectangle for line collisions
        rectangle = new Rectangle2D.Double(
                (double) nodeArrayList.get(0).getX(),   // top left x-value
                (double) nodeArrayList.get(0).getY(),   // top left y-value
                Math.abs(nodeArrayList.get(0).getX() - nodeArrayList.get(1).getX()),    // width
                Math.abs(nodeArrayList.get(0).getY() - nodeArrayList.get(1).getY())     // height
        );
    }

    public int getXMin() {
        return xMin;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMin() {
        return yMin;
    }

    public int getYMax() {
        return yMax;
    }

    public ArrayList<Node> getNodeArrayList() {
        return nodeArrayList;
    }

    public Rectangle2D getRectangle() {
        return rectangle;
    }
}
