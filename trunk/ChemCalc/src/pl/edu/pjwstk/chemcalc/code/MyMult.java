package pl.edu.pjwstk.chemcalc.code;

public class MyMult {
  public int multiply(int x, int y) {
    // the following is just an example
    if (x > 100) {
      throw new IllegalArgumentException("X should be integer less than 101");
    }
    return x * y;
  }
}