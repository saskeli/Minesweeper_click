# Class diagrams

The conceptual diagram is meant to (on a very abstract level,) show what kind of classes the application contains as well as some very simple visualizations regarding the connection flow.

The class diagram shows more of the actual structure of the application.

## Extremely abstract conceptual diagram

![Conceptual diagram] (https://raw.githubusercontent.com/saskeli/Minesweeper_click/master/util/Conceptual_Class_diagram.png)
 
Note that the connections are very abstract even for a conecptual diagram. For instance the Utility class "Coordinate" is referenced and used by several of the other classes as well, the drawn connection is mostly to show that it is a utility class used by the "game".

## A bit more detailse class diagram

![class diagram] (https://raw.githubusercontent.com/saskeli/Minesweeper_click/master/util/Class_diagram.png)

Here the main take-away could be that the Game efficiently separates all the GUI logic from the game logic. Also the Score logic is counter intuitively only connected to the GUI. This is done because an envisioned new gameMode would use many of the same components as the current GUI but without the Score system.
