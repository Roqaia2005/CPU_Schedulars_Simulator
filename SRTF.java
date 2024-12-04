import java.util.*;

public class SRTF {
    PriorityQueue<Process> arrivalQueue;
    PriorityQueue<Process> readyQueue;
    int totalWaitingTime;
    int totalTurnAroundTime;
    int numOfProcesses;
    StringBuilder executionOrder;
    Queue<Process> completed;
    public SRTF(PriorityQueue<Process> queue){ //take initial queue as input(sorted on arrival time)
        this.arrivalQueue =queue;
        readyQueue = new PriorityQueue<>(new RemaingTimeComparator());
        totalTurnAroundTime = 0;
        totalWaitingTime = 0;
        numOfProcesses = queue.size();
        executionOrder = new StringBuilder();
        completed = new LinkedList<>();
    }
    public void executeProcess(){
        int currentTime= 0;
        while(!arrivalQueue.isEmpty()||!readyQueue.isEmpty()){ // there are still tasks to do
            while(!arrivalQueue.isEmpty() && arrivalQueue.peek().getArrivalTime() <= currentTime){
                readyQueue.add(arrivalQueue.poll());
            }
            if(!readyQueue.isEmpty()){
                Process currentProcess = readyQueue.poll();
                executionOrder.append(currentProcess.getName() + " ->");
                currentProcess.execute();
                currentTime ++;
                if(!currentProcess.isCompleted()){
                    if(currentTime - currentProcess.getLastExecuteTime() > 5){
                        int currentPriority =currentProcess.getPriority();
                        currentProcess.setPriority(currentPriority - 1 );
                    }
                    currentProcess.setLastExecuteTime(currentTime);
                    readyQueue.add(currentProcess);
                }
                else{ // the process is done
                    int turnAroundTime = currentTime -  currentProcess.arrivalTime;
                    int waitingTime = turnAroundTime - currentProcess.getBurstTime();
                    currentProcess.setWaitingTime(waitingTime);
                    currentProcess.setTurnAroundTime(turnAroundTime);
                    totalWaitingTime += waitingTime;
                    totalTurnAroundTime += turnAroundTime;
                    completed.add(currentProcess);
                }
            }
            else{
                currentTime++;
            }

        }
    }
    public void print(){
        System.out.println(executionOrder);
        
        for( Process p1 : completed){
            System.out.println(p1.getName() + ":: waited: " + p1.getWaitingTime() +" turn around time: "+ p1.getTurnAroundTime());
        }
        System.out.println("Average waiting time : " + totalWaitingTime/ numOfProcesses);
        System.out.println("Average TurnAround Time: " + totalTurnAroundTime / numOfProcesses);

    }

}
