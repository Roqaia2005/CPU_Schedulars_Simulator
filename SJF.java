import java.util.*;


public class SJF {
    Queue<Process> ProcessQueue;
    List<Process> ExecutedProcesses = new ArrayList<>();
    int totalWaitingTime;
    int totalTurnAroundTime;
    int numOfProcesses;
    public SJF(){
        ProcessQueue = new PriorityQueue<>(Comparator.comparingInt(p -> p.arrivalTime));
        totalTurnAroundTime = 0;
        totalWaitingTime = 0;
    }
    public void addProcess(Process p){
       ProcessQueue.add(p);
    }
    public void NonPreemptiveSJF(){
    int time = 0;
    
    while(!ProcessQueue.isEmpty()){
    // CHECK FOR ALL PROCESSES THAT CAME UP TO A CERTAIN TIME....
    // SUPPOSE A SHORTESTBURST PROCESS WITH A VALUE OF NULL
    List<Process>READYQUEUE = new ArrayList<>();
    for(Process p:ProcessQueue){
        if(p.arrivalTime <= time){
         // CHECK THE SHORTEST BURST PROCESS THAT CAME UP TO THAT TIME.
        READYQUEUE.add(p);
        }
    }

    if(READYQUEUE.isEmpty()){
        time++;
        continue;
    }
    /// Find that process with the shortest burst time.
    
    Process ShortestProcess = READYQUEUE.stream().min(Comparator.comparingInt(p->p.burstTime)) // trun it into array then find the min burst time
    .orElse(null);
     /// Execute the process...
      if(ShortestProcess != null){
       time+=ShortestProcess.getBurstTime();
       int completiontime = time;
       int turnaroundtime = completiontime-ShortestProcess.getArrivalTime();
       int waitingtime = turnaroundtime - ShortestProcess.getBurstTime();
       ShortestProcess.setTurnAroundTime(turnaroundtime);
       ShortestProcess.setWaitingTime(waitingtime);
        ExecutedProcesses.add(ShortestProcess);
        ProcessQueue.remove(ShortestProcess);
        totalWaitingTime += waitingtime;
        totalTurnAroundTime += turnaroundtime;
    

  

    }
}

    }

    public void print(){
        for( Process p1 : ExecutedProcesses){
            System.out.println(p1.getName() + " Executed.");
        }
        
        for( Process p1 : ExecutedProcesses){
            System.out.println(p1.getName() + ":: waited: " + p1.getWaitingTime() +" turn around time: "+ p1.getTurnAroundTime());
        }
        System.out.println("Average waiting time : " + totalWaitingTime/ ExecutedProcesses.size());
        System.out.println("Average Turnaround time: " + totalTurnAroundTime / ExecutedProcesses.size());

    }
}
