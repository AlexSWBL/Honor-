package honor;
import java.util.ArrayList;
import java.util.List;

public class Hero {
    private String name;
    private String type;
    private int baseHealth;
    private int baseAttack;
    private List<Equipment> equipmentList;

    public Hero(String name, String type, int baseHealth, int baseAttack) {
        this.name = name;
        this.type = type;
        this.baseHealth = baseHealth;
        this.baseAttack = baseAttack;
        this.equipmentList = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getType() { return type; }
    public int getBaseHealth() { return baseHealth; }
    public int getBaseAttack() { return baseAttack; }
    public List<Equipment> getEquipmentList() { return equipmentList; }
    public void equip(Equipment eq) { equipmentList.add(eq); }
}