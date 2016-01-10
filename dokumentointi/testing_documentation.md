# Testing documentation

Below are short reports on testing coverage by class.

## Logic

### GameGrid.java

100% row coverage.

Row | Description |
----|-------------|
192 | Chaning the conditional boundary to >= instead of >, effecively does nothing. Instead of the method with the loop for adding extra mines not executing at all the loop is executed 0 times. |
214 | The shuffle call not getting executed would instantly be visible while testing the GUI. And the GUI has to be manually tested either way in this project. |
254 | As the previous note. This omission would be apparent in GUI testing. |

### Tile

100% row and mutation coverage

## Main

### Game

100% row coverage.

Row | Description |
----|-------------|
222 | The assignment doesn't ever get called in one of the same tests that check for the assignment having been done. But different tests rely on the assignment working. |

### Main

No coverage.

Coverage of the entry point would be redundant since any omissions/errors here would instantly break GUI testing.

## Score

### Score

100% row and mutation coverage.

### Scores

100% row and mutation coverage.

## Util

### Coordinate

100% row and mutation coverage.

### ObjectStorage

70% row coverage 50% mutation coverage.

The idea behind testing the ObjectStorage class was to automate hard to trace faliure modes that do not depend on messing with the file system.

The remaining unchecked stuff has been manually tested:
* read/write privelages have been edited to prompt exception handling.
* files/folders have been removed/broken to prompt exception handling.

## Eventhandlers

### ButtonActionListener & SquareClickListener

No coverage.

Attempts were made to automate testing with AssertJ. However the attempts resulted in unhandled exceptions in Javas threading so AssertJ testing was abandoned.

However extensive manual testing has been done. All the buttons have been clicked and many a game of minesweeper have been played.

## Gui

### GamePanel, Gui, MineSweeperMenuBar, Square, StatPanel

No coverage.

See Eventhandlers.
