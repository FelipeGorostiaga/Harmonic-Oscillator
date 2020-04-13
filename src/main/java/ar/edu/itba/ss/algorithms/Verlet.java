package ar.edu.itba.ss.algorithms;

import ar.edu.itba.ss.Configuration;

public class Verlet implements Algorithm {

    private Configuration config;

    public Verlet(Configuration configuration) {
        this.config = configuration;
    }

    @Override
    public void start() {
        double prevPosition, position, speed, k, dt, mass, gamma;
        prevPosition = calculatePreviousPosition();
        position = config.getPosition();
        speed = config.getSpeed();
        k = config.getElasticity();
        dt = config.getDt();
        mass = config.getMass();
        gamma = config.getGamma();

        System.out.println(0 + "\t" + position);
        for (double t = 0; t < config.getTime(); t += dt) {
            double force = - (k * position + gamma * speed);
            double newPosition = 2 * position - prevPosition + Math.pow(dt, 2) * force / mass;
            speed = calculateSpeed(newPosition, prevPosition);
            prevPosition = position;
            position = newPosition;

            System.out.println((t + dt) + "\t" + newPosition);
        }
    }

    private double calculateSpeed(double position, double previousPosition){
        return (position - previousPosition) / (2 * config.getDt());
    }

    private double calculatePreviousPosition(){
        double position = config.getPosition() - config.getDt() * config.getSpeed();
        double force = - (config.getElasticity() * config.getPosition() + config.getGamma() * config.getSpeed());
        position -= Math.pow(config.getDt(), 2) * force / (2 * config.getMass());
        return position;
    }
}
