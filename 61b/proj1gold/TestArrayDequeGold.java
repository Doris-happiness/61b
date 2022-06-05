import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    StudentArrayDeque<Integer> stu = new StudentArrayDeque<>();
    ArrayDequeSolution<Integer> sol = new ArrayDequeSolution<>();


    @Test
    public void testAddFirst() {
        for (int i = 0; i < 100; i += 1) {
            Integer number = StdRandom.uniform(0, 100);
            stu.addFirst(number);
            sol.addFirst(number);
            Integer stuAns = stu.get(0);
            Integer solAns = sol.getRecursive(0);
            assertEquals(solAns, stuAns);
        }
    }

    @Test
    public void testAddLast() {
        for (int i = 0; i < 100; i += 1) {
            Integer number = StdRandom.uniform(0, 100);
            stu.addLast(number);
            sol.addLast(number);
            Integer stuAns = stu.get(i);
            Integer solAns = sol.getRecursive(i);
            assertEquals(solAns, stuAns);
        }
    }

    @Test
    public void testRemoveFirst() {
        for (int i = 0; i < 100; i += 1) {
            Integer number = StdRandom.uniform(0, 100);
            stu.addLast(number);
            sol.addLast(number);
        }
        for (int j = 0; j < 100; j++) {
            Integer stuAns = stu.removeFirst();
            Integer solAns = sol.removeFirst();
            //assertEquals(solAns, stuAns);
            assertEquals("removeFirst()\n " + stuAns
                            + " not equal to " + solAns + "!",
                    solAns, stuAns);
        }
    }


    @Test
    public void testRemoveLast() {
        while (true) {
            for (int i = 0; i < 10; i += 1) {
                Integer number = StdRandom.uniform(0, 100);
                stu.addLast(number);
                sol.addLast(number);

            }
            for (int j = 0; j < 10; j++) {
                Integer stuAns = stu.removeLast();
                Integer solAns = sol.removeLast();
                assertEquals("removeLast()\n " + stuAns
                                + " not equal to " + solAns + "!",
                        solAns, stuAns);
            }
        }

    }


}
