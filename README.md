# Typing Tutor
## UBC CPSC 210

This application is a typing game that aims to improve the user's touch typing skills.
The game will begin, and the goal of the user is to type as many words during a predetermined span of time
before the game ends.
 
This program will be targeted towards kids learning how to type but can be used by people of all skill levels.

This project is of interest to me because touch typing websites and games are an enjoyable means of developing an
essential skill.

##User Stories
1. as a user, I want to be able to see a leader board of all the recorded scores.
2. as a user, I want to be able to submit my own score to the leader board with a nickname.
3. as a user, I want to be able to play a game that ends after a predetermined amount of time.
4. as a user, I want to be able to type a word and have its accuracy to target word be reflected in my score.
5. as a user, I want to be able to add my own words to the game so I can customize my practice.
6. as a user, I want to be able to remove words from the game.
7. as a user, when I exit the application I want the leader board to automatically save.
8. as a user, when I re-open the application and load the leader board I want it to be the same as the most recently
  saved leader board.
9. as a user, when I exit the application I want the word bank to save automatically.
10. as a user, when I re-open the application I want the most recent word bank to be loaded.
11. as a user, I want a pinging-sound to go off every time I correctly type a word.

##Instructions for Grader

**First required event:** adding/removing word from word bank

*You can generate the first required event by...*
- Running the Main class.
- Clicking the "Add/Remove Word" button.
- Entering "cranberry" (or a word of your choice) into the text field and click the "Add" button.
- The message "cranberry was added successfully" should appear on-screen.
- With "cranberry" still in the text field try clicking the "Remove" button.
- The message "cranberry was successfully removed" should appear on-screen.

**Second required event:** adding a leader board entry

*You can generate the second required event by...*
- Running the Main class.
- Clicking the "Play" button.
- Clicking the "Start" button.
- Waiting for the timer to run down (maybe try playing the game while you do this).
- Once the timer has run down, the end game frame will appear.
- Inputting your name, or a nickname, into the "Your nickname: " field.
- Clicking the "Submit score" button.
- Now scroll through the leader board until you find your nickname.

*You can trigger my audio component by...*
- Running the Main class.
- Clicking the "Play" button.
- Clicking the "Start" button.
- Correctly typing the large word in the center of the screen into the text field.
- As soon as the inputted word matches the target word, a pinging sound should go off.
- This sound will be triggered every subsequent time you correctly type the target word for the duration of the game.

*You can save the state of my application by...*
- Clicking the "x" button in the top right-hand corner of the frame, the same one that closes the frame and stops the 
application.
- Upon closing the frame, the word bank and leader board are automatically backed up to JSON files. So if the user added
or removed any words, and/or submitted their score to the leader board, those changes will be saved.

*You can reload the state of my application by...*
- Running the Main class.
- Starting the application will automatically reload the state of the application from the last time it was saved.

##Phase 4: Task 2
I have chosen to include a type hierarchy in my code.
The classes that play a role in my type hierarchy are:

*Superclass*
- Frame

*Subclasses*
- EndGameFrame
- GameFrame
- LeaderBoardFrame
- MainMenuFrame
- StartGameFrame
- WordFrame

##Phase 4: Task 3

**Poor cohesion in the GameFrame class:**

I extracted the code responsible for playing audio and moved it into a separate class AudioPlayer in the ui package.
Then I added an instance of AudioPlayer to GameFrame and used that to play the audio.

**Poor cohesion in the Frame class**

In the exit procedure, the Frame class was responsible for saving the state on the game. I moved this into the
TypingTutor class which was responsible for loading the state of the game so it should be responsible for saving it too.

**Semantic coupling between classes with fonts:**

I created a static instance of a Font in the Frame class named STANDARD_FONT. I made this the single point of control
for all fonts meant to be consistent in some way throughout the gui. Every font that is related somehow to the 
STANDARD_FONT now refers back to it.

**Coupling/Repeated code between Frame subclasses:**

The methods initializeComponents(), implementComponentsFunctionality(), and addToFrame() were called in every
subclass of Frame in each of their respective constructors. I moved the calls to these methods (all of which were 
already abstract methods in Frame) into Frame's constructor.