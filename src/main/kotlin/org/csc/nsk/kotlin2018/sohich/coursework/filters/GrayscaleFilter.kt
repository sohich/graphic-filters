package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.*
import java.awt.image.BufferedImage

class GrayscaleFilter: Filter {
    override fun apply(originalImage: BufferedImage): BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        var bufColor:Int
        for (j in 0 until height) {
            for (i in 0 until width) {
                bufColor = originalImage.getRGB(i, j)
                newImage.setRGB(i, j, invertPixelColor(bufColor))
            }
        }
        return newImage
    }

    private fun invertPixelColor(pixelRGB: Int): Int {
        val pixelColor = Color(pixelRGB)
        return Color(
                255 - pixelColor.red,
                255 - pixelColor.green,
                255 - pixelColor.blue).rgb
    }
}