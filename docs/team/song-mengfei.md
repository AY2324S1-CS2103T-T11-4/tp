---
layout: page
title: Song Mengfei's Project Portfolio Page
---

### Project: Flashlingo

FlashLingo is a desktop application to facilitate the learning of new languages through flashcards. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **New Feature**: 
  * Added the ability to update the proficiency level of reviewed flash cards.
      * What it does: It receives the user's feedback on the flash card and updates the proficiency level of the flash card accordingly.
      * Justification: The implementation of `Yes` and `No` commands allows users to actively review words, thereby enhancing their memory retention.
      * Highlights: This upgrade impacts both current and future commands, demanding an in-depth analysis of design options. Integrating the Singleton pattern posed challenges, necessitating complex adjustments to existing commands.
  * Added the ability to filter flash cards by their language or review date.
      * What it does: It lists all flash cards with the specified language or the review date before the given date.
      * Justification: The implementation of `Review` and `Language` command allows users to view all flash cards in the flash card list.
      * Highlights: This upgrade impacts both current and future commands, demanding an in-depth analysis of design options. Integrating the Singleton pattern posed challenges, necessitating complex adjustments to existing commands.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=song-mengfei&breakdown=true)

* **Enhancements to existing features**:
    * Update `FindCommand` to support searching by `KEYWORD` [#83](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/83) [#89](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/89)
    * Update `AddCommand`, `AddCommandParser` and their test. Users can add flash cards with optional parameters`WordLanguage` and `TranslationLanguage` [#161](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/161) [#302](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/302)
    * Update `EditCommand`, `EditCommandParser` and their test. Users can edit a flash card with at least one and at most four parameters[#216](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/216) [#305](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/305)
    * Update existing test file [#173](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/173)
    * Fix checkstyle issues

* **Documentation**:
    * User Guide:
        * Added documentation for the features `add`, `delete`, `edit` and `switch`: [#202](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/202)
    * Developer Guide:
        * Added implementation details of the features `yes` and `no` commands: [#176](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/176)

* **Contributions to team-based tasks**:
    * Ensuring coding standard is followed
    * Issue creation and assignment
* **Community**:
    * PRs reviewed (with non-trivial review comments):[#318](https://github.com/AY2324S1-CS2103T-T11-4/tp/pull/318)
