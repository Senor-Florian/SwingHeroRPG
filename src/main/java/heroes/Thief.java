package heroes;

import heroes.Hero;

public class Thief extends Hero {

    public Thief(String name, int strength, int dexterity, int intelligence, int constitution, int speed, int perception) {
        super(name, strength, dexterity, intelligence, constitution, speed, perception);
    }

    public String Attack(Hero enemy) {
        // Chance to hit is determined by the attacker's offensive and the defender's defensive rating
        if(getCurrentAP() >= 3) {
            setCurrentAP(getCurrentAP() - 3);
            if(Math.random() <= (getOffensiveRating() - enemy.getDefensiveRating() + 0.6f)) {
                float damage;
                if(Math.random() <= getCriticalChance()) {
                    // Critical damage: (15 - 30) * damage modifier
                    damage = getDamageModifier() * (int)(Math.random() * 6 + 5) * 3;
                    enemy.setHealth(enemy.getHealth() - damage);
                    return getName() + " attacked " + enemy.getName() + " for " + damage + " damage.";
                } else {
                    // Normal damage: (5 - 10) * damage modifier
                    damage = getDamageModifier() * (int)(Math.random() * 6 + 5);
                    enemy.setHealth(enemy.getHealth() - damage);
                    return getName() + " attacked " + enemy.getName() + " for " + damage + " damage.";
                }
            } else {
                System.out.println("You missed.");
                return getName() + " missed.";
            }
        } else {
            System.out.println("You don't have enough action points to perform this action.");
            return getName() + " doesn't have enough action points to perform this action.";
        }

    }

    public String Spell(Hero enemy) {
        if(getCurrentAP() >= 6 && getSpellCooldown() == 0) {
            setCurrentAP(getCurrentAP() - 6);
            // Does -15 HP worth of damage to the enemy
            enemy.setHealth(enemy.getHealth() - 15);
            // Can't use spell for the next 2 turns
            setSpellCooldown(3);
            System.out.println(getSpellCooldown());
            return getName() + " poisoned " + enemy.getName()  + " for 15 damage.";
        } else if(getSpellCooldown() > 0) {
            System.out.println(getSpellCooldown());
            return getName() + "'s spell is still in cooldown.";
        } else {
            System.out.println(getSpellCooldown());
            System.out.println("You don't have enough action points to perform this action.");
            return getName() + " doesn't have enough action points to perform this action.";
        }

    }

    public String Heal() {
        if(getCurrentAP() >= 5) {
            setCurrentAP(getCurrentAP() - 5);
            // Heals the hero for 15 HP
            setHealth(getHealth() + 15);
            return getName() + " healed themself for 15 HP.";
        } else {
            System.out.println("You don't have enough action points to perform this action.");
            return getName() + " doesn't have enough action points to perform this action.";
        }
    }

    @Override
    public String toString() {
        return "heroes.Thief{} " + super.toString();
    }
}

