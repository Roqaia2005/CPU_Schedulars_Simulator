import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a list to hold our processes
        List<Process> processList = new ArrayList<>();

        // Adding 10 processes to the list with random values for the example
        for (int i = 0; i < 10; i++) {
            int arrivalTime = (int) (Math.random() * 10); // random arrival time between 0 and 9
            int burstTime = (int) (Math.random() * 10) + 1; // random burst time between 1 and 10
            int red = (int)(Math.random() *256);
            int blue = (int)(Math.random() *256);
            int green = (int)(Math.random() *256);
            processList.add(new Process("P" + (i + 1), "Color"+ (i + 1), red, blue, green, i, arrivalTime, burstTime)); // no priority for now
        }

        // Print out the process list for clarity
        System.out.println("Processes Created:");
        for (Process p : processList) {
            System.out.println(p.getName() + " Arrival Time: " + p.getArrivalTime() + " Burst Time: " + p.getBurstTime() + " Priority: " + p.getPriority());
        }

        // Create a priority queue sorted by arrival time
        PriorityQueue<Process> arrivalQueue = new PriorityQueue<>(new ArrivalTimeComparator());
        // Add processes to the arrival queue
        arrivalQueue.addAll(processList);

        //check all proccess
        PriorityQueue<Process> check = new PriorityQueue<>(new ArrivalTimeComparator());
        System.out.println("Proccess sorted on order time: ");
    while(!arrivalQueue.isEmpty()){
        Process p = arrivalQueue.poll();
        System.out.println(p.getName() + " Arrival Time: " + p.getArrivalTime() + " Burst Time: " + p.getBurstTime());
        check.add(p);
    }
    while(!check.isEmpty()){
        Process p = check.poll();
        arrivalQueue.add(p);
    }
        // Create SRTF scheduler
        SRTF srtf = new SRTF(arrivalQueue);

        // Execute the processes
        srtf.executeProcess();

        // Print the results
        srtf.print();
        
// test for priority scheduling

List<Process> processes = new ArrayList<>();
processes.add(new Process("P1", 10, 2));
processes.add(new Process("P2", 5, 0));
processes.add(new Process("P3", 8, 1));
        
        PriorityScheduler simulator = new PriorityScheduler();

        
        simulator.priority((ArrayList<Process>) processes);

         // test for SJF...
    SJF sjf = new SJF();
    Process p1 = new Process("p1","red",255,0,0,1,0,7);
    Process p2 = new Process("p2","purple",128,0,128,2,2,4);
    Process p3 = new Process("p3","green",0,255,0,3,4,1);
    Process p4 = new Process("p4","blue",0,0,255,4,5,4);
        sjf.addProcess(p1);
        sjf.addProcess(p2);
        sjf.addProcess(p3);
        sjf.addProcess(p4);

        sjf.NonPreemptiveSJF();
        sjf.print();
    


// test FCAI scheduling
List<Process> fcaiTest = new ArrayList<>();
fcaiTest.add(new Process(1,"p1",17,0,4,4));
fcaiTest.add(new Process(2,"p2",6,3,4,3));
fcaiTest.add(new Process(3,"p3",10,4,4,5));
fcaiTest.add(new Process(4,"p4",4,29,4,2));

FCAI_Scheduler fcai = new FCAI_Scheduler(fcaiTest);
fcai.execute();

}
}
