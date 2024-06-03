package higher_grade_task2;

import java.util.Random;

public class Inheritance extends Random {
    private long seed;
    private static final long multiplier = 0x5DEECE66DL;
    private static final long addend = 0xBL;
    private static final long mask = 0xFFFFFFFFFFFFFFFFL;

    public Inheritance(){ 
    }

    @Override
    public void setSeed(long seed){
        this.seed = (seed ^ multiplier) & mask;
    }

    @Override
    public int nextInt(){
        long oldseed, nextseed;
        
        oldseed = seed;
        nextseed = (oldseed * multiplier + addend) & mask;
        seed = nextseed;

        return (int)(nextseed >>> 32);

    }

}
