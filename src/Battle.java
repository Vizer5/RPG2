import java.util.Random;

public class Battle extends Thread {
    Player player;
    Person monster;
    Person winBattle;


    public Battle(Player player) {
        this.player = player;
        this.monster = choiceMonster();
    }

    Person choiceMonster() {
        if (new Random().nextInt(2) == 0) return new Skeleton(player);
        else return new Goblin(player);
    }

    void startBattle() {
        while (true) {
            player.attack(monster);
            if (checkPerson(monster)) {
                winBattle = player;
                break;
            }
            monster.attack(player);
            if (checkPerson(player)) {
                winBattle = monster;
                break;
            }
        }
        System.out.println("Бой окончен");
        System.out.println("Выиграл " + winBattle.name);
    }

    boolean checkPerson(Person person) {
        return person.hp <= 0;
    }

    @Override
    public void run() {
        startBattle();
        if (winBattle.name.equals(player.name)) player.playerWin();
        else player.playerLooser();
    }


}
