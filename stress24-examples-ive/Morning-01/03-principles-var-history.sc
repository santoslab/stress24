// #Sireum #Logika

import org.sireum._

var x: Z = 1
val y: Z = x + x + 1

x = x + y
x = x + x

assert (x > y)

// Exercise:
// - Add additional assignments to x and try
//   to predict the names used for x
//    Logika's representation of the history (seen in the bulb/fact display)
// - change y to a var and add multiple assignments.
//   Try to predict the names used in the Logika history.

