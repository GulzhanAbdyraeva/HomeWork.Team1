import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Truck {
    private static final Path WRITE_PATH = Paths.get("./trucks.json");
    private int id;
    private String name;
    private String driver;
    private State state;

    public Truck(int id, String name, String driver, State state) {
        this.id = id;
        this.name = name;
        this.driver = driver;
        this.state = state;
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

    public String getDriver() {
        return  driver;
    }

    public void setDriver(String drive) {
        this.driver = drive;
    }
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public static void write(String object){
        Path write = Paths.get(String.valueOf(WRITE_PATH));
        try{
            Files.writeString(write,object, StandardOpenOption.CREATE,StandardOpenOption.WRITE);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    @Override
    public String toString() {
        return "Track{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", drive=" + driver +
                ", state=" + state +
                '}';
    }
}
