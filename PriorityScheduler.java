import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PriorityScheduler extends  schedulersSimulator {
    public void priority(ArrayList<Process>processes){
        int n=processes.size();
        //sort based on priority
        Collections.sort(processes, Comparator.comparingInt(p -> p.priority));


        
        System.out.println("Processes execution order based on priority:");
        for (int i = 0; i < n; i++) {

            System.out.print(processes.get(i).name + " ");
            System.out.println();
        }
        calculateAverageTimes(processes);
    }
    
}
