# tictactoe-ai
This is an implementation of the game of TicTacToe in which you play against a computer. The computer uses a method called the minimax algorithm to determine its next move. To run the game, simply run the App class in the client package.
___
This algorithm works by creating a decision tree for all possible decisions at each point in the game. This is possible because TicTacToe is a relatively small game, meaning that there are 9! total decisions (not too difficult for the computer to handle). After each turn, the computer recursively simulates all possible outcomes of the current state. Each individual "cell" on the board is then assigned one of three values: -1, 0, 1. Any cells with a -1 are losing decisions, cells with a 0 are neutral decisions, and cells with a 1 are winning decisions. Using this, the computer selects the best move by choosing the "best" available cell.
___
Credits to Holczer Balazs who talks about implementing the minimax algorithm in a TicTacToe AI in his "Artificial Intelligence & Games in Java."
