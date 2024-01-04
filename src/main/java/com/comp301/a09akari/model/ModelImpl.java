package com.comp301.a09akari.model;

import java.util.ArrayList;
import java.util.List;

public class ModelImpl implements Model {
  private final PuzzleLibrary puzzles;
  private final List<ModelObserver> modelObservers;
  private boolean[][] lamps;
  private int activePuzzleIndex;
  private Puzzle puzzle;

  public ModelImpl(PuzzleLibrary library) {
    this.puzzles = library;
    this.activePuzzleIndex = 0;
    this.puzzle = library.getPuzzle(0);
    this.lamps = new boolean[puzzle.getHeight()][puzzle.getWidth()];
    this.modelObservers = new ArrayList<>();
    // Your constructor code here
  }

  @Override
  public void addLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds");
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("cell type is not corridor");
    }
    lamps[r][c] = true;
    notifyObservers(this);
  }

  @Override
  public void removeLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds");
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("cell type is not corridor");
    }
    lamps[r][c] = false;
    notifyObservers(this);
  }

  @Override
  public boolean isLit(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds");
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("cell type is not corridor");
    }

    // moving right
    for (int j = c; j < puzzle.getWidth(); j++) {
      if (lamps[r][j]) {
        return true;
      } else if (puzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    // going left
    for (int j = c; j >= 0; j--) {
      if (lamps[r][j]) {
        return true;
      } else if (puzzle.getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }
    for (int j = r; j < puzzle.getHeight(); j++) {
      if (lamps[j][c]) {
        return true;
      } else if (puzzle.getCellType(j, c) != CellType.CORRIDOR) {
        break;
      }
    }

    for (int j = r; j >= 0; j--) {
      if (lamps[j][c]) {
        return true;
      } else if (puzzle.getCellType(j, c) != CellType.CORRIDOR) {
        break;
      }
    }
    return false;
  }

  @Override
  public boolean isLamp(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds");
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CORRIDOR) {
      throw new IllegalArgumentException("cell type is not corridor");
    }

    return lamps[r][c];
  }

  @Override
  public boolean isLampIllegal(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds.");
    }
    if (!lamps[r][c]) {
      throw new IllegalArgumentException("cell doesn't contain a lamp.");
    }

    // moving right
    for (int j = c + 1; j < getActivePuzzle().getWidth(); j++) {
      if (lamps[r][j]) {
        return true;
      } else if (this.getActivePuzzle().getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }

    // moving left
    for (int j = c - 1; j >= 0; j--) {
      if (lamps[r][j]) {
        return true;
      } else if (this.getActivePuzzle().getCellType(r, j) != CellType.CORRIDOR) {
        break;
      }
    }

    // moving up
    for (int i = r + 1; i < getActivePuzzle().getHeight(); i++) {
      if (lamps[i][c]) {
        return true;
      } else if (this.getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }

    // moving down
    for (int i = r - 1; i >= 0; i--) {
      if (lamps[i][c]) {
        return true;
      } else if (this.getActivePuzzle().getCellType(i, c) != CellType.CORRIDOR) {
        break;
      }
    }

    return false;
  }

  @Override
  public Puzzle getActivePuzzle() {
    return puzzles.getPuzzle(activePuzzleIndex);
  }

  @Override
  public int getActivePuzzleIndex() {
    return activePuzzleIndex;
  }

  @Override
  public void setActivePuzzleIndex(int index) {
    if (index < 0 || index >= getPuzzleLibrarySize()) {
      throw new IndexOutOfBoundsException("index is out of bounds");
    }
    this.activePuzzleIndex = index;
    this.puzzle = puzzles.getPuzzle(index);
    this.lamps = new boolean[puzzle.getHeight()][puzzle.getWidth()];
    notifyObservers(this);
  }

  @Override
  public int getPuzzleLibrarySize() {
    return puzzles.size();
  }

  @Override
  public void resetPuzzle() {
    for (int j = 0; j < lamps.length; j++) {
      for (int i = 0; i < lamps[0].length; i++) {
        lamps[j][i] = false;
      }
    }
    notifyObservers(this);
  }

  @Override
  public boolean isSolved() {
    for (int j = 0; j < this.getActivePuzzle().getHeight(); j++) {
      for (int i = 0; i < this.getActivePuzzle().getWidth(); i++) {
        if (puzzle.getCellType(j, i) == CellType.CLUE) {
          if (!isClueSatisfied(j, i)) {
            return false;
          }
        }
        if (puzzle.getCellType(j, i) == CellType.CORRIDOR) {
          if (!isLit(j, i)) {
            return false;
          }

          if (isLamp(j, i) && isLampIllegal(j, i)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public boolean isClueSatisfied(int r, int c) {
    if (r < 0 || c < 0 || r >= getActivePuzzle().getHeight() || c >= getActivePuzzle().getWidth()) {
      throw new IndexOutOfBoundsException("row or column is out of bounds");
    }
    if (getActivePuzzle().getCellType(r, c) != CellType.CLUE) {
      throw new IllegalArgumentException("cell type is not clue");
    }

    int numClues = puzzle.getClue(r, c);
    int adjacentLamps = 0;

    // testing above
    // in bounds
    if (r > 0 && lamps[r - 1][c]) {
      adjacentLamps++;
    }
    if (r < puzzle.getHeight() - 1 && lamps[r + 1][c]) {
      adjacentLamps++;
    }
    if (c > 0 && lamps[r][c - 1]) {
      adjacentLamps++;
    }
    if (c < puzzle.getWidth() - 1 && lamps[r][c + 1]) {
      adjacentLamps++;
    }
    return (adjacentLamps == numClues);
  }

  @Override
  public void addObserver(ModelObserver observer) {
    modelObservers.add(observer);
  }

  @Override
  public void removeObserver(ModelObserver observer) {
    modelObservers.remove(observer);
  }

  private void notifyObservers(ModelImpl model) {
    for (ModelObserver observer : modelObservers) {
      observer.update(model);
    }
  }
}
