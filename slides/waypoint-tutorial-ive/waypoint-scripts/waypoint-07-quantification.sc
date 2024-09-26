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
{ // invariant added in Part 6
  @spec def WayPoint_Inv = Invariant(
    x >= -1000 &
      y >= -1000 &
      z >= -1000
  )
}  // no methods defined for this type

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

// ---- Part 6 (datatype invariant) -----

// add a datatype invariant to the WayPoint data type above

//@datatype class WayPoint(x: Z, y: Z, z: Z)  // x,y,z are members of the class of Slang type Z (unbounded integers)
//{ // datatype invariant is checked for every constructor call
//  //  and assumed for every use
//  @spec def WayPoint_Inv = Invariant(
//    x >= -1000 &
//      y >= -1000 &
//      z >= -1000
//  )
//}

// illustrating datatype invariant enforcement
val wayPoint03 = WayPoint(-984,-20,92)  // change "-984" to "-1084" to illustrate Logika finding error (pre-condition violation)

// illustrate the effect of the invariant when using a datatype value

def arbitraryWayPoint_y(wp: WayPoint): Z = {
  Contract(
    Ensures(Res[Z] > -1100)
  )
  // the parameter wp is an arbitrary WayPoint, we don't know anything about it,
  // other than the fact that it must satisfy the invariant
  // Robby: why isn't the invariant displayed in the assumption set of the sequent
  val local_x = wp.x
  assert(local_x >= -1500) // holds, because invariant says that x coordinate of WayPoint must always be >= -1000
  return wp.y
}

// ---- Part 7 (quantification) -----

val wayPoint02 = WayPoint(84,-20,92)  // change "92" to "192" to illustrate Logika finding error (pre-condition violation)
val wayPoint02moved = moveForward(wayPoint02)

// illustrate quantification over lists of waypoints

val wayPoints = ISZ(wayPoint01, wayPoint01moved, wayPoint02, wayPoint02moved)
//
// mock up for an "imported library function"
//  - we are simulating the fact that Logika will check the pre-condition of the library function
//    when it is invoked by client code in this file.  The client code passes a list of waypoints as
//    parameters
def setMission(wps: ISZ[WayPoint], overTarget: WayPoint): Unit = {
  Contract(
    // require all the waypoints to be in the zone
    Requires(All(wps.indices)(i => inZone(wps(i)))
    )
  )
  return ()
}
//
//// set up mission
val target = wayPoints(2)
setMission(wayPoints,target)


