package waypoint

import org.scalacheck.Gen
import org.sireum.Z
import util.Base_Types_Gen

object App_Types_Gen {

  object WayPoint {

    val simple: Gen[WayPointLib.WayPoint] = for {
      x <- Base_Types_Gen.Z.simple
      y <- Base_Types_Gen.Z.simple
      z <- Base_Types_Gen.Z.simple
      waypoint = WayPointLib.WayPoint(x, y, z)
      // if (check executable waypoint invariant)
    } yield waypoint

    def bounded(lowbound: Z, highbound: Z): Gen[WayPointLib.WayPoint] = for {
      x <- Base_Types_Gen.Z.bounded(lowbound, highbound)
      y <- Base_Types_Gen.Z.bounded(lowbound, highbound)
      z <- Base_Types_Gen.Z.bounded(lowbound, highbound)
      waypoint = WayPointLib.WayPoint(x, y, z)
      // if (check executable waypoint invariant)
    } yield waypoint

    var default = simple
  }
}
