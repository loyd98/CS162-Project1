public class FCFS extends Scheduler {

    public FCFS() {
        super();
    }

    @Override
    public void execute(Process[] processList) {
        int timeElapsed = 0;
        int totalBurstTime = 0;

        /*
        Clones the processList array so that it is arranged by arrival time.
        The processList array is retained so that each process is arranged by index when
        the array is iterated upon for the performance analysis portion of the output.
        The arrivalTimeList is a shallow copy of processList.
        */
        Process[] arrivalTimeList = processList.clone();
        bubbleSort(arrivalTimeList);

        for(int i = 0; i < arrivalTimeList.length; i++) {
            if(timeElapsed < arrivalTimeList[i].getArrivalTime()) {
                timeElapsed = arrivalTimeList[i].getArrivalTime();
            }
            // This updates the process's waiting time if it's been waiting for the previous process to finish.
            else if(timeElapsed > arrivalTimeList[i].getArrivalTime()) {
                arrivalTimeList[i].updateWaitingTime(timeElapsed - arrivalTimeList[i].getArrivalTime());
            }

            // Updates the performance parameters of current process
            arrivalTimeList[i].updateTurnaroundTime(arrivalTimeList[i].getWaitingTime() + arrivalTimeList[i].getBurstTime());
            arrivalTimeList[i].updateResponseTime(timeElapsed - arrivalTimeList[i].getArrivalTime());

            // Output of FCFS Algorithm per processor
            System.out.println(timeElapsed + " " + arrivalTimeList[i].getIndex() + " " + arrivalTimeList[i].getBurstTime() + "X");

            // Keeps track of the time elapsed and total burst time. To be used for analysis portion of data output.
            timeElapsed += arrivalTimeList[i].getBurstTime();
            totalBurstTime += arrivalTimeList[i].getBurstTime();
        }

        // FCFS Data Analysis
        analyzePerformance(processList, timeElapsed, totalBurstTime);
    }

}
