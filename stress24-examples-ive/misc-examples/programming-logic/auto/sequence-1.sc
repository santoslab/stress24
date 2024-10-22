// #Sireum #Logika
//@Logika: --background save
import org.sireum._

val a: ZS = ZS(1, 2, 3)

assert(a.size == 3)

assert(a(0) == 1)         // 0 ≤ 0 and 0 < a.size are checked

assert(a(1) == 2)         // 0 ≤ 1 and 1 < a.size are checked

assert(a(2) == 3)         // 0 ≤ 2 and 2 < a.size are checked

assert(a == ZS(1, 2, 3))  // == is equivalence on content!