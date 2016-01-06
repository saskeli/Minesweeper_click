# Sequence diagrams

## Startup sequence

![Startup diagram] (https://raw.githubusercontent.com/saskeli/Minesweeper_click/master/util/Minesweeper%20click%20startup%20%28simplified%29.png)

This diagram illustrates the startup sequence of the apllication.

The main idea is to show the interaction to retrieve a possible savestate and how the game is passed to the Gui.

## Click logic

![click diagram] (https://raw.githubusercontent.com/saskeli/Minesweeper_click/master/util/Minesweeper_click_%20tile%20click%20logic.png)

Diagram illusrating the branching of the game logic behind clicking a tile. Many of the method calls are somewhat abstract to keep the clutter to a minimum. generateTiles, clearMines and clearSurroundingTiles are mostly to show that these get called if a condition is met, more than to show the internal workings. The execution of updateGrid is also left abstract since the execution is beyond the scope of this diagram. 
