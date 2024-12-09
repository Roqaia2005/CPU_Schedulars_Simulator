import java.util.*;
import static java.lang.Math.ceil;

public class FCAI_Scheduler {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of processes:");
        int numProcesses = sc.nextInt();

        System.out.println("Enter Context Switching Time:");
        int contextSwitching = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < numProcesses; i++) {
            System.out.println("Enter process name, burst time, arrival time, priority and Quantum:");
            String processName = sc.next();
            int burstTime = sc.nextInt(), arrivalTime = sc.nextInt(), priority = sc.nextInt(), quantum = sc.nextInt();

            processes.add(new Process(processName, arrivalTime, burstTime, priority, quantum));
        }

        fcaiAlgorithm(processes, contextSwitching);
        sc.close();
    }

    public static void fcaiAlgorithm(List<Process> processes, int contextSwitching) {
        int currTime = 0;
        int contextSwitchingValue = contextSwitching;
        double v1 = processes.stream().mapToInt(p -> p.getArrivalTime()).max().orElse(1) / 10.0;
        double v2 = processes.stream().mapToInt(p -> p.getBurstTime()).max().orElse(1) / 10.0;
        System.out.println("v1 = " + v1 + ", v2 = " + v2);
        processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));
        for(Process p : processes){
            p.fcaiFactor = calcFCAIFactor(p, v1, v2);
        }
        List<Process> completedProcesses = new ArrayList<>();
        int sz = processes.size();
        List<Process> queue = new LinkedList<>();
        findProcessAtTime(processes, currTime, queue);
        System.out.printf("%-15s %-15s %-15s %-20s %-20s %-15s %-15s%n",
                "Time", "Process", "Executed Time", "Remaining Burst Time", "Updated Quantum", "Priority", "FCAI Factor");
        while(completedProcesses.size() != sz){
            contextSwitching = contextSwitchingValue;
            if(!queue.isEmpty()){
                Process curr = queue.getFirst();
                boolean flag = false;
                int startTime = currTime;
                int quantum = curr.getQuantum();
                int threShold =(int) ceil(0.4 * quantum);
                while(curr.remainingTime > 0 && quantum > 0){
                    findProcessAtTime(processes, currTime, queue);
                    if(threShold <= 0){
                        Process betterProcess = findBestProcess(queue, currTime);
                        if(betterProcess != null && betterProcess.fcaiFactor < curr.fcaiFactor){
                            deleteProcess(queue, curr);
                            curr.quantum += quantum;
                            curr.fcaiFactor = calcFCAIFactor(curr, v1, v2);
                            curr.quantumHistory.add(curr.quantum);
                            queue.add(curr);
                            deleteProcess(queue, betterProcess);
                            queue.addFirst(betterProcess);
                            while (contextSwitching-- > 0) {
                                currTime++;
                                findProcessAtTime(processes, currTime, queue);
                            }
                            flag = true;
                            break;
                        }
                    }
                    curr.remainingTime--;
                    quantum--;
                    currTime++;
                    threShold--;
                }
                if (curr.remainingTime == 0) {
                    curr.turnaroundTime = currTime - curr.getArrivalTime();
                    curr.waitingTime = curr.turnaroundTime - curr.getBurstTime();
                    completedProcesses.add(curr);
                    deleteProcess(queue, curr);
                    deleteProcess(processes, curr);

                } else if (!flag && curr.remainingTime > 0) {
                    curr.quantum += 2;
                    curr.fcaiFactor = calcFCAIFactor(curr, v1, v2);
                    curr.quantumHistory.add(curr.quantum);
                    deleteProcess(queue, curr);
                    queue.add(curr);

                }
                while (contextSwitching-- > 0) {
                    currTime++;
                    findProcessAtTime(processes, currTime, queue);
                }

                int endTime = currTime;
                int executedTime = endTime - startTime;
                printIteration(startTime, endTime, curr, executedTime);
            }
            else{
                currTime++;
                findProcessAtTime(processes, currTime, queue);
            }
        }
        display(completedProcesses);
    }

    private static Process findBestProcess(List<Process> queue, int currentTime) {
        return queue.stream()
                .filter(p -> p.getArrivalTime() <= currentTime)
                .min(Comparator.comparingDouble(p -> p.fcaiFactor))
                .orElse(null);
    }

    private static void deleteProcess(List<Process> queue, Process p){
        for(int i = 0; i < queue.size(); i++){
            if(queue.get(i).getProcessName().equals(p.getProcessName())){
                queue.remove(i);
                break;
            }
        }
    }


    private static int calcFCAIFactor(Process p, double v1, double v2) {
        double fcai = (10 - p.getPriority()) + ceil(p.getPriority() / v1) + ceil(p.remainingTime / v2);
        return (int) Math.ceil(fcai);
    }


    private static void display(List<Process> processes) {
        System.out.println("\nExecution Results:");
        double totalWaitingTime = 0;
        double totalTurnaroundTime = 0;

        for (Process p : processes) {
            System.out.printf("Process %s: Waiting Time = %d, Turnaround Time = %d%n",
                    p.getProcessName(), p.waitingTime, p.turnaroundTime);
            totalWaitingTime += p.waitingTime;
            totalTurnaroundTime += p.turnaroundTime;
            System.out.println("Quantum History for " + p.getProcessName() + ": " + p.quantumHistory);
        }

        System.out.printf("Average Waiting Time: %.2f%n", totalWaitingTime / processes.size());
        System.out.printf("Average Turnaround Time: %.2f%n", totalTurnaroundTime / processes.size());
    }

    private static void printIteration(int startTime, int endTime, Process curr, int executedTime) {
        System.out.printf("%-16s %-15s %-15s %-20s %-20s %-15s %-14s%n",
                startTime + "->" + endTime, curr.getProcessName(), executedTime,
                curr.remainingTime, curr.quantum, curr.getPriority(), curr.fcaiFactor);
    }

    private static void findProcessAtTime(List<Process> processes, int currTime, List<Process> queue) {
        for (Process p : processes) {
            if (p.getArrivalTime() <= currTime && !queue.contains(p)) {
                for(Process q : queue){
                    if(q.getProcessName().equals(p.getProcessName())){
                        return;
                    }
                }
                queue.add(p);
            }
        }
    }
}