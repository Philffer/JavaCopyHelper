package de.pp.copyhelper;

import org.junit.*;

/**
 * @author Philipp Pfeiffer
 * Testclass for the Pair DS
 */
public class PairTest {

    private Entity e1;
    private Entity e2;

    @BeforeClass
    public static void testStartMessage() {
        System.out.println("Starting tests in PairTest Class");
    }

    @Before
    public void prepareData() {

        System.out.println("Starting tests");

        e1 = new EntityImpl();
        e1.setId(1L);
        e1.setName("Entity 1");
        e1.setDoNotCopy("Don't call it Schnitzel");

        e2 = new EntityImpl();
        e2.setId(2L);
        e2.setName("Entity 2");
        e2.setDoNotCopy("Don't call it Filet");
    }

    @AfterClass
    public static void testEndMessage() {
        System.out.println("Ending tests in PairTest Class");
    }

    @Test
    public void bothPairShouldBeEqualTest() {

        Pair<Entity, Entity> pair1 = new Pair<Entity, Entity>(e1, e2);
        Pair<Entity, Entity> pair2 = new Pair<Entity, Entity>(e1, e2);

        Assert.assertEquals(pair1, pair2);

    }

    @Test
    public void bothPairShouldNotBeEqualTest() {
        Pair<Entity, Entity> pair1 = new Pair<Entity, Entity>(e1, e2);
        Pair<Entity, Entity> pair2 = new Pair<Entity, Entity>(e2, e1);

        Assert.assertNotEquals(pair1, pair2);
    }

    @Test
    public void shouldSetObjectsCorrectTest() {
        Pair<Entity, Entity> pair1 = new Pair<Entity, Entity>(e1, e2);

        Assert.assertEquals(pair1.getFirst(), e1);
        Assert.assertEquals(pair1.getSecond(), e2);
    }

}
