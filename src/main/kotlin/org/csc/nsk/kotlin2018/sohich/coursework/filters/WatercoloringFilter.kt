package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class WatercoloringFilter: Filter {
    private val range = 2
    override fun apply(originalImage: BufferedImage): BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        val redValues = ArrayList<Int>()
        val greenValues = ArrayList<Int>()
        val blueValues = ArrayList<Int>()
        for (j in 0 until height) {
            for (i in 0 until width) {
                redValues.clear()
                greenValues.clear()
                blueValues.clear()
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
                        redValues.add(bufColor.red)
                        greenValues.add(bufColor.green)
                        blueValues.add(bufColor.blue)
                    }
                }
                redValues.sort()
                greenValues.sort()
                blueValues.sort()
                val color = Color(
                        redValues[redValues.size / 2],
                        greenValues[greenValues.size / 2],
                        blueValues[blueValues.size / 2]
                ).rgb
                newImage.setRGB(i, j, color)
            }
        }
        return SharpeningFilter().apply(newImage)
    }
}