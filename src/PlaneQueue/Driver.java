package PlaneQueue;

public class Driver {

    public static void main(String[] args) {
        // In seconds
        final double avgArrivalTime = 146.2;
        final double avgServiceTime = 42.0;
        PlaneQueue sq = new PlaneQueue(avgServiceTime, avgArrivalTime, 30, 5);
        System.out.printf("%12s %12s %12s", "Plane Callsign", "Entry Time", "Service Time\n");
        for(Plane p: sq.planes)
            System.out.println(p.toString());
    }
}
