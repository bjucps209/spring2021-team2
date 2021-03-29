# Fish Game Specification

This document contains the specifications of our Fish game.

## Characters and Objects

The player controls the knight on an ostrich. The controls are given in the next heading.

Insert Player Image

The enemy of the player rides a buzzard. The buzzards move randomly around the map defaulting at a hover above the platforms.

Insert Opponent Image

When a buzzard is defeated, the enemy riding will turn into an egg and fall onto a platform. Eggs can be collected for points. After a while if an egg is never collected, an enemy will hatch from the egg and a buzzard will come and pick him up.

Insert Opponent Image

Pterodactyls spawn when the player has taken too long to defeat all the buzzards in a wave. They are able to thrust themselves at the player when close and are difficult to destroy.

Insert Opponent Image

Platforms are placed throughout the map in the sky. A default platform will always be present in the bottom center of the map. Some platforms have respawn points that will bring back the fallen player or bring new buzzards.

Insert Opponent Image

## Art Style

The art style is based off of the Joust arcade game from 1982. The 2D sprites were recreated based off of the sprites from the 1982 Joust game. Animations will be given to the player, buzzards, and pterodactyls.

## Story

Welcome to Joust. On earth in the year 2050, the evil dictator Mik Gnoj Nu of North Aerok carried out a mass nuclear-holocaust of the planet earth, leaving nature to crumble. With lava pits opening up everywhere and reanimated dinosaurs now ruling the planet with their radiation-enhanced intellect and power, you and your mutated flying ostrich Ralph travel the hellscape searching and saving the last vestiges of humanity.

However, the evil Un still has his evil lackeys (all named Mik) flying about on their evil mutated buzzards trying to subjugate all survivors with pterodactyls making their day even worse. As the hero of our story, it's your duty to end North Aerok's reign of terror by traversing danger-fraught levels, each with more enemies than the last all the while breaking the dropped eggs before they hatch into more trouble. Can you bring earth back from this radical nightmare?

## Gameplay

In Joust, you control an ostrich mounted by a knight holding a lance. The goal is to survive waves of enemies and earn as many points as possible by defeating all enemies. You defeat an enemy when your lance is higher than your opponent's lance when you collide with that enemy. However, if your lance is beneath your opponent's lance, you will be defeated.

## Player Lives

Players start with three lives. When a player is defeated, that player will lose one life and re-spawn at a random spawn platform.

## Level Design

Each level will contain a base platform at the bottom of the screen. On either side of the base platform is a lava pit that consumes any bird or egg it touches. There will be several smaller platforms above the base platform. The base platform and about half of the other platforms will be spawn platforms. Birds can land on the platforms and walk on them.

## Player Death

When a buzzard dies, it will drop an egg from its last location with its last speed and direction. If the egg hits a platform it will lose all momentum and remain stationary. If a player touches an egg, he will collect it for points. If the egg is not collected within ten seconds, it will hatch into a new rider. A new buzzard will spawn from a spawn platform and fly to the rider. The rider will mount the buzzard and the player will have to defeat it again.

Single Player

In single player mode, the game will run on the local computer (Internet connectivity is not required for single player).

Heads Up Display

The HUD (Heads Up Display) will be shown on the base platform for each player. It will show the players current score and number of lives.

## Game Controls

Each ostrich can move left, right, and up. Due to gravity, the ostrich will have a downward force and will move down if not moving up by input. The max velocity of the ostrich up and down is 275 pixels per second. The wing flap lasts 0.1 seconds allowing the player to quickly change a downwards velocity to an upwards velocity. A single wing flap will increase the ostrich's upward acceleration by 1375 pixels per second squared for the duration of the wing flap.

Left - Pressing the A or left arrow key
Right - Pressing the D or right arrow key
Up - Pressing the W or up arrow key
Down - Gravity
Scoring

The points are obtained by collecting eggs, killing a buzzard, or killing a pterodactyl. The score will display at the bottom of the screen.

Eggs - 250 points
Buzzard - 500 points
Pterodactyl - 1000 points
A player's total points can be found with this formula: (eggsCollected * 250) + (buzzardsMurdered * 500) + (pterodactylsMassacred * 1000)

Level Progression

Joust progressively gets harder as the player completes more waves. A wave is made more difficult by changing the amount of buzzards that spawn in that wave. More pterodactyls will also spawn as the player progresses. The player starts at wave 0 (unless they select another difficulty) and progresses to wave 99. Upon completing wave 99, the player will be sent back to wave 0.

Title Screen

The first screen seen by players when they load the game will be the title screen. The title screen will have five buttons. The first button will take the player to the single player screen. The second will display the help screen. The third button will show the about screen. The fourth button will go to the high score screen. The fifth button will go to the level designer. Every screen except the title screen will have a button at the bottom that will take the player to the previous screen.

Single Player Screen

The single player screen will have a button to start a new game, a saves area to start from a save file, a button that goes to the custom stages screen, a button to turn off and on cheat mode, and a textbox to set difficulty.

Custom Stage Screen

This screen has a listbox of buttons that start new games in the listed custom stages.

Multiplayer Start Game Screen

The first player to join the server will be the host. The multiplayer start game screen will display a cheat mode toggle switch and a start game button to the host only. The host can also choose what difficulty to start the game at with a text box. Non-hosts can leave the server or wait for the host to start the game. The start game button will take each player on the server to the game itself.

Help Screen

This screen will have a slideshow that shows helpful information to inform the player about how to play and win the game. There will be a buttons leading to the progression screen, player/controls screen, enemies screen, and scoring screen.

Progression Screen

Contains info about game progression.

Player/Controls Screen

Contains info about the player ostrich and explains how to play.

Enemies Screen

Contains buttons that lead to screens of info for buzzards, eggs, and pterodactyls.

About Screen

This screen will credit the programmers who worked on the game and list the story.

High Score Screen

This screen lists the high scores on file in order.

Level Designer Screen

This screen contains the level designer. Buttons place controls in the top right corner after which the user can place them on the given gray space to design a level. Controls can be deleted and after the user is done, he can exit the designer or save it to play on later.

## Cheat Mode

When cheat mode is on, no ostrich (player) can die. If an ostrich collides with another bird and its lance is lower than the other's lance, the will simply collide as if their lances had been at the same height. Ostriches will also bounce off the lava instead of dying.

## Custom Stages Mode

In custom stages mode, the player does not have the ability to save and load. This is a bonus mode created as an simply as an endurance test.

## Artificial Intelligence

Buzzards and Pterodactyls will be given some artificial intelligence. They will hover by default, moving in the same direction. They may, randomly, change direction or fly up or down. Pterodactyls, when close to the player, will charge at him quickly.

## Technical Description

The game will be available to Windows machines.