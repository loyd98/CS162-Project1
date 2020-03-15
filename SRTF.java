import java.lang.reflect.Array;
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
        ArrayList<Process> out = new ArrayList<>();


        System.out.println(processQueue.get(0).getArrivalTime() + " " + processQueue.get(0).getIndex() + " " + processQueue.get(1).getArrivalTime());
        
        System.out.println(processQueue.get(1).getIndex());
    }
}
