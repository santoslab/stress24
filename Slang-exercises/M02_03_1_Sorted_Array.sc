// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

@abs def sorted(a: MSZ[Z]): B = All(a.indices)(j => All(0 until j)(i => a(i) <= a(j)))

@abs def also_sorted(a: MSZ[Z]): B = All(1 until a.size)(h => a(h - 1) <= a(h))

// Prove that "also_sorted(a)" implies "sorted(a)"
@pure def also_sorted_prop(a: MSZ[Z]): Unit = {
}