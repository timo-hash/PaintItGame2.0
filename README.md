# PaintIt Project

## Game: Paint It 


- **What will the application do?**

*A game that allows the players to maneuver a square around a blank screen. Every step the square moves will fill
its previous spot with another black box (i.e. the square leaves a trail behind).
The objective is to fill the entire screen with the black box. The square is restricted to move within the boundaries
and the square cannot cross the trail. If the stage is completed, the next stage will appear with more obstacles. If
the player fails, the game will be over and the player will be asked if they would like to try again or quit. Players 
who complete all stages will have their name recorded on the leaderboard along with the time taken to complete 
the stages.*

- **Who will use it?**

*Anyone who enjoys a challenge, solving puzzles or is simply just bored.*

- **Why is the project of interests to you?**

*Game development has always been an area of interest regarding computer programming. I would like to build a game for
others to play.*


## Overview
- I want to be able to end the game when I finish the puzzle successfully
- I want to be able to end the game when I run outside of the boundaries or into the trail (lose)
- I want to be able to move the square in all 4 directions
- I want to be able to enter my name into the leaderboard once I have finished or failed
- I want to be able to see the leaderboard
- I want to be able to be able to try again

- I want to be able to save my leaderboard scores to file
- I want to be able to load my leaderboard scores from file when game starts 


**Java Construct: Robustness**
- Within the Grid class, an exception will be thrown by setGridSize(int gridSize) method if the given size is too small 
or negative.


**Possible Refactoring**
- Could abstract out the duplicated code in check button methods (i.e. checkUpButton(), checkDownButton(),
 checkLeftButton(), checkRightButton()) of PaintIt Class.
 - Could have some GUI classes extend ScreenSize and directly inherit the game screen size width and height rather
 than having an association relationship.
 - Could reduce duplication when creating a new Grid class. Instead of instantiating and setting grid size separately,
 the set grid size method can be placed inside the constructor.