package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage


interface Filter {
    fun apply(originalImage: BufferedImage): BufferedImage
}