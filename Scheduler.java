import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class Scheduler {
    private int testCases, numOfProcesses;
    private ArrayList<Integer> timeQuantum;
    private DecimalFormat df;
    private Process processList[][];
    private Scanner input;
    private String[] schedulerAlgo, stdInput;


    public Scheduler() {
        timeQuantum = new ArrayList<>();
        df = new DecimalFormat("0.0###");
        df.setRoundingMode(RoundingMode.FLOOR);
        input = new Scanner(System.in);
    }

    // Scheduler Algorithms 
    public void useFCFS(Process[] processList) {
        int timeElapsed = 0;
        int totalBurstTime = 0;
        float totalWaitingTime = 0;
        float totalTurnaroundTime = 0;
        float totalResponseTime = 0;
        Process[] arrivalTimeList = processList.clone();
        bubbleSort(arrivalTimeList);
        for(int i = 0; i < arrivalTimeList.length; i++) {
            if(timeElapsed < arrivalTimeList[i].getArrivalTime()) {
                timeElapsed = arrivalTimeList[i].getArrivalTime();
            }
            else if(timeElapsed > arrivalTimeList[i].getArrivalTime()) {
                arrivalTimeList[i].updateWaitingTime(timeElapsed - arrivalTimeList[i].getArrivalTime());
            }
            arrivalTimeList[i].updateTurnaroundTime(arrivalTimeList[i].getWaitingTime() + arrivalTimeList[i].getBurstTime());
            arrivalTimeList[i].updateResponseTime(timeElapsed - arrivalTimeList[i].getArrivalTime());
            System.out.println(timeElapsed + " " + arrivalTimeList[i].getIndex() + " " + arrivalTimeList[i].getBurstTime() + "X");
            timeElapsed += arrivalTimeList[i].getBurstTime();
            totalBurstTime += arrivalTimeList[i].getBurstTime();
        }

        System.out.println("Total time elapsed: " + timeElapsed + "ns");
        System.out.println("Total CPU burst time: " + totalBurstTime + "ns");
        System.out.println("CPU Utilization: " + (int)Math.floor(((float) totalBurstTime / timeElapsed) * 100) + "%");
        System.out.println("Throughput: " + (df.format((float)processList.length / timeElapsed)) + " processes/ns");

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

    public void useSJF(Process[] processList) {}

    public void useSRTF(Process[] processList) {}

    public void useP(Process[] processList) {}

    public void useRR(Process[] processList, int q) {}

    // Arranges the list of processes by their arrival time
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

    public void executeScheduler() {
        System.out.println("-----INPUT-----");
        testCases = input.nextInt();
        input.nextLine();
        schedulerAlgo = new String[testCases];
        processList = new Process[testCases][];

        for(int i = 0; i < testCases; i++) {
            stdInput = input.nextLine().split(" ");
            numOfProcesses = Integer.parseInt(stdInput[0]);
            schedulerAlgo[i] = stdInput[1];

            if(schedulerAlgo[i].equals("RR")) {
                timeQuantum.add(Integer.parseInt(stdInput[2]));
            }

            processList[i] = new Process[numOfProcesses];
            for(int j = 0; j < numOfProcesses; j++) {
                int arrivalTime = input.nextInt(); 
                int burstTime = input.nextInt();
                int niceLevel = input.nextInt();
                processList[i][j] = new Process(j, arrivalTime, burstTime, niceLevel);
            }
        } 
        
        System.out.println("\n" + "-----OUTPUT-----");
        for(int i = 0; i < processList.length; i++) {
            System.out.println((i + 1) + " " + schedulerAlgo[i]);

            if(schedulerAlgo[i].equals("FCFS")) {
                useFCFS(processList[i]);
            }
            else if(schedulerAlgo[i].equals("SJF")) {
                useSJF(processList[i]);
            }
            else if(schedulerAlgo[i].equals("SRTF")) {
                useSRTF(processList[i]);
            }
            else if(schedulerAlgo[i].equals("P")) {
                useP(processList[i]);
            }
            else if(schedulerAlgo[i].equals("RR")) {
                int q = timeQuantum.get(0);
                timeQuantum.remove(0);
                useRR(processList[i], q);
            }
        }
    }
}