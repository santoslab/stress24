// #Sireum #Logika

import org.sireum._

var x:Z = 0
var delta:Z = 1

// ========== service code ===========
def inc() : Unit = {
  Contract(
    Requires(delta > 0),
    Modifies(x),
    Ensures(x > In(x))
  )
  x = x + delta
}

// ========== client code ==========
val savex = x
val savedelta = delta

inc()

assert(x >= savex)
assert(savedelta == delta)

delta = -1
inc()
