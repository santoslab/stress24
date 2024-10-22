// #Sireum #Logika
//@Logika: --background save
import org.sireum._

// Given non-negative integer arguments m and n,
// mult computes multiplication of m and n by using repeated addition.
def mult(m: Z, n: Z): Z = {
  Contract(
    Requires(m >= 0 & n >= 0), // call this Pre below
    Ensures(Res[Z] == m * n) // call this Post below
  )

  var r: Z = 0
  var i: Z = 0

  println("Before loop: m = ", m, ", n = ,", n, ", i = ", i, ", r = ", r)

  while (i != n) {
    Invariant(
      Modifies(r, i),
      r == m * i
    )

    println("Loop pre: m = ", m, ", n = ", n, ", i = ", i, ", r = ", r)

    r = r + m
    i = i + 1

    println("Loop post: m = ", m, ", n = ", n, ", i = ", i, ", r = ", r)
  }

  println("After loop: m = ", m, ", n = ", n, ", i = ", i, ", r = ", r)

  return r
}

