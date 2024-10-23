// #Sireum #Logika
//  #Sireum tells IVE to check that code in this file is in Slang subset of Scala
//  #Logika tells IVE to apply Logika checking to the file

// Importing the Sireum libraries is required for Slang files.
//.This must be the first import after the #Sireum tag above
import org.sireum._

val x: Z = 1
val y: Z = x + x + 1
val z: Z = x + y

assert(z == 3)

// Suggested Exercise: Replace the '3' in the assertion above
//  with a value that makes the assertion succeed
