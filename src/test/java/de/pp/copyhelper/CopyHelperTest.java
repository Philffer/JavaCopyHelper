package de.pp.copyhelper;

import org.junit.*;

/**
 * @author Philipp Pfeiffer
 * Testclass for the CopyHelper
 */
public class CopyHelperTest {

    //Interfaces
    private Entity e1;
    private Entity e2;
    private EntityChild e3;

    //Classes
    private EntityChild2 e4;
    private EntityImpl e1Impl;
    private EntityImpl e2Impl;
    private EntityChildImpl e3Impl;

    @BeforeClass
    public static void testStartMessage() {
        System.out.println("Starting tests in PairTest Class");
    }

    @AfterClass
    public static void testEndMessage() {
        System.out.println("Ending tests in PairTest Class");
    }

    @Before
    public void prepareData() {

        System.out.println("Starting tests");

        e1 = new EntityImpl();
        e1.setId(1L);
        e1.setName("Entity 1");
        e1.setDoNotCopy("Don't do it");

        e2 = new EntityImpl();

        e3 = new EntityChildImpl();

        e4 = new EntityChild2();

        e1Impl = new EntityImpl();
        e1Impl.setId(1L);
        e1Impl.setName("Entity 1");
        e1.setDoNotCopy("Don't do it");

        e2Impl = new EntityImpl();

        e3Impl = new EntityChildImpl();

    }

    @Test
    public void shouldCopyValuesInterfaceTest() throws CopyHelperException {
        CopyHelper.copyData(Entity.class, Entity.class, e1, e2);

        Assert.assertEquals(e1.getId(), e2.getId());
        Assert.assertEquals(e1.getName(), e2.getName());

    }

    @Test
    public void shouldNotCopyValueWithNoCopyAnnoationInterfaceTest() throws CopyHelperException {
        CopyHelper.copyData(Entity.class, Entity.class, e1, e2);

        Assert.assertNull(e2.getDoNotCopy());
    }

    @Test
    public void shouldCopyValuesToChildWithCastInterfaceTest() throws CopyHelperException {
        CopyHelper.copyData(Entity.class, Entity.class, e1, e4);

        Assert.assertEquals(e1.getId(), ((Entity) e4).getId());
        Assert.assertEquals(e1.getName(), ((Entity) e4).getName());
        Assert.assertNull(((Entity) e4).getDoNotCopy());
        Assert.assertNull(e4.getEntityChild2Specific());
    }

    @Test
    public void shouldCopyValuesToChildWithoutCastInterfaceTest() throws CopyHelperException {
        CopyHelper.copyData(Entity.class, EntityChild.class, e1, e3);

        Assert.assertEquals(e1.getId(), e3.getId());
        Assert.assertEquals(e1.getName(), e3.getName());
        Assert.assertNull(e3.getDoNotCopy());
        Assert.assertNull(e3.getChildSpecific());
    }

    @Test
    public void shouldCopyValuesClassTest() throws CopyHelperException {
        CopyHelper.copyData(EntityImpl.class, EntityImpl.class, e1Impl, e2Impl);

        Assert.assertEquals(e1Impl.getId(), e2Impl.getId());
        Assert.assertEquals(e1Impl.getName(), e2Impl.getName());
    }

    @Test
    public void shouldNotCopyValueWithNoCopyAnnoationClassTest() throws CopyHelperException {
        CopyHelper.copyData(EntityImpl.class, EntityImpl.class, e1Impl, e2Impl);

        Assert.assertNull(e2Impl.getDoNotCopy());
    }

    @Test
    public void shouldCopyValuesToChildClassTest() throws CopyHelperException {
        CopyHelper.copyData(EntityImpl.class, EntityChild2.class, e1Impl, e4);

        Assert.assertEquals(e1Impl.getId(), e4.getId());
        Assert.assertEquals(e1Impl.getName(), e4.getName());
        Assert.assertNull(e4.getDoNotCopy());
        Assert.assertNull(e4.getEntityChild2Specific());
    }

}
