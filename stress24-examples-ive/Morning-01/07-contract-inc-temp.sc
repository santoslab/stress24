// #Sireum #Logika

import org.sireum._

// ========= service code ===========
def inc(x: Z, delta: Z) : Z = {
  Contract(
    Requires(delta > 0),
    Ensures(Res[Z] > x)
  )
  val result = x + delta
  return result
}

// ======== client code =========

var x:Z = 0
val savex = x

x = inc(x,1)

assert(x >= savex)

assert(x == savex + 1)


