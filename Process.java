public class Process {
    private int index, arrivalTime, burstTime, niceLevel, waitingTime, turnaroundTime, responseTime;

    public Process(int index, int arrivalTime, int burstTime, int niceLevel) {
        this.index = index + 1;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.niceLevel = niceLevel;
        waitingTime = 0;
        turnaroundTime = 0;
        responseTime = 0;
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

    public int getNiceLevel() {
        return niceLevel;
    }

    public void updateWaitingTime(int n) {
        waitingTime = n;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void updateTurnaroundTime(int n) {
        turnaroundTime = n;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public void updateResponseTime(int n) {
        responseTime = n;
    }

    public int getResponseTime() {
        return responseTime;
    }
}