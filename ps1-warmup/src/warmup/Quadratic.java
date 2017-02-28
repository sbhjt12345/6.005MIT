package warmup;

import java.util.HashSet;
import java.util.Set;

public class Quadratic {

    /**
     * Find the integer roots of a quadratic equation, ax^2 + bx + c = 0.
     * @param a coefficient of x^2
     * @param b coefficient of x
     * @param c constant term.  Requires that a, b, and c are not ALL zero.
     * @return all integers x such that ax^2 + bx + c = 0.
     */
    public static Set<Integer> roots(int a, int b, int c) {
    	assert false;
    	Set<Integer> set = new HashSet<>();
    	long  cc = (long)c;
        long delta = b*b - 4*a*cc;
        System.out.println(delta);
        if (delta<0) return set;
        else if (delta == 0) {
        	set.add(-b/(2*a));
        	return set;
        }
        else{
        	int tmp = (-b+ (int)Math.sqrt((double)delta))/(2*a);
        	int tmp2 = (-b- (int)Math.sqrt((double)delta))/(2*a);
        	set.add(tmp);set.add(tmp2);
        }
        return set;
    }

    
    /**
     * Main function of program.
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("For the equation x^2 - 4x + 3 = 0, the possible solutions are:");
        Set<Integer> result = roots(1, 0, -45000*45000);
        System.out.println(result);
    }

    /* Copyright (c) 2016 MIT 6.005 course staff, all rights reserved.
     * Redistribution of original or derived work requires explicit permission.
     * Don't post any of this code on the web or to a public Github repository.
     */
}
