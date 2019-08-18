import io.Inputs;
import mapper.ProbabilisticRoadMap;
import storage.Node;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        // string to store scanner input
        String in;

        // scanner to read input
        Scanner scanner = new Scanner(System.in);

        // get first line k, p, n, d
        in = scanner.nextLine();

        // separate inputs and store
        String[] strings = in.split(" ");

        // store inputs in class
        Inputs inputs = new Inputs(
                Integer.parseInt(strings[0]),   // k number of neighbours
                Integer.parseInt(strings[1]),   // p number of obstacles
                Integer.parseInt(strings[2]),   // n number of sample points
                Integer.parseInt(strings[3])    // d dimension of map
        );

        // get start point
        in = scanner.nextLine();
        strings = in.split(",");
        inputs.setStart(new Node(
                Integer.parseInt(strings[0].trim()),   // start node x-value
                Integer.parseInt(strings[1].trim())    // start node y-value
        ));

        // get end point
        in = scanner.nextLine();
        strings = in.split(",");
        inputs.setEnd(new Node(
                Integer.parseInt(strings[0].trim()),   // end node x-value
                Integer.parseInt(strings[1].trim())    // end node y-value
        ));

        // get obstacles
        for (int i = 0; i < inputs.getP(); i++) {

            // get line
            in = scanner.nextLine();

            // add obstacle
            inputs.addObstacle(in);
        }

        // get sample points
        for (int i = 0; i < inputs.getN(); i++) {

            // get line
            in = scanner.nextLine();

            // add sample point
            inputs.addSamplePoint(in);
        }

        // create prm
        ProbabilisticRoadMap roadmap = new ProbabilisticRoadMap(inputs);

        // print adjacency
        roadmap.print();
    }
}
