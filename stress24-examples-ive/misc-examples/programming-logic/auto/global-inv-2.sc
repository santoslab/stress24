// #Sireum #Logika
//@Logika: --background save
import org.sireum._

var balance: Z = 0
var elite: B = false
val eliteMin: Z = 1000000 // $1M is the minimum balance for elite members

@spec def inv = Invariant(
  balance >= 0,
  elite == (balance >= eliteMin)
)

// note that balance_in is the initial value of balance at the function entry point
def deposit(amount: Z): B = {
  Contract(
    Requires(amount >= 0),
    Modifies(balance, elite),
    Ensures(In(balance) == balance - amount)
  )

  balance = balance + amount
  updateElite()

  return elite
}

def withdraw(amount: Z): B = {
  Contract(
    Requires(balance >= amount),
    Modifies(balance, elite),
    Ensures(balance == In(balance) - amount)
  )

  balance = balance - amount
  updateElite()

  return elite
}

@helper def updateElite(): Unit = {
  Contract(
    Modifies(elite),
    Ensures(elite == (balance >= eliteMin))
  )

  if (balance >= eliteMin) {
    elite = true
  } else {
    elite = false
  }
}

deposit(500000)
assert(balance == 500000 & !elite)
deposit(600000)
assert(balance == 1100000 & elite)
withdraw(150000)
assert(balance == 950000 & !elite)
deposit(200000)
assert(balance == 1150000 & elite)
