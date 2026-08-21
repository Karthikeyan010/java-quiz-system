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





