// #Sireum #Logika

import org.sireum._

// ========= service code ===========

def inc(x: Z, delta: Z) : Z = {
  Contract(
    // pre-condition:
    //   for client: these properties must hold in the client context when the service is called
    //.  for service: service gets to assume these properties
    Requires(delta > 0),
    // post-condition:
    //   for service: these properties must hold in the service context when service completes
    //.  for client: client gets to assume these properties after call
    Ensures(Res[Z] > x)
  )
  val result = x + delta // change "+" to "-" to seed error (post-condition violation)
  return result
}

// ======== client code =========

var x:Z = 0

// x = inc(x,0) - contract violation: delta value must be > 0
val savex = x
// the contract pre-condition holds: delta > 0 (since delta == 1)
x = inc(x,1)

// we can prove the following, due to the service contract
assert(x >= savex)
// we can't prove the following,
//  ...because even though this is what the service achieved,
//. ...it did not expose this stronger property in its contract
// assert(x == savex + 1)
