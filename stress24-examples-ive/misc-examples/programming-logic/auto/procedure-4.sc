// #Sireum #Logika
//@Logika: --background save
import org.sireum._

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

assert (myMoney >= 0)
assert (interestRate >= 0)

calcInterest(myMoney)

assert(interest == myMoney * interestRate)


