package org.csc.nsk.kotlin2018.sohich.coursework

import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


fun main(args: Array<String>) {
    if (args.size < 2) {
        print(Helper.usageString)
        return
    }

    try {
        val config = ArgumentParser().parseArguments(args)
        val input = ImageIO.createImageInputStream(File(config.inputFileName))

        input.use {
            val readers = ImageIO.getImageReaders(input)
            if (readers.hasNext()) {
                val reader = readers.next()
                try {
                    reader.input = input
                    var image = reader.read(0)
                    image = config.aggregator.apply(image)

                    val writer = ImageIO.getImageWriter(reader)
                    try {
                        val output = ImageIO.createImageOutputStream(File(config.outputFileName))
                        output.use {
                            writer.output = output
                            writer.write(image)
                        }
                    }
                    finally {
                        writer.dispose()
                    }
                } finally {
                    reader.dispose()
                }
            }
        }
    } catch (e: IllegalArgumentException) {
        println("Invalid input: ${e.message}")
    } catch (e: IOException) {
        println("IO Error: ${e.message}")
    }


}

private object Helper {
    const val usageString = "Usage:\n" +
                            "filters <source image path> [-option [arg]] <target image path>\n\n" +
                            "Options supported:\n" +
                            "-negate\t\t\t\t\t: replace each pixel with its complementary color;\n" +
                            "-grayscale\t\t\t\t: convert image to grayscale;\n" +
                            "-rotate degrees\t: apply image rotation to the image,\n" +
                            "-gamma value\t\t\t: apply gamma correction to the image;\n" +
                            "-median range\t\t\t: apply median filter with given range to the image;\n" +
                            "-emboss\t\t\t\t\t: emboss the image;\n" +
                            "-dither red green blue\t: apply a Floyd-Steinberg error diffusion dither to image,\n" +
                            "input parameters set count of shades in each channel's palette;\n" +
                            "-ordered-dither\t\t\t: dither the image using a pre-defined ordered dither;\n" +
                            "-sharpen\t\t\t\t: sharpen the image;\n" +
                            "-watercolor\t\t\t\t: watercolor the image;\n" +
                            "-zoom\t\t\t\t\t: double the image at both of width and height\n"
}