package waypoint

import org.scalatest.funsuite.AnyFunSuite

import org.scalacheck.Test.Parameters
import org.scalacheck.Prop
import org.scalacheck.Test

class WayPointTestScalaCheckAsScalaTest extends AnyFunSuite {

  test("moveFoward XContract") {

    val validProperty =
      Prop.forAll(App_Types_Gen.WayPoint.default){ w: WayPointLib.WayPoint =>
        println(w)
        WayPointXContracts.moveForward.testWithOracle(w)
      }

    // val scalaCheckResult: Test.Result = Test.check(Parameters.defaultVerbose,validProperty)
    val scalaCheckResult: Test.Result = Test.check(Parameters.defaultVerbose.withMinSuccessfulTests(10000),validProperty)
    assert(scalaCheckResult.passed)
  }

}
