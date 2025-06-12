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
    public String getTeamId() { return teamId; }
    public List<Player> getMembers() { return members; }
    public int getWins() { return wins; }
    public int getLosses() { return losses; }
    public double getWinRate() { return (double) wins / (wins + losses) * 100.0; }
    public double getAverageLevel() {
        if (members.isEmpty()) {
            return 0.0;
        }
        else {
            int total = 0;
            for (Player p: members ) {
                total += p.getLevel();
            }
            return (double) total / members.size();
        }
    }
    public void recordWin() { wins++; }
    public void recordLoss() { losses++; }
}