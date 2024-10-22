// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

// This development demonstrates inductive proof on a recursive binary tree type.

// Trees are immutable datastructures consisting of nodes and leaves.
@sig sealed trait Tree
@datatype class Node(val left: Tree, val value: Z, val right: Tree) extends Tree
@datatype class Leaf() extends Tree

// Definition of abstract property of containment in a tree.
// It must be '@abs' to be used in rewriting.
@abs def contains(t: Tree, x: Z): B = {
  t match {
    case Node(_, y, _) if x == y => true
    case Node(l, y, _) if x < y => contains(l, x)
    case Node(_, y, r) if x > y => contains(r, x)
    case Leaf() => false
  }
}

// Recursive implementation of inserting an element in a tree.
@strictpure def insert_imp(t: Tree, x: Z): Tree =
  t match {
    case Node(l, y, r) => {
      if (x == y) {
        t
      } else if (x < y) {
        Node(insert_imp(l, x), y, r)
      } else {
        Node(l, y, insert_imp(r, x))
      }
    }
    case Leaf() => {
      Node(Leaf(), x, Leaf())
    }
  }

// Proof that after inserting an element in a tree,
// that element is contained in the tree.
@pure def insert_lemma(t: Tree, x: Z): Unit = {
  Contract(
    Ensures(contains(insert_imp(t, x), x))
  )
  // Inductive proof follows the structure of insert programmatically
  // but contains no executable code except for the if-statements.
  // The proof is a complicated way to express the skip-statement.
  (t: @induct) match {
    case Node(l, y, r) =>
      if (x == y) {
        Deduce(
          1 (x == y) by Premise,
          2 (contains(Node(l, y, r), x)) by RSimpl(RS(contains _))
        )
        // Inserting these deductions at each branch proves the ensures clause
        // and fails for separately for each branch that remains to be proved.
        // Once the proof succeeds the corresponding deduction can be commented out.
        // Deduce(|- (contains(t, x)))
        return
      } else if (x < y) {
        Deduce(
          1 (contains(insert_imp(l, x), x)) by Premise,
          2 (x < y) by Premise,
          3 (contains(Node(insert_imp(l, x), y, r), x)) by RSimpl(RS(contains _))
        )
        // Deduce(|- (contains(insert_imp(t, x), x)))
        return
      } else {
        Deduce(
          1 (contains(insert_imp(r, x), x)) by Premise,
          2 (!(x < y)) by Premise,
          3 (!(x == y)) by Premise,
          4 (x > y) by Algebra and (2, 3),
          5 (contains(Node(l, y, insert_imp(r, x)), x)) by RSimpl(RS(contains _))
        )
        // Deduce(|- (contains(insert_imp(t, x), x)))
        return
      }
    case Leaf() => {
      Deduce(|- (contains(Node(Leaf(), x, Leaf()), x)))
      return
    }
  }
}

// Function insert inserts a node with value x in the tree if it's not
// already contained in the tree. It uses insert_imp as it's implementation
// and insert_lemma to prove the ensures clause.
@pure def insert(t: Tree, x: Z): Tree = {
  Contract(
    Ensures(contains(insert_imp(t, x), x))
  )
  insert_lemma(t, x) // The lemma contains the proof but has no effect
  return insert_imp(t, x)
}