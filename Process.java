public class Process {
    private int index, arrivalTime, burstTime, niceLevel;

    public Process(int index, int arrivalTime, int burstTime, int niceLevel) {
        this.index = index + 1;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.niceLevel = niceLevel;
    }

    public int getIndex() {
        return index;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getNiceTime() {
        return niceLevel;
    }
}