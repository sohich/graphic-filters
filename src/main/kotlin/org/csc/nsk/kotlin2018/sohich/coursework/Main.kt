package org.csc.nsk.kotlin2018.sohich.coursework


fun main(args: Array<String>) {
    if (args.isEmpty()) {
        print(Helper.usageString)
        return
    }
}

private object Helper {
    const val usageString = "Usage:\n" +
                            "filters <source image path> [-option [arg]] <target image path>\n\n" +
                            "Options supported:\n" +
                            "-negate\t\t\t\t\t: replace each pixel with its complementary color;\n" +
                            "-grayscale\t\t\t\t: convert image to grayscale;\n" +
                            "-rotate degrees{<}{>}\t: apply image rotation to the image,\n" +
                            "< rotates the image only if its width is less than the height, > does contrariwise;\n" +
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