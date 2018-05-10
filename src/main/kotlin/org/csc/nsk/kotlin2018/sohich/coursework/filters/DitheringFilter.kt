package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class FloydDitheringFilter(private val redCount: Int,
                           private val greenCount: Int,
                           private val blueCount: Int):Filter {
    private val matrix = arrayOf(
            doubleArrayOf(0.0, 0.0, 0.0),
            doubleArrayOf(0.0, -1.0, 7.0 / 16),
            doubleArrayOf(3.0 / 16, 5.0 / 16, 1.0 / 16))

    override fun apply(originalImage: BufferedImage):BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        newImage.graphics.drawImage(originalImage, 0, 0, width, height, null)

        for (j in 0 until height - 1) {
            for (i in 0 until width - 1) {
                for (n in matrix.indices) {
                    val y = j + n - 1
                    if (y < 1 || y >= height - 1) {
                        continue
                    }
                    for (m in 0 until matrix[n].size) {
                        val x = i + m - 1
                        if (x < 1 || x >= width - 1) {
                            continue
                        }
                        val oldColor = Color(newImage.getRGB(x, y))
                        val newColor = toPaletteColor(oldColor)
                        val diff = diffColor(oldColor, newColor)
                        val bufColor = addColor(newColor, multiplyByConst(diff, matrix[m][n]))
                        newImage.setRGB(x, y, bufColor.rgb)
                    }
                }
            }
        }
        return newImage
    }
    private fun diffColor(c1:Color, c2:Color):Color {
        return Color(
                saturate(Math.abs(c1.red   - c2.red)),
                saturate(Math.abs(c1.green - c2.green)),
                saturate(Math.abs(c1.blue  - c2.blue))
        )
    }
    private fun addColor(c1:Color, c2:Color):Color = Color(
                saturate(c1.red   - c2.red),
                saturate(c1.green - c2.green),
                saturate(c1.blue  - c2.blue)
        )
    private fun multiplyByConst(color:Color, value:Double):Color = Color(
                saturate((color.red * value).toInt()),
                saturate((color.green * value).toInt()),
                saturate((color.blue * value).toInt()))

    private fun toPaletteColor(color:Color):Color = Color(
                saturate(toIntervalEdge(color.red, redCount)),
                saturate(toIntervalEdge(color.green, greenCount)),
                saturate(toIntervalEdge(color.blue, blueCount)))

    private fun toIntervalEdge(value: Int, count: Int): Int {
        val interval = 256 / count
        val round = value + interval / 2
        return round - round % interval
    }
    private fun saturate(value: Int) = Math.max(0, Math.min(value, 255))
}