public class Plane implements Comparable<Plane>{
    private double entryTime, serviceTime;
    private int planeNumber;
    private String airline;

    public Plane(int id, double entryTime, double serviceTime, String airline) {
        this.planeNumber = id;
        this.entryTime = entryTime;
        this.serviceTime = serviceTime;
        this.airline = airline;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public double getEntryTime() {
        return entryTime;
    }

    public int getID() {
        return planeNumber;
    }

    public void setServiceTime(double serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int compareTo(Plane p) {
        if (p.getEntryTime() > this.entryTime)
            return -1;
        else if (p.getEntryTime() < this.entryTime)
            return 1;
        else if (p.getEntryTime() == this.entryTime && p.getServiceTime() > this.serviceTime)
            return -1;
        else if (p.getEntryTime() == this.entryTime && p.getServiceTime() < this.serviceTime)
            return 1;
        return 0;
    }

    public String toString() {
        return airline + " " + planeNumber +  "\t\t" + "Entry Time: " + entryTime + "\t" + "Service Time: " + serviceTime;
    }
}
