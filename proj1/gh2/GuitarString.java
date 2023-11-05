package gh2;

import deque.Deque;
import deque.ArrayDeque;

// import deque.Deque;

// Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
  /**
   * Constants. Do not change. In case you're curious, the keyword final means the values cannot be
   * changed at runtime. We'll discuss this and other topics in lecture on Friday.
   */
  private static final int SR = 44100; // Sampling Rate

  private static final double DECAY = .996; // energy decay factor

  private Deque<Double> samples;
  private int capacity;

  /* Buffer for storing sound data. */
  // private Deque<Double> buffer;

  /* Create a guitar string of the given frequency.  */
  public GuitarString(double frequency) {
    capacity = (int) Math.round(SR / frequency);
    samples = new ArrayDeque<>();
    pluck();
  }

  /* Pluck the guitar string by replacing the buffer with white noise. */
  public void pluck() {
    //       Make sure that your random numbers are different from each
    //       other. This does not mean that you need to check that the numbers
    //       are different from each other. It means you should repeatedly call
    //       Math.random() - 0.5 to generate new random numbers for each array index.
    samples = new ArrayDeque<>();
    for (int i = 0; i < capacity; i++) {
      samples.addFirst(Math.random() - 0.5);
    }
  }

  /* Advance the simulation one time step by performing one iteration of
   * the Karplus-Strong algorithm.
   */
  public void tic() {
    if (samples.size() <= 1) {
      return;
    }
    double frontSample = samples.removeFirst();
    double nextSample = samples.getFirst();
    double newSample = 0.996 * 0.5 * (frontSample + nextSample);
    samples.addLast(newSample);
  }

  /* Return the double at the front of the buffer. */
  public double sample() {
    if (samples.isEmpty()) {
      return 0.0;
    }

    return samples.getFirst();
  }
}
    // TODO: Remove all comments that say TODO when you're done.
