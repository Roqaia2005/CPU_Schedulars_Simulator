import static java.lang.Math.ceil;
import java.util.ArrayList;

public class schedulersSimulator {
private void calculateWaitingTime(ArrayList<Process>processes, int n)
{
    // waiting time for first process is 0
    processes.get(0).waitingTime=0;

    
    for (int i = 1; i < n; i++)
    // p1 p2
    // waiting time for p2 = burst time for p1+waiting time for p1 (wait unitll p1 wait and executed)
        processes.get(i).waitingTime = processes.get(i - 1).burstTime + processes.get(i-1).waitingTime;
}
private void calculateTurnAroundTime(ArrayList<Process>processes, int n)
{
    //turn around time = total time from arrival to execution
    for (int i = 0; i < n; i++)
        processes.get(i).turnAroundTime = processes.get(i).burstTime + processes.get(i).waitingTime;
}
protected void calculateAverageTimes(ArrayList<Process>processes){
    int n=processes.size();
    double totalWaitingTime = 0;
    double totalTurnAroundtime = 0;
    calculateWaitingTime(processes, n);
    calculateTurnAroundTime(processes, n);

    for (int i = 0; i < n; i++) {
        totalWaitingTime +=processes.get(i).waitingTime;
        totalTurnAroundtime += processes.get(i).turnAroundTime;
        System.out.println(processes.get(i).name+ " wating time " + processes.get(i).waitingTime + " turn around time " + processes.get(i).turnAroundTime);
}
System.out.println("Average waiting time: ");
System.out.println(ceil(totalWaitingTime/n));

System.out.println("Average turn around time: ");
System.out.println(ceil(totalTurnAroundtime/n));


}

}
