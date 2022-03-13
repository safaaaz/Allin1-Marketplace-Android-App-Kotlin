package com.safaashatha.allin1

import androidx.annotation.DrawableRes




class product {
    var name:String?=null
    var price:String=""
    var category:String=""
    var image:Int?=null
    


    constructor(
        name:String,
        price:String="",
        category:String="",
        image:Int=0,

    )
    {
        this.name=name
        this.price=price
        this.category=category
        this.image=image


    }
     fun printproduct(){
        print(this.name)
        print(this.category)
        print(this.price)
    }
}