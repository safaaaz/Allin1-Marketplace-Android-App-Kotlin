package com.safaashatha.allin1

import android.media.Image
import android.provider.ContactsContract
import java.net.PasswordAuthentication
import java.util.*
import kotlin.collections.HashMap

data class user(
    var id:String="",
    var Firstname: String = "",
    var Lastname: String = "",
    var Email: String="",
    var Password: PasswordAuthentication? = null,
    var Birthday: Date = Date(),
    var Address: String = "",
    var phone: Int = 0,
    var Image: Int=0,
    var Cardnum: Int = 0,
    var Cardcode: Int=0,
    var Cardid: Int = 0,
    var Cardname: Int=0,
)
