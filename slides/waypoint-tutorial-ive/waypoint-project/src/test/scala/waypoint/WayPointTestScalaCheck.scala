package waypoint

import org.scalacheck.Properties
import org.scalacheck.Prop.forAll
import WayPointLib._

object IntExamplesScalaCheckTests extends Properties("WayPoint") {

  // specify input / output relationship manually (without relying on executable contract/oracle)
  property("moveForwardBounded") = forAll(App_Types_Gen.WayPoint.bounded(-20,20)) { w =>
    // println(w)
    w(x = w.x + 10) == moveForward(w)
  }

  // use automatically/systematically derived oracle/contract
  property("moveForwardBoundedXContract") = forAll(App_Types_Gen.WayPoint.bounded(-20,20)) { w =>
    println(w)
    WayPointXContracts.moveForward.testWithOracle(w)
  }
}