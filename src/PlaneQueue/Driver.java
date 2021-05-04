package PlaneQueue;

public class Driver {

    public static void main(String[] args) {
        // In seconds
        final double avgArrivalTime = 146.2;
        final double avgServiceTime = 42.0;
        double busyTime = 0.0; //running number of how long the server is busy for
        double uHat = 0.0;
        double totalSim = 0.0; //total time of simulation
        double lastService = 0.0; 
        PlaneQueue sq = new PlaneQueue(avgServiceTime, avgArrivalTime, 30, 5);
        System.out.printf("%12s %12s %12s", "Plane Callsign", "Entry Time", "Service Time\n");
        for(Plane p: sq.planes) {
            System.out.println(p.toString());
            busyTime = busyTime + p.getServiceTime();
            totalSim = p.getEntryTime();
            lastService = p.getServiceTime();
        }
        
        //Calculations for u-hat
        totalSim += lastService;
        uHat = busyTime / totalSim;
        uHat *= 100;
        
        
        String tempUHat  = String.format("%7.2f", uHat);
        System.out.printf("u-hat : " + tempUHat + "%% \n");
        
        String tempBusy  = String.format("%9.2f", busyTime);
        System.out.printf("B(n)  : " + tempBusy + "\n");
        
        String tempTotalSim  = String.format("%7.2f", totalSim);
        System.out.printf("Total Simulation Time : " + tempTotalSim + "\n");

    }
}
