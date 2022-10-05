package kr.hs.dion_kotlin

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {
    private val prefNm = "mPref"
    private val prefs = context.getSharedPreferences(prefNm, MODE_PRIVATE)

    var token: String?
        get() = prefs.getString("token", null)
        set(value) {
            prefs.edit().putString("token", value).apply()
        }
    var id: Int
        get() = prefs.getInt("id", -1)
        set(value) {
            prefs.edit().putInt("id", value).apply()
        }
    var point: Int
        get() = prefs.getInt("point", 0)
        set(value) {
            prefs.edit().putInt("point", value).apply()
        }
    var name: String?
        get() = prefs.getString("name", null)
        set(value) {
            prefs.edit().putString("name", value).apply()
        }
}

class App : Application() {
    companion object {
        lateinit var prefs: Prefs
    }

//    override fun onCreate() {
//        prefs = Prefs(applicationContext)
//        super.onCreate()
//    }
}