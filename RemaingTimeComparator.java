public class RemaingTimeComparator implements java.util.Comparator<Process> {
    @Override
    public int compare(Process p1, Process p2){
        int priorityComparison = Integer.compare(p1.getPriority(),p2.getPriority());
        if(priorityComparison == 0){
            return Integer.compare(p1.getRemainingTime(), p2.getRemainingTime());
        }
        return  priorityComparison;
    }

}
