package higher_grade_task2;

import java.util.Random;

public class Composition {
    private Random generator;
    private int nextInt;

    public Composition(long seed){
        generator = new Random(seed);
    }

    public int nextInt(){
        nextInt = generator.nextInt();
        return nextInt;
    }
}
