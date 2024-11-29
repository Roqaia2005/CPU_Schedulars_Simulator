import java.util.Arrays;

public class schedularsSimulator {
    void calculateWaitingTime(process processes[], int n, int waitingTime[])
{
    // waiting time for first process is 0
    waitingTime[0] = 0;

    
    for (int i = 1; i < n; i++)
    // p1 p2
    // waiting time for p2 = burst time for p1+waiting time for p1 (wait unitll p1 wait and executed)
        waitingTime[i] = processes[i - 1].burstTime + waitingTime[i - 1];
}
void calculateTurnAroundTime(process processes[], int n, int waitingTime[],int turnArroundTime[])
{
    //turn around time = total time from arrival to execution
    for (int i = 0; i < n; i++)
        turnArroundTime[i] = processes[i].burstTime + waitingTime[i];
}
void calculateAverageTimes(process processes [],int n){
    

    int[] waitingTime=new int[n];
    int[] turnAroundTime=new int[n];
    int totalWatingTime = 0;
    int totalTurnAroundtime = 0;

    
    calculateWaitingTime(processes, n, waitingTime);

    
    calculateTurnAroundTime(processes, n, waitingTime, turnAroundTime);

    for (int i = 0; i < n; i++) {
        totalWatingTime +=waitingTime[i];
        totalTurnAroundtime += turnAroundTime[i];
        System.out.println(processes[i].name+ "wating time " + waitingTime[i] + "turn around time " + turnAroundTime[i]);
}
System.out.println("Average waiting time:");
System.out.println(totalWatingTime/n);

System.out.println("Average turn around time:");
System.out.println(totalTurnAroundtime/n);


}


    public void prioritySchedular(process processes[], int n){
        //sort based on prioroty
        Arrays.sort(processes, (a, b) -> a.priority - b.priority);

        
        System.out.println("Processes execution order based on priority:");
        for (int i = 0; i < n; i++) {
            System.out.print(processes[i].name + " ");
            System.out.print("waiting time for each process");
            System.out.print(processes[i].waitingTime);
            System.out.print("turnaround time for each process");
            
            System.out.print(processes[i].turnAroundTime);
        }

        calculateAverageTimes(processes,n);
    
        
    }

    
    // public void SJF_Schedular(){
        
    // }
    // public void SRTF_Schedular(){

    // }
    // public void FCAI_Schedular(){

    // }
    public static void main(String[] args) {
        // Sample process data
        process[] processes = {
            new process("P1", 10, 0, 2,3,4,3),
            new process("P2", 9, 3, 2,3,4,3),
            new process("P3", 8, 5, 2,3,4,3),
            
        };
        // Create an instance of SchedulersSimulator
        schedularsSimulator simulator = new schedularsSimulator();

        // Run priority-based scheduling
        simulator.prioritySchedular(processes, processes.length);
}



}
