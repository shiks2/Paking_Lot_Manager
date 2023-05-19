import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;
//Phase 1
class PL{
    private int totalSpaces ;
    private int availableSpaces;
    private int occupiedSpaces;
    private List<Car> parkedCar;
    public PL(int totalSpaces){
        this.totalSpaces = totalSpaces;
        this.availableSpaces = totalSpaces;
        this.occupiedSpaces = 0;
        this.parkedCar  = new ArrayList<>();
    }
    public int getTotalSpaces() {
        return totalSpaces;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public int getOccupiedSpaces() {
        return occupiedSpaces;
    }
    public void setTotalSpaces(int totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public void setAvailableSpaces(int availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public void setOccupiedSpaces(int occupiedSpaces) {
        this.occupiedSpaces = occupiedSpaces;
    }
    public boolean parkcar(Car car){
        if(availableSpaces > 0){
            parkedCar.add(car);
            availableSpaces--;
            occupiedSpaces++;
            return true;
        }
        else
            return false;
    }
    public boolean removeCar(Car car){
        if(parkedCar.remove(car))
        {
            availableSpaces++;
            occupiedSpaces--;
            return true;
        }
        else
            return false;
    }
    public List<Car> getParkedCar(){
        return parkedCar;
    }
    public void display() {
        System.out.println("Total Spaces: " + totalSpaces);
        System.out.println("Available Spaces: " + availableSpaces);
        System.out.println("Occupied Spaces: " + occupiedSpaces);
        System.out.println("Parked Cars: ");
        if(parkedCar.isEmpty()){
            System.out.println("No Car is parked right now");
        }
        else
        {
            for (Car car : parkedCar) {
                System.out.println("Registration Number: " + car.getRegNo());
                System.out.println("Timestamp: " + car.getFormattedTimestamp());
            }
        }
    }
}
class Car{
    String RegNo ;
    //String timeStamp;
    LocalDateTime timeStamp = LocalDateTime.now();

    // Format the timestamp as per your requirement
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedTimestamp = timeStamp.format(formatter);
    public Car(String RegNo,LocalDateTime timeStamp){
        this.RegNo = RegNo;
        this.timeStamp = timeStamp;
    }
    public String getRegNo(){
        return RegNo;
    }
    public LocalDateTime gettimeStamp(){
        return timeStamp;
    }
    public void setRegNo(){
        this.RegNo = RegNo;
    }
    public void settimeStamp(){
        this.timeStamp = timeStamp;
    }
    public String getFormattedTimestamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return timeStamp.format(formatter);
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        PL v = new PL(10);
        Car c1 = new Car("GJ01VI2343",LocalDateTime.now());
        v.parkcar(c1);
        Car c2 = new Car("GJ01VI2343",LocalDateTime.now());
        v.parkcar(c2);
        //v.removeCar(c2);
        v.display();
    }
}