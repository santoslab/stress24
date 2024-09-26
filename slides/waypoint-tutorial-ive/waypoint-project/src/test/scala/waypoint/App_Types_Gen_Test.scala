package waypoint

import org.scalacheck.Test.Parameters
import org.scalacheck.{Prop, Test}
import org.scalatest.funsuite.AnyFunSuite
import org.sireum.{Z, println}

class App_Types_Gen_Test extends AnyFunSuite{


  // illustrate the use of Base Type (could be auto-generated)

  test("WayPoint sampling") {

    // use ScalaCheck "sample" on a generator object to get random values from the generator
    val myWayPointGen = App_Types_Gen.WayPoint.default
    println(s"Z Sample 1: ${myWayPointGen.sample}")
    println(s"Z Sample 2: ${myWayPointGen.sample}")
    println(s"Z Sample 3: ${myWayPointGen.sample}")

    assert(true)
  }

  test("WayPoint default") {
    val prop =
      Prop.forAll(App_Types_Gen.WayPoint.default){w: WayPointLib.WayPoint =>
        // replace code below with call to method, etc. to check with
        // argument w value here.  Result should be Boolean indicating
        // success or fail
        println(w)
        true
      }

    // ScalaCheck parameters such as # times to test property, etc. can be tweaked
    // via the parameters below
    val scalaCheckResult: Test.Result = Test.check(Parameters.defaultVerbose,prop)
    assert(scalaCheckResult.passed)
  }

  test("WayPoint bounded [-20,20]") {
    val prop =
      Prop.forAll(App_Types_Gen.WayPoint.bounded(-20,20)){w: WayPointLib.WayPoint =>
        // replace code below with call to method, etc. to check with
        // argument w here.  Result should be Boolean indicating
        // success or fail
        println(w)
        true
      }

    // ScalaCheck parameters such as # times to test property, etc. can be tweaked
    // via the parameters below
    val scalaCheckResult: Test.Result = Test.check(Parameters.defaultVerbose,prop)
    assert(scalaCheckResult.passed)
  }
}
