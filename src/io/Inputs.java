package io;

import storage.Node;
import storage.Obstacle;

import java.util.ArrayList;

public class Inputs {

    // attributes
    private int k, p, n, d;
    private Node start, end;
    private ArrayList<Node> samplePoints;
    private ArrayList<Obstacle> obstacles;

    /**
     * Used to store information received from scanner
     * @param k number of neighbours
     * @param p number of obstacles
     * @param n number of sample points
     * @param d dimension of map
     */
    public Inputs(int k, int p, int n, int d) {
        this.k = k;
        this.p = p;
        this.n = n;
        this.d = d;
        obstacles = new ArrayList<>();
        samplePoints = new ArrayList<>();
    }

    public ArrayList<Obstacle> getObstacles() {
        return obstacles;
    }

    public void addObstacle(String points) {
        Obstacle obstacle = new Obstacle(points.trim());
        obstacles.add(obstacle);
    }

    public Node getStart() {
        return start;
    }

    public void setStart(Node start) {
        this.start = start;
    }

    public Node getEnd() {
        return end;
    }

    public int getP() {
        return p;
    }

    public int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public void setEnd(Node end) {
        this.end = end;
    }

    public ArrayList<Node> getSamplePoints() {
        return samplePoints;
    }

    public void setSamplePoints(ArrayList<Node> samplePoints) {
        this.samplePoints = samplePoints;
    }

    public void addSamplePoint(String samplePoint) {
        String[] point = samplePoint.trim().split(",");
        samplePoints.add(new Node(
                Integer.parseInt(point[0]), // x-value
                Integer.parseInt(point[1])  // y-value
        ));
    }

    public void print() {

        // number of neighbours
        System.out.println("Neighbours\t:\t" + k);

        // skip line
        System.out.println();

        // number of obstacles
        System.out.println("Obstacles\t:\t" + p);

        // print obstacles
        for (int i = 0; i < obstacles.size(); i++) {
            System.out.println(i + 1);
            ArrayList<Node> tmp = obstacles.get(i).getNodeArrayList();
            for (Node node : tmp)
                System.out.println(node.getX() + " , " + node.getY());

        }

        // skip line
        System.out.println();

        // number of sample points
        System.out.println("Sample points\t:\t" + n);

        for (Node n : samplePoints) {
            System.out.print("(" + n.getX() + "," + n.getY() + ")" +
                    " order = " + n.getId() + " [ ");
            for (Node m : n.getNeighbours())
                System.out.print(m.getId() + " ");
            System.out.println("]");
        }

        // skip line
        System.out.println();

        // dimension of map
        System.out.println("Dimension of map\t:\t" + d);
    }
}
