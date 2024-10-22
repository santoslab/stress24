// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._

// Given non-negative integer arguments m and n,
// mult computes multiplication of m and n by using repeated addition.
def mult(m: Z, n: Z): Z = {
  Contract(
    Requires(m >= 0 & n >= 0), // call this Pre below
    Ensures(Res[Z] == m * n) // call this Post below
  )

  // Assume: Pre
  var r: Z = 0
  var i: Z = 0

  println("Before loop: m = ", m, ", n = ,", n, ", i = ", i, ", r = ", r)

  // B is i < n (continuation condition)
  // I is r == m * i
  //
  // Guarantee: prove I holds
  Deduce(
    //@formatter:off
    1  (  r == 0      ) by Premise,
    2  (  i == 0      ) by Premise,
    3  (  r == m * i  ) by Algebra* (1, 2)
    //@formatter:on
  )

  while (i != n) {
    Invariant(
      Modifies(r, i),
      r == m * i
    )

    // Assume: I, B hold
    println("Loop pre: m = ", m, ", n = ", n, ", i = ", i, ", r = ", r)

    r = r + m
    Deduce(
      //@formatter:off
      1  (  r == Old(r) + m  ) by Premise, // from assignment
      2  (  Old(r) == m * i  ) by Premise, // from loop invariant
      3  (  r == m * i + m   ) by Subst_<(2, 1)
      //@formatter:on
    )

    i = i + 1

    Deduce(
      //@formatter:off
      1  (  i == Old(i) + 1      ) by Premise,
      2  (  r == m * Old(i) + m  ) by Premise,
      3  (  r == m * i           ) by Algebra* (1, 2)
      //@formatter:on
    )
    // Guarantee: I holds

    println("Loop post: m = ", m, ", n = ", n, ", i = ", i, ", r = ", r)
  }

  println("After loop: m = ", m, ", n = ", n, ", i = ", i, ", r = ", r)

  // Assume: I and ¬(B) hold
  // Guarantee: Post
  Deduce(
    //@formatter:off
    1  (  !(i != n)   ) by Premise, // from loop condition
    2  (  r == m * i  ) by Premise, // from loop invariant
    3  (  r == m * n  ) by Algebra* (1, 2)
    //@formatter:on
  )

  return r
}

