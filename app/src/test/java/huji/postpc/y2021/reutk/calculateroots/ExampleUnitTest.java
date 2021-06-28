package huji.postpc.y2021.reutk.calculateroots;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {

    @Test
    public void checkingSort(){
        CalculateRootsAdapter adapter = new CalculateRootsAdapter();
        Calculation firstCalc = new Calculation(42);
        Calculation secondCalc = new Calculation(121);
        Calculation thirdCalc = new Calculation(17);
        adapter.addCalc(firstCalc);
        adapter.addCalc(secondCalc);
        adapter.addCalc(thirdCalc);
        Calculation calculation1 = adapter.getCalculations().get(0);
        assertEquals(17, calculation1.getNemToCalc(), 0.0);
        Calculation calculation2 = adapter.getCalculations().get(1);
        assertEquals(42, calculation2.getNemToCalc(), 0.0);
        Calculation calculation3 = adapter.getCalculations().get(2);
        assertEquals(121, calculation3.getNemToCalc(), 0.0);
    }


    @Test
    public void checkingAddCalc(){
        CalculateRootsAdapter adapter = new CalculateRootsAdapter();
        assertEquals(0, adapter.getItemCount());

        Calculation firstCalc = new Calculation(93);
        adapter.addCalc(firstCalc);
        assertEquals(1, adapter.getItemCount());
    }

    @Test
    public void checkingStartInProgress(){
        CalculateRootsAdapter adapter = new CalculateRootsAdapter();
        assertEquals(0, adapter.getItemCount());

        Calculation calc = new Calculation(93);
        adapter.addCalc(calc);
        assertEquals(Calculation.Status.IN_PROGRESS, calc.getStatus());
    }

}