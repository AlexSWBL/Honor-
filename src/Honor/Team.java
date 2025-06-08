package honor;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class Team {
    private String teamId;
    private String name;
    private List<Player> members;
    private int wins;
    private int losses;

    public Team(String teamId, String name) {
        this.teamId = teamId;
        this.name = name;
        this.members = new ArrayList<>();
    }

    public void addPlayer(Player p) {
        members.add(p);
        p.setTeam(this);
    }

    public String getName() { return name; }
    public List<Player> getMembers() { return members; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public void recordWin() { wins++; }
    public void recordLoss() { losses++; }
}