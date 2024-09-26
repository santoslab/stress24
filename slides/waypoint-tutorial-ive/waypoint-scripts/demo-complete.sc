// #Sireum #Logika
//@Logika: --rlimit 500000 --timeout 4 --background type

import org.sireum._

var my_x = 50
val my_y = 30

my_x = my_x + 1

assert(my_x >= 50)

println("All good so far")

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

assert(inZone(wayPoint01))


// method to move the waypoint forward along x axis
def moveForward(wp: WayPoint): WayPoint = {
  Contract(
    Requires(inZone(wp)),
    Ensures(Res[WayPoint].x > wp.x,
      Res[WayPoint].y == wp.y,
      Res[WayPoint].z == wp.z)
  )
  val x_new = wp.x + 10
  val wayPoint_new = wp(x = x_new)
  return wayPoint_new
}

val wayPoint02 = WayPoint(84,-20,92)

val z: Z = randomInt()
val wayPoint03 = WayPoint(84,-20,z)

val wayPoints = ISZ(wayPoint01,wayPoint02,wayPoint03)

assert(inZone(wayPoints(1)))

Deduce(
  (-80 < z & z < 80)
  |- (∀(wayPoints.indices)(i => inZone(wayPoints(i))))
)










