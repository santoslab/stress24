// Note: This file is **Scala**, not Slang (there is no 'hash'Sireum)
package waypoint

import org.sireum._
import waypoint.WayPointLib._

import org.scalatest.funsuite.AnyFunSuite

// These example tests use the ScalaTest style "FunSuite".  Other ScalaTest styles
// can be used as well (see https://www.scalatest.org/user_guide/selecting_a_style)
class WayPointTest extends AnyFunSuite {
  test("Test moveForward with inputs that satisfy pre-condition") {
    val wayPoint1: WayPoint = WayPoint(50, 50, 50) // construct input value
    val result: WayPoint = moveForward(wayPoint1)  // call test on input value and get result

    assert(result == WayPoint(60, 50, 50))         // compare result value to expected result

    // or to more clearly distinguish expected result from actual result
    assertResult(WayPoint(60,50,50)) {result}
    assertResult(WayPoint(60,50,50)) {moveForward(WayPoint(50, 50, 50))}
  }

  test("Illustrate use of ScalaTest Assume") {
    val wayPoint1: WayPoint = WayPoint(110, 50, 50)
    assume(inZone(wayPoint1))  // if the method pre-condition isn't satisfied, we want to discard this test.
    val result: WayPoint = moveForward(wayPoint1)

    // The expected result is wrong, but doesn't matter because pre-condition
    // wasn't satisfied (110 coordinate is not inZone)
    assertResult(WayPoint(60,50,50)) {result}
  }

  test("Using executable contract framework (pre-cond satisfied)") {
    assert(WayPointXContracts.moveForward.testWithOracle(WayPoint(60,50,50)))
  }

  test("Using executable contract framework (pre-cond unsatisfied)") {
    assert(WayPointXContracts.moveForward.testWithOracle(WayPoint(160,50,50)))
  }
}