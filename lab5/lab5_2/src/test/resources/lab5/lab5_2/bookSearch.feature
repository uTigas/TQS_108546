Feature: Book Search
    Background: Book Library
        Given the following books
        |title                                    | author              | tag                            | year | publisher                     |
        |1984                                     | George Orwell       | Dystopian fiction              | 1949 | Secker & Warburg (UK)         |
        |To Kill a Mockingbird                    | Harper Lee          | Bildungsroman, Southern Gothic | 1960 | J.B. Lippincott & Co. (US)    |
        |The Catcher in the Rye                   | J.D. Salinger       | Bildungsroman                  | 1951 | Little, Brown and Company (US)|
        |The Great Gatsby                         | F. Scott Fitzgerald | Literary fiction, Jazz Age     | 1925 | Charles Scribner's Sons (US)  |
        |Harry Potter and the Philosopher's Stone | J.K. Rowling        | Fantasy, Young Adult           | 1997 | Bloomsbury (UK)               |

    Scenario: Search by author 
        When I search by author 'George Orwell'
        Then I get the Book with title '1984'

    Scenario: Search by tags 
        When I search by tag 'Bildungsroman'
        Then I get 2 books

    Scenario: Search by Year 
        When I search books published between 1950 and 1990
        Then I get 2 books
        