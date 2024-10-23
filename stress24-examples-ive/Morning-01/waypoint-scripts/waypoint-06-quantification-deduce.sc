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
// ---- Part 5 (contract) -----

// method to move the waypoint forward along x axis
def moveForward(wayPoint: WayPoint): WayPoint = {
  Contract(
    // PRE-CONDITION
    // require that we are given a waypoint "in the zone"
    Requires(inZone(wayPoint)),
    // POST-CONDITION (reference the return value with Res[T] where T is the type of the return value
    // Ensures(inZone(Res[WayPoint])) // this post-condition is too strong
    Ensures(Res[WayPoint].x > wayPoint.x,
      Res[WayPoint].y == wayPoint.y,
      Res[WayPoint].z == wayPoint.z)
  )
  val x_new = wayPoint.x + 10  // illustrate post-condition violation by subtracting 10 instead of adding
  // the following syntax creates a new version of the wayPoint where the "x" field
  // now has the value x_new.  Note that this does not destructively update the original
  // waypoint
  val wayPoint_new = wayPoint(x = x_new)
  assert(wayPoint != wayPoint_new) // sanity check
  return wayPoint_new
}

val wayPoint01moved = moveForward(wayPoint01)

// print(s"Original waypoint: ${wayPoint01}; Moved: ${wayPoint01moved}")

// ---- Part 5 (contract) -----

// when we call the method, the pre-condition of the method must be satisfied
moveForward(wayPoint01)
moveForward(WayPoint(50,90,50))
// moveForward(WayPoint(50,110,50))  // pre-condition is not satisfied (the waypoint is not "inZone")

// when the method returns, we get to use the facts specified in the post-condition
assert(wayPoint01moved.y == wayPoint01.y)
// Neither of these assertions hold because the Ensures (post-condition) abstracts
//  the change to the x field (the contract is correct, but doesn't give us complete information)
// assert(inZone(wayPoint01moved))
// assert(wayPoint01moved.x == wayPoint01.x + 10)

// ---- Part 6 (quantification and Deduce) -----

// make a second waypoint
val wayPoint02 = WayPoint(84,-20,92)

// make a third waypoint, where the z element is unknown
val z: Z = randomInt()
val wayPoint03 = WayPoint(84,-20,z)

// make a sequence (value-based array) of waypoints
//. note: "ISZ" is a Slang data type meaning "Immutable Sequence indexed by Integer (Z)"
val wayPoints = ISZ(wayPoint01,wayPoint02,wayPoint03)

// assert that the first waypoint in the
assert(inZone(wayPoints(1)))

// illustrate simple Logika proof block (Deduce statement)
Deduce(
  (-80 < z & z < 80)
    // illustrate quantification syntax
    |- (All(wayPoints.indices)(i => inZone(wayPoints(i))))
)

// Activity
//   - comment out the antecedent of the sequent in the Deduce block
//.    and then notice that the consequent cannot be proven because, z holds a random value.
//.    ...without the assumption about z, wayPoint03 could be out of the zone