// #Sireum #Logika
//@Logika: --background save
import org.sireum._

val x: Z = Z.read()
val y: Z = Z.read()

var max: Z = 0

if (x > y) {
  max = x
} else {
  max = y
}

assert(max >= x & max >= y & (max == x | max == y))