# README

### Assumptions
* The board Row and Col number specified in the Move class both start from 0.
* The implemented Player class resides within the aiproj.squatter package.

### Code Structure
      Xichangz:Player
       /          \
      / (has a)    \
     /              \
Board              


### O(n) Captured Cells Checking Algorithm
We have used Flood Fill algorithm to check captured cells. Starting from a specified cell, and with a loop edge color (WHITE or BLACK) given, all its adjacent non-loop-color cells (inluding CAPWHITE or CAPBLACK) are added to an exploringList. After this, the explored cell is added to a capturedList, and itself is removed from the exploringList. All cells in the exploringList are continually explored until empty or when a cell is found to be a border cell. Since all cells in the exploringList are non-loop-color cells, if one cell is found to be a border cell, a loop is not formed, the exploration is stopped. Otherwise, when all cells in the exploringList have been explored and no border cell is found, a loop is found, all cells in the capturedList are marked as captured. This way we are able to achieve O(n) efficiency with O(n) space to check if one cell is captured and update all other cells in its capturing space accordingly, with n being the number of cells in a board. 


### Search Optimization -- Symmetry Checking
We have implemented symmetry checking as one of the ways for pruning in our MiniMax algorithm. Board status A is considered symmetric with Board status B if the B is identical with A after rotating A 0, 90, 180 or 270 degrees. We used Zobrist hashing algorithm to generate 4 hash values for each board status, with each value represents the board status after one of its four rotations. We stored the hash values in a transposition table for every board status that we have explored in our MiniMax algorithm. Before the exploration of a new board status, we will check if its hash value is already contained in the transposition table, and skip this exploration if it is.



