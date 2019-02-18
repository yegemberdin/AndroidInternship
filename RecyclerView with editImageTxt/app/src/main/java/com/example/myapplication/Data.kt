package com.example.myapplication

import android.R.attr.name



class Data {


    var name: String = ""
    var surname: String = ""
    var imageUri: Int= 0
    fun getNames(): String {
        return name.toString()
    }

    fun setNames(name: String) {
        this.name = name
    }
    fun getSurnames(): String {
        return surname.toString()
    }

    fun setSurnames(surname: String) {
        this.surname = surname
    }

    fun getImage_drawables(): Int {
        return imageUri
    }

    fun setImage_drawables(image_drawable: Int) {
        this.imageUri = image_drawable
    }

    constructor() {}

    constructor(name: String, surname: String) {
        this.name = name
        this.surname = surname
    }

}