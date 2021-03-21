/*
    🐛 LOGGING UTILS

    Small abstractions over console.* calls. To make a future
    Kotlin Multiplatform refactoring a bit easier.
 */

fun logD(msg: String, vararg o: Any?) {
    if(o.isEmpty()) console.log(msg) else console.log(msg, *o)
}
fun logD(vararg o: Any?) = console.log(o)

fun logI(msg: String, vararg o: Any?) {
    if(o.isEmpty()) console.info(msg) else console.info(msg, *o)
}
fun logI(vararg o: Any?) = console.info(o)

fun logW(msg: String, vararg o: Any?) {
    if(o.isEmpty()) console.warn(msg) else console.warn(msg, *o)
}
fun logW(vararg o: Any?) = console.warn(o)

fun logE(msg: String, vararg o: Any?) {
    if(o.isEmpty()) console.error(msg) else console.error(msg, *o)
}
fun logE(vararg o: Any?) = console.error(o)

/**
 * Dumps details on the given object to a console.
 */
fun logDetails(o:Any) = console.dir(o)
