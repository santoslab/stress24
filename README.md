# stress24
Github for materials for STRESS 2024 

# Expected Schedule Structure

Morning Session #1 (M1): 1.5 hours

Morning Session #2 (M2): 1 hour

Lunch (2 hour break)

Afternoon Session #1 (A1) 1.5 hours

Afternoon Session #2 (A2) 1 hour

*Note*: It could be the case that every session is 1.5 hours, or at least M2 is 1.5 hours.   At this point, I think it is best to plan for less time, since the lectures/exercises tend to take more time than expected.

# Infrastructure Preparation

## Installation (robby)

* Written installation instructions to be mailed to participants 1-2 weeks before meeting
  - download and install
  - loading up existing files / models to confirm that tools are working appropriately

* Video (optional)
  - It would be ideal to develop a reusable Presentasi-based video for installing and loading initial example.  
  
We want the video and instructions to be reusable, so it shouldn't be tied to STRESS or STRESS examples.

## STRESS Example repository (robby/john)

Github repo of lecture examples and exercises

## Documentation for Slang and Logiak

??? What is the best we can do for this.

# Proposed Schedule

## M1 (John)

Slang Overview (10 mins) 
  * Slang purpose and design goals
  * Slang use (implemenenting Sireum, HAMR context)
  * Slang language features (example based)
  * Pointers to more information

Logika Overview (10 mins)
  * Logika purpose and design goals
  * Logika use (Slang stand alone, HAMR context)
  * Logika GUI illustration
  * Logika scalability
  * Contract example (just viewing)
  * Deduce examples (just viewing)
  * Advanced manual proof with rewriting examples (just viewing)
  * Teaching material

Logika UI Principles -- Interactive (20 mins) 
  * idea: 
    - students click around on bolts and bulbs in various examples, they seed various errors and show them being detected, etc.
    - this could be based on the Waypoint Example.  Note, if this is converted to Presentasi, then it could be broken into three sections 
      
Logika Checking for Compositional Reasoning (15 mins) 
  * idea:
    - students are given partial program and the goal is to add method implementation, method clients, and contracts to illustrate compositional reasoning

???

Extended exercise 
  * idea:
    - students are given a description of code and specifications to write, potentially along with some starting content
    - slang/logika features
      - conditionals
      - contracts
      - method calls
    - pitfalls: need to make sure that students have enough documentation.
  * **What example should be used for this?**


## M2 (Stefan)

Solution walkthrough of exercise given before break (5 mins) (John)

Logika Checking for Loops, Loop Invariants -- Interactive -- Stefan (15 mins)
  * idea: 
    - students are presented with loops for integer calculations (summing, factorial)


Logika Checking for Sequences, Quantification -- Interactive -- Stefan (15 mins)
  * idea: 
    - students are presented with examples of sequences, quantification, index bounds checking, etc.
    - consider example to find max or min element in a sequence (as needed for insertion sort)


Extended exercise - Stefan (25 mins)
  * idea:
    - students are given a description of code and specifications to write, potentially along with some starting content
    - if we give the students the code for selection sort (as built up in previous activties, they could be asked to do bubble sort)
    - slang/logika features
      - loops, sequences, quantification
  * **What example should be used for this?**

## A1

Manual Proofs / Rewriting Overview -- John or Stefan
  * idea:
    - students are given completed examples of rewriting, etc.
    - slang/logika features
      - rewriting, rules
  * **What example should be used for this?**

???


Extended exercise

Other Verificaton Concepts Supported in Logika (?? where exactly would this go ??)
  - in the logika example of conformance-swap, there is a proof of equivalence between swapping by arithemetic and swapping by updates

## A2 

HAMR / GUMBO Overview -- John

Extended exercise (based on Isolette)














