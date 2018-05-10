package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class EmbossFilter: Filter {
    // convolution matrix here:
    private val matrix = arrayOf(
            intArrayOf( 0,  1, 0),
            intArrayOf(-1,  0, 1),
            intArrayOf( 0, -1, 0))

    override fun apply(originalImage:BufferedImage): BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        for (j in 0 until height) {
            for (i in 0 until width) {
                var redAmount = 0
                var greenAmount = 0
                var blueAmount = 0
                for (m in matrix.indices) {
                    val y = j + m - matrix.size / 2
                    if (y < 0 || y >= originalImage.height) {
                        continue
                    }
                    for (n in 0 until matrix[m].size) {
                        val x = i + n - matrix[m].size / 2
                        if (x < 0 || x >= originalImage.width) {
                            continue
                        }

                        val bufColor = Color(originalImage.getRGB(x, y))
                        redAmount   += bufColor.red * matrix[n][m]
                        greenAmount += bufColor.green * matrix[n][m]
                        blueAmount  += bufColor.blue * matrix[n][m]
                    }
                }
                fun saturate(value: Int) = Math.max(0, Math.min(value, 255))
                val value = ((
                        saturate(redAmount + 128) * 0.299 +
                        saturate(greenAmount + 128) * 0.587 +
                        saturate(blueAmount + 128) * 0.114)).toInt()
                val color = Color(value, value, value).rgb
                newImage.setRGB(i, j, color)
            }
        }
        return newImage
    }
}