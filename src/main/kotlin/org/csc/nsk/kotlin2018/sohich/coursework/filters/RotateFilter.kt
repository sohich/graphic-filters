package org.csc.nsk.kotlin2018.sohich.coursework.filters


import java.awt.Color
import java.awt.image.BufferedImage

class RotateFilter(private val paramAngle: Int): Filter {
    override fun apply(originalImage:BufferedImage): BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        var angle = (paramAngle / 180.0) * Math.PI
        val centerX = (width / 2).toDouble()
        val centerY = (height / 2).toDouble()

        //filling canvas with white
        for (j in 0 until height) {
            for (i in 0 until width) {
                newImage.setRGB(i, j, Color.WHITE.rgb)
            }
        }
        //do operating
        for (j in 0 until height) {
            for (i in 0 until width) {
                val x = Math.cos(angle) * (i - centerX) - Math.sin(angle) * (j - centerY) + centerX
                val y = Math.sin(angle) * (i - centerX) + Math.cos(angle) * (j - centerY) + centerY
                if ((x < 0 || x >= width ||
                     y < 0 || y >= height)) {
                    continue
                }
                val bufColor = Color(originalImage.getRGB(i, j))
                newImage.setRGB(x.toInt(), y.toInt(), bufColor.rgb)
            }
        }
        //do antialiasing
        angle *= -1.0
        for (j in 0 until height) {
            for (i in 0 until width) {
                if (newImage.getRGB(i, j) == Color.WHITE.rgb) {
                    val x = Math.cos(angle) * (i - centerX) - Math.sin(angle) * (j - centerY) + centerX
                    val y = Math.sin(angle) * (i - centerX) + Math.cos(angle) * (j - centerY) + centerY
                    if ((x < 0 || x >= width ||
                         y < 0 || y >= height)) {
                        continue
                    }
                    val bufColor = Color(originalImage.getRGB(x.toInt(), y.toInt()))
                    newImage.setRGB(i, j, bufColor.rgb)
                }
            }
        }
        return newImage
    }
}