// #Sireum #Logika

import org.sireum._

var my_x = 50   // a variable identifier
val my_y = 30   // a value identifier

assert(my_x == 50)
assert(my_y > 20)

my_x = my_x + 1
// my_y = my_y + 1  // re-assignment to val identifier not allowed

// assert(my_x == 50)  // Logika detects assertion violation
assert(my_x > 50)

// Suggested Exercises
//   - Try adding your own variables, assignments with basic arithmetic,
//      and simple assertions

