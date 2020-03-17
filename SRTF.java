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
        int timeEntered = 0;

        Process[] arrivalTimeList = processList.clone();
        ArrayList<Process> processQueue = new ArrayList<>(Arrays.asList(bubbleSort(arrivalTimeList)));

        // added arbitrary number for control
        while (timeElapsed != 30) {
            Process current = null;
            int minBurstTime = Integer.MAX_VALUE;

            for (Process p: processQueue) {
                // If process is not done yet
                if (p.getBurstTime() != -1) {
                    if (p.getArrivalTime() == timeElapsed && p.getBurstTime() < minBurstTime) {
                        //switch
                        timeEntered = p.getArrivalTime();
                        current = p;
                        minBurstTime = p.getBurstTime();

                        //must print this out when switching only
                        System.out.println(timeEntered + " P" + current.getIndex() + " 1" + current.getRemainingTime());
                    }
                }
            }


            if (current != null) {
//                System.out.println(timeElapsed);
                timeElapsed++;
                current.decreaseBurstTime(1);
            }
        }

//        for (Process p: processQueue) {
//            System.out.println(p.getArrivalTime() + " P" + p.getIndex() + " " + (p.getBurstTime()));
//        }


//        if newly arrived process has shorter burst time then switch
//        else continue current process
//        System.out.println(processQueue.get(0).getArrivalTime() + " " + processQueue.get(0).getIndex() + " " + processQueue.get(1).getArrivalTime());
//
//        System.out.println(processQueue.get(1).getIndex());

    }
}
