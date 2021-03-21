package pikaday

import kotlin.js.Date
import kotlin.js.Json

/*
@JsModule("js-datepicker")
@JsNonModule
@JsName("datepicker")
external fun datepicker(sel:dynamic, options:dynamic?)
*/
@JsModule("pikaday")
@JsNonModule
external class Pikaday(options: Json) {
    override fun toString():String
    fun getDate(): Date
    fun setDate(string:String)
    fun setDate(string:String, preventSelect:Boolean)
    fun clear()
    fun gotoDate(date: Date)
    fun gotoToday()
    fun setMinDate(date: Date)
    fun setMaxDate(date: Date)
    fun destroy()
    fun hide()
    fun show()
    fun isVisible():Boolean
}