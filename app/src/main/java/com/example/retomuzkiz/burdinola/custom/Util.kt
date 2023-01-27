package com.example.retomuzkiz.burdinola.custom

import java.util.*
import kotlin.math.abs


object Util {

    private val sRand = Random()

    // ASCII A = 65 - Z = 90
    val randomChar: Char
        get() =// ASCII A = 65 - Z = 90
            getRandomIntRange(65, 90).toChar()

    /**
     * genera numero aleratorio entre min y max (inclusive)
     */
    private fun getRandomIntRange(min: Int, max: Int): Int {
        return min + randomInt % (max - min + 1)
    }

    @JvmStatic
    val randomInt: Int
        get() = abs(sRand.nextInt())

}