package PlaneQueue;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class PlaneQueue {
    // private double minServiceTime, maxServiceTime;
    Plane[] planes;
    Random rand = new Random();
    Queue<Plane> server = new LinkedList<Plane>();
    String[] airlines = {"AAL", "JBU", "ENY", "UAL", "ASH", "QTR", "SKW"};

    public PlaneQueue(double avgServiceTime, double avgArrivalTime, int maxSize, int slowPlanes) {
        double minServiceTime = avgServiceTime - (avgServiceTime * .9), maxServiceTime = avgServiceTime + (avgServiceTime * .9);
        double minArrivalTime = avgArrivalTime - (avgArrivalTime * .9), maxArrivalTime = avgArrivalTime + (avgArrivalTime * .9);

        planes = new Plane[maxSize];
        Random randy = new Random();
        long start = System.nanoTime();

        double totalTime = 0.0;
        for (int i = 0; i < planes.length; i++) {
            // System.out.println(maxSize + " | " + i);
            double entryTime, serviceTime;
            serviceTime = Math.round(ThreadLocalRandom.current().nextDouble(minServiceTime, maxServiceTime) * 1000.0) / 1000.0;
            entryTime = (Math.round(ThreadLocalRandom.current().nextDouble(minArrivalTime, maxArrivalTime) * 1000.0) / 1000.0) ;
            int airlineIndex = randy.nextInt(airlines.length);
            int planeNumber = randy.nextInt(10000);
            Plane p = new Plane(planeNumber, entryTime + totalTime, serviceTime, airlines[airlineIndex]);
            planes[i] = p;
            totalTime += entryTime;
        }
        long end = System.nanoTime();
        totalTime = (end - start) / 1000000.0;
        printTime("Creating Planes: ", totalTime);

        // Randomize customers with extra service time in array
        start = System.nanoTime();
        int[] nums = new int[slowPlanes];
        for (int j = 0; j < nums.length; j++) {
            int index = ThreadLocalRandom.current().nextInt(0, maxSize);
            while (repeatedIndex(nums, index))
                index = ThreadLocalRandom.current().nextInt(0, maxSize);
            nums[j] = index;
            // System.out.println("" + index);
            planes[index].setServiceTime(
                    Math.round(ThreadLocalRandom.current().nextDouble(maxServiceTime, maxServiceTime + (maxServiceTime * .2)) * 1000.0)
                            / 1000.0);
        }
        end = System.nanoTime();
        totalTime = (end - start) / 1000000.0;
        printTime("Inserting Slow Planes: ", totalTime);

        // Order array by firt entry
        start = System.nanoTime();
        // groupByTime(planes);
        end = System.nanoTime();
        totalTime = (end - start) / 100000.0;
        printTime("Sorting: ", totalTime);

        System.out.println("Done!");
    }

    private void printTime(String message, double time) {
        if (time < 1000.0)
            System.out.println(message + Math.round(time * 1000.0) / 1000.0 + " ms");
        else if (time / 1000.0 > 60.0)
            System.out.println(message + Math.round((time / 1000) / 60 * 1000.0) / 1000.0 + " m");
        else
            System.out.println(message + Math.round((time / 1000.0) * 1000.0) / 1000.0 + " s");
    }

    public void printPlanes() {
        for (Plane p : planes)
            System.out.println(p);
    }

    private boolean repeatedIndex(int[] nums, int check) {
        for (int i = 0; i < nums.length; i++)
            if (nums[i] == check)
                return true;
        return false;
    }

    public void addToQueue(Plane p) {
        server.add(p);
    }

    public void popQueue() {
        server.remove();
    }

    private void shuffleArray(Plane[] ar) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            Plane a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    private void groupByTime(Plane[] p) {
        Arrays.sort(p);
    }
}
