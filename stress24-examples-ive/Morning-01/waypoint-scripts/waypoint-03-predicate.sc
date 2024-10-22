// #Sireum #Logika

import org.sireum._

var my_x = 50   // a variable identifier
val my_y = 30   // a value identifier

assert(my_x == 50)
assert(my_y > 20)

my_x = my_x + 1
// my_y = my_y + 1  // re-assignment to val identifier not allowed

// assert(my_x == 50)  // Logika detects assertion violation
assert(my_x > 50)

// ---- Part 2 (datatype) -----

// Define datatype (immutable) for a 3-dimensional waypoint (similar to ML datatype)
@datatype class WayPoint(x: Z, y: Z, z: Z)  // x,y,z are members of the class of Slang type Z (unbounded integers)
{}  // no methods defined for this type

// Construct a way point
val wayPoint01 = WayPoint(30,30,50)

// Make an assertion on a WayPoint field
assert(wayPoint01.x < my_x)

// ---- Part 3 (inZone WayPoint predicate) -----

// define a predicate as a special form (strictpure) of Slang function.
// B is the Slang type for boolean values
@strictpure def inZone(wayPoint: WayPoint): B =
  // body of most strict pure functions is an expression (no side effects)
  ((-100 <= wayPoint.x & wayPoint.x <= 100)
    &  (-100 <= wayPoint.y & wayPoint.y <= 100)
    &  (-100 <= wayPoint.z & wayPoint.z <= 100))

assert(inZone(wayPoint01))

// Activity
//. - change the Z coordinate of wayPoint01 to 150 and observe the assertion violation

// Suggested exercises
//  - create a waypoint that does not satisfy the inZone predicate and illustrate
//    the failure with a failing assertion.  Then add a second assertion in which
//    you assert that the waypoint is not inZone (this assertion should hold)


