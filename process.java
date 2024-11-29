class process{
    String name;
    int burstTime;// the time which the process need to complete its execution
    int arrivalTime;
    int turnAroundTime;
    int waitingTime;
    int priority;
    int quantum;
    
    public process(String name, int burstTime,int arrivalTime,int turnAroundTime,int waitingTime,int priority,int quantum){
        this.name=name;
        this.burstTime=burstTime;
        this.arrivalTime=arrivalTime;
        this.turnAroundTime=turnAroundTime;
        this.waitingTime=waitingTime;
        this.priority=priority;
        this.quantum=quantum;
    }


}

