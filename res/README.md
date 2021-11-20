![Jumptastic Game](https://cdna.artstation.com/p/assets/images/images/010/248/334/large/jordon-gonzales-jordongonzaleslookdevfinal.jpg?1523394149&dl=1)

  <p align="center">
    <strong>Welcome to the World of Dungeons 2.0 !!!</strong>
</p>
<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
    </li>
    <li>
      <a href="#list-of-features">List Of Features</a>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#how-to-run">How to Run</a></li>
        <li><a href="#installation">How to Use Program</a></li>
      </ul>
    </li>
    <li><a href="#Description">Description of Examples</a></li>
    <li><a href="#assumptions">Assumptions</a></li>
    <li><a href="#limitations">Limitations</a></li>
     <li><a href="#design-and-model-changes">Design and Model Changes</a></li>
    <li><a href="#citations">Citations</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

We are back again with a new adventurous game "The World of Dungeons 2.0". The world for our game consists of a model, a network of tunnels and caves that are interconnected so that player can explore the entire world by traveling from cave to cave through the tunnels that connect them. Let's dive in and explore some cool features. The Dungeon is filled with Monsters (Otyughs), players can move through
caves and shoot the otyughs using arrows. Players cannot enter caves with Otyughs.

The game proceeds as follows:

* Firstly, the game starts with initializing a model from inputs provided by the client.
* These inputs involve the size of the model, treasure percentage, interconnectivity with in the model, whether the model is wrapping or non wrapping and number of monsters.
* Randomly two caves are designated as start and end cave and the goal of the player is to reach end cave starting at the start cave.
* Along the way the player also comes across treasure and arrows in certain caves. Available treasures right now are RUBY, SAPPHIRE and DIAMONDS. 
* Player can move in one of the four directions(NORTH, SOUTH, EAST and WEST) if there is path connecting his current cave to the next cave.
* Players can shoot the monsters and kill them.
* Finally, the player wins when he reaches the end cave without a monster or encounters a half injured monster.

<!-- Guidelines to the Player -->

## Guidelines to the Player

### <strong> <u> <i> Monsters:  </i> </u> </strong>
Otyughs (Links to an external site.) are extremely smelly creatures that lead solitary lives in the deep, dark places of the world like our dungeon.
```
* There is always at least one Otyugh in the dungeon at the end cave.
* Otyughs are always found in caves and not in tunnels.
* They can be detected by their smell. In general, the player can detect two levels of smell:
        1. A less pungent smell can be detected when there is a single Otyugh 2 positions from the player's current location.
        2. A more pungent smell when there is a single Otyugh 1 position from the player's current location, 
           or that there are multiple Otyughs within 2 positions from the player's current location.
* A player entering a cave with an Otyugh that has not been slayed will be killed and eaten.
```

### <strong> <u> <i> Slaying a Monster:  </i> </u> </strong>
* A player that has arrows, can attempt to slay an Otyugh by specifying a direction and distance in which to shoot their crooked arrow. 
* Distance is defined as the number of caves (but not tunnels) that an arrow travels. Arrows travel freely down tunnels (even crooked ones) but only travel in a straight line through a cave.
```
1. A tunnel that has exits to the west and south can have an arrow enter the tunnel from the west and exit the tunnel to the south, or vice-versa (this is the reason the arrow is called a crooked arrow).
2. A cave that has exits to the east, south, and west will allow an arrow to enter from the east and exit to the west, or vice-versa; but an arrow that enters from the south would be stopped since there is no exit to the north.
```
* Distances must be exact. For example, if you shoot an arrow a distance of 3 to the east and the Otyugh is at a distance of 2, you miss the Otyugh.
* It takes 2 hits to kill an Otyugh. Players has a 50% chance of escaping if the Otyugh if they enter a cave of an injured Otyugh that has been hit by a single crooked arrow.

<!-- List of Features -->

## List of Features

The 'World of Dungeons' provides the following features/benefits to the user:

* The game supports both wrapping and non wrapping dungeons, making the game more challenging.
* Provides support for 3 types of treasures.
* Provides client with the ability to choose the percentage of treasure he wants to assign to the model, ability to choose if the model wrapping and non wrapping, size of the model etc.
* The game is made interactive by allowing users to choose the next move and the ability to pick up treasure as they desire.
* At any point user can request for player's current location and the status of the treasure with him.
* Collect and deploy Arrows and shoot through the caves.
* Shoot and Kill Otyughs.
* Add Otyughs to the caves.

<!-- Getting Started -->

## Getting Started

### How to Use

The folder structure of the project basically contains three directories.

* src directory contains all the interfaces and the classes.
* test directory contains all the test cases for every class's public methods.
* res directory contains docs, jar file, and manifests

User can <a href="#run-the-jar"> run the jar file </a> directly or use the driver class to execute
the project.

<!-- run-the-jar -->

### How to Run

<strong><u> Running Using A Jar File </u></strong>

To run the jar file use the following command in the terminal.

```
java -jar <jarName> <no_of_rows> <no_of_columns> <degree_of_interconnectivity> <isWrapping?> <treasure_Percentage>
```

The jar file added in the res folder is name Project4-DungeonGame.jar.

<strong> <u> Running Driver Class </u> </strong>

Edit your program configurations to take in command line inputs as per your IDE. The inputs should be in the same order as <i> <no_of_rows> <no_of_columns> <degree_of_interconnectivity> <isWrapping?> <treasure_Percentage> <no_of_monsters></i>.

Navigate to src/driver directory. You will find a **GameDriver** class with main method in it.
Right click and Run the program.

<!-- Description of Examples-->

## Description of Examples

As a sample dry run, I have executed the program multiple times:

The run demos a following features:
1. Initially details of the model like player's location treasure and number of arrows with are printed. 
2. User can not see the model and all the connections.
3. For every move the user is prompted whether to move to another cave, pick up the treasure or shoot an arrow.
4. Based on the option the player chooses the game proceeds. 
5. The treasure collected keeps accumulating till the end. 
6. Player can kill the monsters along the with the arrows and pick up more arrows.
7. If the player enters the cave with a fully healthy monster the player dies.
8. If the player enters the cave with an injured monster the player has 50% chance to survive.
9. The game continuous till the reaches the end cave.

<b><i> Run 1(WrappingWinner.txt) - displays a player navigating through the wrapping dungeon and winning the code. </i></b>

<b><i> Run 2(WrappingLosing.txt) - displays a player navigating through the wrapping dungeon and being killed by a monster. </i></b>

<b><i> Run 1(NonWrappingWinner.txt) - displays a player navigating through the non wrapping dungeon and winning the code. </i></b>

<b><i> Run 2(NonWrappingLosing.txt) - displays a player navigating through the non wrapping dungeon and being killed by a monster. </i></b>

<!-- Assumptions -->

## Assumptions

To successfully implement the project, the following assumptions have been made

1. It is assumed that the product of number of rows and number of columns in a model should be greater than 10.
2. The value of provided interconnectivity should be non-negative.
3. A cave can have more than one treasure and maximum value of a particular treasure is 300.
4. When interconnectivity is a very large number and if model doesn't have that many leftover paths to connect, then all the leftover paths are connected and the program proceeds.
5. Tunnels do not hold Treasure.
6. A player can only move in four directions and cannot move in diagonal Caves.
7. Treasure Percentage will be calculated in the rest Caves which are not Tunnels.
8. Each cave can only one item of each treasure type.
9. Player is allowed to shoot with arrows, but it will hit any Monster or Otyugh when the distance is exact.
10. Once cave can only have one monster.

<!-- Limitations -->
## Limitations

1. The distance between start and end cave is restricted to be minimum 5, so the grid has to be selected accordingly. Smaller values of rows and columns might lead to program to run in infinite loop searching for a suitable start and end path.
2. For hassle-free gaming experience, choose the size greater than 4x4.
3. Only one Player can play at once.
4. Supports only three types of Treasure but also satisfies the criteria of supporting at least 3 Treasure types.
5. Only one type of Monster is available.


<!-- Design and Model Changes -->

## Design and Model Changes

* The design and UML diagram can be found in the res folder.
* Based on the discussions with instructor, I have created the model, initiated the player and start and end caves with the Game object creation only instead of calling different methods in driver class.
* Apart from the above-mentioned point the majority of the model remains same. 

<!-- Citations -->

## Citations
1. [Finding paths between 2 nodes in the grid](https://www.geeksforgeeks.org/find-paths-given-source-destination/)
2. [JavaDoc for Using Randon Integer generator](https://docs.oracle.com/javase/8/docs/api/java/util/Random.html)
3. https://www.geeksforgeeks.org/treeset-in-java-with-examples/#:~:text=TreeSet%20is%20one%20of%20the
5. https://docs.oracle.com/javase/8/docs/api/java/lang/Comparable.html
6. https://www.javatpoint.com/Comparable-interface-in-collection-framework

<!-- Contact -->

## Contact

```
Name : Rishita Reddy
Email : reddy.ri@northeatern.edu
Project Link: https://github.com/RishrReddy/Monkey-Project.git
```