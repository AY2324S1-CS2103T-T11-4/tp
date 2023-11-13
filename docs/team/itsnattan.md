---
layout: page
title: Nathanael M. Tan's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: UI "yes", "no" and "reveal/hide" buttons
  * What it does: Invoke the yes, no and reveal command upon clicking
  * Justification: Makes it more convenient for users who prefer a GUI interaction and seamlessly integrate it to perform exactly as the CLI command would.
  * Highlights: In order to integrate it seamlessly to perform exactly as the CLI would, it required some changes to the structure of the architecture, so that the UI would be able to access some other part of the code in order to execute. Furthermore, it would also require the UI to respond synchronously with the execution of the commands, requiring a deeper understanding of JavaFX and the pre-existing code in order to "refresh" for new data.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=itsNatTan&breakdown=false)

* **Project management**:
  * Watched deadlines, ensured that internal deadlines were met so that progress was smooth.
  * Kept a close watch on code quality, ensuring that proper coding standards were adhered to.
  * Interacted closely with TA and Prof to make sure that certain things are allowed and that we are doing things right.

* **Enhancements to existing features**:
  * Changed the UI to accept FlashCards instead of People (Adapted to our project)
  * Added critical functionality to the UI to make things work smoothly and is easy to use.
  * Add some tests for the flashcard model to ensure that it is working as intended

* **Documentation**:
  * User Guide:
    * Created the UGDraft.md file, and added the documentation for the help, exit, save, load functions.
    * Create the Table of Content, and designed the notation for command to ensure that users will have clarity when deciphering the unfamiliar formats. 
    * Ensured that all command formats followed the proper standardised notation for ease of use.
    * Update Quick Start section to accommodate less technologically savvy users as well as a section on how to use the application.
    * Ensured all hyperlinks work as intended.
  * Developer Guide:
    * Created User Stories section.
    * Added some extensions to Use Case section.
    * Created sequence diagrams for the UI buttons
    * Changed some UML diagrams to adapt to our current program

* **Contributions to team-based tasks**:
  * Ensuring coding standard is followed
  * Managing the GitHub repository
  * Issue creation and assignment
  * Assisted in refactoring fragments of code left behind by AB3 into FlashLingo
  * Extensive bug-hunting throughout the creation of the product

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#324](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/324#pullrequestreview-1722643494), [#194](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/194)
  * Put in full effort in bug hunting during the PE-D, finding a total of 15 bugs for the other team.
