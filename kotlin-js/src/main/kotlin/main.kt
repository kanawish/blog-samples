import external.model.Customer
import firebase.database.Database
import kotlinext.js.require
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.dom.create
import kotlinx.html.h1
import kotlinx.html.js.div
import kotlinx.html.js.nav
import org.w3c.dom.HTMLDivElement
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import kotlin.js.Json
import kotlin.js.json


/**
 * Pikaday init is really only css for now. Also stateless for now.
 */
fun initPikaday() {
    require("pikaday/css/pikaday.css")
}

/**
 * Bootstrap init is simple enough it can live in stateless function.
 *
 * CSS notes: https://discuss.kotlinlang.org/t/kotlin-js-a-style-from-the-npm-package/16759/2
 */
fun initBootstrap() {
    logI("require(popper)")
    val foo = require("@popperjs/core")
    logW("direct %o", foo)
    require("bootstrap/dist/css/bootstrap.css")
}

fun main() {
    initPikaday()
    initBootstrap()

    logI("Hello console world!")
    logD("And now a styled %cDebug message", "color: yellow; font-style: italic; background-color: blue;padding: 2px")

    val sandboxNav = SandboxNav()
    logW(sandboxNav, window)
    logI("start [%i, %o, %s, %.2f] end", 42, sandboxNav, "patate", 9.9)

    document.body!!.append(sandboxNav.navBar)

    document.body!!.append(HelloDiv("Foo").div)
    document.body!!.append(HelloDiv("Bar").div)
    document.body!!.append(HelloDiv("Baz").div)

    // Firebase dependencies. Note the use of `dynamic` type below...
    val fbDb = firebaseDbInit()

    GlobalScope.launch {
        val customer = fetchCustomer(fbDb, "0001")
        logI( "Fetched customer: %o", customer)
    }
}

@Suppress("UNCHECKED_CAST_TO_EXTERNAL_INTERFACE")
private fun firebaseDbInit(): Database {
    val firebase: dynamic = require("firebase/app").default
    require("firebase/database") as Database
    val firebaseConfig: Json = json(
        "apiKey" to "AIzaSyD8uQHyfWh2HBdppW-SiVG-clhRiXTRJcM",
        "authDomain" to "kanawish-blog-samples.firebaseapp.com",
        "databaseURL" to "https://kanawish-blog-samples-default-rtdb.firebaseio.com",
        "projectId" to "kanawish-blog-samples",
        "storageBucket" to "kanawish-blog-samples.appspot.com",
        "messagingSenderId" to "872035858041",
        "appId" to "1:872035858041:web:38673c69d5f931261f06a8",
        "measurementId" to "G-625CTE2C2L"
    )
    firebase.initializeApp(firebaseConfig)
    return firebase.database()
}

fun firebaseConfigExample() {

}

suspend fun fetchCustomer(database:Database, key:String):Customer {
    return suspendCoroutine { continuation ->
        database.ref("customers/$key")
            .once("value") { snap, _ ->
                val value = snap.`val`()
                logI("pre-cast %o", value)
                continuation.resume(value.unsafeCast<Customer>())
            }
            .catch { throwable ->
                logE("Error on fetchCustomer(): %o", throwable)
                continuation.resumeWithException(throwable)
            }
    }
}

/**
 * Verbatim code found the first blog sample, the cleaner version is above.
 */
fun mainSample1() {
    // Bootstrap CSS
    require("bootstrap/dist/css/bootstrap.css")

    // Firebase dependencies. Note the use of `dynamic` type below...
    val firebase: dynamic = require("firebase/app").default
    require("firebase/auth")
    require("firebase/database")

    val firebaseConfig: Json = json(
        // Add needed Firebase configuration values here, format being:
        "key" to "value"
    )
    firebase.initializeApp(firebaseConfig)

    // ...
}

class SandboxNav {
    val navBar = document.create
        .nav("fixed-top navbar navbar-expand-md navbar-light bg-light border-bottom") {
            a(classes = "navbar-brand text-responsive m-0") {
                h1("m-0") { +"SANDBOX" }
            }
            div("collapse navbar-collapse") {}
        }
}

class HelloDiv(private val name:String) {
    val div: HTMLDivElement = document.create
        .div("m-2") {
            +"Hello $name."
        }
}