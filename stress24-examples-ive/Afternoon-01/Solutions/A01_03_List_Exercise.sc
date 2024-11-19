// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

// List trait with some basic list functionality implemented.
@datatype trait List[T] {

  @strictpure def length: Z = this match {
    case List.Cons(_, next) => 1 + next.length
    case _ => 0
  }

  @strictpure def hd: T = this match {
    case List.Cons(value, _) => value
    case _ => halt("Trying to access hd on an empty list")
  }

  @strictpure def tl: List[T] = this match {
    case List.Cons(_, next) => next
    case _ => List.Nil()
  }

  @strictpure def take(n: Z): List[T] = if (n > 0) {
    this match {
      case List.Cons(value, next) => List.Cons(value, next.take(n - 1))
      case _ => List.empty
    }
  } else {
    List.empty
  }
}

object List {

  @datatype class Nil[T] extends List[T]

  @datatype class Cons[T](val value: T, val next: List[T]) extends List[T]

  @strictpure def empty[T]: List[T] = Nil()

}

// Provide the inductive proof. Give a series of deductions,
// starting with a set of Premises.
@pure def take_all[T](x: List[T]): Unit = {
  Contract(
    Ensures(x.take(x.length) ≡ x)
  )

  (x: @induct) match {
    case List.Cons(value, next) => return
    case List.Nil() => return
  }
}