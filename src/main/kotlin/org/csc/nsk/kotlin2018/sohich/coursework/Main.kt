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
                            "-negate: replace each pixel with its complementary color;\n" +
                            "-grayscale: convert image to grayscale;\n" +
                            "-rotate degrees{<}{>}: apply image rotation to the image,\n" +
                            "< rotates the image only if its width is less than the height, > does contrariwise;\n" +
                            "-gamma value: apply gamma correction to the image;\n" +
                            "-median range: apply median filter with given range to the image;\n" +
                            "-emboss: emboss the image;\n" +
                            "-dither red green blue: apply a Floyd-Steinberg error diffusion dither to image,\n" +
                            "input parameters set count of shades in each channel's palette;\n" +
                            "-ordered-dither: dither the image using a pre-defined ordered dither;\n" +
                            "-sharpen: sharpen the image;\n" +
                            "-watercolor: watercolor the image;" +
                            "-zoom: double the image at both of width and height\n"
}