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
        ArrayList<Process> readyQueue = new ArrayList<>();
        
        while (true) {   
            if(timeElapsed < processQueue.get(0).getArrivalTime()) {
                timeElapsed = processQueue.get(0).getArrivalTime();
            }

            for (int i = 0; i < processQueue.size(); i++) {
                if (timeElapsed >= processQueue.get(i).getArrivalTime()) {
                    readyQueue.add(processQueue.get(i));
                    processQueue.remove(i);
                }
                else {
                    break;
                }
            }

            for (int i = 0; i < readyQueue.size(); i++) {
                for(int j = 0; j < readyQueue.size() - 1 - i; j++) {
                    if (readyQueue.get(j).getBurstTime() > readyQueue.get(j + 1).getBurstTime()) {
                        Process temp = readyQueue.get(j);
                        readyQueue.set(j, readyQueue.get(j + 1));
                        readyQueue.set(j + 1, temp);
                    }
                }
            }
            readyQueue.get(0).updateArrivalTime(timeElapsed);
    }
    analyzePerformance(processList, timeElapsed, totalBurstTime);
}

/*
outerLoop:
            while (true) {
                if (!processQueue.isEmpty() && timeElapsed < processQueue.get(0).getArrivalTime()) {
                    for (int i = 0; i < processQueue.size(); i++) {
                        if (timeElapsed >= processQueue.get(i).getArrivalTime()) {
                            if (readyQueue.get(0).getBurstTime() >= processQueue.get(i).getBurstTime()) {
                                readyQueue.add(processQueue.get(i));
                                processQueue.remove(i);

                                readyQueue.get(0).updateWaitingTime(readyQueue.get(0).getWaitingTime() - timeElapsed);
                                readyQueue.get(0).updateBurstTime(readyQueue.get(0).getBurstTime() - timeElapsed);
                                System.out.println(readyQueue.get(0).getArrivalTime() + " " + readyQueue.get(0).getIndex() + " " + timeElapsed);
                                break outerLoop;
                            }
                            else {
                                readyQueue.get(0).updateBurstTime(readyQueue.get(0).getBurstTime() - 1);
                                totalBurstTime++;
                                if (readyQueue.get(0).getBurstTime() == 0) {
                                    readyQueue.get(0).updateWaitingTime(timeElapsed - readyQueue.get(0).getWaitingTime());
                                    readyQueue.get(0).updateTurnaroundTime(readyQueue.get(0).getWaitingTime() + readyQueue.get(0).getBurstTime());
                                    readyQueue.get(0).updateResponseTime(timeElapsed - readyQueue.get(0).getArrivalTime());
                                    System.out.println(readyQueue.get(0).getArrivalTime() + " " + readyQueue.get(0).getIndex() + " " + timeElapsed + "X");
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
                else {
                    readyQueue.get(0).updateBurstTime(readyQueue.get(0).getBurstTime() - 1);
                    totalBurstTime++;
                    if (readyQueue.get(0).getBurstTime() == 0) {
                        readyQueue.get(0).updateWaitingTime(timeElapsed - readyQueue.get(0).getWaitingTime());
                        readyQueue.get(0).updateTurnaroundTime(readyQueue.get(0).getWaitingTime() + readyQueue.get(0).getBurstTime());
                        readyQueue.get(0).updateResponseTime(timeElapsed - readyQueue.get(0).getArrivalTime());
                        System.out.println(readyQueue.get(0).getArrivalTime() + " " + readyQueue.get(0).getIndex() + " " + timeElapsed + "X");
                        break;
                    }
                }
                timeElapsed++;
            }
        }

        while (!readyQueue.isEmpty()) {
            for (int i = 0; i < readyQueue.size(); i++) {
                for(int j = 0; j < readyQueue.size() - 1 - i; j++) {
                    if (readyQueue.get(j).getBurstTime() > readyQueue.get(j + 1).getBurstTime()) {
                        Process temp = readyQueue.get(j);
                        readyQueue.set(j, readyQueue.get(j + 1));
                        readyQueue.set(j + 1, temp);
                    }
                }
            }

            readyQueue.get(0).updateArrivalTime(timeElapsed);

            outerLoop:
            while (true) {
                if (!processQueue.isEmpty() && timeElapsed < processQueue.get(0).getArrivalTime()) {
                    for (int i = 0; i < processQueue.size(); i++) {
                        if (timeElapsed >= processQueue.get(i).getArrivalTime()) {
                            if (readyQueue.get(0).getBurstTime() >= processQueue.get(i).getBurstTime()) {
                                readyQueue.add(processQueue.get(i));
                                processQueue.remove(i);

                                readyQueue.get(0).updateWaitingTime(readyQueue.get(0).getWaitingTime() - timeElapsed);
                                readyQueue.get(0).updateBurstTime(readyQueue.get(0).getBurstTime() - timeElapsed);
                                System.out.println(readyQueue.get(0).getArrivalTime() + " " + readyQueue.get(0).getIndex() + " " + timeElapsed);
                                break outerLoop;
                            }
                            else {
                                readyQueue.get(0).updateBurstTime(readyQueue.get(0).getBurstTime() - 1);
                                totalBurstTime++;
                                if (readyQueue.get(0).getBurstTime() == 0) {
                                    readyQueue.get(0).updateWaitingTime(timeElapsed - readyQueue.get(0).getWaitingTime());
                                    readyQueue.get(0).updateTurnaroundTime(readyQueue.get(0).getWaitingTime() + readyQueue.get(0).getBurstTime());
                                    readyQueue.get(0).updateResponseTime(timeElapsed - readyQueue.get(0).getArrivalTime());
                                    System.out.println(readyQueue.get(0).getArrivalTime() + " " + readyQueue.get(0).getIndex() + " " + timeElapsed + "X");
                                    break outerLoop;
                                }
                            }
                        }
                    }
                }
                else {
                    readyQueue.get(0).updateBurstTime(readyQueue.get(0).getBurstTime() - 1);
                    totalBurstTime++;
                    if (readyQueue.get(0).getBurstTime() == 0) {
                        readyQueue.get(0).updateWaitingTime(timeElapsed - readyQueue.get(0).getWaitingTime());
                        readyQueue.get(0).updateTurnaroundTime(readyQueue.get(0).getWaitingTime() + readyQueue.get(0).getBurstTime());
                        readyQueue.get(0).updateResponseTime(timeElapsed - readyQueue.get(0).getArrivalTime());
                        System.out.println(readyQueue.get(0).getArrivalTime() + " " + readyQueue.get(0).getIndex() + " " + timeElapsed + "X");
                        break outerLoop;
                    }
                }
                timeElapsed++;
            }
        }

*/
        // 4 SRTF
        // 0 50 2 p1
        // 40 2 3 p2
        // 20 3 1 p3
        // 30 55 1 p4
    

        // 1 SRTF
        // 0 1 20
        // 20 3 3X
        // 23 1 17
        // 40 2 2X
        // 42 1 13X
        // 55 4 55X







//             int minBurstTime = Integer.MAX_VALUE;

//             for (Process p: processQueue) {
//                 // If process is not done yet
//                 if (p.getBurstTime() != -1) {
//                     if (p.getArrivalTime() <= timeElapsed && p.getBurstTime() < minBurstTime) {
//                         //switch

//                         timeEntered = p.getArrivalTime();
//                         current = p;
//                         minBurstTime = p.getBurstTime();

//                         System.out.println(timeEntered + " P" + p.getIndex() + " " + p.getBurstTime() + "........" + timeElapsed);
//                         //must print this out when switching only

//                     }
//                 }
//             }

//             timeElapsed++;

//             if (current != null) {
// //                System.out.println(timeElapsed);
//                 current.decreaseBurstTime(1);
//             }
//}

//        for (Process p: processQueue) {
//            System.out.println(p.getArrivalTime() + " P" + p.getIndex() + " " + (p.getBurstTime()));
//        }


//        if newly arrived process has shorter burst time then switch
//        else continue current process
//        System.out.println(processQueue.get(0).getArrivalTime() + " " + processQueue.get(0).getIndex() + " " + processQueue.get(1).getArrivalTime());
//
//        System.out.println(processQueue.get(1).getIndex());
