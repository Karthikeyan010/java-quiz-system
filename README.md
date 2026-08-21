<div align="center">

# 🎯 Java Quiz System

### A modular, object-oriented quiz management system built with Java

**Java 21 • Maven • JUnit • OOP • Factory Pattern**

</div>

---

## 📖 Overview

**Java Quiz System** is a modular quiz application developed using **Java 21** and **Maven**.

The project demonstrates practical object-oriented software design by separating responsibilities across **interfaces, implementations, factories, and domain models**.

It supports multiple question types, student management, quiz generation, answer evaluation, question-pool management, and statistics, with automated tests covering the core components.

---

## ✨ Features

* 📝 Create and manage quiz questions
* 🔘 Support **multiple-choice questions**
* ✍️ Support **free-response questions**
* 🗂️ Maintain a reusable question pool
* 🎯 Generate and manage quizzes
* 👨‍🎓 Create and manage students
* ✅ Evaluate responses and produce verdicts
* 📊 Provide quiz-related statistics
* 🏭 Factory-based object creation
* 🧪 Automated unit testing of core components

---

## 🛠️ Tech Stack

| Technology  | Purpose                              |
| ----------- | ------------------------------------ |
| **Java 21** | Core application development         |
| **Maven**   | Build and dependency management      |
| **JUnit 5** | Primary unit-testing framework       |
| **JUnit 4** | Compatibility with existing tests    |
| **Git**     | Version control                      |
| **GitHub**  | Repository hosting and collaboration |

---

## 🏗️ Architecture

The project follows a package-based architecture that separates contracts, implementations, object creation, and domain models.

```text
src/
├── main/
│   └── java/
│       └── com/
│           └── quizsystem/
│               ├── Main.java
│               │
│               ├── factory/
│               │   ├── QuestionFactory.java
│               │   └── StudentFactory.java
│               │
│               ├── implementations/
│               │   ├── QuestionPoolManager.java
│               │   ├── QuizImpl.java
│               │   └── StatisticsProviderImpl.java
│               │
│               ├── interfaces/
│               │   ├── Question.java
│               │   ├── QuizGenerator.java
│               │   └── StatisticsProvider.java
│               │
│               └── models/
│                   ├── FreeResponseQuestion.java
│                   ├── MultipleChoiceQuestion.java
│                   ├── Student.java
│                   └── Verdict.java
│
└── test/
    └── java/
        ├── FreeResponseQuestionTest.java
        ├── MultipleChoiceQuestionTest.java
        ├── QuestionFactoryTest.java
        ├── QuestionPoolManagerTest.java
        ├── QuizImplTest.java
        ├── StatisticsProviderImplTest.java
        ├── StudentFactoryTest.java
        └── StudentTest.java
```

### Package Responsibilities

| Package           | Responsibility                                                        |
| ----------------- | --------------------------------------------------------------------- |
| `factory`         | Centralises creation of questions and students                        |
| `implementations` | Contains concrete quiz, question-pool, and statistics implementations |
| `interfaces`      | Defines contracts used by the core application                        |
| `models`          | Contains domain objects such as questions, students, and verdicts     |
| `test`            | Contains automated tests for the application's core components        |

---

## 🧩 Design

### Question Abstraction

The `Question` interface provides a common abstraction for question types.

The application currently provides:

* `MultipleChoiceQuestion`
* `FreeResponseQuestion`

This allows quiz functionality to work with different question implementations through a shared contract.

### Factory Pattern

Object creation is separated into dedicated factories:

```text
QuestionFactory  → Question creation
StudentFactory   → Student creation
```

This keeps construction logic separate from the components that consume those objects.

### Quiz Management

`QuizGenerator` defines the quiz-generation contract, while `QuizImpl` provides its concrete implementation.

`QuestionPoolManager` manages the collection of questions available to the quiz system.

### Statistics

Statistics responsibilities are separated through:

```text
StatisticsProvider
        │
        ▼
StatisticsProviderImpl
```

This keeps statistics functionality independent from the primary quiz logic.

---

## 💡 Object-Oriented Principles

The project demonstrates several fundamental software-engineering concepts:

* **Abstraction** — interfaces define clear behavioural contracts
* **Encapsulation** — domain state and behaviour are contained within models
* **Polymorphism** — multiple question types implement a shared abstraction
* **Factory Pattern** — object construction is separated from usage
* **Separation of Concerns** — responsibilities are divided across dedicated packages
* **Testability** — core components can be tested independently

---

## 🚀 Getting Started

### Prerequisites

Make sure you have installed:

* **JDK 21**
* **Apache Maven**
* **Git**

Verify your environment:

```bash
java -version
mvn -version
git --version
```

### Clone the Repository

```bash
git clone https://github.com/Karthikeyan010/java-quiz-system.git
cd java-quiz-system
```

### Build the Project

```bash
mvn clean package
```

A successful build should finish with:

```text
BUILD SUCCESS
```

---

## 🧪 Testing

Run the complete test suite with:

```bash
mvn clean test
```

The automated tests cover the main application components:

| Component                 | Test                         |
| ------------------------- | ---------------------------- |
| Free-response questions   | `FreeResponseQuestionTest`   |
| Multiple-choice questions | `MultipleChoiceQuestionTest` |
| Question factory          | `QuestionFactoryTest`        |
| Question pool             | `QuestionPoolManagerTest`    |
| Quiz implementation       | `QuizImplTest`               |
| Statistics provider       | `StatisticsProviderImplTest` |
| Student factory           | `StudentFactoryTest`         |
| Student model             | `StudentTest`                |

> **Note:** The project currently contains both JUnit 5 and JUnit 4 tests. JUnit 5 is used for most tests, while JUnit 4 is retained for compatibility with existing tests.

---

## 🔄 Development Workflow

When making changes:

```bash
git checkout -b feature/my-feature

# Make your changes

mvn clean test

git add .
git commit -m "feat: describe the change"
git push origin feature/my-feature
```

Running the tests before committing helps ensure existing functionality continues to work correctly.

---

## 🗺️ Roadmap

Future improvements could include:

* [ ] Migrate remaining JUnit 4 tests to JUnit 5
* [ ] Add persistent storage for questions, students, and quiz results
* [ ] Introduce quiz categories and difficulty levels
* [ ] Add additional question types
* [ ] Improve validation and exception handling
* [ ] Expose functionality through REST APIs
* [ ] Build a web-based user interface
* [ ] Add GitHub Actions CI
* [ ] Expand integration and edge-case testing
* [ ] Introduce structured application logging

---

## 🤝 Contributing

Contributions, suggestions, and improvements are welcome.

1. Fork the repository
2. Create a feature branch
3. Implement your changes
4. Run the test suite
5. Commit your changes
6. Push your branch
7. Open a pull request

---

## 👨‍💻 Author

**Karthikeyan**

[![GitHub](https://img.shields.io/badge/GitHub-Karthikeyan010-181717?logo=github)](https://github.com/Karthikeyan010)

---

<div align="center">

**Built with Java ☕ and a focus on clean object-oriented design.**

⭐ If you find this project useful, consider starring the repository.

</div>
