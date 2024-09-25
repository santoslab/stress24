// #Sireum #Logika
import org.sireum._

def swap(a: ZS, i:Z, j: Z) : Unit = {
  Contract(
    Requires(0 <= i, i < a.size, 0 <= j, j < a.size),
    Modifies(a),
    Ensures(
      a(i) == In(a)(j),
      a(j) == In(a)(i),
      ∀(a.indices)(k => k == i | k == j | a(k) == In(a)(k)),
      a.size == In(a).size
    )
  )
  val t: Z = a(i)
  a(i) = a(j)
  a(j) = t
}

def bubbleSort(a: ZS): Unit = {
  Contract(
    Modifies(a),
    Ensures(∀(0 until a.size-1)(i => a(i) <= a(i+1)), a.size == In(a).size)
  )
  var k: Z = 0
  while (k < a.size-1) {
    Invariant(
      Modifies(k),
      0 <= k,
      a.size <= 1 | k <= a.size-1,
      a.size > 1 | k == 0,
      ∀(a.size-k until a.size)(i => ∀(0 until a.size-k)(j => a(j) <= a(i))),
      ∀(a.size-k until a.size-1)(i => a(i) <= a(i+1))
    )
    var l: Z = 0
    while (l < a.size-k-1) {
      Invariant(
        Modifies(l),
        0 <= l,
        l <= a.size-k-1,
        ∀(0 until l)(i => a(i) <= a(l)),
        ∀(a.size-k until a.size-1)(i => a(i) <= a(i+1))
      )
      if (a(l) > a(l+1)) {
        swap(a, l, l+1) // swaps a(l) and a(l+1)
      }
      l = l + 1
    }
    k = k + 1
  }
}