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

    def findInverse(def a, def m) {
        (gcdex(a, m).x % m + m) % m
    }

    def gcdex(def a, def b) {
        if (a == 0) {
            return new Expando(x: 0, y: 1, gcd: b)
        }

        def answer = gcdex(b % a, a);
        int x = answer.y - (b / a) * answer.x;
        int y = answer.x;

        return new Expando(x: x, y: y, gcd: answer.gcd)
    }
}
