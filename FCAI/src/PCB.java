import java.util.ArrayList;
import java.util.List;

class Process {
    private String processName;
    private int arrivalTime;
    private int burstTime;
    private int priority;
     int quantum;
     int remainingTime;
     int fcaiFactor;
     int waitingTime, turnaroundTime;
    List<Integer> quantumHistory; // To store quantum updates

    public Process(String processName, int arrivalTime, int burstTime, int priority, int initialQuantum) {
        this.processName = processName;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.quantum = initialQuantum;
        this.remainingTime = burstTime;
        this.quantumHistory = new ArrayList<>();
        this.quantumHistory.add(initialQuantum); // Record the initial quantum
    }
    public String getProcessName() {
        return processName;
    }
    public void setProcessName(String processID) {
        this.processName = processID;
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
    public int getPriority() {
        return priority;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public int getQuantum() {
        return quantum;
    }
    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
}
