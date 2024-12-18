// #Sireum #Logika  -- #Sireum indicates file is in Slang, #Logika tells the IVE to apply Logika checking

import org.sireum._

def max2(x:Z, y:Z): Z = {
  var max: Z = 0
  if (x < y) {
    max = y
  } else {
    max = x
  }

  // Exercise 1
  // Assert that max is equal to x or max is equal to y
  // Note: Use "double equal" for equality and a single "vertical bar" for disjunction
  assert(max == x | max == y)

  // Exercise 2
  // Assert that max is greater or equal to x and max is greater than or equal to y
  // Note: Use "single ampersand" for conjunction
  assert(y <= max & x <= max)

  return max
}
