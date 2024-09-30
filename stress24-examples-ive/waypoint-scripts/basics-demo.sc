// #Sireum #Logika  -- #Sireum indicates file is in Slang, #Logika tells the IVE to apply Logika checking

import org.sireum._

val m: Z = 3
val n: Z = 5
val z: Z = m + n
val y: Z = z - n
val x: Z = z - y

assert(x == n & y == m)

