import java.util.*;

public class Scheduler {
    private int testCases, numOfProcesses;
    private ArrayList<Integer> timeQuantum = new ArrayList<>();
    private Process processList[][];
    private Scanner input = new Scanner(System.in);
    private String[] schedulerAlgo, stdInput;

    public Scheduler() {}

    // Scheduler Algorithms 
    public void useFCFS() {}

    public void useSJF() {}

    public void useSRTF() {}

    public void useP() {}

    public void useRR() {}

    public void executeScheduler() {
        testCases = input.nextInt();
        schedulerAlgo = new String[testCases];
        processList = new Process[testCases][];

        // Input
        for(int i = 0; i < testCases; i++) {
            stdInput = input.nextLine().split(" ");
            numOfProcesses = Integer.parseInt(stdInput[0]);
            schedulerAlgo[i] = stdInput[1];

            if(schedulerAlgo[i].equals("RR")) {
                timeQuantum.add(input.nextInt());
            }

            processList[i] = new Process[numOfProcesses];
            for(int j = 0; j < numOfProcesses; j++) {
                int arrivalTime = input.nextInt(); 
                int burstTime = input.nextInt();
                int niceLevel = input.nextInt();
                processList[i][j] = new Process(j, arrivalTime, burstTime, niceLevel);
            }
        } 

        // Output
        for(int i = 0; i < processList.length; i++) {
            for(int j = 0; j < processList[i].length; j++) {
                System.out.println((i + 1) + " " + schedulerAlgo[i]);

                if(schedulerAlgo[i].equals("FCFS")) {
                    useFCFS();
                }
                else if(schedulerAlgo[i].equals("SJF")) {
                    useSJF();
                }
                else if(schedulerAlgo[i].equals("SRTF")) {
                    useSRTF();
                }
                else if(schedulerAlgo[i].equals("P")) {
                    useP();
                }
                else if(schedulerAlgo[i].equals("RR")) {
                    int q = timeQuantum.get(0);
                    timeQuantum.remove(0);
                    useRR();
                }
            }
        }
    }
}