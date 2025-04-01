import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Vehicle extends Rentable {
    private Company company;
    private double cargoSpace;
    public Vehicle(Company company,double cargoSpace){
        this.cargoSpace=cargoSpace;
        this.company=company;
    }

    public Company getCompany() {
        return company;
    }

    public double getCargoSpace() {
        return cargoSpace;
    }

    @Override
    public void setAtStoreRentStatus(Store store) {
        RentStatus s=new AtStoreRentStatus(store)
        setRentStatus(s);
    }

    @Override
    public void setRentedRentStatus(Rental rental) {
        RentStatus s=new AtStoreRentStatus(store)
        setRentStatus(s);
    }
}
class Store {
    private List<Vehicle> vehicles;
    public Store() {
        this.vehicles=new ArrayList<>();
    }
    public Rental rent(Vehicle vehicle;Customer customer){
        vehicles.remove(vehicle);
        Rental nr=new Rental(vehicle, customer, this);
        return nr;
    }
    public void returnVehicle(Rental rental){
        Vehicle v=rental.getVehicle();
        vehicles.add(v);
    }
}
class Customer{
    private Optional<Rental> rental = Optional.empty();

    public Optional<Rental> getRental() {
        return rental;
    }

    public void setRental(Optional<Rental> rental) {
        this.rental = rental;
    }
}
class Rental{
    private Vehicle vehicle;
    private Customer customer;
    private Store from;
    public Rental(Vehicle vehicle,Customer customer,Store from){
        this.customer=customer;
        this.from=from;
        this.vehicle=vehicle;
        customer.setRental(Optional.of(this));
    }

    public Customer getCustomer() {
        return customer;
    }

    public Store getFrom() {
        return from;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}

class Company {
    private List<Store> stores;
    private List<Vehicle> vehicles;
    public Company(){
        this.stores=new ArrayList<>();
        this.vehicles=new ArrayList<>();
    }
}
interface RentStatus {
    public boolean isRented();
    public boolean canRent();
}
abstract class Rentable {
    private RentStatus rentStatus;

    public RentStatus getRentStatus() {
        return rentStatus;
    }
    public abstract void setAtStoreRentStatus(Store store);
    public abstract void setRentedRentStatus(Rental rental);
}
class RentedRentStatus implements RentStatus{
    private Rental rental;
    public RentedRentStatus(Rental rental){
        this.rental=rental;
    }
    public Rental getRental() {
        return rental;
    }

    @Override
    public boolean isRented() {
        return true;
    }

    @Override
    public boolean canRent() {
        return false;
    }
}
class AtStoreRentStatus implements RentStatus{
    private Store store;

    @Override
    public boolean isRented() {
        return false;
    }

    @Override
    public boolean canRent() {
        return true;
    }

    public Store getStore() {
        return store;
    }
    public AtStoreRentStatus(Store store){
        this.store=store;
    }
}



public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}