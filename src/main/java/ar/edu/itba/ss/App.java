package ar.edu.itba.ss;

import ar.edu.itba.ss.algorithms.Algorithm;
import ar.edu.itba.ss.algorithms.Beeman;
import ar.edu.itba.ss.algorithms.GearPredictorO5;
import ar.edu.itba.ss.algorithms.Verlet;

public class App {
    public static void main( String[] args ) {
        Configuration config = CommandLineParser.parseCommandLine(args);
        Algorithm algorithm;
        switch (config.getAlgorithm()) {
            case "Verlet":
                algorithm = new Verlet(config);
                break;
            case "GP5":
                algorithm = new GearPredictorO5(config);
                break;
            default:
                algorithm = new Beeman(config);
        }
        algorithm.start();
    }
}
