// #Sireum #Logika
import org.sireum._
import org.sireum.justification._

// Proof with various tactics: Simpl, Subst, Auto,
// Use record or datatype?

@datatype class Ident (val name: String, val n: Z)

// Observe the following property:
@pure def symm_prop(decls: ISZ[Ident]): Unit = {
  Contract(
    Requires(All(decls.indices)(i => All(0 until i)(j => (decls(i) != decls(j))))),
    Ensures(All(decls.indices)(i => All(decls.indices)(j => (i != j) ->: (decls(i) != decls(j)))))
  )
}

@abs def unique_S(decls: ISZ[Ident]): B =
  All(decls.indices)(i => All(decls.indices)(j => (i != j) ->: (decls(i) != decls(j))))

// ... to implement more efficiently:
@pure def unique(decls: ISZ[Ident]): B = {
  Contract(
    Ensures(Res == unique_S(decls))
  )
  var h: Z = 0
  while (h < decls.size) {
    Invariant(
      Modifies(h),
      0 <= h, h <= decls.size,
      All(0 until h)(i => All(0 until i)(j => (decls(i) != decls(j))))
    )
    var k: Z = 0
    while (k < h) {
      Invariant(
        Modifies(k),
        0 <= k, k <= decls.size,
        All(0 until k)(j => (decls(h) != decls(j)))
      )
      if (decls(h) == decls(k)) {
        return false
      }
      k = k + 1
    }
    h = h + 1
  }
  return true
}

@pure def unique_I (seq: ISZ[Ident]): Unit = {
  Contract(
    Requires(All(seq.indices)(i => All(0 until i)(j => (seq(i) != seq(j))))),
    Ensures(unique_S(seq))
  )
  Deduce(
    1 (All(seq.indices)(i => All(0 until i)(j => (seq(i) != seq(j))))) by Premise,
    2 (All(seq.indices)(i => All(seq.indices)(j => (i != j) ->: (seq(i) != seq(j))))) by Auto,
    3 (unique_S(seq)) by Auto and 2
  )
}

@pure def unique_extend(seq: ISZ[Ident], x: Ident): Unit = {
  Contract(
    Requires(unique(seq), All(seq.indices)(i => seq(i) != x)),
    Ensures(unique(seq :+ x))
  )
  Deduce(
    1 (unique(seq)) by Auto,
    2 (All(seq.indices)(i => seq(i) != x)) by Auto,
    3 (All(seq.indices)(i => All(seq.indices)(j => (i != j) ->: (seq(i) != seq(j))))) by Auto and 1,
    4 Let ((seqx: ISZ[Ident]) => SubProof(
      5 Assume(seqx == seq :+ x),
      6 Let ((i: Z) => SubProof(
        7 Assume(0 <= i & i < (seq :+ x).size),
        8 Let ((j: Z) => SubProof(
          9 Assume(0 <= j & j < i),
          10 SubProof(
            11 Assume(i < seq.size),
            12 (seqx(i) != seqx(j)) by Auto
          ),
          13 SubProof(
            14 Assume(i >= seq.size),
            15 (i == seq.size) by Auto,
            16 (seqx(i) != seqx(j)) by Auto
          ),
          17 (seqx(i) != seqx(j)) by Auto
        ))
      )),
      18 (All((seqx).indices)(i => All(0 until i)(j => ((seqx)(i) != (seqx)(j))))) by Auto,
      19 (All(seqx.indices)(i => All(seqx.indices)(j => (i != j) ->: (seqx(i) != seqx(j))))) by Auto,
      20 (unique(seqx)) by Auto
    ))
  )
}

@pure def contains[T](seq: ISZ[T], x: T): B = {
  Contract(
    Ensures(Res == Exists(seq.indices)(i => seq(i) == x))
  )
  var k: Z = 0
  while (k < seq.size) {
    Invariant(
      Modifies(k),
      0 <= k, k <= seq.size,
      All(0 until k)(i => seq(i) != x)
    )
    if (seq(k) == x) {
      return true
    }
    k = k + 1
  }
  return false
}

@pure def natural(decls: ISZ[Ident]): B = {
  Contract(
    Ensures(Res == All(decls.indices)(i => decls(i).n >= 0))
  )
  var k: Z = 0
  while (k < decls.size) {
    Invariant(
      Modifies(k),
      0 <= k, k <= decls.size,
      All(0 until k)(i => decls(i).n >= 0)
    )
    if (decls(k).n < 0) {
      return false
    }
    k = k + 1
  }
  return true
}

@pure def ubound(decls: ISZ[Ident], name: String): Z = {
  Contract(
    Ensures(Res[Z] >= -1, All(decls.indices)(i => (decls(i).name == name) ->: (decls(i).n <= Res)))
  )
  var b: Z = -1
  var k: Z = 0
  while (k < decls.size) {
    Invariant(
      Modifies(k, b),
      0 <= k, k <= decls.size,
      b >= -1,
      All(0 until k)(i => (decls(i).name == name) ->: (decls(i).n <= b))
    )
    if (decls(k).name == name && decls(k).n > b) {
      b = decls(k).n
    }
    k = k + 1
  }
  return b
}

@record class SymTab(var decls: ISZ[Ident])

def fresh_ident(symtab: SymTab, name: String): Z = {
  Contract(
    Requires(unique(symtab.decls), natural(symtab.decls)),
    Modifies(symtab),
    Ensures(unique(symtab.decls), natural(symtab.decls), contains(symtab.decls, Ident(name, Res)))
  )
  val bnd: Z = ubound(symtab.decls, name)
  Deduce(|- (bnd >= -1))
  val n: Z = bnd + 1
  val ident: Ident = Ident(name, n)
  Deduce(|- (All(symtab.decls.indices)(i => symtab.decls(i) != ident)))
  val newdecls = symtab.decls :+ ident
  Deduce(|- (unique(newdecls)))
  symtab.decls = newdecls
  return n
}