# 🎮 Game Project

## 📖 Overview
This project is a Java-based game that involves two players competing against each other. The game includes features such as bomb and unflippable discs, and allows players to undo moves, reset the game, and determine the winner based on the number of discs owned.

## 🌟 Features
- **👥 Two Players**: Player 1 and Player 2, each with unique attributes and abilities.
- **💣 Bombs and ⭕ Unflippable Discs**: Special discs that add strategic depth to the game.
- **↩️ Undo Last Move**: Ability to undo the last move made.
- **🔄 Game Reset**: Reset the game to its initial state.
- **🏁 Game End Detection**: Automatically detects when the game has ended and declares the winner.

## 🧩 Object-Oriented Programming (OOP) Concepts

### 🔒 Encapsulation
Encapsulation is achieved by using private fields and providing public getter and setter methods. This ensures that the internal state of objects is protected and can only be accessed or modified through well-defined interfaces.

### 🧬 Inheritance
The `Player` class is an abstract class that defines common attributes and methods for all players. Specific types of players can inherit from this class and implement additional functionality.

### 🔄 Polymorphism
Polymorphism is utilized through method overriding. For example, the `isHuman` method in the `Player` class is abstract and must be implemented by subclasses, allowing different types of players to define their own behavior.

### 🕶️ Abstraction
Abstraction is used to hide complex implementation details and expose only the necessary functionality. The `GameLogic` class provides high-level methods to manage the game, while the underlying details are encapsulated within the class.

## 🛠️ Classes

### `GameLogic`
Handles the core game logic, including:
- Managing the game board.
- Determining valid moves.
- Flipping discs.
- Checking if the game has ended.
- Resetting the game.
- Undoing the last move.

### `Position`
Represents a position on the game board with row and column coordinates. Includes methods for:
- Retrieving row and column values.
- Checking equality of positions.

### `Player`
Abstract class representing a player in the game. Includes:
- Player attributes (e.g., number of bombs, number of unflippable discs).
- Methods to manage player state (e.g., resetting bombs and unflippable discs, adding wins).

## 📜 Game Rules

### 🎯 Objective
The objective of the game is to have the most discs of your color on the board when the game ends.

### 🛠️ Setup
- The game is played on an 8x8 board.
- Each player starts with two discs of their color placed diagonally in the center of the board.

### 👥 Players
- There are two players: Player 1 and Player 2.
- Players take turns placing discs on the board.

### ➕ Placing Discs
- A disc can only be placed in a position that outflanks one or more of the opponent's discs.
- To outflank means to place a disc such that there is a straight (horizontal, vertical, or diagonal) line between the new disc and another disc of the same color, with one or more of the opponent's discs in between.

### 🔄 Flipping Discs
- When a disc is placed, all outflanked opponent's discs are flipped to the player's color.

### 💣 Special Discs
- **Bombs**: Can be used to remove opponent's discs in a specified area.
- **⭕ Unflippable Discs**: These discs cannot be flipped by the opponent.

### 🏁 Ending the Game
- The game ends when neither player can make a valid move.
- The player with the most discs of their color on the board wins.
- If both players have the same number of discs, the game is a draw.

### 🕹️ Commands
- **↩️ Undo Last Move**: Reverts the last move made.
- **🔄 Reset Game**: Resets the game to its initial state.

## 🚀 Getting Started

### 📋 Prerequisites
- Java Development Kit (JDK) 8 or higher.
- IntelliJ IDEA or any other Java IDE.

### 🛠️ Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/YakirLitmanovitch/OOP-reversi.git
    ```
2. Open the project in your IDE.

### ▶️ Running the Game
1. Compile the project.
2. Run the `GameLogic` class to start the game.

## 📚 Usage

### 🔄 Game Flow
1. Players take turns placing discs on the board.
2. Special discs (bombs and unflippable) can be used strategically.
3. The game ends when there are no valid moves left.
4. The player with the most discs on the board wins.

### 🕹️ Commands
- **↩️ Undo Last Move**: Reverts the last move made.
- **🔄 Reset Game**: Resets the game to its initial state
