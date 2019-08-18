package mapper;

import io.Inputs;
import storage.Node;
import storage.Obstacle;

import java.awt.geom.Line2D;
import java.util.ArrayList;

public class ProbabilisticRoadMap {

    // attributes
    private Inputs inputs;

    public ProbabilisticRoadMap(Inputs inputs) {
        this.inputs = inputs;
        KNN knn = new KNN(inputs);
        this.make();
        knn.create();
        this.removeLinks();
    }

    private void make() {

        // get sample inputs array and obstacles
        ArrayList<Obstacle> obstacles = inputs.getObstacles();
        ArrayList<Node> samplePoints = inputs.getSamplePoints();

        // create list of elements to remove
        ArrayList<Node> remove = new ArrayList<>();

        for (Node samplePoint : samplePoints) {
            for (Obstacle obstacle : obstacles) {
                if (collision(samplePoint, obstacle))
                    remove.add(samplePoint);
            }
        }

        // remove collisions
        for (Node node : remove)
            samplePoints.remove(node);

        // set ids
        for (int i = 0; i < samplePoints.size(); i++)
            samplePoints.get(i).setId(i + 2);

        // set start and end id
        inputs.getStart().setId(0);
        inputs.getEnd().setId(1);
    }

    private void removeLinks() {

        // get sample inputs array and obstacles
        ArrayList<Obstacle> obstacles = inputs.getObstacles();
        ArrayList<Node> samplePoints = inputs.getSamplePoints();

        // add start and end
        samplePoints.add(inputs.getStart());
        samplePoints.add(inputs.getEnd());

        // remove shape collisions
        for (Node samplePoint : samplePoints) {

            // get neighbours
            ArrayList<Node> neighbours = samplePoint.getNeighbours();

            // remove trailing neighbours
            ArrayList<Node> tmp = new ArrayList<>();

            for (int i = 0; i < inputs.getK(); i++)
                tmp.add(neighbours.get(i));

            // set to k neighbours
            neighbours = tmp;
            samplePoint.setNeighbours(tmp);

            // create list of elements to remove
            ArrayList<Node> remove = new ArrayList<>();

            for (Node node : neighbours) {
                for (Obstacle obstacle : obstacles) {
                    // remove colliding things
                    if (shapeCollision(samplePoint, node, obstacle)) {
                        remove.add(node);
                    }
                }
            }

            // remove neighbours with collisions
            neighbours.removeAll(remove);
            samplePoint.setNeighbours(neighbours);
        }
    }

    private boolean collision(Node point, Obstacle obstacle) {
        return (obstacle.getXMin() <= point.getX() && point.getX() <= obstacle.getXMax())
                && (obstacle.getYMin() <= point.getY() && point.getY() <= obstacle.getYMax());
    }

    private boolean shapeCollision(Node a, Node b, Obstacle obstacle) {

        // return variable
        boolean ret;

        // get rectangle corners
        double minX = obstacle.getRectangle().getMinX();
        double maxX = obstacle.getRectangle().getMaxX();
        double minY = obstacle.getRectangle().getMinY();
        double maxY = obstacle.getRectangle().getMaxY();

        // define line segment
        Line2D line = new Line2D.Double(a.getX(), a.getY(), b.getX(), b.getY());

        // check left edge
        ret = line.intersectsLine(minX, minY, minX, maxY);
        if (ret) return true;

        // check top edge
        ret = line.intersectsLine(minX, minY, maxX, minY);
        if (ret) return true;

        // check right edge
        ret = line.intersectsLine(maxX, minY, maxX, maxY);
        if (ret) return true;

        // check bottom edge
        ret = line.intersectsLine(minX, maxY, maxX, maxY);

        return ret;
    }

    public void print() {

        // size
        int size = inputs.getSamplePoints().size();

        // matrix
        int[][] matrix = new int[size][size];

        for (int i = 0; i < size; i++) {

            // get id outer
            int outer = inputs.getSamplePoints().get(i).getId();

            // get neighbours
            ArrayList<Node> neighbours = inputs.getSamplePoints().get(i).getNeighbours();

            for (Node neighbour : neighbours) {

                // get id inner
                int inner = neighbour.getId();

                // set adjacency
                matrix[outer][inner] = 1;
            }
        }

        // string builder for output
        StringBuilder builder = new StringBuilder();

        for (int[] ints : matrix) {
            for (int j = 0; j < matrix.length; j++) {
                builder.append(ints[j]).append(" ");
            }
            builder.deleteCharAt(builder.length() - 1);
            builder.append("\n");
        }

        System.out.println(builder.toString());
    }
}
