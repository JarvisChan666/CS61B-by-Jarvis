package timingtest;
//import edu.princeton.cs.algs4.Stopwatch;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        /**
         * use Stopwatch class and printTiming table methods.
         *
         * use "addList" method from AList to create "N, time and opCount"
         */
        AList<Integer> Ns = new AList<Integer>();
        AList<Double> time = new AList<Double>();
        AList<Integer> opCount = new AList<Integer>();
        Stopwatch sw = new Stopwatch();
        for (int i = 1; i < 100000; i++) {
            // Ns: how many items
            //time:total time
            Ns.addLast(i);
            double timeInSeconds = sw.elapsedTime();
            time.addLast(timeInSeconds);
            opCount.addLast(i);
        }
        printTimingTable(Ns, time, opCount);
    }
}
