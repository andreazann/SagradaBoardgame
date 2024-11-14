# Sagrada Boardgame in Java

Java representation of Cranio's boardgame Sagrada. Features include: remote play in multiplayer mode using Socket or RMI methods, command line/GUI view, Client-Server approach.

## Coverage test
	
	- Model 97,8% (2,2% unable to test packages executed when running jar)
## UML 

https://www.lucidchart.com/documents/view/6b7fbe65-86ba-4f5c-9911-90f8060f67d2

![UML Sagrada(1)](https://github.com/user-attachments/assets/e4983cf5-2525-4568-a137-7dbdd67e1f92)

## Features implemented 

Complete rules of the game + CLI + GUI + RMI + Socket + Dynamic Card Schemas, Client-Server approach

## Design patterns used 

Design patterns used: State, MVC, Observer, Strategy, Proxy
	
## Additional features:

	- implemented una signup/login phase and lobby while waiting to play where you can choose the starting cards
	- managed reconnection of players to the lobby and room of ongoing game
	- designed a protocol to communicate via Socket without using Serializable methods. Contents to send are encoded with markers in a string 
	
## Game limitations

This game is only supported by UNIX systems and with JDKx

## Instructions to run the Jar:

	- Compatible only with JDKx

	- Execute Server from command line using 
	>java -cp Game.jar repolezanettiperuzzi.controller.MasterGame
	
	- Execute Client from command line using 
	>java -cp Game.jar repolezanettiperuzzi.view.GameView
	
