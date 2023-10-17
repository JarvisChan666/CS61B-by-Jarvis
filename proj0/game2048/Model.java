package game2048;

import java.util.Formatter;
import java.util.Observable;

/**
 * The state of a game of 2048.
 *
 * @author TODO: YOUR NAME HERE
 */
public class Model extends Observable {

  /** Current contents of the board. */
  private Board board;

  /** Current score. */
  private int score;

  /** Maximum score so far. Updated when game ends. */
  private int maxScore;

  /** True iff game is ended. */
  private boolean gameOver;



  /* Coordinate System: column C, row R of the board (where row 0,
   * column 0 is the lower-left corner of the board) will correspond
   * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
   */

  /** Largest piece value. */
  public static final int MAX_PIECE = 2048;

  /** A new 2048 game on a board of size SIZE with no pieces and score 0. */
  public Model(int size) {
    board = new Board(size);
    score = maxScore = 0;
    gameOver = false;
  }

  /**
   * A new 2048 game where RAWVALUES contain the values of the tiles (0 if null). VALUES is indexed
   * by (row, col) with (0, 0) corresponding to the bottom-left corner. Used for testing purposes.
   */
  public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
    int size = rawValues.length;
    board = new Board(rawValues, score);
    this.score = score;
    this.maxScore = maxScore;
    this.gameOver = gameOver;
  }

  /**
   * Return the current Tile at (COL, ROW), where 0 <= ROW < size(), 0 <= COL < size(). Returns null
   * if there is no tile there. Used for testing. Should be deprecated and removed.
   */
  public Tile tile(int col, int row) {
    return board.tile(col, row);
  }

  /**
   * Return the number of squares on one side of the board. Used for testing. Should be deprecated
   * and removed.
   */
  public int size() {
    return board.size();
  }

  /**
   * Return true iff the game is over (there are no moves, or there is a tile with value 2048 on the
   * board).
   */
  public boolean gameOver() {
    checkGameOver();
    if (gameOver) {
      maxScore = Math.max(score, maxScore);
    }
    return gameOver;
  }

  /** Return the current score. */
  public int score() {
    return score;
  }

  /** Return the current maximum game score (updated at end of game). */
  public int maxScore() {
    return maxScore;
  }

  /** Clear the board to empty and reset the score. */
  public void clear() {
    score = 0;
    gameOver = false;
    board.clear();
    setChanged();
  }

  /** Add TILE to the board. There must be no Tile currently at the same position. */
  public void addTile(Tile tile) {
    board.addTile(tile);
    checkGameOver();
    setChanged();
  }

  /**
   * Tilt the board toward SIDE. Return true iff this changes the board.
   *
   * <p>1. If two Tile objects are adjacent in the direction of motion and have the same value, they
   * are merged into one Tile of twice the original value and that new value is added to the score
   * instance variable 2. A tile that is the result of a merge will not merge again on that tilt. So
   * each move, every tile will only ever be part of at most one merge (perhaps zero). 3. When three
   * adjacent tiles in the direction of motion have the same value, then the leading two tiles in
   * the direction of motion merge, and the trailing tile does not.
   */
  int where = 0;

  public Tile findTheNearest(Tile t) {

    for (int i = t.row() + 1; i <= 3; i++) {
      Tile t1 = board.tile(t.col(), i);
      if (t1 != null)
        return board.tile(t.col(), i);
      //where means how many zero in front of me
      where++;//
    }
    return null;//if return null that mean front is all 0
  }
  public boolean tilt(Side side) {

    boolean changed;
    changed = false;

    // TODO: Modify this.board (and perhaps this.score) to account
    // for the tilt to the Side SIDE. If the board changed, set the
    // changed local variable to true.
    // we have finished

    // write the up direction first
    // start from row 3 the top, col 0
    this.board.setViewingPerspective(side);

    //helper methods: find the nearest
    //find the nearest null tile and return


    for (int j = 0; j < 4; j++) {
      where = 0;//reset where
      boolean isMerged = false;
      for (int i = 2; i >= 0; i--) {
        Tile t = board.tile(j, i);
        //
        // the 3 row won't change
        // each "not null" tile compare value with front "not null" tile's value
        // if it is different, and find the nearest zero move to--
        // --(don't need to move the front tile because it has been moved)
        // if it is same and not null, merge together and find the nearest
        // if the nearest still the same, then merge

        //(the following maybe not true)
        // if the row 3 has 0 null, all value just move:
        // if the value can be merged, then all in row 3,
        // else they just move x - y, "x" is how many block in the front
        // and "y" is how many existing tilt in the front

        // if there has value in row 3, then just see the nearest one
        // if same, merge and merge to row 3.if no merge, same code as common move
        if (t != null) {
          Tile nearest = findTheNearest(t);
          if (nearest == null) {
            board.move(j, 3, t);// move to the top
            changed = true;
            continue;
          }
            // we can't store the location
            // if this is the first tile in a col
            //now the problem is how to find the nearest not null tile
          if(nearest.value() == t.value() && where <= 2 && isMerged == false) {
            board.move(j, nearest.row(), t);
            score += board.tile(j, nearest.row()).value();
            changed = true;
            isMerged = true;
            // 2/2/4/4 -> /4/8/0/0
            // 0/2/2/4 -> //4/4/0
            // 2/2/4/0 ->
            // 2/2/0/4
            // check t.row - 1  if same as score the pass one
            // will t change if merged
            Tile tNext = board.tile(j, i - 1);
            if(tNext != null)
              if (score == tNext.value()) {
                //just move it, and definitely the third one will move to row 2
                board.move(j, 2, tNext);
              }
            continue;
          }
          //  eg: /2/0/0/4   col
          //  eg: /2/2/4/4 -> /4/
          //when you successfully merged, you can't merged again
          // not zero and not the same
          board.move(j, nearest.row() - 1, t);
          changed = true;
//          for (int k = i; k >=0; k--) {
//            Tile t1 = board.tile(j, k);
//            if (t1 != null) {
//              //merge
//              board.move(j, k, t);
//              score += board.tile(j, k).value();
//              //then find the nearest, and we can find that we use 2 times "find the nearest"
//              //so we need a helper methods called "FIND THE NEAREST" return nearest tile
//            }
//          }
        }
      }
    }

    checkGameOver();

    if (changed) {
      setChanged();
    }
    return changed;
  }

  /** Checks if the game is over and sets the gameOver variable appropriately. */
  private void checkGameOver() {
    gameOver = checkGameOver(board);
  }

  /** Determine whether game is over. */
  private static boolean checkGameOver(Board b) {
    return maxTileExists(b) || !atLeastOneMoveExists(b);
  }

  /**
   * Returns true if at least one space on the Board is empty. Empty spaces are stored as null. Only
   * use "tile() and size() methods in Board class" if b.tile (i, j) has a 0, then return true
   */
  public static boolean emptySpaceExists(Board b) {
    for (int i = 0; i < b.size(); i++) {
      for (int j = 0; j < b.size(); j++) {
        if (b.tile(i, j) == null) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * Returns true if any tile is equal to the maximum valid value. Maximum valid value is given by
   * MAX_PIECE. Note that given a Tile object t, we get its value with t.value().
   */
  public static boolean maxTileExists(Board b) {
    // TODO: Fill in this function.
    for (int i = 0; i < b.size(); i++) {
      for (int j = 0; j < b.size(); j++) {
        if (b.tile(i, j) != null) {
          if (b.tile(i, j).value() == MAX_PIECE) {
            return true;
          }
        }
      }
    }
    return false;
  }

  /**
   * Returns true if there are any valid moves on the board. There are two ways that there can be
   * valid moves: 1. There is at least one empty space on the board. 2. There are two adjacent tiles
   * with the same value. 3. We can use
   */
  public static boolean atLeastOneMoveExists(Board b) {
    // TODO: Fill in this function.
    // 1. if at least one empty space exist, then return true
    // 2. write a function to try all the move/same value adjacent, if can, return true
    // we can't move two that are not adjacent
    if (emptySpaceExists(b)) {
      return true;
    }
    // if two adjacent not the same, if you use move it will bug error
    // so we need to use tile.value to compare two value
    else {

      for (int i = 0; i < b.size(); i++) { // col
        for (int j = 0; j < b.size(); j++) {
          // row
          // need to compare next col and next row, if row==3, reloop
          // how to compare tile(i + 1, j) and tile(i, j+ 1)
          if ((i == b.size() - 1 && j == b.size() - 1)) {
            return false;
          }
          if (i == b.size() - 1) {
            if (b.tile(i, j).value() == b.tile(i, j + 1).value()) {
              return true;
            }
          }
          if (j == b.size() - 1) { // the last row compare with right handside
            if (b.tile(i, j).value() == b.tile(i + 1, j).value()) {
              return true;
            } else {
              continue;
            }
          }
          if (i < b.size() - 1) {
            if ((b.tile(i, j).value() == b.tile(i + 1, j).value()
                || b.tile(i, j).value() == b.tile(i, j + 1).value())) {
              return true;
            }
          }
        }
      }
      // if the last one is iterated, then return false
      return false;
    }
  }

  @Override
  /** Returns the model as a string, used for debugging. */
  public String toString() {
    Formatter out = new Formatter();
    out.format("%n[%n");
    for (int row = size() - 1; row >= 0; row -= 1) {
      for (int col = 0; col < size(); col += 1) {
        if (tile(col, row) == null) {
          out.format("|    ");
        } else {
          out.format("|%4d", tile(col, row).value());
        }
      }
      out.format("|%n");
    }
    String over = gameOver() ? "over" : "not over";
    out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
    return out.toString();
  }

  @Override
  /** Returns whether two models are equal. */
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    } else if (getClass() != o.getClass()) {
      return false;
    } else {
      return toString().equals(o.toString());
    }
  }

  @Override
  /** Returns hash code of Model’s string. */
  public int hashCode() {
    return toString().hashCode();
  }
}
