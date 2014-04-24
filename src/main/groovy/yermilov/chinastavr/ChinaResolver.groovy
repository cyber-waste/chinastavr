package yermilov.chinastavr

/**
 * @author yaroslav.yermilov
 */
class ChinaResolver {

    def a, m

    def mInverse
    def b

    def resolve() {
        findMInverse()
        findB()

        def A = findA()
        def M = findM()

        "${A}+${M}n"
    }

    def findMInverse() {
        mInverse = (0..<a.size()).collect {
            i ->
                (0..<i).collect {
                    j -> findInverse(m[j], m[i])
                }
        }
    }

    def findB() {
        b = []
        (0..<a.size()).each {
            i ->
                b[i] = a[i];
                (0..<i).each {
                    j ->
                        b[i] = (mInverse[i][j] * (b[i] - b[j]) % m[i] + m[i]) % m[i]
                }
        }
    }

    def findA() {
        (0..<b.size()).sum {
            m[0..<it].inject(b[it], {
                a, b -> a * b
            })
        }
    }

    def findM() {
        m.inject(1, {
            a, b -> a * b
        })
    }

    def findInverse(BigInteger a, BigInteger m) {
        (gcdex(a, m).x % m + m) % m
    }

    def gcdex(BigInteger a, BigInteger b) {
        if (a == 0) {
            return new Expando(x: new BigInteger(0), y: new BigInteger(1), gcd: b)
        }

        def answer = gcdex(b % a, a)
        BigInteger div = b / a

        BigInteger x = answer.y - div * answer.x
        BigInteger y = answer.x

        return new Expando(x: x, y: y, gcd: answer.gcd)
    }
}
