package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class MedianFilter(private val range: Int): Filter {
    override fun apply(originalImage: BufferedImage): BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        for (j in 0 until height) {
            for (i in 0 until width) {
                var redAmount = 0
                var greenAmount = 0
                var blueAmount = 0
                var neighborsMet = 0

                for (m in -range..range) {
                    val y = j + m
                    if (y < 0 || y >= originalImage.height) {
                        continue
                    }
                    for (n in -range..range) {
                        val x = i + n
                        if (x < 0 || x >= originalImage.width) {
                            continue
                        }
                        val bufColor = Color(originalImage.getRGB(x, y))
                        redAmount   += bufColor.red
                        greenAmount += bufColor.green
                        blueAmount  += bufColor.blue
                        ++neighborsMet
                    }
                }
                val color = Color(
                        redAmount   / neighborsMet,
                        greenAmount / neighborsMet,
                        blueAmount  / neighborsMet
                ).rgb
                newImage.setRGB(i, j, color)
            }
        }
        return newImage
    }
}