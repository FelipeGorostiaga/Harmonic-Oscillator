package ar.edu.itba.ss;

public class Configuration {

    private double elasticity;
    private double mass;
    private double gamma;
    private double time;
    private double position;
    private double speed;
    private double dt;
    private String algorithm;

    Configuration(double elasticity, double mass, double gamma, double time, double position, double dt, String algorithm) {
        this.elasticity = elasticity;
        this.mass = mass;
        this.gamma = gamma;
        this.time = time;
        this.position = position;
        this.speed = -gamma / (2 * mass);
        this.dt = dt;
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public double getElasticity() {
        return elasticity;
    }

    public void setElasticity(double elasticity) {
        this.elasticity = elasticity;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getGamma() {
        return gamma;
    }

    public void setGamma(double gamma) {
        this.gamma = gamma;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public double getPosition() {
        return position;
    }

    public void setPosition(double position) {
        this.position = position;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

}
