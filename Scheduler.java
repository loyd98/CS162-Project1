import java.util.*;

public class Scheduler {
    private int testCases, numOfProcesses;
    private ArrayList<Integer> timeQuantum = new ArrayList<>();
    private Process processList[][];
    private Scanner input = new Scanner(System.in);
    private String[] schedulerAlgo, stdInput;

    public Scheduler() {}

    // Scheduler Algorithms 
    public void useFCFS(Process[] processList) {}

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
            bubbleSort(processList[i]);
        } 
        
        System.out.println("\n" + "-----OUTPUT-----");
        for(int i = 0; i < processList.length; i++) {
            System.out.println((i + 1) + " " + schedulerAlgo[i]);
            for(int j = 0; j < processList[i].length; j++) {

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
}