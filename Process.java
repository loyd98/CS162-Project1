public class Process {
    private int index, arrivalTime, burstTime, niceLevel, waitingTime, turnaroundTime, responseTime, runningTime;

    public Process(int index, int arrivalTime, int burstTime, int niceLevel) {
        this.index = index + 1;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.niceLevel = niceLevel;
        runningTime = 0;
        waitingTime = 0;
        turnaroundTime = 0;
        responseTime = 0;
    }

    public int getIndex() {
        return index;
    }

    public void updateArrivalTime(int n) {
        arrivalTime = n;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void updateBurstTime(int n) {
        burstTime = n;
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

    public int getRunningTime() {
        return runningTime;
    }

    public void increaseRunningTime(int runningTime) {
        this.runningTime += runningTime;
    }

    public void decreaseBurstTime(int n) {
        this.burstTime -= n;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public boolean isCompleted() {
        return burstTime <= -1;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }
}