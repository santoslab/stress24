// #Sireum #Logika
import org.sireum._

def findMin(seq: ISZ[Z], k: Z): Z = {
  Contract(
    Requires(0 <= k, k < seq.size),
    Ensures(All(k until seq.size)(i => Res[Z] <= seq(i)))
  )
  var h: Z = k+1
  var x: Z = seq(k)
  while (h < seq.size) {
    Invariant(
      Modifies(x, h),
      h >= 0,
      h <= seq.size,
      All(k until h)(i => (x <= seq(i)))
    )
    if (seq(h) < x) {
      x = seq(h)
    }
    h = h + 1
  }
  return x
}
