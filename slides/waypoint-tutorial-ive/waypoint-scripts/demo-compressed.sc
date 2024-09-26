// #Sireum #Logika
//@Logika: --par --par-branch --rlimit 500000 --timeout 1 --background type

import org.sireum._

var my_x = 50
val my_y = 30

my_x = my_x + 1

assert(my_x >= 50)

println("All good so far")

// ...without assertion added
// illustrate bulb at line 8
// illustrate bulb (post-state at line 10)

// add assertion, show bolt

// run script with green arrow

// make assertion false: my_x == 50
// show bulb output

// run and show run-time exception

// Define (immutable) datatype  for a 3-dimensional waypoint
@datatype class WayPoint(x: Z, y: Z, z: Z){}

// Construct a way point
val wayPoint01 = WayPoint(30,30,50)

// define a predicate (Slang strictpure)
//  ..with return type B (Slang boolean)
@strictpure def inZone(wayPoint: WayPoint): B =
  // side-effect free expression
  ((-100 <= wayPoint.x & wayPoint.x <= 100)
    &  (-100 <= wayPoint.y & wayPoint.y <= 100)
    &  (-100 <= wayPoint.z & wayPoint.z <= 100))

// use executable predicate in logical constraints
assert(inZone(wayPoint01))

println("All still good")

// add waypoint datatype
// constructor for datatype
// strictpure predicate
// assertion (with bolt annotation)
// failing assertion

// method to move the waypoint forward along x axis
def moveForward(wayPoint: WayPoint): WayPoint = {
  Contract(
    // PRE-CONDITION
    Requires(inZone(wayPoint)),
    // POST-CONDITION
    Ensures(Res[WayPoint].x > wayPoint.x,
      Res[WayPoint].y == wayPoint.y,
      Res[WayPoint].z == wayPoint.z)
  )
  val x_new = wayPoint.x + 10
  val wayPoint_new = wayPoint(x = x_new)
  assert(wayPoint != wayPoint_new) // sanity check
  return wayPoint_new
}

// discuss syntax for pre/post-condition
//  ...illustrate refactoring - to show that contract notation makes
//      contracts first class citizens
// may need to mention update of value-based data structure
// show bolt for post-condition clauses
// illustrate contract violation by changing + 10 to - 10

val wayPoint02 = WayPoint(84,-20,92)

val z: Z = randomInt()
val wayPoint03 = WayPoint(20,-20, z)

val wayPoints = ISZ(wayPoint01, wayPoint02, wayPoint03)

assert(inZone(wayPoints(1)))


Deduce(
  (-80 < z & z <80)
    |- ∀(wayPoints.indices)(i => inZone(wayPoints(i)))
)






