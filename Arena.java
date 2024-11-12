import java.util.Random;

class Player {
    int health;
    int strength;
    int attack;

    Player(int health, int strength, int attack) {
        this.health = health;
        this.strength = strength;
        this.attack = attack;
    }

    int rollAttackDice() {
        return new Random().nextInt(6) + 1;  // Random roll between 1 and 6
    }

    int rollDefenseDice() {
        return new Random().nextInt(6) + 1;  // Random roll between 1 and 6
    }

    boolean isAlive() {
        return health > 0;
    }
}

class Arena {
    Player playerA;
    Player playerB;

    Arena(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    void startMatch() {
        Player attacker, defender;
        
        if (playerA.health < playerB.health) {
            attacker = playerA;
            defender = playerB;
        } else {
            attacker = playerB;
            defender = playerA;
        }

        while (attacker.isAlive() && defender.isAlive()) {
            takeTurn(attacker, defender);
            
            // Swap roles
            Player temp = attacker;
            attacker = defender;
            defender = temp;
        }

        System.out.println((attacker.isAlive() ? "Attacker" : "Defender") + " wins the match!");
    }

    void takeTurn(Player attacker, Player defender) {
        int attackRoll = attacker.rollAttackDice();
        int defenseRoll = defender.rollDefenseDice();

        int damage = attacker.attack * attackRoll;
        int defended = defender.strength * defenseRoll;
        int netDamage = Math.max(damage - defended, 0);

        defender.health -= netDamage;

        System.out.println("Attacker dealt " + netDamage + " damage. Defender's health is now " + defender.health);
    }
}

public class Main {
    public static void main(String[] args) {
        Player playerA = new Player(50, 5, 10);
        Player playerB = new Player(100, 10, 5);
        
        Arena arena = new Arena(playerA, playerB);
        arena.startMatch();
    }
}
