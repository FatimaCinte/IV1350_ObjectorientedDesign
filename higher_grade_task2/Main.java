package higher_grade_task2;

import java.util.Random;

public class Main {  
    //private static final long SEED  = 0x0123456789ABCDEFL;
    private static final long SEED  = 0xFFFFFFFFFFFFFFFFL;

    //private static final long SEED2  = 0x456789ABCDEFL;

    public static void main(String[] args) {
        Composition compositionRandomGenerator = new Composition(SEED);
        Inheritance inheritanceRandomGenerator = new Inheritance();

        inheritanceRandomGenerator.setSeed(SEED);

        int nextCompositeNumber = compositionRandomGenerator.nextInt();
        System.out.println(nextCompositeNumber);

        int nextInheritanceNumber = inheritanceRandomGenerator.nextInt();
        System.out.println(nextInheritanceNumber);  
    }
}
