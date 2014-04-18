package yermilov.chinastavr

import org.apache.commons.io.IOUtils

/**
 * @author yaroslav.yermilov
 */

String.metaClass.lines = {
    IOUtils.readLines(getClass().getResourceAsStream(delegate))
}

def a = []
def m = []

'/input.data'.lines().each {
    line ->
        def elements = line.replace('x', ' ').replace('=', ' ').replace('mod', ' ').findAll({
            it.trim().length() > 0
        })

        def aAsString = elements[0]
        def mAsString = elements[1]

        a << new BigInteger(aAsString)
        m << new BigInteger(mAsString)
}

def chinaResolver = new ChinaResolver(a: a, m: m)
println chinaResolver.resolve()
