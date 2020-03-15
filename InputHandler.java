import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

public class InputHandler {
    private int testCases, numOfProcesses;
    private ArrayList<Integer> timeQuantum;
    private DecimalFormat df;

    // First [] indicates the test case while the second [] stores the processes of said test case.
    private Process testCaseList[][];

    private Scanner input;
    private String[] schedulerAlgo, stdInput;

    private Scheduler fcfs;
    private Scheduler sjf;
    private Scheduler srtf;


    public InputHandler() {
        timeQuantum = new ArrayList<>();
        df = new DecimalFormat("0.0###");
        df.setRoundingMode(RoundingMode.FLOOR);
        input = new Scanner(System.in);

        fcfs = new FCFS();
        sjf = new SJF();
        srtf = new SRTF();
    }

    public void executeScheduler() {
        System.out.println("-----INPUT-----");
        testCases = input.nextInt();
        input.nextLine();
        schedulerAlgo = new String[testCases];
        testCaseList = new Process[testCases][];

        for(int i = 0; i < testCases; i++) {
            stdInput = input.nextLine().split(" ");
            numOfProcesses = Integer.parseInt(stdInput[0]);
            schedulerAlgo[i] = stdInput[1];

            if(schedulerAlgo[i].equals("RR")) {
                timeQuantum.add(Integer.parseInt(stdInput[2]));
            }

            testCaseList[i] = new Process[numOfProcesses];
            for(int j = 0; j < numOfProcesses; j++) {
                int arrivalTime = input.nextInt(); 
                int burstTime = input.nextInt();
                int niceLevel = input.nextInt();
                input.nextLine();
                testCaseList[i][j] = new Process(j, arrivalTime, burstTime, niceLevel);
            }
        } 
        
        System.out.println("\n" + "-----OUTPUT-----");
        for(int i = 0; i < testCaseList.length; i++) {
            System.out.println((i + 1) + " " + schedulerAlgo[i]);

            if(schedulerAlgo[i].equals("FCFS")) {
                fcfs.execute(testCaseList[i]);
            }
            else if(schedulerAlgo[i].equals("SJF")) {
                sjf.execute(testCaseList[i]);
            }
            else if(schedulerAlgo[i].equals("SRTF")) {
                srtf.execute(testCaseList[i]);
            }
            else if(schedulerAlgo[i].equals("P")) {
                useP(testCaseList[i]);
            }
            else if(schedulerAlgo[i].equals("RR")) {
                int q = timeQuantum.get(0);
                timeQuantum.remove(0);
                useRR(testCaseList[i], q);
            }
        }
    }

    // Shortest Remaining Time First (preemptive) Algorithm 
    public void useSRTF(Process[] processList) {}

    // Priority (preemptive) Algorithm 
    public void useP(Process[] processList) {}

    // Round-Robin Algorithm 
    public void useRR(Process[] processList, int q) {}
}