package com.comp301.a09akari.model;

public class PuzzleImpl implements Puzzle {
  int[][] board;

  public PuzzleImpl(int[][] board) {
    this.board = board;
  }

  @Override
  public int getWidth() {
    return board[0].length;
  }

  @Override
  public int getHeight() {
    return board.length;
  }

  @Override
  public CellType getCellType(int r, int c) {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds");
    }
    if (board[r][c] == 0) {
      return CellType.CLUE;
    } else if (board[r][c] == 1) {
      return CellType.CLUE;
    } else if (board[r][c] == 2) {
      return CellType.CLUE;
    } else if (board[r][c] == 3) {
      return CellType.CLUE;
    } else if (board[r][c] == 4) {
      return CellType.CLUE;
    } else if (board[r][c] == 5) {
      return CellType.WALL;
    } else if (board[r][c] == 6) {
      return CellType.CORRIDOR;
    } else {
      return null;
    }
  }

  @Override
  public int getClue(int r, int c) {
    if (r < 0 || r >= getHeight() || c < 0 || c >= getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds");
    }
    if (this.getCellType(r, c) == CellType.CLUE) {
      if (board[r][c] == 0) {
        return 0;
      }
      if (board[r][c] == 1) {
        return 1;
      }
      if (board[r][c] == 2) {
        return 2;
      }
      if (board[r][c] == 3) {
        return 3;
      }
      if (board[r][c] == 4) {
        return 4;
      }
    }
    throw new IllegalArgumentException("Cell type is not clue");
  }
}
