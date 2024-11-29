class Process{
    String name;
    int burstTime;// the time which the process need to complete its execution
    int priority;

    int arrivalTime;
    int turnAroundTime;
    int waitingTime;
    int quantum;
    
    public Process(String name, int burstTime,int priority){
        this.name=name;
        this.burstTime=burstTime;

        this.turnAroundTime=0;
        this.waitingTime=0;
        this.priority=priority;
    
    }


}

