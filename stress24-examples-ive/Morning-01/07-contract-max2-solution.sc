// #Sireum #Logika  -- #Sireum indicates file is in Slang, #Logika tells the IVE to apply Logika checking

import org.sireum._

def max2(x:Z, y:Z): Z = {
  // Exercise
  // Write a contract with only a post-condition that captures that constraints represented
  // in the two assertions that you previously wrote.   These assertions capture the following
  // properties:
  //. - max is equal to x or max is equal to y
  //. - max is greater or equal to x and max is greater than or equal to y
  //
  // Note: for an example of contract syntax, see the moveForward method in the
  //  waypoint-scripts/waypoint-05-contract.sc example
  Contract(
    Ensures(Res[Z] == x | Res[Z] == y,
      x <= Res[Z] & y <= Res[Z])
  )
  var max: Z = 0
  if (x < y) {
    max = y
  } else {
    max = x
  }

  return max
}
