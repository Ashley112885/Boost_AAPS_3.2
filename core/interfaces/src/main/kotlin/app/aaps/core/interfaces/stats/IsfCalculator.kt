package app.aaps.core.interfaces.stats

import app.aaps.core.interfaces.iob.GlucoseStatus
import app.aaps.core.interfaces.profile.Profile
import org.json.JSONObject

interface IsfCalculator {
    fun calculateAndSetToProfile(profile : Profile, insulinDivisor: Int, glucose: GlucoseStatus, isTempTarget: Boolean, profileJson : JSONObject? = null) : IsfCalculation
}