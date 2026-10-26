# CS 320 – Software Testing, Automation, and Quality Assurance Portfolio

This repository contains selected artifacts from CS 320 that demonstrate my work with software testing, automation, and quality assurance.

## Portfolio Artifacts

### Project One – Contact Service
- [Contact.java](ProjectOne/Contact.java)
- [ContactService.java](ProjectOne/ContactService.java)
- [ContactTest.java](ProjectOne/ContactTest.java)
- [ContactServiceTest.java](ProjectOne/ContactServiceTest.java)

These files demonstrate the implementation and JUnit testing of the contact service. The tests cover valid and invalid inputs, boundary values, duplicate identifiers, updates, deletion, and preservation of existing data after rejected operations.

### Project Two – Summary and Reflections
- [Project Two Summary and Reflections Report](ProjectTwo/ProjectTwo_Summary_and_Reflections.md)

This report summarizes my testing approach across the contact, task, and appointment services and reflects on testing techniques, quality, bias, discipline, and technical debt.

## How can I ensure that my code, program, or software is functional and secure?

I can ensure software is functional by translating requirements into testable behaviors and verifying both expected and unexpected conditions. In Project One, I used JUnit to test valid inputs, invalid inputs, boundary values, duplicate identifiers, and update and deletion operations. I also checked that failed operations did not alter previously valid data. These practices make it easier to identify defects early and confirm that the program continues to meet its requirements after changes.

Security and reliability also depend on validating inputs and preventing the program from entering an invalid state. For larger applications, I would combine these practices with code reviews, dependency management, access controls, security testing, and repeated regression testing. Testing should be treated as an ongoing part of development rather than an activity performed only at the end.

## How do I interpret user needs and incorporate them into a program?

I begin by turning user needs into clear, specific, and testable requirements. I identify what the system must do, what data it should accept, what restrictions apply, and what behavior should occur when invalid data is supplied. This creates a direct connection between the user's expectations, the implementation, and the tests.

The contact service demonstrates this process. Requirements such as maximum field lengths and the ten-digit phone-number format were implemented as validation rules and then verified with JUnit tests. This reinforced that software is not complete simply because it runs; it must behave according to the requirements that represent the user's needs.

## How do I approach designing software?

I approach software design by starting with the requirements and dividing the solution into small components with clear responsibilities. In Project One, the contact object is responsible for storing and validating contact information, while the service class is responsible for managing contact records. Keeping these responsibilities separate makes the code easier to understand, maintain, and test.

I also believe software should be designed with testing in mind. Clear methods, input validation, modular components, and automated tests make future changes safer and help reduce technical debt. Going forward, I plan to continue using a structured approach that connects requirements, design, implementation, and testing throughout the software development life cycle.
