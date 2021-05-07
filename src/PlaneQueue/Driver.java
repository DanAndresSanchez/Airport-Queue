package PlaneQueue;

public class Driver {

    public static void main(String[] args) {
        // In seconds
        final double avgArrivalTime = 146.2;
        final double avgServiceTime = 42.0;
        
        //Variables for u-hat
        double busyTime = 0.0; //running number of how long the server is busy for
        double uHat = 0.0;
        double totalSim = 0.0; //total time of simulation
        double lastService = 0.0; 
        
        //Variables for q-hat
        double qHat = 0.0;
        double workingTime = 0.0; //time each plane takes to finish
        double moreBusyTime = 0.0;
        
        PlaneQueue sq = new PlaneQueue(avgServiceTime, avgArrivalTime, 30, 5, .19, .71, .69, .59);
        System.out.printf("%12s %12s %12s", "Plane Callsign", "Entry Time", "Service Time\n");
        
        for(Plane p: sq.planes) {
            System.out.println(p.toString());
            //u-hat calculations
            busyTime = busyTime + p.getServiceTime();
            totalSim = p.getEntryTime();
            lastService = p.getServiceTime();
            
            //q-hat calculations
        	if(workingTime > p.getEntryTime()) {
        		moreBusyTime += p.getServiceTime();
        	}
        	workingTime = p.getEntryTime() + p.getServiceTime();            
        }
        
        //Additional calculations for u-hat
        totalSim += lastService;
        uHat = (busyTime / totalSim) * 100;
        
        //Additional calculations for q-hat
        moreBusyTime += busyTime;
        qHat = moreBusyTime / totalSim;
        
        
        String tempUHat  = String.format("%7.2f", uHat);
        System.out.printf("u-hat : " + tempUHat + "%% \n");
        
        String tempQHat  = String.format("%6.2f", qHat);
        System.out.printf("q-hat : " + tempQHat +  "\n");        
        
        String tempBusy  = String.format("%9.2f", busyTime);
        System.out.printf("B(n)  : " + tempBusy + "\n");
        
        String tempTotalSim  = String.format("%7.2f", totalSim);
        System.out.printf("Total Simulation Time : " + tempTotalSim + "\n");

    }
}
