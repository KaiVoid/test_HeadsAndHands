package test;

import com.kaivoid.Creature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CreatureTest {

    @Test
    void createTestWithWrongName() {
        Assertions.assertThrows(Exception.class, () -> new Creature(null, 4, 4, 4, 4, 4) {
        });
    }

    @Test
    void createTestWithWrongAttack() {
        for (int i = -100; i != 100; i++) {
            int finalI = i;
            if (i > 0 && i <= 30) {
                Assertions.assertDoesNotThrow(() -> new Creature("testName", finalI, 4, 4, 4, 4) {
                });
            } else {
                Assertions.assertThrows(Exception.class, () -> new Creature("testName", finalI, 4, 4, 4, 4) {
                });
            }
        }
    }

    @Test
    void createTestWithWrongProtection() {
        for (int i = -100; i != 100; i++) {
            int finalI = i;
            if (i > 0 && i <= 30) {
                Assertions.assertDoesNotThrow(() -> new Creature("testName", 5, finalI, 4, 4, 4) {
                });
            } else {
                Assertions.assertThrows(Exception.class, () -> new Creature("testName", 5, finalI, 4, 4, 4) {
                });
            }
        }
    }

    @Test
    void createTestWithWrongDamageMin() {
        int damageMax = 40;
        for (int i = -100; i != 100; i++) {
            int finalI = i;
            if (i > 0 && i <= damageMax) {
                Assertions.assertDoesNotThrow(() -> new Creature("testName", 5, 5, finalI, damageMax, 4) {
                });
            } else {
                Assertions.assertThrows(Exception.class, () -> new Creature("testName", 5, 5, finalI, damageMax, 4) {
                });
            }
        }
    }

    @Test
    void createTestWithWrongDamageMax() {
        int damageMin = 4;
        for (int i = -100; i != 100; i++) {
            int finalI = i;
            if (i >= damageMin) {
                Assertions.assertDoesNotThrow(() -> new Creature("testName", 5, 5, damageMin, finalI, 4) {
                });
            } else {
                Assertions.assertThrows(Exception.class, () -> new Creature("testName", 5, 5, damageMin, finalI, 4) {
                });
            }
        }
    }

    @Test
    void createTestWithWrongHealthMax() {
        for (int i = -100; i != 100; i++) {
            int finalI = i;
            if (i > 0) {
                Assertions.assertDoesNotThrow(() -> new Creature("testName", 5, 5, 2, 4, finalI) {
                });
            } else {
                Assertions.assertThrows(Exception.class, () -> new Creature("testName", 5, 5, 2, 4, finalI) {
                });
            }
        }
    }

    @Test
    void isAliveTest() throws Exception {
        Creature creature = new Creature("testName", 5, 1, 2, 4, 10) {
        };
        Creature damager = new Creature("damager", 30, 5, 9, 10, 10) {
        };
        Assertions.assertTrue(creature.isAlive());
        for (int i = 0; i < 20; i++) {
            damager.hitEnemy(creature);
        }
        Assertions.assertFalse(creature.isAlive());
    }
}