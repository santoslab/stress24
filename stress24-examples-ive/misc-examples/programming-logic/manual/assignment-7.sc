// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

var dimes: Z = Z.read()
var money: Z = dimes * 10

assert(money == dimes * 10)

Deduce((  money == dimes * 10  ) by Premise)

// Say that one more dime shows up:
dimes = dimes + 1

Deduce(
  //@formatter:off
  1  (  dimes == Old(dimes) + 1    ) by Premise,
  2  (  money == Old(dimes) * 10   ) by Premise,
  3  (  Old(dimes) == dimes - 1    ) by Algebra* 1,
  4  (  money == (dimes - 1) * 10  ) by Subst_<(3, 2)
  //@formatter:on
)

// The amount of money is less that what it should be; fix it:
money = money + 10

Deduce(
  //@formatter:off
  1  (  Old(money) == (dimes - 1) * 10  ) by Premise,
  2  (  money == Old(money) + 10        ) by Premise,
  3  (  Old(money) == money - 10        ) by Algebra* 2,
  4  (  money - 10 == (dimes - 1) * 10  ) by Subst_<(3, 1),
  5  (  money == (dimes - 1) * 10 + 10  ) by Algebra* 4,
  6  (  money == dimes * 10             ) by Algebra* 5
  //@formatter:on
)

// Claim that the invariant has been re-established
assert(money == dimes * 10)