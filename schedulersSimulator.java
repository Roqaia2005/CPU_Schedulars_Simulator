import static java.lang.Math.ceil;
import java.util.Arrays;

public class schedulersSimulator {
    void calculateWaitingTime(Process processes[], int n)
{
    // waiting time for first process is 0
    processes[0].waitingTime=0;

    
    for (int i = 1; i < n; i++)
    // p1 p2
    // waiting time for p2 = burst time for p1+waiting time for p1 (wait unitll p1 wait and executed)
        processes[i].waitingTime = processes[i - 1].burstTime + processes[i-1].waitingTime;
}
void calculateTurnAroundTime(Process processes[], int n)
{
    //turn around time = total time from arrival to execution
    for (int i = 0; i < n; i++)
        processes[i].turnAroundTime = processes[i].burstTime + processes[i].waitingTime;
}
void calculateAverageTimes(Process processes [],int n){
    double totalWatingTime = 0;
    double totalTurnAroundtime = 0;
    calculateWaitingTime(processes, n);
    calculateTurnAroundTime(processes, n);

    for (int i = 0; i < n; i++) {
        totalWatingTime +=processes[i].waitingTime;
        totalTurnAroundtime += processes[i].turnAroundTime;
        System.out.println(processes[i].name+ " wating time " + processes[i].waitingTime + " turn around time " + processes[i].turnAroundTime);
}
System.out.println("Average waiting time: ");
System.out.println(ceil(totalWatingTime/n));

System.out.println("Average turn around time: ");
System.out.println(ceil(totalTurnAroundtime/n));


}


public void priorityScheduler(Process processes[], int n){
        //sort based on prioroty
        Arrays.sort(processes, (a, b) -> b.priority - a.priority);


        
        System.out.println("Processes execution order based on priority:");
        for (int i = 0; i < n; i++) {

            System.out.print(processes[i].name + " ");
            System.out.println();
        }
        calculateAverageTimes(processes,n);
    }

    
    // public void SJF_Scheduler(){
        
    // }
    // public void SRTF_Scheduler(){

    // }
    // public void FCAI_Scheduler(){

    // }
    public static void main(String[] args) {
        // Sample process data
        Process[] processes = {
            new Process("P1", 10, 2),
            new Process("P2", 5,0),
            new Process("P3", 8,1),
            
        };
        // Create an instance of SchedulersSimulator
        schedulersSimulator simulator = new schedulersSimulator();

        // Run priority-based scheduling
        simulator.priorityScheduler(processes, processes.length);
}
}
