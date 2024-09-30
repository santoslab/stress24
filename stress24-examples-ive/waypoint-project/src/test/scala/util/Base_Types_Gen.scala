package util

import org.scalacheck.Gen
import org.sireum.Z

object Base_Types_Gen {

  //===============================================
  //   Z   Generators
  //===============================================
  object Z {

    // TODO: Develop mechanism to set the bounds on simple generators for base types.
    val simple: Gen[Z] = for {
      // Use ScalaCheck's long generator to generate values for Z
      n <- Gen.choose(-1000: Long, 1000: Long)
    } yield org.sireum.Z.MP.Long(n)

    def bounded(low_Z: Z, high_Z: Z): Gen[Z] = for {
      // Use ScalaCheck's long generator to generate values for Z
      n <- Gen.choose(low_Z.toLong: Long, high_Z.toLong: Long)
    } yield org.sireum.Z.MP.Long(n)

    val default: Gen[Z] = simple
  }

}
