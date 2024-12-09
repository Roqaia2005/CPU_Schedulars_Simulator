import java.util.*;

public class SRTF {
    PriorityQueue<Process> arrivalQueue;
    PriorityQueue<Process> readyQueue;
    int totalWaitingTime;
    int totalTurnAroundTime;
    int numOfProcesses;
    int contextSwitchTime;
    int previousProcessId;
    StringBuilder executionOrder;
    Queue<Process> completed;
    public SRTF(PriorityQueue<Process> queue, int contextSwitchTime){ //take initial queue as input(sorted on arrival time)
        this.arrivalQueue =queue;
        readyQueue = new PriorityQueue<>(new RemainingTimeComparator());
        totalTurnAroundTime = 0;
        totalWaitingTime = 0;
        numOfProcesses = queue.size();
        executionOrder = new StringBuilder();
        completed = new LinkedList<>();
        this.contextSwitchTime = contextSwitchTime;
        previousProcessId = -1;
    }
    public SRTF(PriorityQueue<Process> queue){ //override if no contextswitch time included then set it to 1
        this.arrivalQueue =queue;
        readyQueue = new PriorityQueue<>(new RemainingTimeComparator());
        totalTurnAroundTime = 0;
        totalWaitingTime = 0;
        numOfProcesses = queue.size();
        executionOrder = new StringBuilder();
        completed = new LinkedList<>();
        contextSwitchTime = 1;
        previousProcessId = -1;
    }

    public void executeProcess(){
        int currentTime= 0;
        while(!arrivalQueue.isEmpty()||!readyQueue.isEmpty()){ // there are still tasks to do
            while(!arrivalQueue.isEmpty() && arrivalQueue.peek().getArrivalTime() <= currentTime){
                readyQueue.add(arrivalQueue.poll());
            }
            if(!readyQueue.isEmpty()){
                Process currentProcess = readyQueue.poll();
                if( previousProcessId != currentProcess.getId() && previousProcessId != -1){
                    int newCurrentTime = currentTime + contextSwitchTime;
                    executionOrder.append(currentTime +"[ context Switch] "+ newCurrentTime +" ");
                    currentTime = newCurrentTime; 
                }
                if(previousProcessId != currentProcess.getId()){
                    executionOrder.append(currentProcess.getColorCode()+"["+ currentProcess.getName() + "]"+"\033[0m");
                }
                currentProcess.execute();
                ++currentTime;
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
                    if(readyQueue.isEmpty()){
                        executionOrder.append(currentTime);
                    }
                }

                previousProcessId = currentProcess.getId();
                
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
