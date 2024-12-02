class Process{
    String name;
    int burstTime;// the time which the process need to complete its execution
    int priority;

    int arrivalTime;
    int turnAroundTime;
    int waitingTime;
    int quantum;
    int remainingBurstTime;
    int FCAI_factor;
    
    public Process(String name, int burstTime,int priority){
        this.name=name;
        this.burstTime=burstTime;
        this.priority=priority;
    
    }
// this consturctor for FCAI algorithm
    public Process(String name, int burstTime,int arrivalTime,int priority,int quantum){
        this.name=name;
        this.burstTime=burstTime;
        this.arrivalTime=arrivalTime;
        this.priority=priority;
        this.quantum=quantum;
    
    }

    // public void calculateFCAIFactor(double V1, double V2) {
    //     this.FCAI_factor = (10 - this.priority) +(this.arrivalTime / V1) +(this.remainingBurstTime / V2);
    // }


}

