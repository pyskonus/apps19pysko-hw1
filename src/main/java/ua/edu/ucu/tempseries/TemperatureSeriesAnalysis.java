package ua.edu.ucu.tempseries;


import java.util.Arrays;
import java.util.InputMismatchException;

public class TemperatureSeriesAnalysis {
    private double[] temperatureSeries;
    private int realSize;

    public TemperatureSeriesAnalysis(double[] temperatureSeries) throws InputMismatchException {
        if (!inputValid(temperatureSeries)) throw new InputMismatchException();
        this.temperatureSeries = Arrays.copyOf(temperatureSeries, temperatureSeries.length);
        realSize = this.temperatureSeries.length;
    }

    private boolean inputValid(double[] riad) {
        for (double value: riad) {
            if (value < -273) return false;
        }
        return true;
    }

    public double average() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException();
        double suma = 0;
        for (double value: this.temperatureSeries) {
            suma += value;
        }
        return suma / this.temperatureSeries.length;
    }

    public double deviation() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException();
        double sum_of_sq = 0;
        double avg = this.average();
        for (double value: this.temperatureSeries) {
            sum_of_sq += Math.pow(value - avg, 2);
        }
        return sum_of_sq/this.temperatureSeries.length;
    }

    public double min() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException();
        double minimum = this.temperatureSeries[0];
        for (double value: this.temperatureSeries) {
            if (value < minimum) minimum = value;
        }
        return minimum;
    }

    public double max() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException();
        double maximum = this.temperatureSeries[0];
        for (double value: this.temperatureSeries) {
            if (value < maximum) maximum = value;
        }
        return maximum;
    }

    public double findTempClosestToZero() throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException();
        return findTempClosestToValue(0);
    }

    public double findTempClosestToValue(double tempValue) throws IllegalArgumentException {
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException();
        double result = this.temperatureSeries[0];
        double smallest = Math.abs(result - tempValue);
        for (double value: this.temperatureSeries) {
            double temp = Math.abs(value - tempValue);
            if (temp < smallest || (temp == smallest && temp > result)) {
                smallest = temp;
                result = temp;
            }
        }
        return result;
    }

    private double[] helper(double tempValue, boolean mode) {
        int amount = 0;
        for (double value: this.temperatureSeries) {
            if ((value < tempValue) == mode) amount += 1;
        }
        double result[];
        result = new double[amount];
        return result;
    }

    public double[] findTempsLessThen(double tempValue) {
        int index = 0;
        double[] result = helper(tempValue, true);
        for (double value: this.temperatureSeries) {
            if (value < tempValue) {
                result[index] = value;
                index += 1;
            }
        }
        return result;
    }

    public double[] findTempsGreaterThen(double tempValue) {
        int index = 0;
        double[] result = helper(tempValue, false);
        for (double value: this.temperatureSeries) {
            if (value >= tempValue) {
                result[index] = value;
                index += 1;
            }
        }
        return result;
    }

    public TempSummaryStatistics summaryStatistics() throws IllegalArgumentException{
        if (this.temperatureSeries.length == 0) throw new IllegalArgumentException();
        TempSummaryStatistics result;
        result = new TempSummaryStatistics(this.temperatureSeries);
        return result;
    }

    public int addTemps(double... temps) throws InputMismatchException {
        if (!inputValid(temps)) throw new InputMismatchException();
        for (double value: temps) {
            if (realSize == this.temperatureSeries.length) {
                this.temperatureSeries = Arrays.copyOf(this.temperatureSeries, this.temperatureSeries.length * 2);
            }
            this.temperatureSeries[realSize] = value;
            this.realSize += 1;
        }
        return 0;
    }
}
