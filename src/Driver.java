public class Driver {
    public static void main(String[] args) {
        PlaneQueue sq = new PlaneQueue(301, 469, 20, 5);
        for(Plane p: sq.planes)
            System.out.println(p.toString());
    }
}
