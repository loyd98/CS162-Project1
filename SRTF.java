import java.util.ArrayList;
import java.util.Arrays;

public class SRTF extends Scheduler {
    public SRTF() {
        super();
    }

    @Override
    public void execute(Process[] processList) {
        int timeElapsed = 0;
        int totalBurstTime = 0;

        Process[] arrivalTimeList = processList.clone();
        ArrayList<Process> processQueue = new ArrayList<>(Arrays.asList(bubbleSort(arrivalTimeList)));
        Process current = null;
        boolean firstTime = true;
        int minBurstTime = Integer.MAX_VALUE;
        int endingTime = 0;

        while (!processQueue.isEmpty()) {

            for (Process p: processQueue) {
                if (p.getArrivalTime() == timeElapsed && p.getBurstTime() >= 0 && p.getBurstTime() < minBurstTime) {

                    if (!firstTime && !current.isCompleted()) {
                        System.out.println(endingTime + " " + current.getIndex() + " " + current.getRunningTime());
                        current.setRunningTime(0);
                    }

                    firstTime = false;

                    endingTime = timeElapsed;
                    current = p;
                    minBurstTime = p.getBurstTime();

                } else if (p.getBurstTime() == 0) {
                    System.out.println(endingTime + " " + p.getIndex() + " " + p.getRunningTime() + "X");
                    endingTime = timeElapsed;
                    current = p;
                }
            }

            if (current.getBurstTime() == 0 ) {
                processQueue.remove(current);
                if (!processQueue.isEmpty()) {
                    current = processQueue.get(0);
                }
            }

            current.increaseRunningTime(1);
            current.decreaseBurstTime(1);

            timeElapsed++;
        }

        analyzePerformance(processList, timeElapsed - 1, timeElapsed - 1);
    }
}
