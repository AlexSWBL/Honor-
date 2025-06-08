package honor;
import java.util.Date;

public class Match {
    private String matchId;
    private Team team1;
    private Team team2;
    private Team winner;
    private Date matchDate;

    public Match(String matchId, Team team1, Team team2, Team winner) {
        this.matchId = matchId;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
        this.matchDate = new Date();
    }

    public Team getWinner() { return winner; }
    public String getMatchId() { return matchId; }
    public Date getMatchDate() { return matchDate; }
}