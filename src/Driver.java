import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Driver {
    private static final Path WRITE_PATH = Paths.get("json.drivers");
    private int id;
    private String name;

    private String  truckname;

    public Driver() {
    }

    public Driver(int id, String name, String truckname) {
        this.id = id;
        this.name = name;
        this.truckname = truckname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static void write(String obj){
        Path write = Paths.get(String.valueOf(WRITE_PATH));
        try{
            Files.writeString(write,obj, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void setTruckname(String truckname) {
        this.truckname = truckname;
    }

    public String getTruckname() {
        return truckname;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", truckname=" + truckname +
                '}';
    }
}
