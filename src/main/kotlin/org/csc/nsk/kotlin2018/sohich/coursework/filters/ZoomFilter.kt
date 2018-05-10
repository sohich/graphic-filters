package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage
import java.awt.Color

class ZoomFilter: Filter {
    override fun apply(originalImage: BufferedImage): BufferedImage {
        val oldWidth = originalImage.width
        val oldHeight = originalImage.height
        val newWidth = 2 * oldWidth
        val newHeight = 2 * oldHeight
        val newImage = BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB)

        val xScale = 1.0 * (oldWidth - 1) / newWidth
        val yScale = 1.0 * (oldHeight - 1) / newHeight

        for (j in 0 until newHeight) {
            val y = (yScale * j).toInt()
            val yDiff = yScale * j - y
            for (i in 0 until newWidth) {
                val x = (xScale * i).toInt()
                val xDiff = xScale * i - x
                val x00 = Color(originalImage.getRGB(x, y))
                val x01 = Color(originalImage.getRGB(x + 1, y))
                val x10 = Color(originalImage.getRGB(x, y + 1))
                val x11 = Color(originalImage.getRGB(x + 1, y + 1))
                val redValue = ((
                        x00.red.toDouble() * (1 - xDiff) * (1 - yDiff) +
                        x01.red.toDouble() * xDiff * (1 - yDiff) +
                        x10.red.toDouble() * (1 - xDiff) * yDiff  +
                        x11.red.toDouble() * xDiff * yDiff)).toInt()
                val greenValue = ((
                        x00.green.toDouble() * (1 - xDiff) * (1 - yDiff) +
                        x01.green.toDouble() * xDiff * (1 - yDiff) +
                        x10.green.toDouble() * (1 - xDiff) * yDiff +
                        x11.green.toDouble() * xDiff * yDiff)).toInt()
                val blueValue = ((x00.blue.toDouble() * (1 - xDiff) * (1 - yDiff)
                        + x01.blue.toDouble() * xDiff * (1 - yDiff)
                        + x10.blue.toDouble() * (1 - xDiff) * yDiff
                        + x11.blue.toDouble() * xDiff * yDiff)).toInt()

                fun saturate(value: Int) = Math.max(0, Math.min(value, 255))
                newImage.setRGB(i, j, Color(
                        saturate(redValue),
                        saturate(greenValue),
                        saturate(blueValue)).rgb)
            }
        }
        return newImage
    }

}