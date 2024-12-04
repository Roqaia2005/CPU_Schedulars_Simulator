

public class ArrivalTimeComparator implements java.util.Comparator<Process>{
    @Override
    public int compare(Process p1, Process p2){
        return Integer.compare(p1.getArrivalTime(),p2.getArrivalTime());
    }
}
