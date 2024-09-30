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

// ---- Part 4 (method) -----

// method to move the waypoint forward along x axis
def moveForward(wayPoint: WayPoint): WayPoint = {
  val x_new = wayPoint.x + 10
  // the following syntax creates a new version of the wayPoint where the "x" field
  // now has the value x_new.  Note that this does not destructively update the original
  // waypoint
  val wayPoint_new = wayPoint(x = x_new)
  assert(wayPoint != wayPoint_new) // sanity check
  return wayPoint_new
}

val wayPoint01moved = moveForward(wayPoint01)

// print(s"Original waypoint: ${wayPoint01}; Moved: ${wayPoint01moved}")

// Suggested exercises
//  - uncomment the print state and run the Slang script to show the effect of
//    the move operation
//  - assert that the y fields of wayPoint01 and wayPoint01moved are equal (==)
//    Note: this assertion should fail, because Logika checks method
//     compositionally and relies on method contract information to determine
//     the facts known to method callers (we'll explain this in the next part)
//  - click on the light bulb at the failing assertion to see the facts
//    that Logika knows at that point.

