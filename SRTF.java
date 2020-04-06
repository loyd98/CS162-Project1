import java.util.ArrayList;
import java.util.Arrays;

public class SRTF extends Scheduler {
    public SRTF() {
        super();
    }

    @Override
    public void execute(Process[] processList) {
        int timeElapsed = 0;

        Process[] arrivalTimeList = processList.clone();
        ArrayList<Process> processQueue = new ArrayList<>(Arrays.asList(bubbleSort(arrivalTimeList)));

        // current represents the process currently in the CPU
        Process current = null;

        int minBurstTime = Integer.MAX_VALUE;
        int startOfBurst = 1;

        while (!processQueue.isEmpty()) {

            if (current != null) {
                for (Process p: processQueue) {
                    // Switches the process that is ready to run (has shorter burst time, bound to arrive, and hasn't halted yet) and sends it to the CPU
                    if (p.getArrivalTime() == timeElapsed && p.getBurstTime() > 0 && p.getBurstTime() < minBurstTime) {
                        System.out.println(startOfBurst + " " + current.getIndex() + " " + current.getRunningTime());
                        current.setRunningTime(0);

                        startOfBurst = timeElapsed;
                        current = p;
                        minBurstTime = p.getBurstTime();

                    } else if (p.getBurstTime() == 0) {
                        // If the process is done running, it is printed and removed from the list of processes.
                        System.out.println(startOfBurst + " " + p.getIndex() + " " + p.getRunningTime() + "X");
                        startOfBurst = timeElapsed;

                        // code for the extra requirements
                        p.updateTurnaroundTime(startOfBurst - p.getArrivalTime());
                        p.updateWaitingTime(p.getTurnaroundTime() - p.getBurstCopy());
                        p.updateResponseTime(p.getActualArrival() - p.getArrivalTime());

                        processQueue.remove(p);
                        // After removing the process, CPU is assigned with the first item in the list (which is about to arrive or has arrived already)
                        if (!processQueue.isEmpty()) {
                            current = processQueue.get(0);
                        }
                        break;
                    }
                }
            } else {
                startOfBurst = timeElapsed;
                current = processQueue.get(0);
                minBurstTime = processQueue.get(0).getBurstTime();
            }

            // This portion records the actual time the process has arrived in the CPU.
            if (!current.hasEntered()) {
                current.setActualArrival(timeElapsed);
                current.setEntered(true);
            }

            timeElapsed++;
            current.increaseCPUTime(1);
            current.decreaseBurstTime(1);


//            for (Process p: processQueue) {
//
//                if (p.getArrivalTime() == timeElapsed && p.getBurstTime() > 0 && p.getBurstTime() < minBurstTime) {
//
//                    if (current != null) {
//                        System.out.println(exitTime + " " + current.getIndex() + " " + current.getRunningTime());
//                        current.setRunningTime(0);
//                    }
//
//                    exitTime = timeElapsed;
//                    current = p;
//                    minBurstTime = p.getBurstTime();
//
//                } else if (p.getBurstTime() == 0) {
//                    System.out.println(exitTime + " " + p.getIndex() + " " + p.getRunningTime() + "X");
//                    exitTime = timeElapsed;
//                    p.updateTurnaroundTime(exitTime - p.getArrivalTime());
//                    p.updateWaitingTime(p.getTurnaroundTime() - p.getBurstCopy());
//
//                    // fix this
//                    int responseDiff = p.getActualArrival() - p.getArrivalTime() - 1;
//                    if (responseDiff == -1) {
//                        responseDiff = 0;
//                    }
//                    p.updateResponseTime(responseDiff);
//                    current = p;
//                }
//            }
//
//            if (current != null) {
//                if (!current.hasEntered()) {
//                    current.setActualArrival(timeElapsed);
//                    current.setEntered(true);
//                    System.out.println(current.getActualArrival());
//                }
//
//                if (current.getBurstTime() == 0 ) {
//                    processQueue.remove(current);
//                    if (!processQueue.isEmpty()) {
//                        current = processQueue.get(0);
//                    }
//                }
//
//                current.increaseCPUTime(1);
//                current.decreaseBurstTime(1);
//            }
//
//            timeElapsed++;
        }

        analyzePerformance(processList, timeElapsed - 1, timeElapsed - 1);
    }
}
