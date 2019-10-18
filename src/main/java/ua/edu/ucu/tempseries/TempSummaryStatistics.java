package ua.edu.ucu.tempseries;

public class TempSummaryStatistics {
    double avgTemp;
    double devTemp;
    double minTemp;
    double maxTemp;

    public TempSummaryStatistics(double avgTemp, double devTemp, double minTemp, double maxTemp) {
        this.avgTemp = avgTemp;
        this.devTemp = devTemp;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public TempSummaryStatistics(double[] arr) {
        TemperatureSeriesAnalysis temporary;
        temporary = new TemperatureSeriesAnalysis(arr);
        this.avgTemp = temporary.average();
        this.devTemp = temporary.deviation();
        this.minTemp = temporary.min();
        this.maxTemp = temporary.max();
    }
}
