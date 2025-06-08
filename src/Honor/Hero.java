package honor;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String name;
    private String type;
	private String property;
    private List<Equipment> equipmentList;

    public Hero(String name, String type, String property) {
        this.name = name;
        this.type = type;
        this.property = property;
        this.equipmentList = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public String getProperty() { return property; }
    public List<Equipment> getEquipmentList() { return equipmentList; }
    public void equip(Equipment eq) { equipmentList.add(eq); }

}