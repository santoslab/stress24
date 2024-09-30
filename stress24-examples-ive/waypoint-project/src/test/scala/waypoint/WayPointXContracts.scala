package waypoint

import org.sireum._
import waypoint.WayPointLib._
object WayPointXContracts {

  // imagine that the following are auto-generated from the Slang contract for moveForward
  object moveForward {
    def precond(wayPoint: WayPoint): Boolean = {
      return inZone(wayPoint)
    }

    def postcond(wayPoint_in: WayPoint, result: WayPoint): Boolean = {
      return (result.x == wayPoint_in.x + 10 &&
              result.y == wayPoint_in.y &&
              result.z == wayPoint_in.z)
    }

    def oracle(wayPoint_in: WayPoint, result: WayPoint): Boolean = {
      if (!precond(wayPoint_in)) {
        return true // failing to satisfy pre-condition makes contract trivially true
      } else {
        // check post-cond on pre-state and post-state
        return postcond(wayPoint_in,result)
      }
    }

    def testWithOracle(wayPoint_in: WayPoint): Boolean = {
      if (!precond(wayPoint_in)) {
        return true // failing to satisfy pre-condition makes contract trivially true
      } else {
        val result = WayPointLib.moveForward(wayPoint_in)
        // check post-cond on pre-state and post-state
        return postcond(wayPoint_in,result)
      }
    }
  }
}
