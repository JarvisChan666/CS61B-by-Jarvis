package IntList;

public class IntListExercises {

    /**
     * Part A: (Buggy) mutative method that adds a constant C to each
     * element of an IntList
     *
     * @param lst IntList from Lecture
     */
    public static void addConstant(IntList lst, int c) {
        /** bug 1 use dummy to solve*/
        IntList dummy = new IntList(1, lst);
        IntList head = lst;
        while (dummy.rest != null) {
            head.first += c;
            head = head.rest;
            dummy = dummy.rest;
        }
    }

    /**
     * Part B: Buggy method that sets node.first to zero if
     * the max value in the list starting at node has the same
     * first and last digit, for every node in L
     *
     * @param L IntList from Lecture
     */
    public static void setToZeroIfMaxFEL(IntList L) {
        IntList p = L;
        while (p != null) {
            int currentMax = max(p);
            boolean firstEqualsLast = firstDigitEqualsLastDigit(currentMax);
            if (firstEqualsLast) {
                p.first = 0;
            }
            p = p.rest;
        }
    }

    /** Returns the max value in the IntList starting at L. */
    public static int max(IntList L) {
        int max = L.first;
        IntList p = L.rest;
        while (p != null) {
            if (p.first > max) {
                max = p.first;
            }
            p = p.rest;
        }
        return max;
    }

    /** Returns true if the last digit of x is equal to
     *  the first digit of x.
     */
    public static boolean firstDigitEqualsLastDigit(int x) {
        int lastDigit = x % 10;
        /** bug >= */
        while (x >= 10) {
            x = x / 10;
        }
        int firstDigit = x % 10;
        return firstDigit == lastDigit;


    }

    /**
     * Part C: (Buggy) mutative method that squares each prime
     * element of the IntList.
     *
     * @param lst IntList from Lecture
     * @return True if there was an update to the list
     */

    public static boolean squarePrimes(IntList lst) {
        IntList dummy = new IntList(0, lst);
        IntList p = lst;


        int arr[] = new int[lst.size()];
        for (int i = 0; i < lst.size(); i++) {
            arr[i] = lst.get(i);
        }
        IntList prev = IntList.of(arr);

        /** bug: no while loop to do iteration
         * solution 1:
         *  we can make a comparison
         *  if the new lst is not equal to the previous one
         *  then return true, that means changed = true
         *
         *  do a iteration , get all lst value to a int array
         *  and then use "of(x, x, x)"method to create prev
         * */
        while(dummy.rest != null) {
            // Base Case: we have reached the end of the list
            if (lst == null) {
                return false;
            }

            boolean currElemIsPrime = Primes.isPrime(p.first);

            if (currElemIsPrime) {
                p.first *= p.first;
            }
            p = p.rest;
            dummy = dummy.rest;
        }
        if (prev.rest != lst) {
            return true;
        } return false;

    }
}

