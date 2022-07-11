package com.company;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.sound.midi.Track;
import javax.swing.plaf.nimbus.State;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final GsonBuilder GSON_BUILDER = new GsonBuilder();
    private static final Gson GSON = GSON_BUILDER.setPrettyPrinting().create();
    public static final Path PATH1 = Paths.get("./json.json");
    private static final Path WRITE_PATH = Paths.get("json.drivers");
    public static void main(String[] args) {
        Truck[] trucks = {
                new Truck(1, "Renault Magnum", "", State.BASE),
                new Truck(2, "Volvo", "", State.BASE),
                new Truck(3, "DAF XT", "", State.BASE)
        };
        List<Truck> list1 = new ArrayList<>(List.of(trucks));

        String json = GSON.toJson(trucks);
        Truck.write(json);
        viewTruck(list1);

        Driver[] drivers = {
                new Driver(1, "Petr",""),
                new Driver(2, "Askar",""),
                new Driver(3, "Uson","")};
        List<Driver> list2 = new ArrayList<>(List.of(drivers));
        String json2 = GSON.toJson(drivers);
        Driver.write(json2);
        viewDriver(list2);

        outPut(list1,list2);


    }
    public static void viewTruck(List<Truck> list1){
        System.out.println("                  Trucks                   ");
        System.out.println("| id |      Bus      |    Driver   |State |");
        System.out.println("|~~~ |~~~~~~~~~~~~~~~|~~~~~~~~~~~~~|~~~~~~|");
        for(Truck autoBase : list1){
            System.out.printf("|%-3s ",autoBase.getId());
            System.out.printf("|%-15s|", autoBase.getName());
            System.out.printf("%-13s|", autoBase.getDriver() + " ");
            System.out.printf("%-6s|",autoBase.getState());
            System.out.println();
        }
    }
    public static void viewDriver(List<Driver> list2){
        System.out.println("            Drivers                ");
        System.out.println("| Id of driver |  Driver |   Bus   ");
        System.out.println("|~~~~~~~~~~~~~~|~~~~~~~~~|~~~~~~~~~");
        for (Driver driverInformation : list2) {
            System.out.printf("|%-14s", driverInformation.getId());
            System.out.printf("|%-9s", driverInformation.getName());
            System.out.printf("|%-14s", driverInformation.getTruckname());
            System.out.println();
        }
    }
    public static void outPut(List<Truck> list1, List<Driver> list2){
        String choice;
        int id;
        while(true){
            try {
                System.out.println("Choose one of the TRUCK: ");
                Scanner scanner = new Scanner(System.in);
                id = scanner.nextInt();
                id--;
                System.out.println("             TRUCK-INF                ");
                System.out.println("Number                :" + list1.get(id).getId());
                System.out.println("Truck              :" + list1.get(id).getName());
                System.out.println("Driver           :" + list1.get(id).getDriver());
                System.out.println("State            :" + list1.get(id).getState());
                System.out.println("Press 1 to change or assign new DRIVER");
                System.out.println("Press 2 to start Driving");
                System.out.println("Press 3 to start Repairing");
                System.out.println("Press 4 if you wish to stop program");
                scanner.nextLine();
                choice = scanner.nextLine();
                if(choice.equals("4")){
                    System.exit(0);
                }
                Servicelmpl action = new Servicelmpl(list1, list2, id);
                switch (choice) {
                    case "1" -> action.changeDriver(list1, list2, id);
                    case "2" -> action.startDriving(list1, list2, id);
                    case "3" -> action.startRepair(list1, list2, id);
                }
                list1 = action.getList1();
                list2 = action.getList2();
                viewDriver(list2);
                viewTruck(list1);
            }
            catch(IndexOutOfBoundsException e){
                System.out.println("The truck with this number not founded: \n"+
                        "Total number of trucks is:"+list1.size());
            }
        }
    }
}
