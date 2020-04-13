package ar.edu.itba.ss;

import ar.edu.itba.ss.algorithms.Algorithm;
import ar.edu.itba.ss.algorithms.Beeman;
import ar.edu.itba.ss.algorithms.GearPredictorO5;
import ar.edu.itba.ss.algorithms.Verlet;


public class App {
    public static void main( String[] args ) {
        Configuration config = CommandLineParser.parseCommandLine(args);
        Algorithm algorithm = null;
        switch (config.getAlgorithm()) {
            case "Beeman":
                algorithm = new Beeman(config);
                break;
            case "Verlet":
                algorithm = new Verlet(config);
                break;
            case "GP":
                algorithm = new GearPredictorO5(config);
                break;
            default:
                algorithm = new Beeman(config);
        }
        algorithm.start();
    }
}
