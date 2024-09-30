// #Sireum #Logika  -- #Sireum indicates file is in Slang, #Logika tells the IVE to apply Logika checking

import org.sireum._

//======================================
// (1)  Define a method ```def zeroOut(s: ZS): Unit``` that takes a mutable sequence `s` of type ZS
// and sets all of its values to 0.   Add, and use Logika to verify, a contract that specifies
// the complete behavior of the method, i.e., the updating sequence s has each of its elements
// replaced with `0`.
//======================================

def zeroOut(s: ZS): Unit = {
  Contract(
    Modifies(s),
    Ensures(All(s.indices)(k => s(k) == 0),
      s.size == In(s).size)
  )
  var x: Z = 0

  while (x < s.size) {
    Invariant(
      Modifies(s,x),
      s.size == In(s).size,
      0 <= x & x <= s.size,
      All(0 until x)(k => s(k) == 0)
    )
    s(x) = 0
    x = x + 1
  }
}

//======================================
// (2) Define a method ```incrSlice(s: ZS, i:Z, j:Z, k:Z): Unit```
//  that takes a mutable sequence s and index values i and j
//  and increments by k each value in s
//  within the index positions from i to j.
//  All other positions of s are unchanged.
//
//  This exercise combines aspects of the following examples from the lecture:
//  * it is like the `square` method in that it modifies each value of the sequence (but within a range),
//  * it is like the `swap` in that it takes to index values i and j as arguments to control the behavior.
//======================================

def incrSlice(s: ZS, i:Z, j:Z, k:Z): Unit = {
  Contract(
    Requires(0 <= i, i < s.size, 0 <= j, j < s.size,
      i <= j),
    Modifies(s),
    Ensures(
      All(s.indices)(q => (   (q < i) ->: (s(q) == In(s)(q)
        & (q >= i & q <= j) ->: (s(q) == In(s)(q) + k))
        & (q > j) ->: (s(q) == In(s)(q))))
    )
  )
  var x: Z = i

  while (x <= j) {
    Invariant(
      Modifies(s,x),
      i <= x,
      x <= j + 1,
      s.size == In(s).size,
      All(s.indices)(q => (q < i) ->: (s(q) == In(s)(q))),
      All(s.indices)(q => (i <= q & q < x) ->: (s(q) == In(s)(q) + k)),
      All(s.indices)(q => (x <= q & q <= j) ->: (s(q) == In(s)(q))),
      All(s.indices)(q => (j < q) ->: (s(q) == In(s)(q)))
    )
    s(x) = s(x) + k
    x = x + 1
  }
}

//======================================
// (3) For the findMin method below, add an appropriate contracts that
//  * constrain the inputs sufficiently to avoid any index violations
//  * characterize the return value appropriately (i.e., it's between `lower` and `upper` and the value
//    at is position in `a` is indeed a minimum within the range indicated by `lower`/`upper`.)
//
//  Add a loop variant as appropriate to help establish the method contract.
//======================================

def findMin(a: ZS, lower: Z, upper: Z): Z = {
  Contract(
    Requires(0 <= lower & lower < a.size,
      0 <= upper & upper < a.size,
      lower <= upper),
    Ensures(lower <= Res[Z] & Res[Z] <= upper,
      All(0 until a.size)(k => (lower <= k & k <= upper) ->: (a(Res[Z]) <= a(k))))
  )

  var candidateMinIndex : Z = lower
  var scannerIndex : Z = lower + 1

  // loop condition B = scannerIndex <= upper
  // Guarantee I
  while (scannerIndex <= upper) {
    Invariant(
      Modifies(scannerIndex, candidateMinIndex),
      lower <= scannerIndex & scannerIndex <= upper + 1,
      lower <= candidateMinIndex & candidateMinIndex <= upper,
      All(lower until scannerIndex)(k => a(candidateMinIndex) <= a(k))
    )
    // Assume B
    // Assume I

    if (a(scannerIndex) < a(candidateMinIndex)) {
      candidateMinIndex = scannerIndex
    }
    scannerIndex = scannerIndex + 1
    // Guarantee I
  }
  // Assume !B (i.e., scannerIndex > upper)
  // Assume I

  // Show I and !B implies post-condition
  return candidateMinIndex
}

//======================================
// (4) The method below implements the classic Selection Sort algorithm.  The method uses `findMin`
//  and `swapsZS` method from the lecture as helper methods.
//
//  As an extra twist, two errors have been seeded in the selection sort code.
//  Add contracts and loop invariants to the method.  In the process, notice how Logika leads
//  you to discover the errors.  Correct the errors and show that the code satisfies the contracts.
//
//  The selection sort contracts should include appropriate preconditions (e.g., to avoid
//  sequence indexing exceptions) and a post-condition that establishes the ordering property
//  associated with sorting.  You can omit the usual sort function requirement that the output
//  sequence is a permutation of the input sequence (we haven't learned enough to specify/verify
//  that yet).
//======================================


def swapZS(s: ZS, i: Z, j: Z): Unit = {
  Contract(
    Requires(0 <= i, i < s.size, 0 <= j, j < s.size),
    Modifies(s),
    Ensures(
      s.size == In(s).size,
      // capture the effect of swapping (note the use of the In(..) notation, similar to how we used it for globals
      s(i) == In(s)(j),
      s(j) == In(s)(i),
      // capture the fact that the rest of the elements are unchanged.
      // All the three clauses below have the same meaning;
      //  we're just illustrating the same syntax.
      All { k: Z => (0 <= k & k < s.size) -->: (k != i & k != j) ->: (s(k) == In(s)(k)) },
      All(0 until s.size)(k => (k != i & k != j) ->: (s(k) == In(s)(k))),
      All(s.indices)(k => (k != i & k != j) ->: (s(k) == In(s)(k)))
    )
  )
  val t: Z = s(i)
  s(i) = s(j)
  s(j) = t
}

def selectionSort(a: ZS): Unit = {
  Contract(
    Requires(a.size >= 2),
    Modifies(a),
    Ensures(All(0 until a.size - 1)(k => (a(k) <= a(k + 1)))) // omit specifying permutation property
  )
  var i: Z = 0
  // Fixed Error
  //  - loop condition was "i < a.size"
  //    While technically it is possible to iterate with this as the loop
  //    condition, it makes the loop invariant more difficult to manage.
  while (i < a.size - 1) {
    Invariant(
      Modifies(a, i),
      0 <= i & i < a.size,
      All(0 until i)(k => (a(k) <= a(k + 1))
        & All(i until a.size)(m => (a(k) <= a(m))))
    )
    // Fixed Error:
    //  - previous code was "findMin(a, i, a.size)",
    //    but the a.size violates the pre-condition of findMin (which is needed to avoid a sequence indexing error).
    //    Intuitively, calling findMin in this manner would search 1 position past the end of the sequence
    //     (the greatest index position is a.size - 1)
    val min: Z = findMin(a, i, a.size - 1)
    if (min != i) {
      swapZS(a, i, min)
    }
    i = i + 1
  }
}

