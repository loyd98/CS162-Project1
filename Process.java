public class Process {
    private int index, arrivalTime, burstTime, niceLevel, waitingTime, turnaroundTime, responseTime, runningTime, burstCopy, firstArrival;
    private boolean entered;

    public Process(int index, int arrivalTime, int burstTime, int niceLevel) {
        this.index = index + 1;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.niceLevel = niceLevel;
        runningTime = 0;
        waitingTime = 0;
        turnaroundTime = 0;
        responseTime = 0;
        burstCopy = burstTime;
        entered = false;
    }

    public int getIndex() {
        return index;
    }

    public void updateArrivalTime(int n) {
        arrivalTime += n;
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

    public void increaseCPUTime(int runningTime) {
        this.runningTime += runningTime;
    }

    public void decreaseBurstTime(int n) {
        this.burstTime -= n;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getBurstCopy() {
        return burstCopy;
    }

    public int getActualArrival() {
        return firstArrival;
    }

    public void setActualArrival(int firstArrival) {
        this.firstArrival = firstArrival;
    }

    public boolean hasEntered() {
        return entered;
    }

    public void setEntered(boolean entered) {
        this.entered = entered;
    }
}