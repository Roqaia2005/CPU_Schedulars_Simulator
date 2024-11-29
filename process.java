class Process{
    String name;
    double burstTime;
    double arrivalTime;
    int priority;
    int quantum;
    public Process(String name, double burstTime,double arrivalTime,int priority,int quantum){
        this.name=name;
        this.burstTime=burstTime;
        this.arrivalTime=arrivalTime;
        this.priority=priority;
        this.quantum=quantum;
    }
}