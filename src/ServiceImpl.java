import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ServiceImpl implements Service {
    List<Truck> list1 = new ArrayList<>();
    List<Driver> list2 = new ArrayList<>();

    private int id;

    public ServiceImpl() {
    }

    public ServiceImpl(List<Truck> list1, List<Driver> list2, int id) {
        this.list1 = list1;
        this.list2 = list2;
        this.id = id;
    }


    @Override
    public void changeDriver(List<Truck> list1, List<Driver> list2, int id) {

        //if already on the road
        if (list1.get(id).getState().equals("ROUTE")) {
            System.out.println("We can't change the driver, because he is already on the road!");
        }

        //assigning new driver
        else if (list1.get(id).getDriver().equals("")) {
            for (int i = 0; i < list1.size(); i++) {
                if (list2.get(i).getTruckname().equals("")) {
                    list1.get(id).setDriver(list2.get(i).getName());
                    list2.get(i).setTruckname(list1.get(id).getName());
                    System.out.println("Driver has been assigned successfully!");
                    break;
                }
            }
        }
        //changing driver if already has one
        else if (!list1.get(id).getDriver().equals("")) {
            for (int i = 0; i < list1.size(); i++) {
                if (list1.get(id).getDriver().equals(list2.get(i).getName())) {
                    list2.get(i).setTruckname("");
                    continue;
                }
                if (list2.get(i).getTruckname().equals("")) {
                    list1.get(id).setDriver(list2.get(i).getName());
                    list2.get(i).setTruckname(list1.get(id).getName());
                    System.out.println("Driver has been assigned successfully!");
                    break;
                }
            }
        } else if (list1.get(id).getState().equals("REPAIR")) {
            System.out.println("We can't assign or change a driver");
        }
    }

    @Override
    public void startDriving(List<Truck> list1, List<Driver> list2, int id) {
        Random random = new Random();
        int k = random.nextInt(3 - 1) + 1;
        //if already on the road
        if (list1.get(id).getState().equals(State.ROUTE)) {
            System.out.println("The truck is already on the road");
        }
        //if under repair and need to change state
        else if (list1.get(id).getState().equals("REPAIRING") && !list1.get(id).getDriver().equals("")) {
            if (k == 1) {
                list1.get(id).setState(State.ROUTE);
                System.out.println("Changed state from repair to route");
            } else {
                list1.get(id).setState(State.BASE);
                System.out.println("Changed state from repair to state");
            }
        } else if (list1.get(id).getState().equals("REPAIRING") && list1.get(id).getDriver().equals("")) {
            if (k == 2) {
                list1.get(id).setState(State.BASE);
                System.out.println("Changed state from repair to base");

            }
        }
        // can't drive without a driver
        else if (list1.get(id).getState().equals("BASE") && list1.get(id).getDriver().equals("")) {
            System.out.println("Nobody can't start driving without a driver");
        }
        //making a truck driving
        else if (!list1.get(id).getDriver().equals("")) {
            list1.get(id).setState(State.ROUTE);
            System.out.println("Successfully started driving!");
        }

    }

    @Override
    public void startRepair(List<Truck> list1, List<Driver> list2, int id) {
        //if base or route assigning REPAIR
        switch (list1.get(id).getState()) {
            case ROUTE -> {
                list1.get(id).setState(State.REPAIRING);
                System.out.println("Successfully changed from route to repair");
            }
            case BASE -> {
                list1.get(id).setState(State.REPAIRING);
                System.out.println("Successfully changed from base  to repair");
            }
            //if already under repairment
            case REPAIRING -> System.out.println("This truck is already under repairment!");
        }
    }

    public List<Truck> getList1() {
        return list1;
    }

    public List<Driver> getList2() {
        return list2;
    }
}
