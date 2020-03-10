public class Process {
    private int index, arrivalTime, burstTime, niceLevel;

    public Process(int index, int arrivalTime, int burstTime, int niceLevel) {
        this.index = index + 1;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.niceLevel = niceLevel;
    }
}