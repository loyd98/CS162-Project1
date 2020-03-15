import java.util.ArrayList;
import java.util.Arrays;

public class SJF extends Scheduler {

    @Override
    public void execute(Process[] processList) {
        int timeElapsed = 0;
        int totalBurstTime = 0;

        /*
        Clones the processList array so that it is arranged by arrival time.
        Copies the arranged array into an array list.
        This array list will handle which process will run first.
        It is a shallow copy of processList.
        */
        Process[] arrivalTimeList = processList.clone();
        ArrayList<Process> processQueue = new ArrayList<>(Arrays.asList(bubbleSort(arrivalTimeList)));

        while(!processQueue.isEmpty()) {
            if(timeElapsed < processQueue.get(0).getArrivalTime()) {
                timeElapsed = processQueue.get(0).getArrivalTime();
            }

            if(timeElapsed >= processQueue.get(0).getArrivalTime()) {

                // Modified bubble sort algorithm
                for(int i = 0; i < processQueue.size(); i++) {
                    for(int j = 0; j < processQueue.size() - 1 - i; j++) {
                        // Checks if the current time encompasses currently selected process
                        // If true then compare burst times.
                        if (timeElapsed >= processQueue.get(j + 1).getArrivalTime()) {
                            if(processQueue.get(j).getBurstTime() > processQueue.get(j + 1).getBurstTime()) {
                                Process temp = processQueue.get(j);
                                processQueue.set(j, processQueue.get(j + 1));
                                processQueue.set(j + 1, temp);
                            }
                        }
                    }
                }
            }

            // Updates the performance parameters of current process
            processQueue.get(0).updateWaitingTime(timeElapsed - processQueue.get(0).getArrivalTime());
            processQueue.get(0).updateTurnaroundTime(processQueue.get(0).getWaitingTime() + processQueue.get(0).getBurstTime());
            processQueue.get(0).updateResponseTime(timeElapsed - processQueue.get(0).getArrivalTime());

            // Output of SJF Algorithm per processor
            System.out.println(timeElapsed + " " + processQueue.get(0).getIndex() + " " + processQueue.get(0).getBurstTime() + "X");

            // Keeps track of the time elapsed and total burst time. To be used for analysis portion of data output.
            timeElapsed += processQueue.get(0).getBurstTime();
            totalBurstTime += processQueue.get(0).getBurstTime();
            processQueue.remove(0);
        }

        // SJF Data Analysis
        analyzePerformance(processList, timeElapsed, totalBurstTime);
    }
}
