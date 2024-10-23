// #Sireum #Logika  -- #Sireum indicates file is in Slang, #Logika tells the IVE to apply Logika checking

import org.sireum._

@pure def max2(x:Z, y:Z): Z = {
  Contract(
    Ensures(Res[Z] == x | Res[Z] == y,
      x <= Res[Z] & y <= Res[Z])
  )

  var max: Z = 0
  if (x < y) {
    max = y
  } else {
    max = x
  }
  return max
}

// Exercise
//
// Without calling max2,
// (a) complete the implementation of the method max3 that takes
// three integers as arguments and returns the maximum of the arguments.
//
// (b) write an appropriate method contract for max3 that specifies its
// complete functional behavior.

@pure def max3(x:Z, y:Z, z:Z): Z = {

  var max: Z = 0

  return max
}

// Exercise
//
// Write an alternate implementation of max3 that utilizes max2
// and show that the alternate implementation also conforms to the
// same contract that you gave for max3 above.

@pure def max3alt(x:Z, y:Z, z:Z): Z = {
  return 0
}

// Exercise
//
// Consider the declaration of the three variables below.
// Following the declarations, use a Deduce block to prove...
//    assuming a < b
//    we can conclude that max2(b, c) is equal to max3(a,b,c)
var a:Z = randomInt()
var b:Z = randomInt()
var c:Z = randomInt()


// Exercise
//
// Use a deduce block to prove that for all integers q:Z, max3(q,q,q) == q

// Hint:  You will need to use a syntax similar to the following
// Deduce(
//  |- (All{(q: Z) => ... })
// )


// Exercise
//
// Use a deduce block to prove that for all integers q:Z and r:Z,
// if max2(q,r) == max2(r,q)

// Hint: You can quantify over two variables together..
//  All{(q:Z, r:Z) => ...


