// #Sireum #Logika
import org.sireum._

def swap(a: MSZ[Z], i: Z, j: Z) : Unit = {
  Contract(
    Requires(0 <= i, i < a.size, 0 <= j, j < a.size),
    Modifies(a),
    Ensures(
      a(i) == In(a)(j),
      a(j) == In(a)(i),
      All(a.indices)(k => k == i | k == j | a(k) == In(a)(k)),
      a.size == In(a).size
    )
  )
  val t: Z = a(i)
  a(i) = a(j)
  a(j) = t
}

def findMin(a: MSZ[Z], h: Z): Z = {
  Contract(
    Requires(h >= 0, h < a.size),
    Ensures(
      All(h until a.size)(i => a(Res[Z]) <= a(i)), (a(h) <= a(Res[Z])) ->: (Res[Z] == h),
      0 <= Res[Z], Res[Z] < a.size
    )
  )
  var m = h
  var k = h + 1
  while (k < a.size) {
    Invariant(
      Modifies(m, k),
      0 <= k, k <= a.size, 0 <= m, m < a.size,
      All(h until k)(i => a(m) <= a(i)), (a(h) <= a(m)) ->: (m == h)
    )
    if (a(k) < a(m)) {
      m = k
    }
    k = k + 1
  }
  return m
}

def selectionSort(a: MSZ[Z]): Unit = {
  Contract(
    Modifies(a),
    Ensures(All(0 until a.size-1)(i => a(i) <= a(i+1)), a.size == In(a).size)
  )
  var h: Z = 0
  while (h < a.size) {
    Invariant(
      Modifies(a),
      All(0 until h-1)(i => a(i) <= a(i+1)),
      a.size == In(a).size
    )
    val k: Z = findMin(a, h)
    if (h != k) {
      swap(a, h, k)
    }
  }
}