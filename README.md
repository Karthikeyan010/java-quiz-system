\# Java Quiz System



A modular quiz management system built with \*\*Java 21\*\* and \*\*Maven\*\*, designed around object-oriented programming principles.



The application provides a foundation for creating and managing quizzes with different question types while keeping responsibilities separated across models, interfaces, factories, and implementation classes.



\## Features



\* Create and manage quiz questions

\* Support for \*\*multiple-choice questions\*\*

\* Support for \*\*free-response questions\*\*

\* Maintain a reusable pool of quiz questions

\* Generate and manage quizzes

\* Create and manage student objects

\* Evaluate responses and represent outcomes using verdicts

\* Provide quiz-related statistics

\* Use factory classes for question and student creation

\* Validate core application behaviour through automated unit tests



\## Tech Stack



\* \*\*Java 21\*\* — core application development

\* \*\*Maven\*\* — dependency management and build automation

\* \*\*JUnit 5\*\* — primary unit-testing framework

\* \*\*JUnit 4\*\* — support for existing legacy tests

\* \*\*Git\*\* — version control

\* \*\*GitHub\*\* — source-code hosting and project management



\## Prerequisites



To build and test the project locally, install:



\* Java Development Kit (JDK) 21

\* Apache Maven

\* Git



Verify the required tools are available:



```bash

java -version

mvn -version

git --version

```



\## Project Structure



The application follows a package-based structure that separates interfaces, implementations, factories, and domain models.



```text

src/

├── main/

│   └── java/

│       └── com/

│           └── quizsystem/

│               ├── Main.java

│               ├── factory/

│               │   ├── QuestionFactory.java

│               │   └── StudentFactory.java

│               ├── implementations/

│               │   ├── QuestionPoolManager.java

│               │   ├── QuizImpl.java

│               │   └── StatisticsProviderImpl.java

│               ├── interfaces/

│               │   ├── Question.java

│               │   ├── QuizGenerator.java

│               │   └── StatisticsProvider.java

│               └── models/

│                   ├── FreeResponseQuestion.java

│                   ├── MultipleChoiceQuestion.java

│                   ├── Student.java

│                   └── Verdict.java

└── test/

&#x20;   └── java/

&#x20;       ├── FreeResponseQuestionTest.java

&#x20;       ├── MultipleChoiceQuestionTest.java

&#x20;       ├── QuestionFactoryTest.java

&#x20;       ├── QuestionPoolManagerTest.java

&#x20;       ├── QuizImplTest.java

&#x20;       ├── StatisticsProviderImplTest.java

&#x20;       ├── StudentFactoryTest.java

&#x20;       └── StudentTest.java

```



\### Package Responsibilities



\* `factory` — centralises creation of questions and students.

\* `implementations` — contains concrete implementations for quiz generation, question-pool management, and statistics.

\* `interfaces` — defines contracts used by the core quiz functionality.

\* `models` — contains the domain objects representing questions, students, and verdicts.

\* `test` — contains automated tests for the main application components.



\## Architecture \& Design



The Quiz System is structured around object-oriented design principles, with responsibilities separated between domain models, interfaces, factories, and concrete implementations.



\### Question Abstraction



The `Question` interface defines the common contract for questions within the system.



Two question models provide specialised behaviour:



\* `MultipleChoiceQuestion` represents questions with predefined answer choices.

\* `FreeResponseQuestion` represents questions that accept free-form responses.



Using a shared question abstraction allows the quiz system to work with different question types without tightly coupling quiz logic to a specific implementation.



\### Factory Pattern



Object creation is separated through dedicated factory classes:



\* `QuestionFactory` handles question creation.

\* `StudentFactory` handles student creation.



This keeps construction logic separate from the components that use those objects and makes the application easier to extend.



\### Quiz Management



`QuizGenerator` defines the quiz-generation contract, while `QuizImpl` provides the concrete quiz implementation.



`QuestionPoolManager` is responsible for managing the collection of questions available to the quiz system.



\### Statistics



Statistics functionality is separated through:



\* `StatisticsProvider`

\* `StatisticsProviderImpl`



This keeps statistics-related behaviour independent from the main quiz and domain logic.



\### Object-Oriented Principles



The project demonstrates several core software-engineering concepts:



\* \*\*Abstraction\*\* through interfaces

\* \*\*Encapsulation\*\* within domain models

\* \*\*Polymorphism\*\* through different question implementations

\* \*\*Factory pattern\*\* for controlled object creation

\* \*\*Separation of concerns\*\* across packages and components

\* \*\*Testability\*\* through independently testable classes



\---



\## Installation and Setup



\### 1. Clone the Repository



```bash

git clone https://github.com/Karthikeyan010/java-quiz-system.git

cd java-quiz-system

```



\### 2. Verify Java



The project targets Java 21.



```bash

java -version

```



\### 3. Verify Maven



```bash

mvn -version

```



\### 4. Build the Project



Compile the project and run its tests with:



```bash

mvn clean package

```



A successful build should finish with:



```text

BUILD SUCCESS

```



\### 5. Run the Test Suite



Tests can be executed independently with:



```bash

mvn clean test

```



\---



\## Testing



The project includes automated unit tests covering the main domain and application components.



\### Test Coverage



| Component                 | Test Class                   |

| ------------------------- | ---------------------------- |

| Free-response questions   | `FreeResponseQuestionTest`   |

| Multiple-choice questions | `MultipleChoiceQuestionTest` |

| Question creation         | `QuestionFactoryTest`        |

| Question pool management  | `QuestionPoolManagerTest`    |

| Quiz implementation       | `QuizImplTest`               |

| Statistics                | `StatisticsProviderImplTest` |

| Student creation          | `StudentFactoryTest`         |

| Student model             | `StudentTest`                |



The current test suite contains both \*\*JUnit 5\*\* and \*\*JUnit 4\*\* tests. JUnit 5 is used for most tests, while JUnit 4 is retained for compatibility with existing tests.



Run all tests using:



```bash

mvn test

```



Before changes are committed, the project can be validated with:



```bash

mvn clean test

```



\---



\## Future Improvements



Potential enhancements for the project include:



\* Migrate the remaining JUnit 4 tests to JUnit 5

\* Add persistent storage for students, questions, quizzes, and results

\* Introduce configurable quiz categories and difficulty levels

\* Add additional question types

\* Add exception handling and input validation where appropriate

\* Expose quiz functionality through REST APIs

\* Develop a web-based user interface

\* Add GitHub Actions for automated build and test validation

\* Expand edge-case and integration testing

\* Improve application configuration and logging



\---



\## Contributing



Contributions and suggestions are welcome.



To contribute:



1\. Fork the repository.

2\. Create a feature branch.

3\. Make and test your changes.

4\. Commit the changes with a descriptive message.

5\. Push the branch to your fork.

6\. Open a pull request.



For example:



```bash

git checkout -b feature/example-improvement

mvn clean test

git add .

git commit -m "feat: add example improvement"

git push origin feature/example-improvement

```



\---



\## Author



\*\*Karthikeyan\*\*



GitHub: \[@Karthikeyan010](https://github.com/Karthikeyan010)



\---



If you find the project useful or interesting, feel free to explore the source code, open an issue, or contribute an improvement.





