import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public abstract class Scheduler {
    private ArrayList<Integer> timeQuantum;
    private DecimalFormat df;

    public Scheduler() {
        df = new DecimalFormat("0.0###");
        df.setRoundingMode(RoundingMode.FLOOR);
    }

    public abstract void execute(Process[] processList);

    public void analyzePerformance(Process[] processList, int timeElapsed, int totalBurstTime) {
        float totalWaitingTime = 0;
        float totalTurnaroundTime = 0;
        float totalResponseTime = 0;

        System.out.println("Total time elapsed: " + timeElapsed + "ns");
        System.out.println("Total CPU burst time: " + totalBurstTime + "ns");
        System.out.println("CPU Utilization: " + (int)Math.floor(((float) totalBurstTime / timeElapsed) * 100) + "%");
        System.out.println("Throughput: " + (df.format((float) processList.length / timeElapsed)) + " processes/ns");

        System.out.println("Waiting times:");
        for(int i = 0; i < processList.length; i++) {
            System.out.println(" Process " + processList[i].getIndex() + ": " + processList[i].getWaitingTime() + "ns");
            totalWaitingTime += processList[i].getWaitingTime();
        }
        System.out.println("Average waiting time: " + df.format(totalWaitingTime / processList.length) + "ns");

        System.out.println("Turnaround times:");
        for(int i = 0; i < processList.length; i++) {
            System.out.println(" Process " + processList[i].getIndex() + ": " + processList[i].getTurnaroundTime() + "ns");
            totalTurnaroundTime += processList[i].getTurnaroundTime();
        }
        System.out.println("Average turnaround time: " + df.format(totalTurnaroundTime / processList.length) + "ns");

        System.out.println("Response times:");
        for(int i = 0; i < processList.length; i++) {
            System.out.println(" Process " + processList[i].getIndex() + ": " + processList[i].getResponseTime() + "ns");
            totalResponseTime += processList[i].getResponseTime();
        }
        System.out.println("Average response time: " + df.format(totalResponseTime / processList.length) + "ns");
    }

    public static Process[] bubbleSort(Process[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if(arr[j].getArrivalTime() > arr[j + 1].getArrivalTime()) {
                    Process temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }
}
