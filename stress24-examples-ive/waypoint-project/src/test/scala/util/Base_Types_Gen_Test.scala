package util

import org.scalatest.funsuite.AnyFunSuite
import org.scalacheck.{Prop, Test}
import org.scalacheck.Test.Parameters
import org.sireum.println
import org.sireum.Z

class Base_Types_Gen_Test extends AnyFunSuite{


  // illustrate the use of Base Type (could be auto-generated)

  test("Z sampling") {

    // use ScalaCheck "sample" on a generator object to get random values from the generator
    val myZGen = Base_Types_Gen.Z.default
    println(s"Z Sample 1: ${myZGen.sample}")
    println(s"Z Sample 2: ${myZGen.sample}")
    println(s"Z Sample 3: ${myZGen.sample}")

    assert(true)
  }

  test("Z default") {
    val prop =
      Prop.forAll(Base_Types_Gen.Z.default){z: Z =>
        // replace code below with call to method, etc. to check with
        // argument Z value here.  Result should be Boolean indicating
        // success or fail
        println(z)
        true
      }

    // ScalaCheck parameters such as # times to test property, etc. can be tweaked
    // via the parameters below
    val scalaCheckResult: Test.Result = Test.check(Parameters.defaultVerbose,prop)
    assert(scalaCheckResult.passed)
  }

  test("Z bounded [-20,20]") {
    val prop =
      Prop.forAll(Base_Types_Gen.Z.bounded(-20,20)){z: Z =>
        // replace code below with call to method, etc. to check with
        // argument f32 here.  Result should be Boolean indicating
        // success or fail
        println(z)
        true
      }

    // ScalaCheck parameters such as # times to test property, etc. can be tweaked
    // via the parameters below
    val scalaCheckResult: Test.Result = Test.check(Parameters.defaultVerbose,prop)
    assert(scalaCheckResult.passed)
  }
}
