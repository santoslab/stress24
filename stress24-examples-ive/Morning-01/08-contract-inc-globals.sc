// #Sireum #Logika

import org.sireum._

var x:Z = 0
var delta:Z = 1

// ========== service code ===========
def inc() : Unit = {
  Contract(
    // pre-condition:
    //   for client: these properties must hold in the client context when the service is called
    //.  for service: service gets to assume these properties
    Requires(delta > 0),
    // frame-condition:
    //.  for service: must indicate what global might be changed
    //.  for client: all constraints on modified variables will be discarded when method is called
    Modifies(x),
    // post-condition:
    //   for service: these properties must hold in the service context when service completes
    //.  for client: client gets to assume these properties after call
    Ensures(x > In(x))
  )
  x = x + delta
}

// ========== client code ==========

val savex = x
val savedelta = delta
// the contract pre-condition holds: delta > 0 (since delta == 1)
inc()
// we can prove the following, due to the service contract
assert(x >= savex)
// we can prove the following, since service stated that only x was modified
assert(savedelta == delta)

delta = -1

// pre-condition is violated below
// inc()
