package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class SharpeningFilter: Filter {
    override fun apply(originalImage: BufferedImage): BufferedImage {
        val matrix = arrayOf(
                intArrayOf( 0, -1,  0),
                intArrayOf(-1,  5, -1),
                intArrayOf( 0, -1,  0)
        )
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        for (j in 0 until height) {
            for (i in 0 until width) {
                var redAmount = 0
                var greenAmount = 0
                var blueAmount = 0
                var neighborsMet = 0
                for (m in 0 until matrix.size) {
                    val y = j + m - matrix.size / 2
                    if (y < 0 || y >= height) {
                        continue
                    }
                    for (n in 0 until matrix[m].size) {
                        val x = i + n - matrix[m].size / 2
                        if (x < 0 || x >= originalImage.width) {
                            continue
                        }
                        val bufColor = Color(originalImage.getRGB(x, y))
                        redAmount += bufColor.red * matrix[n][m]
                        greenAmount += bufColor.green * matrix[n][m]
                        blueAmount += bufColor.blue * matrix[n][m]
                        neighborsMet += matrix[n][m]
                    }
                }
                fun saturate(value: Int) = Math.max(0, Math.min(value, 255))
                val color = Color(
                        saturate(redAmount   / neighborsMet),
                        saturate(greenAmount / neighborsMet),
                        saturate(blueAmount  / neighborsMet)
                ).rgb
                newImage.setRGB(i, j, color)
            }
        }
        return newImage
    }
}