import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FCAI_Scheduler extends schedulersSimulator {

    ArrayList<Process>processes;

    ArrayList<String> historyOfUpdateQuantum;
    int time=0;

    


    public FCAI_Scheduler(List<Process> processes) {
        this.processes = new ArrayList<>(processes);
        calculateFCAI_factor();

        this.historyOfUpdateQuantum = new ArrayList<>();
        
    }

    

    private int calculateV1(){
        // last arrival time for all processes /10
        int last_time=0;
        for(Process process:processes){
            if(process.arrivalTime>last_time){
                last_time=process.arrivalTime;
            }

        }
        
        return last_time/10;
    }

    private int calculateV2(){
        // max burst time for all processes /10
        int max_time=0;
        for(Process process:processes){
            if(process.burstTime>max_time){
                max_time=process.burstTime;
            }

        }
        return max_time/10;
    
    }

    private void calculateFCAI_factor(){
        int v1=calculateV1();
        int v2=calculateV2();
        for(Process process:processes){
            if (process.arrivalTime <= time && process.remainingTime > 0&&v1!=0&&v2!=0){
            process.FCAI_factor=(10-process.priority)+((process.arrivalTime)/v1)+((process.remainingTime)/v2);
            }
        
        }

    }

    public void execute(){
        Queue<Process>readyQueue=new LinkedList<>();
        int currentTime=0;
        Process currentProcess=null;

        while(!processes.isEmpty()||!readyQueue.isEmpty()){
            readyQueue.addAll(processes);//add all processes arrive at time 0

            if(!readyQueue.isEmpty()){//there are processes ready to be exectued
                currentTime++;


            }
            if(currentProcess==null){// if there is no process executed currently pick first from ready queue
                currentProcess=readyQueue.poll();
                // next execute till 40% of qunatum and check fcai factor,update quntum


        }

        
       // start execute processes from time 0
        // current process being executed while time running till it finish
        // if not finish and its quantum exceeded 40% check for process has least fcai factor
        // if the process prempted added back to the ready queue and update its quantum with the reamining quantum
        // if it finish its quantum but still have remaining work update its quantum by 2
        // still execute processes untill list of processes and the ready queue become empty
        // make sure to recalculate fcai factor dyanmically when quntum updated
        // print the history of quantum update for each process


    }
    
}




}

