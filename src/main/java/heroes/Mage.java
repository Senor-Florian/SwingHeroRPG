package heroes;

import heroes.Hero;

public class Mage extends Hero {

    public Mage(String name, int strength, int dexterity, int intelligence, int constitution, int speed, int perception) {
        super(name, strength, dexterity, intelligence, constitution, speed, perception);
    }

    public String Attack(Hero enemy) {
        // Chance to hit is determined by the attacker's offensive and the defender's defensive rating
        if(getCurrentAP() >= 3) {
            setCurrentAP(getCurrentAP() - 3);
            if(Math.random() <= (getOffensiveRating() - enemy.getDefensiveRating() + 0.4f)) {
                float damage;
                if(Math.random() <= getCriticalChance()) {
                    // Critical damage: (15 - 22.5) * damage modifier
                    damage = getDamageModifier() * (int)(Math.random() * 11 + 5) * 1.5f;
                    enemy.setHealth(enemy.getHealth() - damage);
                    return getName() + " attacked " + enemy.getName() + " for " + damage + " damage.";
                } else {
                    // Normal damage: (10 - 15) * damage modifier
                    damage = getDamageModifier() * (int)(Math.random() * 11 + 5);
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
            // Debuffs the enemy's defensive rating
            enemy.setDefensiveRating(getDefensiveRating() * 0.8f);
            // Can't use spell for the next 2 turns
            setSpellCooldown(3);
            System.out.println(getSpellCooldown());
            return getName() + " debuffed " + enemy.getName()  + "'s defensive rating,";
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
            // Heals the hero for 20 HP
            setHealth(getHealth() + 20);
            return getName() + " healed themself for 20 HP.";
        } else {
            System.out.println("You don't have enough action points to perform this action.");
            return getName() + " doesn't have enough action points to perform this action.";
        }
    }

    @Override
    public String toString() {
        return "heroes.Mage{} " + super.toString();
    }
}
