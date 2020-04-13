package ar.edu.itba.ss.algorithms;

import ar.edu.itba.ss.Configuration;

import java.util.ArrayList;
import java.util.List;

public class GearPredictorO5 implements Algorithm {


    private static final int ORDER = 5;
    private static final int[] factorials = {1, 1, 2, 6, 24, 120};
    private static final double[] alphas5 = {3.0/16, 251.0/360, 1.0, 11.0/18, 1.0/6, 1.0/60};

    private Configuration config;

    public GearPredictorO5(Configuration configuration) {
        this.config = configuration;
    }

    @Override
    public void start() {
        List<Double> derivatives = new ArrayList<>(5);
        derivatives.add(config.getPosition());
        derivatives.add(config.getSpeed());
        derivatives = recursiveDerivatives(ORDER, 1, derivatives);
        double time = config.getTime();
        double dt = config.getDt();
        System.out.println(0 + "\t" + config.getPosition());
        for (double t = 0; t < time; t += dt){
            double[] predictions = getPrediction(derivatives);
            double error = evaluate(predictions);
            derivatives = fix(predictions, error);
            System.out.println((t + dt) + "\t" + derivatives.get(0));
        }
    }

    private List<Double> recursiveDerivatives(int order, int current, List<Double> values){
        if (current == order){
            return values;
        }
        int size = values.size();
        double value = (config.getElasticity() * values.get(size - 2) + config.getGamma() * values.get(size - 1));
        value = - value / config.getMass();
        values.add(value);
        return recursiveDerivatives(order, current + 1, values);
    }


    private double[] getPrediction(List<Double> values){
        int size = values.size();
        double[] predictions = new double[ORDER + 1];
        for (int i = 0; i < size; i++){
            for (int j = 0; j <= i; j++){
                predictions[size - i - 1] += values.get(size - i + j - 1) * Math.pow(config.getDt(), j) / factorials[j];
            }
        }
        return predictions;
    }

    private double evaluate(double[] predictions){
        double realAcceleration = config.getElasticity() * predictions[0] + config.getGamma() * predictions[1];
        realAcceleration = - realAcceleration / config.getMass();
        double error = realAcceleration - predictions[2];
        error = error * Math.pow(config.getDt(), 2) / factorials[2];
        return error;
    }

    private List<Double> fix(double[] predictions, double error){
        List<Double> list = new ArrayList<>(ORDER + 1);
        for (int i = 0; i < predictions.length; i++){
            double value = predictions[i] + alphas5[i] * error * factorials[i] / Math.pow(config.getDt(), i);
            list.add(value);
        }
        return list;
    }

}
