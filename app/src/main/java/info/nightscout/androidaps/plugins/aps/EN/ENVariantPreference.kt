package info.nightscout.androidaps.plugins.aps.EN

import android.content.Context
import android.util.AttributeSet
import androidx.preference.DropDownPreference
import dagger.android.HasAndroidInjector
import info.nightscout.androidaps.R
import info.nightscout.shared.sharedPreferences.SP
import java.util.*
import javax.inject.Inject

class ENVariantPreference(context: Context, attrs: AttributeSet?)
    : DropDownPreference(context, attrs) {


    @Inject lateinit var sp: SP

    init {
        (context.applicationContext as HasAndroidInjector).androidInjector().inject(this)

        val entries = Vector<CharSequence>()
        entries.add(DEFAULT)

        val list = context.assets.list("EN/")
        list?.forEach {
            if (!it.endsWith(".js"))
                entries.add(it)
        }

        entryValues = entries.toTypedArray()
        setEntries(entries.toTypedArray())
        setDefaultValue(sp.getString(R.string.key_boost_variant, DEFAULT))
    }
    companion object {
        const val DEFAULT = "default"

        fun getVariantFileName(sp: SP) : String
        {
            return when (val variant = sp.getString(R.string.key_en_variant, DEFAULT)) {
                DEFAULT -> "EN/determine-basal.js"
                else    -> "EN/$variant/determine-basal.js"
            }
        }
    }
}
