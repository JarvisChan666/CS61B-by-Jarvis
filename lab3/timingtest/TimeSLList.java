package timingtest;
//import edu.princeton.cs.algs4.Stopwatch;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        SLList<Integer> Ns = new SLList<Integer>();
        AList<Double> time = new AList<Double>();
        AList<Integer> opCount = new AList<Integer>();

        AList<Integer> Ns1 = new AList<Integer>();


        /**
         * we want to figure out if the SLList size can cause more time
         */

        for (int i = 1; i < 1000; i++) {
            // Ns: how many items
            //time:total time
            // ops: the number of calls to addLast
            Ns.addLast(i);
            Ns1.addLast(i);
            int m = 10000;
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < m; j++) {
                Ns.getLast();
                opCount.addLast(m);
            }
            double timeInSeconds = sw.elapsedTime();
            time.addLast(timeInSeconds);
        }


        printTimingTable(Ns1, time, opCount);
    }

}
