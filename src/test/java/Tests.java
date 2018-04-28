import heroes.Hero;
import heroes.Warrior;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Tests {

    Hero hero = new Warrior("TestHero", 8, 6, 7, 8, 5, 6);

    @Test
    public void nameTest() {
        assertEquals("TestHero", hero.getName());
    }

    @Test
    public void healthTest() {
        assertEquals(90f, hero.getHealth(), 0.001);
    }

    @Test
    public void offensiveRatingTest() {
        assertEquals(1.2f, hero.getOffensiveRating(), 0.001);
    }

    @Test
    public void defensiveRatingTest() {
        assertEquals(1.1f, hero.getDefensiveRating(), 0.001);
    }

    @Test
    public void startAPTest() {
        assertEquals(7, hero.getStartAP());
    }

    @Test
    public void turnAPTest() {
        assertEquals(6, hero.getTurnAP());
    }

    @Test
    public void criticalChanceTest() {
        assertEquals(0.32f, hero.getCriticalChance(), 0.001);
    }

    @Test
    public void damageModifierTest() {
        assertEquals(1.3f, hero.getDamageModifier(), 0.001);
    }
}
