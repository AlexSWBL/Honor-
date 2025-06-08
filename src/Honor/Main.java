import java.util.*;
import honor.*;

public class Main {
    static ArrayList<Hero> heroArrayList = new ArrayList<>();
    static ArrayList<Player> playerlist = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        heroArrayList.add(new Hero("妲己","疾步之靴、博学者之怒","法师"));
        heroArrayList.add(new Hero("李白","追击刀锋、急速战靴","刺客"));
        heroArrayList.add(new Hero("亚瑟","抵抗之靴、暗影战斧","战士"));
        heroArrayList.add(new Hero("吕布","抵抗之靴、破军","战士"));
        heroArrayList.add(new Hero("花木兰","冷静之靴、暗影战斧","战士"));
        heroArrayList.add(new Hero("诸葛亮","冷静之靴、痛苦面具","法师"));
        heroArrayList.add(new Hero("武则天","冷静之靴、圣杯","法师"));
        heroArrayList.add(new Hero("后羿","急速战靴、闪电匕首","射手"));
        heroArrayList.add(new Hero("孙尚香","急速战靴、宗师之力","射手"));
        heroArrayList.add(new Hero("伽罗","急速战靴、闪电匕首","射手"));
        heroArrayList.add(new Hero("韩信","追击刀锋、急速战靴","刺客"));
        heroArrayList.add(new Hero("阿轲","追击刀锋、急速战靴","刺客"));
        heroArrayList.add(new Hero("虞姬","急速战靴、破晓","射手"));
        heroArrayList.add(new Hero("貂蝉","冷静之靴、噬神之书","法师"));
        heroArrayList.add(new Hero("孙悟空","急速战靴、破军","战士"));



        Player player01 = new Player("Alex","0101");
        player01.addHero(heroArrayList.get(0));
        player01.addHero(heroArrayList.get(1));
        player01.addHero(heroArrayList.get(2));

        Player player02 = new Player("David","0102");
        player01.addHero(heroArrayList.get(3));
        player01.addHero(heroArrayList.get(4));
        player01.addHero(heroArrayList.get(5));

        playerlist.add(player01);
        playerlist.add(player02);

        Team team1 = new Team("001", "team1");
        team1.addPlayer(player01);


        Team team2 = new Team("002", "team2");
        team2.addPlayer(player02);


        while (running) {
            System.out.println("Welcome to Honor of Kings IMS");
            System.out.println("1. Player Lookup");
            System.out.println("2. Team Overview");
            System.out.println("3. Hero Details");
            System.out.println("4. Equipment Stats");
            System.out.println("5. Exit");

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
                    break;
                case 3:
                    // implement hero details
                    break;
                case 4:
                    // implement equipment stats
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    public static void lookupPlayer(String keyword) {
        for (Player p : playerlist) {
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
}
