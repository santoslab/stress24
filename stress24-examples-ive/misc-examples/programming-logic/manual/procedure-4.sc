// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._

var interest : Z = 0
var interestRate : Z = 10

def calcInterest(principal: Z) : Z = {
  Contract(
    Requires(interestRate >= 0 & principal >= 0),
    Modifies(interest),
    Ensures(interest == principal * interestRate)
  )
  interest = principal * interestRate
  return interest
}

var myMoney : Z = 500

Deduce(
  //@formatter:off
  1  (  myMoney == 500  ) by Premise,
  2  (  myMoney >= 0    ) by Algebra* 1
  //@formatter:on
)

assert (myMoney >= 0)

Deduce(
  //@formatter:off
  1  (  interestRate == 10  ) by Premise,
  2  (  interestRate >= 0   ) by Algebra* 1
  //@formatter:on
)

assert (interestRate >= 0)

Deduce(
  //@formatter:off
  1  (  interestRate >= 0                 ) by Premise,
  2  (  myMoney >= 0                      ) by Premise,
  3  (  interestRate >= 0 & myMoney >= 0  ) by AndI(1, 2)
  //@formatter:on
)

calcInterest(myMoney)
// we get to assume its postcondition

Deduce((  interest == myMoney * interestRate  ) by Premise)

assert(interest == myMoney * interestRate)


