import java.util.ArrayList;
import java.util.List;

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

    
}







