package com.safaashatha.allin1

import androidx.annotation.DrawableRes




class product {
    var name:String?=null
    var price:String=""
    var category:String=""

    


    constructor(
        name:String,
        price:String="",
        category:String="",

    )
    {
        this.name=name
        this.price=price
        this.category=category


    }
     fun printproduct(){
        print(this.name)
        print(this.category)
        print(this.price)
    }
}