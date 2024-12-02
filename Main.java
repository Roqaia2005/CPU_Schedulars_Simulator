package srtf;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Create a list to hold our processes
        List<Process> processList = new ArrayList<>();

        // Adding 10 processes to the list with random values for the example
        for (int i = 0; i < 10; i++) {
            int arrivalTime = (int) (Math.random() * 10); // random arrival time between 0 and 9
            int burstTime = (int) (Math.random() * 10) + 1; // random burst time between 1 and 10
            processList.add(new Process("Process" + (i + 1), "Color" + (i + 1), i, arrivalTime, burstTime)); // no priority for now
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
    }
}
