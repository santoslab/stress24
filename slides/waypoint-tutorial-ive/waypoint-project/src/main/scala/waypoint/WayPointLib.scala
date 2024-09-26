// #Sireum #Logika

package waypoint

import org.sireum._

object WayPointLib {

  // Define datatype (immutable) for a 3-dimensional waypoint (similar to ML datatype)
  @datatype class WayPoint(x: Z, y: Z, z: Z)  // x,y,z are members of the class of Slang type Z (unbounded integers)
   { // datatype invariant is checked for every constructor call
    //  and assumed for every use
    @spec def WayPoint_Inv = Invariant(
        x >= -1000 &
        y >= -1000 &
        z >= -1000
    )
  }  // no methods defined for this type

  // Define a predicate that can be applied to a waypoint.
  // Predicates are defined using @strictpure annotatations that indicate that the method has no non-local side effects.
  // Strictpure functions are "purely logical" in the sense that they can be directly translated into SMT logical expression.
  // Stricture functions can be referenced in contracts.
  // The inZone predicate defines an allowed range for each waypoint dimension
  @strictpure def inZone(wayPoint: WayPoint): B = // body of most strict pure functions is an expression
    ((-100 <= wayPoint.x & wayPoint.x <= 100)
      &  (-100 <= wayPoint.y & wayPoint.y <= 100)
      &  (-100 <= wayPoint.z & wayPoint.z <= 100))


  def moveForward(wayPoint: WayPoint): WayPoint = {
    Contract(
      // require that we are given a waypoint "in the zone"
      Requires(inZone(wayPoint)),
      // ensure that the waypoint moves forward in x dimension
      Ensures(Res[WayPoint].x == wayPoint.x + 10,
        Res[WayPoint].y == wayPoint.y,
        Res[WayPoint].z == wayPoint.z)
    )
    val x_new = wayPoint.x + 10  // change "10" to e.g., "12" to illustrate Logika finding error
    val wayPoint_new = wayPoint(x = x_new)
    return wayPoint_new
  }

  def absWaypointX(wayPoint: WayPoint): WayPoint = {
    Contract(
      Ensures(Res[WayPoint].x >= 0)
    )
    assert(wayPoint.x > -2000) // we know this holds because of data type invariant
    val x_new: Z = if (wayPoint.x < 0) -wayPoint.x else wayPoint.x
    val wayPoint_new = wayPoint(x = x_new)
    return wayPoint_new
  }
}
