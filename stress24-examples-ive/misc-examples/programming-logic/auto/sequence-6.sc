// #Sireum #Logika
//@Logika: --background save
import org.sireum._

var a: ZS = ZS()

var b: ZS = a :+ 1

a = 0 +: a

assert(a == ZS(0))
assert(b == ZS(1))
assert(a != b)

a = a :+ 1
b = 0 +: b

assert(a == b)

a(0) = 5
assert(a != b)

b = a
assert(a == b)

val c: ZS = ZS.create(10, 0)

assert(c == ZS(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))