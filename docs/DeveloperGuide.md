---
layout: page
title: Developer Guide
---

## Table of Contents
{:toc}

---

## **Acknowledgements**

This project is based on the AddressBook-Level3 project created by the [SE-EDU initiative](https://se-education.org).

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

<div markdown="span" class="alert alert-primary">

:bulb: **Tip:** The `.puml` files used to create diagrams in this document `docs/diagrams` folder. Refer to the [_PlantUML Tutorial_ at se-edu/guides](https://se-education.org/guides/tutorials/plantUml.html) to learn how to create and edit diagrams.
</div>

### Architecture

<img src="images/ArchitectureDiagram.png" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main.java`](https://github.com/AY2324S1-CS2103T-T11-4/tp/blob/master/src/main/java/seedu/flashlingo/Main.java) and [`MainApp.java`](https://github.com/AY2324S1-CS2103T-T11-4/tp/blob/master/src/main/java/seedu/flashlingo/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<img src="images/ArchitectureSequenceDiagram.png" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<img src="images/ComponentManagers.png" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-T11-4/tp/blob/master/src/main/java/seedu/flashlingo/ui/Ui.java)

![Structure of the UI Component](images/UiClassDiagram.png)

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `FlashCardListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-T11-4/tp/blob/master/src/main/java/seedu/flashlingo/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-T11-4/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Flashcard` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-T11-4/tp/blob/master/src/main/java/seedu/flashlingo/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<img src="images/LogicClassDiagram.png" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

![Interactions Inside the Logic Component for the `delete 1` Command](images/DeleteSequenceDiagram.png)

<div markdown="span" class="alert alert-info">:information_source: **Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</div>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `FlashlingoParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a FlashCard).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<img src="images/ParserClasses.png" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `FlashlingoParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `FlashlingoParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](docs/images/ModelClassDiagram.png)

<img src="images/ModelClassDiagram.png" width="450" />


The `Model` component,

* stores the flashlingo data i.e., all `FlashCard` objects (which are contained in a `UniqueFlashCardList` object).
* stores the currently 'selected' `FlashCard` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<FlashCard>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)


### Storage component

**API** : [`Storage.java`](docs/images/StorageClassDiagram.png)

<img src="images/StorageClassDiagram.png" width="550" />

The `Storage` component,
* can save both flashlingo data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `FlashlingoStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.flashlingo.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.


### UI enhancement (Nathanael M. Tan)

#### Implementation

Display of flashcard details is split into two different classes of `FlashcardBox` and `FlashcardBoxNoButtons`
FlashcardBoxNoButtons is the default way to display the details of the flashcard.\
When `start` command is used, review session begins and FlashcardBox is then used to display the details.\
This is to prevent users from reviewing cards that they are not scheduled to review and erroneously cause changes to the flashcard.

Both `FlashcardBox` and `FlashcardBoxNoButtons` have a button at the right called that is called "Reveal" when the translation is not shown, and "Hide" when it is.\
This button will toggle the state of whether the translation is currently displayed.

`FlashcardBox` has an additional two buttons, the `Yes` button and the `No` button.\
The `Yes` button will invoke the yes command, just like if it were to be typed into the command line
Similarly, the `No` button will invoke the no command.

#### Design considerations:

**Aspect: How to invoke the command:**

* **Alternative 1 (current choice):** Pass MainWindow all the way into FlashcardBox. Use `executeCommand()` method to invoke the respective command
    * Pros:
        * High level of maintainability.
        * Outcome will be the same as if it were to be typed into the CLI.
        * Easy to change logic of the commands
    * Cons:
        * Have to pass MainWindow through multiple classes
        * Classes that do not need references to MainWindow are now forced to have them

* **Alternative 2:** Individual button can perform the `Yes` and `No` command by itself, without executing through a Command
    * Pros:
        * Don't have to keep reference to the MainWindow
    * Cons:
        * Low level of maintainability.
        * Changes made have to be replicated in different places.
        * May not behave the same way as a Command (eg. ResultDisplay does not show the log message)

* **Alternative 3:** Remove the `Yes` and `No` buttons.
    * Pros:
        * Easy to code
        * Only one way to invoke the command, reduce confusion
    * Cons:
        * Less convenient without the buttons, needing to type

### Sequence diagram when clicking the `Yes` and `No` buttons
#### Both diagrams are the same except for the inputs to the methods.
#### Refer to the [Yes Command diagram](#YesSequenceDiagram) for a more detailed insight into the Model
![YesCommand from FlashcardBox](images/UiYesButtonSequenceDiagram.png)

![NoCommand from FlashcardBox](images/UiNoButtonSequenceDiagram.png)


### Start and End Session

#### **Feature Overview**

The "Start and End Review Session" feature is designed for users to start or end a review session. It allows users to initiate and conclude dedicated language learning sessions where they focus on reviewing and practicing vocabulary words. Each session represents a focused period of language reviewing within the application.

#### **Implementation**

The implementation of the "Start and End Review Session" feature involves the introduction of a `SessionManager` component. The `SessionManager` tracks the following aspect of a review session:

- **isReviewSession:** Indicates whether the application in a review session or not.

In addition to that, `start` and `end` commands and their corresponding parsers are also implemented.

Given below is an example usage scenario and how the start/end mechanism behaves at each step.

**Step 1:** The user launches the application for the first time. The `SessionManager` is not yet initialized.

**Step 2:** The user executes the "start" command by interacting with the command line. This will make `FlashlingoParser` class to create its `SessionManager` instance.

![SessionManagerClass](images/SessionManagerClass.png)
<br>
**Note**: The `SessionManager` class adheres to the **Singleton pattern**, guaranteeing that only one instance of the class
can exist. This architectural choice provides a single point of access for managing review sessions and
maintaining the state of whether the session is a review session or not. With the Singleton pattern in place, you can be
confident that there is only one `SessionManager` instance, making it a centralized and controlled entity for session
management within the application.

**Step 3:** The user executes various commands within the action sequence, such as `yes` and `no`.

**Step 4:** The user chooses to end the review session by using `end` command. This action will alternate the boolean value
inside SessionManager class indicating current session is review session or not.

![StartSequenceModel](images/StartSequenceDiagram.png)
<br>
**Recording Vocabulary Review:**
- The `SessionManager` logs the vocabulary words and phrases reviewed and practiced during the language learning session.
- This feature provides users with the ability to track their progress and revisit the words they've worked on.

**User Control:**
- Users can initiate a new language learning session at any time and conclude their session when they have completed their vocabulary review.
- The option to start and end language learning sessions is typically available in the language learning module or settings.

**Data Retention:**
- Session data, including start and end times and vocabulary review records, is stored locally on the user's device.
- Users may have the option to export or clear session data as needed.

**Session Summary:**
- Users can view a summary of their language learning session, including the start and end times and a list of vocabulary words and phrases reviewed.

**Privacy and Security:**
- The application should ensure the privacy and security of session data, particularly if it contains sensitive language learning content.

#### **Usage Example**

1. **Starting a Language Learning Session:** The user accesses the language learning module within the application and chooses to start a new language learning session.

2. **Vocabulary Review:** During the session, the user focuses on reviewing and practicing specific vocabulary words and phrases relevant to their language learning goals.

3. **Ending the Language Learning Session:** When the user is satisfied with their vocabulary review, they conclude the language learning session from the language learning module or settings.

4. **Session Summary:** Users can access a summary of the language learning session, which includes the start and end times and a list of vocabulary words and phrases reviewed.

5. **Reviewing Session History:** Users have the option to revisit their session history, allowing them to track their language learning progress over time.

#### **Design Considerations**

**Aspect: How start & end executes**
* Alternative 1 (current choice): Creating another separate class to manage the logic.
    * Pros: It better adheres to OOP principle and easier to maintain.
    * Cons: It may potentially increase the complexity of codes.

* Alternative 2: Introducing a boolean attribute inside `FlashlingoParser` class.
    * Pros: Easy to implement.
    * Cons: It doesn't conform to the principle of **Single Responsibility Principle**.


**Aspect: Preventing Commands Within a Review Session**

* Alternative 1 (Current Choice): Restricting Users with a Subset of Commands

    * Pros:
    - Increased safety: A limited set of commands reduces the risk of unintended actions, making the review session safer for users.

    * Cons:
        - Limited flexibility: Users may feel constrained if they need to perform specific actions that are not allowed within the review session.
        - Potential user frustration: Restricting commands may lead to user frustration if they can't perform certain actions they expected to be available.

* Alternative 2: Giving Users Full Flexibility to Execute All Commands

    * Pros:
        - Complete control: Users have the freedom to use any command, providing them with full flexibility and control over their learning experience.
        - No perceived limitations: Users are less likely to encounter restrictions or frustrations, making the experience more intuitive.

    * Cons:
        - More error-prone: Allowing all commands may lead to unexpected bugs during a review session.

### Yes and No

#### **Feature Overview**

The "Yes" and "No" commands feature has been created with the intention of enabling users to provide feedback during the review session. It empowers users to share their opinions regarding whether they have successfully committed the words to memory or not. Following the receipt of this feedback, the application will adjust the proficiency level of the specific word in question. The greater the number of times a user memorizes a word, the higher its proficiency level will rise, and subsequently, the longer the user will have before revisiting that particular word during future review sessions.

*Usage*

Both commands can be executed with the following command words:

- For indicating successful memorization: `yes`
- For indicating unsuccessful memorization: `no`

### **Code Structure**

The YesCommand and NoCommand classes consist of important methods:

- `execute(Model model)`: This method is responsible for executing the command. It updates the model and retrieves the next review word. It returns a `CommandResult` with the success message and, if applicable, the response from the model.

#### **Implementation**

The implementation of the "Yes" and "No" commands invoke the `updateLevel` method of `FlashCard` and `hasNextRound` method of `Model`.

- **updateLevel:** updates the proficiency level of the current word and determines the next review date for the word based on the updated proficiency level.
- **hasNextRound:** determines whether there are more words to review.

The application recognizes a word as memorized if the user inputs either 'yes' or 'no.'

It's important to note that the 'yes' and 'no' commands are only functional once the review session has commenced.

Given below is an example usage scenario and how the yes/no mechanism behaves at each step.

**Step 1:** The user initiates the review session, and a single word appears on the screen without its translation.

**Step 2:** The user recalls the translation from memory and compares it with the provided translation.

**Step 3:** The user responds with either 'yes' or 'no' to indicate whether the recalled translation matches the recorded one.

**Step 4:** If 'yes' is chosen, the `updateLevel` method within the `FlashCard` class is invoked with the 'true' parameter; if 'no' is chosen, it is invoked with 'false'.

**Step 5:** Inside the `updateLevel` method, the proficiency level of the flash card is updated, and the next review date for the word is determined based on the updated proficiency level.

**Step 6:** The `hasNextRound` method within the `Model` class is invoked to determine whether there are more words to review.

**Step 7:** If there are more words to review, the next word is retrieved from the model and displayed on the screen. If there are no more words to review, the review session is concluded.

**Step 8:** The user repeats steps 2-7 until the review session is concluded.

The following sequence diagram summarizes the workflow when a user executes a `yes` command:

<a id="YesSequenceDiagram"></a>
![YesSequenceDiagram](images/YesSequenceDiagram.png)

**User Control:**
- User can update the proficiency level of every individual word and also the revisited date, which allows the user to learn in a more targeted manner

The following activity diagram summarizes what happens when a user executes a `yes` command:

![YesCommandActivityDiagram](images/YesCommandActivityDiagram.png)
#### **Usage Example**
To use the YesCommand, simply type yes during a review session. For example:

- `yes`
- `no`

#### **Design Considerations**

**Aspect: How to make our programs more interactive**
* Alternative 1 (current choice): Let the user judge whether he has remembered the word.
    * Pros: Get real-time feedback from users and adjust the proficiency of each word.
    * Cons: Users may trick the program to achieve higher accuracy.

* Alternative 2: Let the user enter the translation he recalls and the program compares it with the recorded translation.
    * Pros: Get real reactions from users.
    * Cons: It is difficult for the program to determine whether the meaning expressed by the two translations is consistent

### Light and Dark Mode

#### Implementation

The preference for light and dark themes is stored in the `UserPrefs` class. The `UserPrefs` is initialized by interacting
with `UserPrefsStorage` when the application is launched. Then `UI` component will obtain the preference from `Logic`
component and set the initial theme. After a `SwitchCommand` is executed by the `LogicManager`, `Model` component will
update the theme in  `UserPrefs`. Finally, `UI` component will update the theme accordingly.

Step 1: Theme initialization.
<br>
Similar to GUI settings, the theme is regarded as a component of user preference stored in `UserPrefs` and in Json
file **preferences.json**.

The initial theme setting works as follows: After constructing the `ModelManager` and `LogicManager` with the
loaded `UserPrefs`, `MainWindow` will obtain theme preference from `LogicManager` and set the initial theme. If no data
can be read from the preference file, the **Default** theme will be used by `Logic` and `Model` components, and set by
`UI`.

Step 2: Theme switching.
<br>
The following sequence diagram shows how the theme switching works. For the discussion purpose, parsing of the command
and `Storage#saveFlashlingo(ReadOnlyFlashlingo)` are omitted:

![SwitchSequenceModel](images/SwitchSequenceDiagram.png)

To be added: `Mainwindow#executeCommand`.

#### Design Considerations

**Aspect: How to update UI changes after command execution:**
* Alternative 1 (current choice): Uses `Logic` component to update `Model` and `Storage`. Add a boolean field **switchTheme**
  in `CommandResult`, informing `UI` to update similarly to what we did in **help** and **exit** commands.
    * Pros: Follows the separation of concerns principle. Each component is responsible for its own work and addresses
      separate concerns, achieving higher cohesion and lower coupling.
    * Cons: The abstraction and division for different components may be complicated and hard to understand. Additional
      field needed in `CommandResult` class.

* Alternative 2: Let `UI` component update the theme directly after receiving the command.
    * Pros: More direct implementation design.
    * Cons: Needs to include more information returned from the execution of command. A potential gap between current storage
      and UI theme setting would occur since `UI` wouldn't rely on `Logic` component to update the theme.


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* language learners, e.g. Duolingo preparers
* have a need to manage a significant number of vocabularies
* need to review learned vocab by scheduled plans
* prefer desktop apps over other types
* can type fast
* prefers typing to mouse interactions
* is reasonably comfortable using CLI apps


**Value proposition**:

Our product empowers users to efficiently manage their vocabulary through vivid flashcards and seamlessly review their acquired language skills according to scientific learning curves. With Flashlingo, language learners can master new words while staying in control of their customized language learning journey.


### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                     | So that I can…​                                                        |
| -------- | ------------------------------------------ | ------------------------------ | ---------------------------------------------------------------------- |
| `* * *` | language learner | add new flashcards |  |
| `* * *` | language learner | delete a flashcard |  |
| `* * *` | language learner | list all my flashcards | see what words are currently saved |
| `* * *` | language learner | save my list of flashcards | keep my progress of my words and the flashcards without having to re-input |
| `* * *` | language learner | load my flashcards | continue my progress from my last save point |
| `* * *` | language learner | flip over a flashcard | reveal the translated word to remember |
| `* * *` | forgetful language learner | be shown the flashcards to see | go through the required flashcards without needing to keep track of what needs to be read |
| `* *`   | not tech saavy language learner | be directed to the help page easily | use the application correctly |
| `* *`   | langauge learner | see my success rate for each flashcard | see what words are challenging |
| `*`     | multi language learner | categorise flashcards by language | study each language individually |
| `*`     | lazy language learner | get translations for the original word | add words in easily without manually searching for the translation |

### Use cases

**System:** Flashlingo\
**Use case:** UC1 - Help\
**Actor:** User\
**MSS:**

1.	User requests help by keying in command or clicking the Help Button on the UI
2.	Flashlingo opens browser with UserGuide

Use case ends.

**System:** Flashlingo\
**Use case:** UC2 – Add a word\
**Actor:** User\
**MSS:**
1.	User chooses to add a word and its translation by keying in command.
2.	Flashlingo adds the word and its translation.\
      Use case ends.

**Extensions:**\
1a. User adds word and translation and specifies the language of the original word and translation.\
1a1. Flashlingo adds the word and its translation as well as the language of both.\
Use case ends.


**System:** Flashlingo\
**Use case:** UC3 – Delete a word\
**Actor:** User\
**MSS:**
1.	User chooses to delete a word by keying in command
2.	Flashlingo deletes the word and its translation.\
      Use case ends.

**System:** Flashlingo\
**Use case:** UC4 – Display list of flashcards\
**Actor:** User\
**MSS:**
1.	User chooses to display list of flashcard.
2.	Flashlingo displays list of cards with words and corresponding translations.\
      Use case ends.

**System:** Flashlingo\
**Use case:** UC5 – Start today’s flashcard sequence\
**Actor:** User\
**MSS:**
1.	User chooses to start.
2.	Flashlingo displays the words user is going to study.\
      Use case ends.

**System:** Flashlingo\
**Use case:** UC6 – Display translation on the other side of flashcard\
**Actor:** User\
**MSS:**
1.	User chooses to flip the flashcard
2.	Flashlingo shows meaning of the word.\
      Use case ends.

**System:** Flashlingo\
**Use case:** UC7 – Indicate user has remembered word\
**Actor:** User\
**MSS:**
1.	User confirms remembrance of the word.
2. Flashlingo increments level of the flashcard.
3. Flashlingo displays congratulatory message.\
   Use case ends.

**Extensions:**\
2a. Flashlingo detects that level of flashcard exceeds threshold\
2a1. Flashlingo deletes the flashcard.\
Use case resumes from step 3.

**System:** Flashlingo\
**Use case:** UC8 – Indicate user has forgotten word\
**Actor:** User\
**MSS:**
1.	User indicates they couldn’t remember word.
2. Flashlingo decements level of flashcard.
3. Flashlingo displays motivational message to keep up.\
   Use case ends.

**Extensions:**\
2a. Flashlingo detects that level of flashcard is at base level of 1\
2a1. Flashlingo does not decrement any further, leaving level at 1.\
Use case resumes from step 3.

**System:** Flashlingo\
**Use case:** UC9 – Stop session\
**Actor:** User\
**MSS:**
1.	User chooses to stop session.
2.	Flashlingo stops and displays the completion message.\
      Use case ends.

**System:** Flashlingo\
**Use case:** UC10 – Exit the platform\
**Actor:** User\
**MSS:**
1.	User chooses to exit
2.	Flashlingo closes GUI and terminates.\
      Use case ends.

**System:** Flashlingo\
**Use case:** UC11 – Change data source\
**Actor:** User\
**MSS:**
1.	User chooses to change data source by adding new file-path.
2.	Flashlingo changes data source and displays success message.\
      Use case ends.

**System:** Flashlingo\
**Use case:** UC12 – Load data source\
**Actor:** user\
**MSS:**
1.	User chooses to load a data source at input file-path.
2.	Flashlingo loads data source and displays success or failure message.\
      Use case ends.

### Non-Functional Requirements

1.  **Environment** - Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  **Environment** - Should be able to store 1000 words with less than 10MB storage.
3.  **Performance** - A user with above average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  **Performance** - Should be able to handle any user input within 2 seconds.
5.  **Quality** - Should be able to update learned words according to schedule and maintain the left ones when a learning session accidentally closes.
6.  **Quality** - Should be able to provide the learner with a reasonable and personalized time schedule for language learning.
7.  **Quality** - Should be able to handle any user input correctly without crashing.
8.  **Capacity** - Should be able to hold up to 100 flash cards without a noticeable sluggishness(longer than 2 seconds) in performance for typical usage.

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Proficiency level**: A number that indicates how well a user knows a word. The higher the number, the better the user knows the word.
* **Flashcard**: A virtual card with a word on one side and its translation on the other side
* **Word**: A word in the language you want to learn
* **Translation**: The word in your native language that corresponds to the word you want to learn

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Planned Enhancement**

### Enhancement 1: Improving UI

**Feature Flaw**

* Users are not able to see the words clearly as the current font size is too small.
* Users now are not able to check the translation language unless they enter the `reveal` command. This is not user-friendly as users may want to know the translation language before revealing the translation.

**Proposed Enhancement**
* Increasing the font size of the words and translations in the flash cards.
* Adding language tag under the `level` tag on each flash card. This will allow users to distinguish different languages of the translations before calling the `reveal` command.

**Sample UI**  

![img.png](images/LanguageTag.png)

### Enhancement 2: Improving the content of output messages

**Feature Flaw**

Some of our current output messages are not formatted properly. For example, the message entering the `reveal` command is "Flashcard has been revealed!" followed directly by the translation. Users may be confused to distinguish the message and the translation.

**Proposed Enhancement**
* Formatting the messages to be more clear and user-friendly.
* Specifying output messages to be more detailed and grammatically correct.

**Sample Output**

| Current Output Message               | After Enhancement         |
|--------------------------------------|---------------------------| 
| ![img.png](images/RevealMessage.png) | Translation: word         |
| ![img.png](images/ReviewMessage.png) | ...1 flashcard(s) listed! |


--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for manual testing**

Given below are instructions to test the app manually.

<div markdown="span" class="alert alert-info">:information_source: **Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</div>

### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    2. Double-click the jar file<br>
       Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

2. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    2. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

3. Adding/deleting a flash card

    1. Try out the `add` command in the command box.<br>
         Expected: A new flash card is added to the flash card list.
    2. Apply `delete 1` command in the command box.<br>
         Expected: The flash card added in the previous step is successfully deleted.
