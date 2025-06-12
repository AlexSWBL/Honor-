package honor;

import java.util.*;
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    static ArrayList<Hero> heroArrayList = new ArrayList<>();
    static ArrayList<Player> playerArrayList = new ArrayList<>();
    static ArrayList<Team> teamArrayList = new ArrayList<>();
    static ArrayList<Equipment> equipmentArrayList = new ArrayList<>();
    static ArrayList<Match> matchArrayList = new ArrayList<>();
    static ArrayList<Admin> adminArrayList = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        String currentUserRole = "";

        Equipment equipment01 = new Equipment("疾步之靴", "01", 99);
        Equipment equipment02 = new Equipment("博学者之怒", "02", 90);

        equipmentArrayList.add(equipment01);
        equipmentArrayList.add(equipment02);

        heroArrayList.add(new Hero("妲己","法师", 100, 20));
        heroArrayList.add(new Hero("李白","刺客", 110, 30));

        heroArrayList.get(0).equip(equipment02);
        heroArrayList.get(1).equip(equipment01);


        Player player01 = new Player("Alex","0101");
        player01.addHero(heroArrayList.get(0));

        Player player02 = new Player("David","0102");
        player02.addHero(heroArrayList.get(1));


        playerArrayList.add(player01);
        playerArrayList.add(player02);

        Team team1 = new Team("001", "team1");
        team1.addPlayer(player01);


        Team team2 = new Team("002", "team2");
        team2.addPlayer(player02);

        teamArrayList.add(team1);
        teamArrayList.add(team2);

        Match match01 = new Match("001", team1, team2, team1);

        matchArrayList.add(match01);

        player01.addMatch(match01);
        player02.addMatch(match01);

        Admin admin = new Admin("A001", "Brother Li");
        adminArrayList.add(admin);


        while (running) {
            System.out.println("Welcome to Honor of Kings IMS");
            System.out.println("1. Player Lookup");
            System.out.println("2. Team Overview");
            System.out.println("3. Hero Details");
            System.out.println("4. Equipment Stats");
            System.out.println("5. Match History");
            System.out.println("6. Win-Rate & Leaderboards");
            System.out.println("7. Data Management");
            System.out.println("8. Authentication");
            System.out.println("9. Save Data to File");
            System.out.println("10. Load Data from File");
            System.out.println("11. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    // implement player lookup
                    System.out.println("Enter your ID or name");
                    String input = scanner.nextLine();
                    lookupPlayer(input);
                    break;
                case 2:
                    // implement team overview
                    System.out.println("Enter team's ID or name");
                    teamOverview(scanner.nextLine());
                    break;
                case 3:
                    // implement hero details
                    System.out.println("Enter hero's name");
                    heroDetails(scanner.nextLine());
                    break;
                case 4:
                    // implement equipment stats
                    equipmentStatistics();
                    break;
                case 5:
                    // implement Match History
                    System.out.println("Enter player's name or team's name");
                    String keyword = scanner.nextLine();
                    System.out.println("How many recent matches to display? ");
                    int n = scanner.nextInt();
                    matchHistory(keyword, n);
                    break;
                case 6:
                    // implement Win-Rate & Leaderboards
                    System.out.println("Top how many players to display?");
                    leaderboard(scanner.nextInt());
                    break;
                case 7:
                    if (!currentUserRole.equals("admin")) {
                        System.out.println("Access denied: Only admin can manage data.");
                        break;
                    }
                    System.out.println("(a) Add Player\n(b) Delete Player\n(c) Edit Player Name");
                    System.out.print("Select action: ");
                    String action = scanner.nextLine();
                    if (action.equalsIgnoreCase("a")) {
                        System.out.print("Enter Player ID: ");
                        String newId = scanner.nextLine();
                        System.out.print("Enter Player Name: ");
                        String newName = scanner.nextLine();
                        addPlayer(newId, newName);
                    } else if (action.equalsIgnoreCase("b")) {
                        System.out.print("Enter Player ID to delete: ");
                        deletePlayer(scanner.nextLine());
                    } else if (action.equalsIgnoreCase("c")) {
                        System.out.print("Enter Player ID to edit: ");
                        String id = scanner.nextLine();
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        editPlayerName(id, newName);
                    } else {
                        System.out.println("Invalid option.");
                    }
                    break;
                case 8:
                    System.out.print("Enter ID: ");
                    String loginId = scanner.nextLine();
                    System.out.print("Enter role (admin/player): ");
                    String role = scanner.nextLine();
                    if (login(loginId, role)) {
                        currentUserRole = role.toLowerCase();
                    } else {
                        currentUserRole = "";
                    }
                    break;
                case 9:
                    System.out.print("Enter filename to save: ");
                    saveDataToFile(scanner.nextLine());
                    break;
                case 10:
                    System.out.print("Enter filename to load: ");
                    loadDataFromFile(scanner.nextLine());
                    break;
                case 11:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void lookupPlayer(String keyword) {
        for (Player p : playerArrayList) {
            if (p.getId().equalsIgnoreCase(keyword) || p.getName().equalsIgnoreCase(keyword)) {
                System.out.println("Player found: " + p.getName());
                System.out.println("Team: " + (p.getTeam() != null ? p.getTeam().getName() : "None"));
                System.out.println("Heroes:");
                for (Hero h : p.getHeroes()) {
                    System.out.println("- " + h.getName() + " (" + h.getType() + ")");
                    for (Equipment eq : h.getEquipmentList()) {
                        System.out.println("    • Equipped: " + eq.getName() + " [" + eq.getRating() + "]");
                    }
                }
                return;
            }
        }
        System.out.println("Player not found.");
    }

    // Input ID or name
    // return list all members, win rates, average level
    public static void teamOverview(String keyword) {
        for (Team t: teamArrayList) {
            if (t.getTeamId().equalsIgnoreCase(keyword) || t.getName().equalsIgnoreCase(keyword)) {
                System.out.println("Team Name: " + t.getName());
                System.out.println("Win Rate: " + String.format("%.2f", t.getWinRate()) + "%");
                System.out.println("Average Player Level: " + String.format("%.2f", t.getAverageLevel()));
                System.out.println("Members:");
                for (Player p : t.getMembers()) {
                    System.out.println("- " + p.getName() + " (Level: " + p.getLevel() + ")");
                }
                return;
            }
        }
        System.out.println("Team not found");
    }

    // Input name
    // return show type, base stats, equipment, and which Players own this Hero
    public static void heroDetails(String name) {
        for (Hero h : heroArrayList) {
            if (h.getName().equalsIgnoreCase(name)) {
                System.out.println("Hero: " + h.getName());
                System.out.println("Type: " + h.getType());
                System.out.println("Base Health: " + h.getBaseHealth());
                System.out.println("Base Attack: " + h.getBaseAttack());
                System.out.println("Equipable Items:");
                for (Equipment e : h.getEquipmentList()) {
                    System.out.println("- " + e.getName() + ": " + e.getDescription() + " [Rating: " + e.getRating() + "]");
                }
                System.out.println("Owned By Players:");
                for (Player p : playerArrayList) {
                    for (Hero owned : p.getHeroes()) {
                        if (owned.getName().equalsIgnoreCase(name)) {
                            System.out.println("- " + p.getName());
                            break;
                        }
                    }
                }
                return;
            }
        }
        System.out.println("Hero not found.");
    }

    // return Rank all Equipment by usage count, average rating, or other custom metrics.
    public static void equipmentStatistics() {
        Map<String, Integer> usageCount = new HashMap<>();
        Map<String, List<Integer>> ratings = new HashMap<>();

        for (Player p : playerArrayList) {
            for (Hero h : p.getHeroes()) {
                for (Equipment eq : h.getEquipmentList()) {
                    usageCount.put(eq.getName(), usageCount.getOrDefault(eq.getName(), 0) + 1);
                    ratings.putIfAbsent(eq.getName(), new ArrayList<>());
                    ratings.get(eq.getName()).add(eq.getRating());
                }
            }
        }

        List<String> sortedEquipments = new ArrayList<>(usageCount.keySet());
        sortedEquipments.sort((a, b) -> usageCount.get(b) - usageCount.get(a));

        System.out.println("Equipment Usage Statistics:");
        for (String eqName : sortedEquipments) {
            int count = usageCount.get(eqName);
            List<Integer> ratingList = ratings.get(eqName);
            double avgRating = ratingList.stream().mapToInt(Integer::intValue).average().orElse(0);
            System.out.println("- " + eqName + ": Used " + count + " times, Avg Rating: " + String.format("%.2f", avgRating));
        }
    }
    public static void matchHistory(String keyword, int n) {
        List<Match> matches = new ArrayList<>();
        for (Player p : playerArrayList) {
            if (p.getName().equalsIgnoreCase(keyword) || p.getId().equalsIgnoreCase(keyword)) {
                matches = p.getMatches();
                break;
            }
        }
        if (matches.isEmpty()) {
            for (Team t : teamArrayList) {
                if (t.getName().equalsIgnoreCase(keyword) || t.getTeamId().equalsIgnoreCase(keyword)) {
                    for (Player p : t.getMembers()) {
                        matches.addAll(p.getMatches());
                    }
                    break;
                }
            }
        }
        matches.sort(Comparator.comparing(Match::getMatchDate).reversed());
        System.out.println("Last " + n + " matches for " + keyword + ":");
        for (int i = 0; i < Math.min(n, matches.size()); i++) {
            Match m = matches.get(i);
            System.out.println("Match ID: " + m.getMatchId() + ", Winner: " + m.getWinner().getName() + ", Date: " + m.getMatchDate());
        }
    }

    public static void leaderboard(int topX) {
        playerArrayList.sort((a, b) -> b.getLevel() - a.getLevel());
        System.out.println("Top " + topX + " Players by Level:");
        for (int i = 0; i < Math.min(topX, playerArrayList.size()); i++) {
            Player p = playerArrayList.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " - Level " + p.getLevel());
        }
    }

    public static void addPlayer(String id, String name) {
        Player p = new Player(id, name);
        playerArrayList.add(p);
        System.out.println("Player added: " + name);
    }

    public static void deletePlayer(String id) {
        playerArrayList.removeIf(p -> p.getId().equalsIgnoreCase(id));
        System.out.println("Player with ID " + id + " deleted.");
    }

    public static void editPlayerName(String id, String newName) {
        for (Player p : playerArrayList) {
            if (p.getId().equalsIgnoreCase(id)) {
                p.name = newName;
                System.out.println("Player name updated.");
                return;
            }
        }
        System.out.println("Player not found.");
    }

    public static boolean login(String id, String role) {
        if (role.equalsIgnoreCase("admin")) {
            for (Admin a : adminArrayList) {
                if (a.getId().equalsIgnoreCase(id)) {
                    System.out.println("Admin login successful: " + a.getName());
                    return true;
                }
            }
        } else {
            for (Player p : playerArrayList) {
                if (p.getId().equalsIgnoreCase(id)) {
                    System.out.println("Player login successful: " + p.getName());
                    return true;
                }
            }
        }
        System.out.println("Login failed.");
        return false;
    }
    public static void saveDataToFile(String filename) {
        try (PrintWriter out = new PrintWriter(filename)) {
            for (Player p : playerArrayList) {
                out.println("PLAYER," + p.getId() + "," + p.getName() + "," + p.getLevel());
                for (Hero h : p.getHeroes()) {
                    out.println("HERO," + h.getName() + "," + h.getType() + "," + h.getBaseHealth() + "," + h.getBaseAttack());
                    for (Equipment e : h.getEquipmentList()) {
                        out.println("EQUIP," + e.getName() + "," + e.getDescription().replace(",", ";") + "," + e.getRating());
                    }
                }
            }
            System.out.println("Data saved to " + filename);
        } catch (Exception e) {
            System.out.println("Failed to save: " + e.getMessage());
        }
    }

    public static void loadDataFromFile(String filename) {
        try (Scanner sc = new Scanner(new File(filename))) {
            Player currentPlayer = null;
            Hero currentHero = null;
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(",");
                switch (parts[0]) {
                    case "PLAYER":
                        currentPlayer = new Player(parts[1], parts[2]);
                        currentPlayer.setLevel(Integer.parseInt(parts[3]));
                        playerArrayList.add(currentPlayer);
                        break;
                    case "HERO":
                        currentHero = new Hero(parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
                        if (currentPlayer != null) currentPlayer.addHero(currentHero);
                        heroArrayList.add(currentHero);
                        break;
                    case "EQUIP":
                        Equipment eq = new Equipment(parts[1], parts[2].replace(";", ","), Integer.parseInt(parts[3]));
                        if (currentHero != null) currentHero.equip(eq);
                        break;
                }
            }
            System.out.println("Data loaded from " + filename);
        } catch (Exception e) {
            System.out.println("Failed to load: " + e.getMessage());
        }
    }



}
