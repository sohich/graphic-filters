package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class GammaCorrectionFilter(private val gamma: Double): Filter {
    override fun apply(originalImage:BufferedImage): BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        for (j in 0 until height)
        {
            for (i in 0 until width)
            {
                val bufColor = Color(originalImage.getRGB(i, j))
                val color = Color(
                        (Math.pow(bufColor.red   / 255.0, 1 / gamma) * 255).toInt(),
                        (Math.pow(bufColor.green / 255.0, 1 / gamma) * 255).toInt(),
                        (Math.pow(bufColor.blue  / 255.0, 1 / gamma) * 255).toInt()
                ).rgb
                newImage.setRGB(i, j, color)
            }
        }
        return newImage
    }
}