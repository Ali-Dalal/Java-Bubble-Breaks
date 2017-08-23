## Bubble Breaks Game ##
Bubble breaks is a Java 2D game build in using basics Java with swing library
![Java bubble breaks](https://image.ibb.co/hdJQGk/Java_bubble_breaks.png)

The game comes with 3 different difficulties (Easy, Medium, Hard). it also support a custom game (set the size of the board and the number of colors)

![Java Bubble Breaks difficulties](https://image.ibb.co/iT7Kbk/difficulties.png)

for counting the similar bubbles, flood fill algorithm is used with directions 

                         Top
                  Left  Center  Right
                        Bottom

Points calculation

the total score is calculated using the following formula:
Points = number of bubbles * number of bubbles -1

At least you need 2 or more adjacent bubbles
The game can be fully played using the console
The buttons is only for representing the 2D array and each click the buttons array is redrawn,

The game also support multi save game, you can save your current game and continue your game later.

The game ends when no more similar adjacent bubbles found
