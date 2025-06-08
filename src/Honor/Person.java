public abstract class Person {
    protected String id;
    protected String name;

    public Person(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }
}

/*
 * Player class (inherits from Person)
 */
import java.util.*;

public class Player extends Person {
    private List<Hero> heroes;
    private List<Match> matches;
    private Team team;

    public Player(String id, String name) {
        super(id, name);
        this.heroes = new ArrayList<>();
        this.matches = new ArrayList<>();
    }

    public void setTeam(Team team) { this.team = team; }
    public Team getTeam() { return team; }
    public List<Hero> getHeroes() { return heroes; }
    public List<Match> getMatches() { return matches; }
    public void addHero(Hero hero) { heroes.add(hero); }
    public void addMatch(Match match) { matches.add(match); }
}

/*
 * Admin class (inherits from Person)
 */
public class Admin extends Person {
    public Admin(String id, String name) {
        super(id, name);
    }
}