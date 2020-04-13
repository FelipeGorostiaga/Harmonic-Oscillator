package ar.edu.itba.ss.algorithms;

import ar.edu.itba.ss.Configuration;

public class Beeman implements Algorithm {

    private Configuration config;

    public Beeman(Configuration configuration) {
        this.config = configuration;
    }

    @Override
    public void start() {

        double position = config.getPosition();
        double v = config.getSpeed();
        double dt = config.getDt();
        double prevPosition = position - v * dt;
        double prevAcceleration = calcAcceleration(prevPosition, v);

        System.out.println(0 + "\t" + position);
        for (double t = 0; t < config.getTime(); t += dt){

            double a = calcAcceleration(position, v);
            position = position + v * dt + (2.0/3) * a * Math.pow(dt, 2) - (1.0/6) * prevAcceleration * Math.pow(dt, 2);
            double predictedV = v + (3.0/2) * a * dt - (1.0/2) * prevAcceleration * dt;
            double newA = calcAcceleration(position, predictedV);
            v = v + 1.0/3 * newA * dt + (5.0/6) * a * dt - (1.0/6) * prevAcceleration * dt;
            prevAcceleration = a;

            System.out.println((t + dt) + "\t" + position);
        }
    }

    private double calcAcceleration(double r, double v){
        double f = -config.getElasticity() * r - config.getGamma() * v;
        return f / config.getMass();
    }
}
