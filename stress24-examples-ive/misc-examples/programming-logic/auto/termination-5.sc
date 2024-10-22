// #Sireum #Logika
//@Logika: --background save
import org.sireum._

def even(n: Z): B = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[B] == ((n % 2) == 0))
  )

  // decreases  n
  val decreasesEntry: Z = n

  var r: B = true

  if (n != 0) {

    val decreasesRec: Z = n - 1

    assert(decreasesRec < decreasesEntry)
    assert(decreasesEntry > 0)

    r = odd(n - 1)
  }
  return r
}

def odd(n: Z): B = {
  Contract(
    Requires(n >= 0),
    Ensures(Res[B] == ((n % 2) != 0))
  )

  // decreases  n
  val decreasesEntry: Z = n

  var r: B = false

  if (n != 0) {

    val decreasesRec: Z = n - 1

    assert(decreasesRec < decreasesEntry)
    assert(decreasesEntry > 0)

    r = even(n - 1)
  }
  return r
}