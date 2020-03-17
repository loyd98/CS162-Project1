import java.util.ArrayList;
import java.util.Arrays;

public class Priority extends Scheduler {

    public Priority() {
        super();
    }

    // Modified to compare nice levels if two processes arrive at the same time.
    public static Process[] modifiedBubbleSort(Process[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j].getArrivalTime() == arr[j + 1].getArrivalTime()) {
                    if (arr[j].getNiceLevel() > arr[j + 1].getNiceLevel()) {
                        Process temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
                else if(arr[j].getArrivalTime() > arr[j + 1].getArrivalTime()) {
                    Process temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    @Override
    public void execute(Process[] processList) {
        int timeElapsed = 0;
        int totalBurstTime = 0;

        Process[] arrivalTimeList = processList.clone();
        ArrayList<Process> processQueue = new ArrayList<>(Arrays.asList(modifiedBubbleSort(arrivalTimeList)));
        ArrayList<Process> readyQueue = new ArrayList<>();

        while (true) {
            if (processQueue.isEmpty() && readyQueue.isEmpty()) {
                break;
            }
            else {
                if (!processQueue.isEmpty()) {
                    if (timeElapsed < processQueue.get(0).getArrivalTime()) {
                        timeElapsed = processQueue.get(0).getArrivalTime();
                    }

                    // Check if any other process arrived already and put it in the ready queue
                    for (int i = 0; i < processQueue.size(); i++) {
                        if (timeElapsed >= processQueue.get(i).getArrivalTime()) {
                            readyQueue.add(processQueue.get(i));
                            processQueue.remove(i);
                        }
                        else {
                            break;
                        }
                    }
                }

                // Sort ready queue by nice level
                for (int i = 0; i < readyQueue.size(); i++) {
                    for(int j = 0; j < readyQueue.size() - 1 - i; j++) {
                        if (readyQueue.get(j).getNiceLevel() > readyQueue.get(j + 1).getNiceLevel()) {
                            Process temp = readyQueue.get(j);
                            readyQueue.set(j, readyQueue.get(j + 1));
                            readyQueue.set(j + 1, temp);
                        }
                    }
                }
            }

            if (!processQueue.isEmpty()) {
                // If the current process will run, check if another process will arrive during the burst time execution
                if (timeElapsed + readyQueue.get(0).getBurstTime() > processQueue.get(0).getArrivalTime()) {
                    // Check if any other process arrived and check if they have a higher priority than the 
                    // current process that is supposed to run.
                    for (int i = 0; i < processQueue.size(); i++) {
                        if (timeElapsed + readyQueue.get(0).getBurstTime() > processQueue.get(i).getArrivalTime()) {
                            // If the newly arrived process has higher priority, place it in the front of the process queue
                            if (readyQueue.get(0).getNiceLevel() > processQueue.get(i).getNiceLevel()) {
                                processQueue.set(0, processQueue.get(i));
                            }
                        }
                        else {
                            break;
                        }
                    }

                    // If the current process has priority, run normally.
                    if (readyQueue.get(0).getNiceLevel() <= processQueue.get(0).getNiceLevel()) {
                        System.out.println(timeElapsed + " " + readyQueue.get(0).getIndex() + " " + readyQueue.get(0).getBurstTime() + "X");

                        readyQueue.get(0).updateWaitingTime(timeElapsed - readyQueue.get(0).getArrivalTime());
                        readyQueue.get(0).updateTurnaroundTime(processQueue.get(0).getWaitingTime() + readyQueue.get(0).getBurstTime());
                        readyQueue.get(0).updateResponseTime(timeElapsed - readyQueue.get(0).getArrivalTime());

                        totalBurstTime += readyQueue.get(0).getBurstTime();
                        timeElapsed += readyQueue.get(0).getBurstTime();
                        readyQueue.remove(0);
                    }
                    // Else, update the current processes parameters and prepare for it to be interrupted.
                    // The current time will be updated to the arrival time of the prioritized process.
                    else {
                        System.out.println(timeElapsed + " " + readyQueue.get(0).getIndex() + " " + (processQueue.get(0).getArrivalTime() - timeElapsed));

                        readyQueue.get(0).updateArrivalTime(processQueue.get(0).getArrivalTime() - timeElapsed);
                        readyQueue.get(0).updateBurstTime((readyQueue.get(0).getBurstTime() + timeElapsed) - processQueue.get(0).getArrivalTime());
                        totalBurstTime += processQueue.get(0).getArrivalTime() - timeElapsed;
                        timeElapsed = processQueue.get(0).getArrivalTime();
                    }
                }
            }
            // Since there are no more processes to arrive, all the processes in the ready queue are arranged by priority.
            // Run normally.
            else {
                System.out.println(timeElapsed + " " + readyQueue.get(0).getIndex() + " " + readyQueue.get(0).getBurstTime() + "X");

                readyQueue.get(0).updateWaitingTime(timeElapsed - readyQueue.get(0).getArrivalTime());
                readyQueue.get(0).updateTurnaroundTime(readyQueue.get(0).getWaitingTime() + readyQueue.get(0).getBurstTime());
                readyQueue.get(0).updateResponseTime(timeElapsed - readyQueue.get(0).getArrivalTime());

                totalBurstTime += readyQueue.get(0).getBurstTime();
                timeElapsed += readyQueue.get(0).getBurstTime();
                readyQueue.remove(0);
            }
        }
        // Priority Data Analysis
        analyzePerformance(processList, timeElapsed, totalBurstTime);
    }
}
