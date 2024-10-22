// #Sireum #Logika
//@Logika: --manual --background save
import org.sireum._
import org.sireum.justification._
import org.sireum.justification.natded.prop._
import org.sireum.justification.natded.pred._

// Updates parameter a, which is of type array of integers (ZS),
// in place so that each of its ints are squared
def square(a: ZS): Unit = {
  Contract(
    Modifies(a),
    Ensures(
      a.size == In(a).size,
      ∀(0 until a.size)(i => a(i) == In(a)(i) * In(a)(i)) // ∀{ (i: Z) => (0 <= i & i < a.size) ___>: (a(i) == In(a)(i) * In(a)(i)) }
    )
  )

  Deduce((  In(a) ≡ a  ) by Premise)

  var x: Z = 0

  Deduce(
   //@formatter:off
    1  (  In(a) ≡ a                                         ) by Premise,
    2  (  x == 0                                            ) by Premise,
    3  (  0 <= x                                            ) by Algebra* 2,
    4  (  0 <= a.size                                       ) by Algebra T,
    5  (  x <= a.size                                       ) by Subst_>(2, 4),
    6  SubProof {(j: Z) => (
        7  SubProof(
          8  Assume(  0 <= j & j < x  ),
          9  (  0 <= j                                      ) by AndE1(8),
         10  (  j < x                                       ) by AndE2(8),
         11  (  F                                           ) by Algebra* (9, 10, 2),
         12  (  a(j) == In(a)(j) * In(a)(j)                 ) by BottomE(11)
       ),
   13  (  (0 <= j & j < x) ___>: (a(j) == In(a)(j) * In(a)(j))  ) by SImplyI(7)
   )},
   14  (  ∀(0 until x)(i => a(i) == In(a)(i) * In(a)(i))    ) by AllI[Z](6),
   15  SubProof {(j: Z) => (
        16  SubProof(
             17  Assume(  x <= j & j < a.size  ),
             18  (  x <= j                                  ) by AndE1(17),
             19  (  j < a.size                              ) by AndE2(17),
             20  (  a(j) == In(a)(j)                        ) by Algebra* (1, 2, 18, 19)
            ),
        21  (  (x <= j & j < a.size) ___>: (a(j) == In(a)(j))   ) by SImplyI(16)
       )},
   22  (  ∀(x until a.size)(i => a(i) == In(a)(i))          ) by AllI[Z](15)
   //@formatter:on
  )

  while (x != a.size) {
    Invariant(
      Modifies(x, a),
      0 <= x,
      x <= a.size,
      a.size == In(a).size,
      ∀(0 until x)(i => a(i) == In(a)(i) * In(a)(i)),
      ∀(x until a.size)(i => a(i) == In(a)(i))
    )

    Deduce(
      //@formatter:off
      1  (  ∀(0 until x)(i => a(i) == In(a)(i) * In(a)(i))  ) by Premise,
      2  (  ∀(x until a.size)(i => a(i) == In(a)(i))        ) by Premise,
      3  (  0 <= x                                          ) by Premise,
      4  (  x <= a.size                                     ) by Premise,
      5  (  x != a.size                                     ) by Premise,
      6  (  x < a.size                                      ) by Algebra* (4, 5)
      //@formatter:on
    )

    a(x) = a(x) * a(x)

    Deduce(
     //@formatter:off
      1  (  a ≡ Old(a)(x ~> (Old(a)(x) * Old(a)(x)))                              ) by Premise,
      2  (  ∀(0 until x)(i => Old(a)(i) == In(a)(i) * In(a)(i))                   ) by Premise,
      3  (  ∀(x until Old(a).size)(i => Old(a)(i) == In(a)(i))                    ) by Premise,
      4  (  x < Old(a).size                                                       ) by Premise,
      5  (  0 <= x                                                                ) by Premise,
      6  (  a.size == Old(a).size                                                 ) by Algebra* (4, 5, 1),
      7  (  x < a.size                                                            ) by Subst_>(6, 4),
      8  (  ∀(x until a.size)(i => Old(a)(i) == In(a)(i))                         ) by Subst_>(6, 3),
      9  (  a(x) == Old(a)(x) * Old(a)(x)                                         ) by Algebra* (5, 7, 6, 1),
     10  (  (x <= x & x < Old(a).size) ___>: (Old(a)(x) == In(a)(x))                  ) by AllE[Z](3),
     11  (  x <= x                                                                ) by Algebra T,
     12  (  x <= x & x < Old(a).size                                              ) by AndI(11, 4),
     13  (  Old(a)(x) == In(a)(x)                                                 ) by SImplyE(10, 12),
     14  (  a(x) == In(a)(x) * In(a)(x)                                           ) by Subst_<(13, 9),
     15  SubProof {(j: Z) => (
          16  SubProof(
               17  Assume(  0 <= j & j <= x  ),
               18  (  0 <= j                                                      ) by AndE1(17),
               19  (  j <= x                                                      ) by AndE2(17),
               20  SubProof(
                    21  Assume(  j < x  ),
                    22  (  0 <= j & j < x                                         ) by AndI(18, 21),
                    23  (  (0 <= j & j < x) ___>: (Old(a)(j) == In(a)(j) * In(a)(j))  ) by AllE[Z](2),
                    24  (  Old(a)(j) == In(a)(j) * In(a)(j)                       ) by SImplyE(23, 22),
                    25  (  Old(a)(j) == a(j)                                      ) by Algebra* (18, 21, 4, 1),
                    26  (  a(j) == In(a)(j) * In(a)(j)                            ) by Subst_<(25, 24)
                   ),
               27  SubProof(
                    28  Assume(  j == x  ),
                    29  (  a(j) == In(a)(j) * In(a)(j)                            ) by Subst_>(28, 14)
                   ),
               30  (  a(j) == In(a)(j) * In(a)(j)                                 ) by OrE(19, 20, 27)
              ),
          31  (  (0 <= j & j <= x) ___>: (a(j) == In(a)(j) * In(a)(j))                ) by SImplyI(16)
         )},
     32  (  ∀(0 to x)(i => a(i) == In(a)(i) * In(a)(i))                           ) by AllI[Z](15),
     33  SubProof {(j: Z) => (
          34  SubProof(
               35  Assume(  x + 1 <= j & j < a.size  ),
               36  (  x + 1 <= j                                                  ) by AndE1(35),
               37  (  j < a.size                                                  ) by AndE2(35),
               38  (  0 <= j                                                      ) by Algebra* (5, 36),
               39  (  j != x                                                      ) by Algebra* 36,
               40  (  a(j) == Old(a)(j)                                           ) by Algebra* (38, 37, 39, 5, 6, 7, 1),
               41  (  (x <= j & j < a.size) ___>: (Old(a)(j) == In(a)(j))             ) by AllE[Z](8),
               42  (  x <= j                                                      ) by Algebra* 36,
               43  (  x <= j & j < a.size                                         ) by AndI(42, 37),
               44  (  Old(a)(j) == In(a)(j)                                       ) by SImplyE(41, 43),
               45  (  a(j) == In(a)(j)                                            ) by Subst_<(44, 40)
              ),
       46  (  (x + 1 <= j & j < a.size) ___>: (a(j) == In(a)(j))                      ) by SImplyI(34)
     )},
     47  (  ∀((x + 1) until a.size)(i => a(i) == In(a)(i))                        ) by AllI[Z](33)
     //@formatter:on
    )

    x = x + 1

    Deduce(
     //@formatter:off
      1  (  x == Old(x) + 1                                                   ) by Premise,
      2  (  0 <= Old(x)                                                       ) by Premise,
      3  (  Old(x) < a.size                                                   ) by Premise,
      4  (  ∀(0 to Old(x))(i => a(i) == In(a)(i) * In(a)(i))                  ) by Premise,
      5  (  ∀(Old(x) + 1 until a.size)(i => a(i) == In(a)(i))                 ) by Premise,
      6  (  0 <= x                                                            ) by Algebra* (1, 2),
      7  (  x <= a.size                                                       ) by Algebra* (1, 3),
      8  (  ∀(x until a.size)(i => a(i) == In(a)(i))                          ) by Subst_>(1, 5),
      9  SubProof {(j: Z) => (
          10  SubProof(
               11  Assume(  0 <= j & j < x  ),
               12  (  0 <= j                                                  ) by AndE1(11),
               13  (  j < x                                                   ) by AndE2(11),
               14  (  j <= Old(x)                                             ) by Algebra* (13, 1),
               15  (  0 <= j & j <= Old(x)                                    ) by AndI(12, 14),
               16  (  (0 <= j & j <= Old(x)) ___>: (a(j) == In(a)(j) * In(a)(j))  ) by AllE[Z](4),
               17  (  a(j) == In(a)(j) * In(a)(j)                             ) by SImplyE(16, 15)
              ),
          18  (  (0 <= j & j < x) ___>: (a(j) == In(a)(j) * In(a)(j))             ) by SImplyI(10)
         )},
     20  (  ∀(0 until x)(i => a(i) == In(a)(i) * In(a)(i))                    ) by AllI[Z](9)
     //@formatter:on
    )

  }

  Deduce(
    //@formatter:off
    1  (  ∀(0 until x)(i => a(i) == In(a)(i) * In(a)(i))       ) by Premise,
    2  (  !(x != a.size)                                       ) by Premise,
    3  (  x == a.size                                          ) by Algebra* 2,
    4  (  ∀(0 until a.size)(i => a(i) == In(a)(i) * In(a)(i))  ) by Subst_<(3, 1),
    5  (  a.size == In(a).size                                 ) by Premise
    //@formatter:on
  )
}