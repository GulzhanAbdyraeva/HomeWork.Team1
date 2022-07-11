import java.util.List;

public interface Service {
    void changeDriver(List<Truck> list1, List<Driver> list2, int id);

    void startDriving(List<Truck> list1, List<Driver> list2, int id);

    void startRepair(List<Truck> list1, List<Driver> list2, int id);




}
