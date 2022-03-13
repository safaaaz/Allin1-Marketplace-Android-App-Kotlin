package com.safaashatha.allin1

class owner {
    var name:String?=null
    var about:String=""
    var address:String=""
    var category:String=""
    var phone:String?=null
    constructor(
        name:String,
        address:String="",
        category:String="",
        phone: String =""){
        this.name=name
        this.address=address
        this.category=category
        this.phone=phone

    }
}