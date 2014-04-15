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
        def aAsString = line.split(' ')[0]
        def mAsString = line.split(' ')[1]

        a << Integer.parseInt(aAsString)
        m << Integer.parseInt(mAsString)
}

def chinaResolver = new ChinaResolver(a: a, m: m)
println chinaResolver.resolve()
