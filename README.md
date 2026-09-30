## Overview
Bingo simulator developed in Java for a first-year data structures course. All data structures are implemented from scratch, without relying on built-in collection classes or external packages, and are used to simulate complete bingo games for 2 to 4 players.

## Game Simulation

* Support for 2 to 4 players per game
* Configurable number of sessions per game
* Configurable number of balls in play
* Configurable points awarded for completing a line
* Configurable points awarded for calling bingo
* Score tracking across sessions

## Data Structures

All structures were implemented manually to understand their behavior, complexity and trade-offs.

**Linked List**

* Dynamic sequence with insertion and removal by node references
* Used for data whose size changes during the game

**Sorted Linked List**

* Elements are kept in order on every insertion
* Allows ordered traversal without a separate sorting step

**Set**

* Collection of unique elements with no duplicates
* Membership checks for matching drawn numbers

**Arrays**

* Fixed-size storage for data with a known size

## Other features

* No built-in collections
* No external libraries or packages
