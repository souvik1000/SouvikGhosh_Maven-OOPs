package com.first.maven_oop;

public abstract class Sweet {

    private double SugarLevel;

    private double Weight;

    public double get_SugarLevel() {
        return SugarLevel;
    }

    public double get_Weight() {
        return Weight;
    }

    public void set_SugarLevel(double sugarLevel) {
        this.SugarLevel = sugarLevel;
    }

    public void set_Weight(double weight) {
        this.Weight = weight;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName() + " " + SugarLevel + " " + Weight);
        return sb.toString();
    }

}

