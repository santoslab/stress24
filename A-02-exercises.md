# Afternoon Session #2  (A-02) - Specification and Verification of Component Based Systems with HAMR

## Purpose

These lectures/activities illustrate the use of Slang and Logika in the context of an industrial strength model-driven development framework called HAMR.   With HAMR, 
* system architectures are defined in AADL or SysMLv2, 
* contracts are added to architecture descriptions to specify desired functional behavior,
* deployable project skeletons are auto-generated from HAMR, along with code-level contracts for application components,
* developers code application logic, and use Logika verification (and automated testing) to demonstrate that the application code conforms to contracts

In this activity, you will learn the basic aspects of the workflow steps above.  *The emphasis is on understanding how Logika verifies that application code conforms to contracts*.

These activities use the KSU SAnToS Isolette example -- a simple infant incubator with curated artifacts designed to illustrate end-to-end formal-methods-integrated model-based development.

## Activity 01 -- Visualizing the use of Logika to show that Manage Heat Source Application code conforms to contracts

Make sure that have installed Sireum -- download and installation instructions can be found here XXXXXX

Confirm that your installation is set up correctly

* (Navigate to the Sireum IVE application and launch)

(What about Proyek ? )

* Open the example workspace
  - Chose the File / Open option
  - Navigate to and select the workspace folder `stress24-examples-ive`, then press the Open button

* In the workspace folder, open (double-click) on the file XXXX `00-open-me-first.sc` XXXX 
  - You can see the Logika program verifier working when purple annotations (light bulbs and lightning bolts) appear in the margin
  - To execute the contents of the file, press the green button in the top-left margin of the file.   This should cause a console window to open at the bottom of the screen and output from the program will be displayed there.

## Morning Session #1 (M1): 1.5 hours

This session will introduce you to the Slang programming language (safety-critical subset of Scala) and the Logika program verifier for Slang.  You will learn basic aspects of the Sireum Integrated Verification Environment (IVE) interface and aspects of the Slang programming language -- building up to some simple exercises using conditional expressions and method calls.

**Overview of Slang** 
  * Slides (XXX insert link XXX)

**Overview of Logika** 
  * Slides (XXX insert link XXX)

**Walkthrough of Slang/Logika features using the Waypoint example**  
  * Follow along in the Slang scripts in the `waypoint-scripts` folder.  Simple exercises for you to try are presented in the comments in each file.

**Logika Verification of Conditional Expressions**

  * Slides
  * Examples

**Logika Verification of Method Contracts**

  * Slides
  * Examples

## Morning Session #2 (M2): 1 hour


## Lunch (2 hour break)


## Afternoon Session #1 (A1) 1.5 hours


## Afternoon Session #2 (A2) 1 hour













