package com.safaashatha.allin1

class product {
    var name:String?=null
    var about:String=""
    var price:String=""
    var category:String=""
    constructor(
        name:String,
        about:String="",
        price:String="",
        category:String=""){
        this.name=name
        this.price=price
        this.category=category
        this.about=about
    }
}