# README

### Assumptions
* The board Row and Col number specified in the Move class both start from 0.
* The implemented Player class resides within the aiproj.squatter package.

### Code Structure


### O(n) Captured Cells Checking Algorithm
We have used Flood Fill algorithm to check captured cells. Starting from a specified cell, and with a loop edge color (WHITE or BLACK) given, all its adjacent non-loop-color cells (inluding CAPWHITE or CAPBLACK) are added to an exploringList. After this, the explored cell is added to a capturedList, and itself is removed from the exploringList. All cells in the exploringList are continually explored until empty or when a cell is found to be a border cell. Since all cells in the exploringList are non-loop-color cells, if one cell is found to be a border cell, a loop is not formed, the exploration is stopped. Otherwise, when all cells in the exploringList have been explored and no border cell is found, a loop is found, all cells in the capturedList are marked as captured. This way we are able to achieve O(n) efficiency with O(n) space to check if one cell is captured and update all other cells in its capturing space accordingly, with n being the number of cells in a board. 


### Search Optimization -- Symmetry Checking
We have implemented symmetry checking as one of the ways for pruning in our MiniMax algorithm. Board status A is considered symmetric with Board status B if the B is identical with A after rotating A 0, 90, 180 or 270 degrees. We used Zobrist hashing algorithm to generate 4 hash values for each board status, with each value represents the board status after one of its four rotations. We stored the hash values in a transposition table for every board status that we have explored in our MiniMax algorithm. Before the exploration of a new board status, we will check if its hash value is already contained in the transposition table, and skip this exploration if it is.


Xichangz implements player, our player in game.

Xichangz has a board, an instance of Board, a role, an opponent role (oppoRole), a boolean indicating which moving algorithm to use (minimax).

Xichangz implements all methods from given class Player.

***************************************************************************************

Board has a array of arrays of Cell objects, a dimension, a winner, a ArrayList of freeCells.

Board has public methods of updateBoard(Move move), checkWinner(), calculateCaptured(int[] capturedCounts), updateCell(int row, int column, int status), validateMove(int row, int column) and checkLoop().

full descriptions and javadoc please see codes.

*****************************************************************************

Cells consistute a board. has row and column index, a value, and a checked boolean.

has public method cellToMove(int role)

***************************************************************************************

Intelligence has a master, a board it is playing on, a cellToUpdate for the move to be made.

It has an abstract mthod of makeMove() that returns a move.

when player's makeMove() method is called, it instantiates an Intelligence type class to make a move.
(notes that Intelligence is abstract, only a concrete class of its extension is valid to instantiate.)

***************************************************************************************

RandomAlgorithm extends Intelligence, randomly choose a free cell to make the move. Used for naive moving algorithm.


*******************************************************************************************

MinimaxAlgorithm extends Intelligence.
Has depth indicating the cut off levels, hashsetExplored storing cells that have checked for symmetry

has method extended superclass makemove, which called maxvalue() method.

maxvalue() calls minvalue() in the code and minvalue() calls maxvalue(), the two recursively follow the algorithm to obtain the max value of the minimax tree. When level reachs to the depth, the evaluator is instantiated to calculate an evaluation value.

***********************************************************************************************

LinearEvaluator has featureNum indicating number of features for the evaluator, and a Feature[] array storing features.

has an abstract method setFeatures(), has a concrete method evalFunction(), which linearly add up features times their weights.

***********************************************************************************************

HeZhaoSquatterAlgorithm extends LinearEvaluator, it is our main evaluation algorithm for Minimax tree.

in method setFeatures(), it sets 4 features: opponent's captured count, own captured count, own side pieces count and own angle pieces count.

***********************************************************************************************

Feature has a role it concerns, a value and a weight. Has an abstract method of setFeature()


**********************************************************************************************

CapCount extends Feature, a common method of getCaptured() that return specific captured count for its role.

****************************************************************************************************

OwnCapCount extends CapCount, set positive weight.


************************************************************************************************

OppoCapCount extends CapCount, set negative weight


***********************************************************************************************

SidePieceCount extends Feature, has count that records number of own pieces on 4 sides of the board, has positive weight

*********************************************************************************************

AngleCount extends Feature, has count that records number of own pieces on 4 angle of the board, has negative weight.



### O(n) Captured Cells Checking Algorithm
We have used Flood Fill algorithm to check captured cells. Starting from a specified cell, and with a loop edge color (WHITE or BLACK) given, all its adjacent non-loop-color cells (inluding CAPWHITE or CAPBLACK) are added to an exploringList. After this, the explored cell is added to a capturedList, and itself is removed from the exploringList. All cells in the exploringList are continually explored until empty or when a cell is found to be a border cell. Since all cells in the exploringList are non-loop-color cells, if one cell is found to be a border cell, a loop is not formed, the exploration is stopped. Otherwise, when all cells in the exploringList have been explored and no border cell is found, a loop is found, all cells in the capturedList are marked as captured. This way we are able to achieve O(n) efficiency with O(n) space to check if one cell is captured and update all other cells in its capturing space accordingly, with n being the number of cells in a board. 


### search strategy and evaluation function

Two algorithms have been written: Random, and minimax, minimax has been used.

The minimax algorithm support alpha beta pruning, symmetry check, which greatly impoves efficiency. When getting succeesors for each state, the successors are not in the type board but just cells to update, which saves heap cost.

four features have been chosen for the linear evaluation function:

first two are the opponent's captured cells count and own captured cells count. these two takes the count and divided by the total number of cells in the board to give a proportion of captured cells over total. This approach presurves the value consistency for different boad sizes. These values are then further multiplied by their weight, oppoCapCount has a weight of -200, OwnCapCount has a weight of 100.

The third one is the own side pieces count, this takes the quotient of count divided by total number of side cells as the feature value for the same purpose of above. The weight is 10.

The last one is the own angle pieces count, which records number of own pieces on 4 angle of the board, it has negative weight of -2000.


### Creative technique

##Search Optimization -- Symmetry Checking

We have implemented symmetry checking as one of the ways for pruning in our MiniMax algorithm. Board status A is considered symmetric with Board status B if the B is identical with A after rotating A 0, 90, 180 or 270 degrees. We used Zobrist hashing algorithm to generate 4 hash values for each board status, with each value represents the board status after one of its four rotations. We stored the hash values in a transposition table for every board status that we have explored in our MiniMax algorithm. Before the exploration of a new board status, we will check if its hash value is already contained in the transposition table, and skip this exploration if it is.



