# stress24

This repository contains lecture materials (slides and example code)
for STRESS 2024 lectures on "Developer-friendly Integrated Coding and
Verification with Slang and Logika"  

## Lecturers

* John Hatcliff - Kansas State University
* Stefan Hallerstede - Aarhus University

with remote assistance from
* Robby - Kansas State University
* Jason Belt - Kansas State University

## Abstract

Developing verified code for embedded systems is challenging.  To be
effective, code should be both (a) clean and include abstractions
amenable to verification as well as (b) efficient
and predicatable with respect to execution speed and resource
utilization.

This series of tutorials presents the Slang programming
language (a safety-critical subset of Scala) and its
accompanying verification framework called Logika.
Slang is designed to support software contract-based verification but
can also be transpiled to C and Rust to obtain
effective embedded system implementations.  The Logika
verifier is supported by a sophisticated integrated
development environment within the IntelliJ IDE
that provides a number of useful visualizations of
contract verification status, automatically discovered
program facts, and developer-friendly displays of how
program constraints are converted to SMT queries.
Slang/Logika are also unique in that they support
an integrated language for manual program proofs to complement
automated SMT-based verification.
Logika also provides a programmer-friendly interface to the 
SMT-based proof support. As a consequence, the tool can also be used 
without prior education in theorem proving.

Development of Slang and Logika has been funded via several
industry partnerships with US defense companies.
We will conclude with an overview of industry-research
applications of Slang and Logika and its ongoing integration
with Rust verification.  This overview will include
walkthroughs of model-driven development of medical devices
and applications within US aerospace companies.

## Useful Links

* [Tool installation instructions](stress24-logika-tool-installation.md)
* [Schedule of Lecture Content and Student Activities](stress24-logika-schedule-students.md)

Supporting information
* [Sireum home page](https://sireum.org), including links to Slang and
  Logika information
* [HAMR home page](https://hamr.sireum.org)  
* [Slang overview paper](https://people.cs.ksu.edu/~hatcliff/Papers/Robby-Hatcliff-ISOLA2021-Slang-Overview.pdf)
* [Logika overview paper](https://people.cs.ksu.edu/~hatcliff/Papers/Robby-etal-FMICS2024-Logika-Overview.pdf)
* [Teaching with Logika paper](https://people.cs.ksu.edu/~hatcliff/Papers//Hallerstede-etal-FMTEA2024-Logika-Education.pdf)
* [Online textbook](https://textbooks.cs.ksu.edu/cis301/) for undergraduate teaching with Logika
* [HAMR development for safety critical systems](https://isolette.santoslab.org) illustrated using
  Isolette example -- industry training and graduate teaching













