package honor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player extends Person {
    private List<Hero> heroes;
    private List<Match> matches;
    private Team team;
    private int level;

    public Player(String id, String name) {
        super(id, name);
        this.heroes = new ArrayList<>();
        this.matches = new ArrayList<>();
        this.level = 1 + new Random().nextInt(20);
    }

    public void setTeam(Team team) { this.team = team; }
    public Team getTeam() { return team; }
    public List<Hero> getHeroes() { return heroes; }
    public List<Match> getMatches() { return matches; }
    public int getLevel() { return level; }
    public void addHero(Hero hero) { heroes.add(hero); }
    public void addMatch(Match match) { matches.add(match); }
    public void setLevel(int level) { this.level = level; }
}
