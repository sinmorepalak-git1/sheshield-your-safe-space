package com.example.sheshield.ui.viewmodels

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Location
import android.os.BatteryManager
import android.telephony.SmsManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.sheshield.data.ContactEntity
import com.example.sheshield.data.User
import com.example.sheshield.repository.ContactRepository
import com.example.sheshield.repository.UserRepository
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class SOSViewModel(application: Application) : AndroidViewModel(application) {
    private val contactRepository = ContactRepository()
    private val userRepository = UserRepository()
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(application)

    private val _countdown = MutableStateFlow(5)
    val countdown: StateFlow<Int> = _countdown.asStateFlow()

    private val _sosState = MutableStateFlow<SOSStatus>(SOSStatus.Idle)
    val sosState: StateFlow<SOSStatus> = _sosState.asStateFlow()

    private val _notifiedContacts = MutableStateFlow<List<String>>(emptyList())
    val notifiedContacts: StateFlow<List<String>> = _notifiedContacts.asStateFlow()

    private var countdownJob: Job? = null

    fun startSOSCountdown() {
        _countdown.value = 5
        _sosState.value = SOSStatus.CountingDown
        countdownJob = viewModelScope.launch {
            while (_countdown.value > 0) {
                delay(1000)
                _countdown.value -= 1
            }
            triggerSOS()
        }
    }

    fun cancelSOS() {
        countdownJob?.cancel()
        _sosState.value = SOSStatus.Idle
        _countdown.value = 5
    }

    @SuppressLint("MissingPermission")
    private fun triggerSOS() {
        _sosState.value = SOSStatus.Activating
        viewModelScope.launch {
            try {
                // 1. Get Location
                val location = fusedLocationClient.getCurrentLocation(
                    Priority.PRIORITY_HIGH_ACCURACY,
                    CancellationTokenSource().token
                ).await()

                // 2. Get User Data
                val userResult = userRepository.getUserProfile()
                val user = userResult.getOrNull()

                // 3. Get Contacts
                val contacts = contactRepository.getContacts().first()

                // 4. Get Battery
                val battery = getBatteryPercentage()

                // 5. Generate Message
                val message = generateSOSMessage(user, location, battery)

                // 6. Send SMS
                sendSMSToContacts(contacts, message)

                _sosState.value = SOSStatus.Active
            } catch (e: Exception) {
                _sosState.value = SOSStatus.Error(e.message ?: "Failed to trigger SOS")
            }
        }
    }

    private fun getBatteryPercentage(): Int {
        val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let { ifilter ->
            getApplication<Application>().registerReceiver(null, ifilter)
        }
        val level: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_LEVEL, -1) ?: -1
        val scale: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_SCALE, -1) ?: -1
        return (level * 100 / scale.toFloat()).toInt()
    }

    private fun generateSOSMessage(user: User?, location: Location?, battery: Int): String {
        val name = user?.name?.ifEmpty { "User" } ?: "User"
        val phone = user?.phone?.ifEmpty { "Not specified" } ?: "Not specified"
        val mapsLink = location?.let { "https://www.google.com/maps/search/?api=1&query=${it.latitude},${it.longitude}" } ?: "Unknown"
        val time = SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())

        return """
            🚨 EMERGENCY ALERT 🚨
            
            $name may need immediate help.
            
            📍 Location:
            $mapsLink
            
            🔋 Battery: $battery%
            📱 Phone: $phone
            🕒 Time: $time
            
            Sent from SheShield.
        """.trimIndent()
    }

    private fun sendSMSToContacts(contacts: List<ContactEntity>, message: String) {
        val smsManager: SmsManager = getApplication<Application>().getSystemService(SmsManager::class.java)
        val notified = mutableListOf<String>()
        
        contacts.forEach { contact ->
            try {
                if (contact.phone.isNotBlank()) {
                    smsManager.sendTextMessage(contact.phone, null, message, null, null)
                    notified.add(contact.phone)
                }
            } catch (e: Exception) {
                // Log SMS failure
            }
        }
        _notifiedContacts.value = notified
    }
}

sealed class SOSStatus {
    object Idle : SOSStatus()
    object CountingDown : SOSStatus()
    object Activating : SOSStatus()
    object Active : SOSStatus()
    data class Error(val message: String) : SOSStatus()
}

// Extension to make Task awaitable in coroutines if not already available
suspend fun <T> com.google.android.gms.tasks.Task<T>.await(): T? {
    if (isComplete) {
        val e = exception
        return if (e == null) result else throw e
    }

    return kotlinx.coroutines.suspendCancellableCoroutine { cont ->
        addOnCompleteListener {
            val e = it.exception
            if (e == null) {
                cont.resume(it.result, null)
            } else {
                cont.resumeWith(Result.failure(e))
            }
        }
    }
}
