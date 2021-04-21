public class Plane {
    private double entryTime, serviceTime;
    private int id;

    public Plane(int id, double entryTime, double serviceTime) {
        this.id = id;
        this.entryTime = entryTime;
        this.serviceTime = serviceTime;
    }

    public double getServiceTime() {
        return serviceTime;
    }

    public double getEntryTime() {
        return entryTime;
    }

    public int getID() {
        return id;
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
        return "ID: " + id + "\t\t" + "Entry Time: " + entryTime + "\t" + "Service Time: " + serviceTime;
    }
}
