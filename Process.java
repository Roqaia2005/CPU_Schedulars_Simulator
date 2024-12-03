package srtf;

public class Process {
    String name;
    String color;
    int id;
    int arrivalTime;
    int burstTime;
    int remainingTime;
    int priority;
    int waitingTime;
    int turnAroundTime;
    int lastExecuteTime;
     // Constructor with default priority value
     public Process(String name, String color,int id, int arrivalTime, int burstTime) {
        this.name = name;
        this.color = color;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = Integer.MAX_VALUE;
        this.lastExecuteTime = arrivalTime;
    }
    //constructor
    public Process(String name, String color,int id, int arrivalTime, int burstTime, int priority){
        this.name = name;
        this.color = color;
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
        this.lastExecuteTime = arrivalTime;
    }

   
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }
    public boolean isCompleted(){
        return remainingTime <= 0;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getWaitingTime(){
        return waitingTime;
    }
    public void setWaitingTime(int time){
        waitingTime = time;
    }
    public int getTurnAroundTime(){
        return turnAroundTime;
    }
    public void setTurnAroundTime(int time){
        turnAroundTime = time;
    }
    public int getLastExecuteTime(){
        return lastExecuteTime;
    }
    public void setLastExecuteTime(int time){
        lastExecuteTime = time;
    }
    
    public void execute() {
        if (!isCompleted()) {
            remainingTime--; 
        }
    }
}

