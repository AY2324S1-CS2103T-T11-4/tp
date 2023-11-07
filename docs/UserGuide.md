---
layout: page
title: User Guide
---

Flashlingo is a **desktop app for learning words by flash cards, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). Flashlingo is designed with beginner language learners in mind, specialising in helping them expand their vocabulary.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `flashlingo.jar` from [here](https://github.com/AY2324S1-CS2103T-T11-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your Flashlingo.

1. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar flashlingo.jar` command to run the application.
   Below shows the steps to perform such a task
    1. Open up the terminal. For mac users, open up `terminal` through the finder. For windows users, search `cmd` and run it.
       <img width="674" alt="Screenshot 2023-10-27 at 2 36 16 PM" src="https://github.com/itsNatTan/tp/assets/64185574/039d9f37-e45e-410f-b819-117ff312e13b">

    3. Navigate to the folder containing the jar file. In this example, it is in the Downloads folder.
   <br>
       `$ cd Downloads`

    4. Simply type in java -jar flashlingo.jar to get started!
   <br>
         `$ java -jar flashlingo.jar`
       <br>
       A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
       ![Ui](images/MainUi.png)

### Using the application

1. Type the command in the command box and press Enter to execute it.
   <img width="728" alt="Screenshot 2023-10-27 at 3 14 07 PM 1" src="https://github.com/itsNatTan/tp/assets/64185574/a4a8dbd2-94ff-417d-84a4-175bf85ff100">

2. The image below shows the result of typing in a command. A log message will be displayed below the command box to give information about the outcome of the command.
   <img width="729" alt="Screenshot 2023-10-27 at 3 11 50 PM" src="https://github.com/itsNatTan/tp/assets/64185574/48b74efc-2a39-48d5-b0aa-70f165484c5e">

3. Refer to the [Commands](#commands) below for details of each command.


--------------------------------------------------------------------------------------------------------------------

## Commands

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `<UPPER_CASE>` are the parameters to be supplied by the user.<br>
  e.g. in `delete <INDEX>`, index is a parameter which needs to be added to the command, without the <>. `delete 1` is an example of the usage.

* Words in square brackets, ie. `[<UPPER_CASE>]` indicate that the parameter is optional and can be omitted if deemed unnecessary
  e.g. in `add w/<WORD> t/<TRANSLATION> [wl/WORD_LANGUAGE] [tl/TRANSLATION_LANGUAGE]`, the `WORD` and `TRANSLATION` parameters are *MANDATORY*, whereas the `WORD_LANGUAGE` and `TRANSLATION_LANGUAGE` do not need to be in the command.
  For example, both `add w/entschuldigung wl/Deutsch t/sorry tl/English` and `add w/regarder t/look` are valid usage of the command

* If the parameters have ... as a suffix, ie. `<UPPER_CASE...>`, it means that it can take multiple parameters that are comma separated.
* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Viewing help : `help`

Opens a browser with the help page (User Guide).

Format: `help`

### Listing all flash cards : `list`

Shows the list of flash cards with both the original word and the corresponding translation.
* Lists all the flash cards saved
  
Format: `list`
> All the saved flash cards, regardless of the review date, are listed.

Output:

| Before/After the review session | ![img.png](images/ListSuccess.png) |
|:-----------------------:|:------------------------------------------:|
| **During the review session** | ![img.png](images/ListDuringReview.png) |

<div markdown="block" class="alert alert-info">

**:information_source: Things to note about the `list` command:**<br>
* `list` command cannot be used during a review session
   * To ensure retention, only the flash cards - with the words to be reviewed - can be seen during the review session.
   * As soon as the review session ends, all the flash cards can be listed once again
> The error message:<br>
> Sorry, currently you are in a review session. Your command is not supported.<br>
> Please end the review session first.
</div>

### Adding a flash card: `add`
:information_source: Notes about adding words:
* In order to give users full customizability, there's no any restriction on input, as long as it is not empty.

Adds a word to the flash card with its translation.
* Creates a wild flash card.
* Works to add a word with its translation in their respective languages.
* The already saved translation can be overridden with a new translation in a different language.

Format: `add w/<WORD> t/<TRANSLATION> [wl/<WORD_LANGUAGE>] [tl/<TRANSLATION_LANGUAGE>]`
> The default language for both word and translation are blank (`""`)

Examples:
* `add w/雪 t/snow` saves the translation of **雪** as **snow**
* `add w/雪 wl/Chinese t/snow tl/English` saves the translation of the **Chinese** word **雪** as an **English** word **snow**

| Words without language  | ![img.png](images/WordWithoutLanguage.png) |
|:-----------------------:|:------------------------------------------:|
| **Words with language** |  ![img.png](images/WordWithLanguage.png)   |

**Note**
* Users are not allowed to add two **exact** same flash cards
    * Same word **and** word language
    * Same translation **and** translation language
* Users can add two flash cards with the **same word and translation BUT in different language**<br>
  The following three commands can exist in the same list:
    * `add w/雪 t/snow`
    * `add w/雪 t/snow wl/Chinese tl/English`
    * `add w/雪 t/snow wl/Japanese tl/English`
> The error message:<br>
> This flash card already exists


### Deleting a flash card : `delete`

Deletes a words and its related information
* Deletes a flash card.

Format: `delete <INDEX>`

Examples:
* `delete 2` deletes the word and its translation at index 2

Output:

|  Before delete   | ![img.png](images/BeforeDelete.png) |
|:----------------:|:-----------------------------------:|
| **After delete** | ![img.png](images/AfterDelete.png)  |

### Editing a flash card : `edit`

Edits the word and its translation at the given index.
* Edits a wild flash card.

Format: `edit <INDEX> [w/<WORD>] [t/<TRANSLATION>] [wl/<WORD_LANGUAGE>] [tl/<TRANSLATION_LANGUAGE>]`

Examples:
* `edit 1 w/こんにちわ t/Hello` edits the translation at index 1

Output:

|  Before edit   |![img.png](images/BeforeEdit.png)  |
|:--------------:|:---------------------------------:|
| **After edit** | ![img.png](images/AfterEdit.png)  |

**Note**
* Users are not allowed to edit a flash card to an existing flash card
* `<INDEX>` is the index of each flash card in the `list`
* Users change at **LEAST** one certain parameter by using certain prefix
    * Word: `w/WORD`
    * Word Language: `wl/WORD_LANGUAGE`
    * Translation: `t/TRANSLATION`
    * Translation Language: `tl/TRANSLATION_LANGUAGE`
> The error message: <br>
> `This flash card already exists in Flashlingo`

### Finding a flash card : `find`

Finds words whose original word or translation contains the given keyword.
* Finds a flash card.
* The search is insensitive. e.g `look` will match `Look`

Format: `find <KEYWORDS...>`

Examples:
* `find look` returns the flash card list and its translation that contains the keyword `look`
* `find look, hello, goodbye` returns the flash card list that has all flash cards that contain `look`, `hello` and `goodbye`


###  Starts review session : `start`

To start a review session, user simply needs to type in `start` command.

Format: `start`

Output: `Review Session has been started.`

**Note**
* Users are not allowed to start a new review session if they are already in one. In this case,
`Sorry, currently you are in a review session. Your command is not supported. Please end the review session first.` will be prompted.
* If there are no words to review, users will not be able to start review session. `You have no more words to review!`
will be displayed.


### Ending the current review session : `end`

Ends the current flash card session and returns to the main menu.

Format: `end`

Output: `Review Session has ended.`

**Note**
* Users are not allowed to end a review session if the session hasn't been started yet. The message of `You are not in a review session.`
will be given.

### Flipping to the other side of the flashcard: `flip`

To show the translation of the flash card in

Format: `flip [<INDEX>]`

Output : `Flashcard has been flipped!`

Examples:![img.png](images/Reveal.png)

**Note**
* When <INDEX> is omitted, the default value is 1.
* Pressing `reveal` or `hide` button will have the same effect.

### Indicating user has memorized the word : `yes`

Marks the word as memorized and advances the word into the next retention stage. If there are still remaining words to review,
they will be automatically shown in the section below. Otherwise, review session will be closed by default.

Format: `yes`

Output: ![img.png](images/Yes.png)
or
![img.png](images/Yes2.png)
if there's no word left in the review session.

**Note**
* `yes` command will only take effect during review session. Otherwise, error message `You are not in a review session.`
  will be printed out.
* Pressing `yes` button will have the same effect.

###  Indicating user has forgotten the word : `no`

Marks the word as not grasped and leaves it in its current retention stage. If there are still remaining words to review,
they will be automatically shown in the section below. Otherwise, review session will be closed by default.

Format: `no`

Output: ![img.png](images/No.png)
or
![img.png](images/No2.png)
if there's no word left in the review session.
**Note**
* `no` command will only take effect during review session. Otherwise, error message`You are not in a review session.`
will be printed out.
* Pressing `no` button will have the same effect.

### Show learning statistics : `stats`

To help track user progress and inspire continued learning, this command offers detailed statistics:
* **Total Flash Cards**: Displays the total count of flash cards you have saved.
* **Remembered Words**: Shows the number of terms you have successfully retained.
* **Success Rate**: Presents a percentage representing your learning success, motivating you to keep improving.

**Format**: `stats`
**Example**:
*`stats` would give the following output

Output:
![img.png](images/Stats.png)

<div markdown="block" class="alert alert-info">

**:information_source: Things to note about the `stats` command:**<br>
* The stats command is operational exclusively outside active review sessions.
* If attempted inside a review session, the system will respond with the following error message:
> Sorry, currently you are in a review session. Your command is not supported.<br>
> Please end the review session first.
</div>

### Filtering list with specified language : `language`

Displays a list where each word is from specified language.

Format: `language <SPECIFIED_LANGUAGE>`

### Getting list for revision : `review`

Displays the flash cards of all the words to be reviewed that day
* The `review` command will display only the flash cards due for study on the current day. If you wish to see all your saved flash cards without date restrictions, please enter the `list` command.

Format: `review`

**Output:**
| When there are words to be reviewed | ![img.png](images/ReviewSuccess.png) |
|:--------------:|:---------------------------------:|
| **When no words to be reviewed** | ![img.png](images/ReviewOver.png)  |
<div markdown="block" class="alert alert-info">

**:information_source: Things to note about the `review` command:**<br>
* Flash cards will not be displayed in the following scenarios:
   * No flash cards are scheduled for review on the current day.
   * All flash cards due for review on the current day have already been completed.
> The error message:<br>
> 0 flashcards listed!<br>
> []
</div>

### Loading list of words: `load`
Loads an Excel file of words into the app. The words will be added to the current list of flash cards and included in the
review session automatically.

Format: `load <FILE_NAME>`

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the file input:**<br>
* **File format:** The file must be an Excel Workbook with the `.xlsx` extension.
* **File location:** The file must be located in the **same folder** as the `flashlingo.jar` file.
* **File content:** The app would only read the **first sheet** of the workbook. The sheet must contain only the following
  **two** columns:
    1. First column - The original words
    2. Second column - The translation of the words
* **Resources:** A sample file can be found [here](SampleData.xlsx).
</div>

Output:
* You have successfully loaded file: `<FILE_NAME>`: Successful loading.
* File not found or accessible:
>⚠️ Make sure the file is in the correct directory with read permission.
* File cannot be read due to invalid content or format:
>⚠️ Make sure the file contains only two columns with the nonempty words/translations.
* `FLASH_CARD` flash card already exists!:
>⚠️ Modify the duplicated word/translation in the file.

### Switching color theme : `switch`
Switches between light and dark appearance of UI dashboard.
* The theme will be saved and loaded when Flashlingo is restarted.
* Default color theme is the **light theme**.

Format: `switch`

Output:

|  light theme   | ![img.png](images/LightTheme.png) |
|:--------------:|:---------------------------------:|
| **dark theme** | ![img.png](images/DarkTheme.png)  |

### Exiting the program : `exit`

Safely terminates the Flashlingo application and closes the graphical user interface (GUI).

Format: `exit`

Example:
* Input exit to end your session and close the application.


### Saving the data

Flashlingo data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

Flashlingo data are saved automatically as a JSON file `[JAR file location]/data/flashlingo.json`. Advanced users are welcome to update data directly by editing that data file.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
If your changes to the data file makes its format invalid, Flashlingo will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.
</div>

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Flashlingo home folder.

**Q**: How can I import my data to the app?<br>
**A**: Save your words and translations in the format specified above in an Excel file. Move the file to the same folder with `flashlingo.jar`.
Then, use the `load` command to import the data.

--------------------------------------------------------------------------------------------------------------------

## Command summary

| Action                  | Format, Examples                                                                                                           |
|-------------------------|----------------------------------------------------------------------------------------------------------------------------|
| **Help**                | `help`                                                                                                                     |
| **List**                | `list`                                                                                                                     |
| **Add**                 | `add w/<WORD> t/<TRANSLATION> [wl/<WORD_LANGUAGE>] [tl/<TRANSLATION_LANGUAGE>]` <br> e.g., `add w/regarder t/look`         |
| **Delete**              | `delete <Index>`<br> e.g., `delete 1`                                                                                      |
| **Edit**                | `edit <INDEX> [w/<WORD>] [t/<TRANSLATION>] [wl/<WORD_LANGUAGE>] [tl/<TRANSLATION_LANGUAGE>]`<br> e.g., `edit 1 w/bye t/再见` |
| **Find**                | `find KEYWORD`<br> e.g., `find bye`                                                                                        |
| **Start**               | `start`                                                                                                                    |
| **Reveal**              | `reveal <INDEX>`                                                                                                           |
| **Yes**                 | `yes`                                                                                                                      |
| **No**                  | `no`                                                                                                                       |
| **End**                 | `end`                                                                                                                      |
| **Language**            | `language SPECIFIED_LANGUAGE`<br> e.g., `language French`                                                                  |
| **Review**              | `review`                                                                                                                   |
| **Learning Statistics** | `stats`                                                                                                                    |
| **Load**                | `load FILE_NAME`<br> e.g., `load SampleData.xlsx`                                                                          |
| **Switch**              | `switch`                                                                                                                   |
| **Exit**                | `exit`                                                                                                                     |
