// #Sireum #Logika
import org.sireum._

@pure def swap(m: Z, n: Z): (Z, Z) = {
  Contract(
    Ensures(Res == (n, m))
  )
  var x: Z = m
  var y: Z = n
  x = x + y
  y = x - y
  x = x - y
  return (x, y)
}

@record class PairMut(var m: Z, var n: Z)

def swap_mut(pm: PairMut): Unit = {
  Contract(
    Modifies(pm),
    Ensures(pm.m == In(pm).n & pm.n == In(pm).m)
  )
  pm.m = pm.m + pm.n
  pm.n = pm.m - pm.n
  pm.m = pm.m - pm.n
}