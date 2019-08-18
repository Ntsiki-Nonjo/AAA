package storage;

import java.util.ArrayList;

public class Node implements Comparable<Node> {

    // attributes
    private Node parent;
    private int id, x, y;
    private double distance;

    private ArrayList<Node> neighbours;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.neighbours = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public int compareTo(Node o) {
        return Double.compare(distance, o.getDistance());
    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(ArrayList<Node> neighbours) {
        this.neighbours = new ArrayList<>(neighbours);
    }
}
