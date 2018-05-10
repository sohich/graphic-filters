package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class OrderedDitherFilter: Filter {
    private val matrixOrder = 4
    private val matrixSize = 1 shl matrixOrder
    private val matrix: Array<IntArray>
    private val coefficient: Int
        get() {
            return 256 / (matrixSize * matrixSize)
        }
    init{
        matrix = Array(matrixSize, {IntArray(matrixSize)})
        var currentSize = 1
        val dx = intArrayOf(1, 1, 0, 0)
        val dy = intArrayOf(1, 0, 1, 0)
        val u  = intArrayOf(1, 3, 2, 0)
        for (i in 0 until matrixOrder) {
            for (j in dx.indices) { for (x in 0 until currentSize) {
                    for (y in 0 until currentSize) {
                        val rx = x + dx[j] * currentSize
                        val ry = y + dy[j] * currentSize
                        matrix[rx][ry] = 4 * matrix[x][y] + u[j]
                    }
                }
            }
            currentSize *= 2
        }
    }
    override fun apply(originalImage: BufferedImage): BufferedImage {
        val width = originalImage.width
        val height = originalImage.height
        val newImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        for (j in 0 until height)
        {
            for (i in 0 until width)
            {
                val ditherValue = matrix[j % matrixSize][i % matrixSize] * coefficient
                val bufColor = Color(originalImage.getRGB(i, j))
                fun threshold(value: Int) = if (value <= ditherValue) 0 else 255
                val color = Color(
                        threshold(bufColor.red),
                        threshold(bufColor.green),
                        threshold(bufColor.blue)
                ).rgb
                newImage.setRGB(i, j, color)
            }
        }
        return newImage
    }
}