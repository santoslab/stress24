# HW 02 - Loops and Sequences - CS 841 - Fall 2023 

**Objectives:**

The objectives of this homework are to:
* enforce your knowledge of Hoare logic (as implemented by Logika) to reason about While loops and sequences,
* enforce your knowledge of how Logika generates contraints for SMT solvers to implement its program reasoning

To complete the homework, 
* Download and unzip the provide .zip file
* Run `$SIRUEM_HOME/bin/sireum proyek ive .` in the folder `HW02-ive` to create IntelliJ project metadata
* Open the folder in IntelliJ
* Following the questions below, add text and image-based answers in the `answers` folder as directed.  
  Add code in the `LoopsSequences.scala` and `LoopsSequencesTest.scala` file as directed.

To "kick the tires" and make sure the project is set up correctly, 
* run Logika on the `LoopsSequences.scala` file and ensure that the bulb and bolt annotations appear.

## 1. Mutable Sequences -- a simple "write only" method with a while loop.
Define a method ```def zeroOut(s: ZS): Unit``` that takes a mutable sequence `s` of type ZS 
and sets all of its values to 0.   Add, and use Logika to verify, a contract that specifies
the complete behavior of the method, i.e., the updating sequence s has each of its elements
replaced with `0`.   This method will require a simple `while` loop and an associated invariant.
Add your solution code to `LoopsSequences.scala`.

## 2. Mutable Sequences -- method with index calculations and a while loop to modify a sequence segment
Define a method ```incrSlice(s: ZS, i:Z, j:Z, k:Z): Unit```
that takes a mutable sequence s and index values i and j
and increments by k each value in s
within the index positions from i to j.
All other positions of s are unchanged.

This exercise combines aspects of the following examples from the lecture:
* it is like the `square` method in that it modifies each value of the sequence (but within a range),
* it is like the `swap` in that it takes to index values i and j as arguments to control the behavior.

Add your solution code to `LoopsSequences.scala`.

## 3. Loops - Searching
The method below takes a sequence and looks for a minimum value between (inclusive) the index positions
of `lower` and `upper`, where lower is less than or equal to upper.  The index within `a` of the found minimum
value is returned.

In the file `LoopsSequences.scala`, Add an appropriate contracts for the method that 
  * constrains the inputs sufficiently to avoid any index violations
  * characterizes the return value appropriately (i.e., it's between `lower` and `upper` and the value
    at is position in `a` is indeed a minimum within the range indicated by `lower`/`upper`.)

Add a loop variant as appropriate to help establish the method contract.

In class, we studied the "deduction schema" for a while-loop invariant that involved indicating how the loop-invariant
was used at four points in the loop structure to "manage" the facts flowing through the loop.   
In the file `answers.txt`, indicate how the Logika information
produced at the bulbs and bolts (choose whatever is appropriate) indicates that Logika's underlying
deductions correspond with the Assume / Guarantee statements at the four points marked in the code below.
```
def findMin(a: ZS, lower: Z, upper: Z): Z = {

  var candidateMinIndex : Z = lower
  var scannerIndex : Z = lower + 1

  // Point 1:
  //   Guarantee I
  while (scannerIndex <= upper) {
    // Point 2:
    //   Assume B
    //   Assume I

    if (a(scannerIndex) < a(candidateMinIndex)) {
      candidateMinIndex = scannerIndex
    }
    scannerIndex = scannerIndex + 1
    // Point 3: Guarantee I
  }
  // Point 4:
  //   Assume !B (i.e., scannerIndex > upper)
  //   Assume I

  // Show I and !B implies post-condition
  return candidateMinIndex
}
```

## 4 Selection Sort
The method below implements the classic Selection Sort algorithm.  The method uses `findMin` 
and `swapsZS` method from the lecture as helper methods.

As an extra twist, two errors have been seeded in the selection sort code.
Add contracts and loop invariants to the method.  In the process, notice how Logika leads
you to discover the errors.  Correct the errors and show that the code satisfies the contracts.
In the `answers.txt` file, describe the erroneous code and your corrections.

The selection sort contracts should include appropriate preconditions (e.g., to avoid
sequence indexing exceptions) and a post-condition that establishes the ordering property
associated with sorting.  You can omit the usual sort function requirement that the output
sequence is a permutation of the input sequence (we haven't learned enough to specify/verify
that yet).

```
// ====================================
//   selectionSort
// ====================================

def selectionSortErrors(a :ZS) : Unit = {
  var i : Z = 0

  while (i < a.size) {
    val min: Z = findMin(a,i,a.size)
    if (min != i) {
       swapZS(a,i,min)
    }
    i = i + 1
   }
}
```












