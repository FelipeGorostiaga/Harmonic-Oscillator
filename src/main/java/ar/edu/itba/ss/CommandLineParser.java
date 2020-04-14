package ar.edu.itba.ss;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

public class CommandLineParser {

    private static Options createOptions() {
        Options options = new Options();
        options.addOption("h", "help", false, "Show help menu");
        options.addOption("t", "time", true, "Time to run simulation");
        options.addOption("g", "gamma", true, "Gamma value");
        options.addOption("k", "elasticity", true, "Elasticity value");
        options.addOption("alg", "algorithm", true, "Algorithm to use");
        options.addOption("m", "mass", true, "Mass of particle");
        options.addOption("dt", "delta-time", true, "Time interval value");
        return options;
    }


    static Configuration parseCommandLine(String[] args) {
        Options options = createOptions();
        // Default values
        double mass = 70;
        double time = 5;
        double k = 10000;
        double gamma = 100;
        double position = 1;
        double dt = 0.01;
        String algo = "Beeman";

        org.apache.commons.cli.CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("h")) printHelp(options);
            if (cmd.hasOption("t")) {
                time = Double.parseDouble(cmd.getOptionValue("t"));
            }
            if (cmd.hasOption("p")) {
                position = Double.parseDouble(cmd.getOptionValue("p"));
            }
            if (cmd.hasOption("dt")) {
                dt = Double.parseDouble(cmd.getOptionValue("dt"));
            }
            if (cmd.hasOption("g")) {
                gamma = Double.parseDouble(cmd.getOptionValue("g"));
            }
            if (cmd.hasOption("mass")) {
                mass = Double.parseDouble(cmd.getOptionValue("m"));
            }
            if (cmd.hasOption("k")) {
                k = Double.parseDouble(cmd.getOptionValue("k"));
            }
            if (cmd.hasOption("alg")) {
                algo = cmd.getOptionValue("alg");
            }
        } catch (Exception e) {
            System.out.println("Invalid command format");
            printHelp(options);
        }
        return new Configuration(k, mass, gamma, time, position, dt, algo);
    }


    private static void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();
        helpFormatter.printHelp("Damped Harmonic Oscillator", options);
        System.exit(0);
    }
}
