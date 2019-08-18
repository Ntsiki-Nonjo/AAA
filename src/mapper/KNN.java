package mapper;

import io.Inputs;
import storage.Node;

import java.util.ArrayList;
import java.util.Collections;

class KNN {

    // attributes
    private Inputs inputs;

    KNN(Inputs inputs) {
        this.inputs = inputs;
    }

    void create() {

        // temporary
        ArrayList<Node> temp = new ArrayList<>(inputs.getSamplePoints());

        // add start and end
        temp.add(inputs.getStart());
        temp.add(inputs.getEnd());

        for (int i = 0; i < temp.size(); i++) {

            // create temporary array and sort
            ArrayList<Node> tmp = new ArrayList<>(temp);

            // get current node
            Node current = tmp.get(i);

            for (int j = 0; j < temp.size(); j++) {
                // to avoid adding itself
                tmp.get(j).setDistance(getDistance(current, tmp.get(j)));
            }

            // sort the array
            Collections.sort(tmp);

            // set neighbours
            current.setNeighbours(tmp);

            // remove duplicate neighbour
            current.getNeighbours().remove(current);
        }
    }

    private double getDistance(Node a, Node b) {

        // x, y distance
        int x = a.getX() - b.getX();
        int y = a.getY() - b.getY();

        // absolute them
        x = Math.abs(x);
        y = Math.abs(y);

        // square them
        x = x * x;
        y = y * y;

        return Math.sqrt(x + y);
    }
}
