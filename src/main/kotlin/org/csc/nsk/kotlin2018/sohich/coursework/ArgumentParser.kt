package org.csc.nsk.kotlin2018.sohich.coursework

import org.csc.nsk.kotlin2018.sohich.coursework.filters.*

import java.lang.NumberFormatException


class ArgumentParser {
    fun parseArguments(args: Array<String>): Config {
        val aggregator = FilterAggregator()
        val it = args.iterator()
        var currentOption = ""
        var currentArg : String
        val inputFileName = it.next() // must work because help should have been invoked if no args there
        var outputFileName = ""

        fun checkNext() {
            if (!it.hasNext())
                throw IllegalArgumentException("${currentOption.drop(1)}: too few arguments.")
        }

        fun parseInt(): Int {
            checkNext()
            currentArg = it.next()
            val value : Int
            try {
                value = currentArg.toInt()
            }  catch (e: NumberFormatException) {
                throw IllegalArgumentException("invalid argument for option '$currentOption': $currentArg")
            }

            return value
        }

        fun parseDouble(): Double {
            checkNext()
            currentArg = it.next()
            val value : Double
            try {
                value = currentArg.toDouble()
            }  catch (e: NumberFormatException) {
                    throw IllegalArgumentException("invalid argument for option '$currentOption': $currentArg")
                }

            return value
        }

        while (it.hasNext() && outputFileName == "") {
            currentOption = it.next()
            when (currentOption) {
                "-negate" -> aggregator.addFilter(NegateFilter())
                "-grayscale" -> aggregator.addFilter(GrayscaleFilter())
                "-rotate" -> {
                    val degrees = parseInt()
                    aggregator.addFilter(RotateFilter(degrees))
                }
                "-gamma" -> {
                    val gamma = parseDouble()
                    aggregator.addFilter(GammaCorrectionFilter(gamma))
                }
                "-median" -> {
                    val range = parseInt()
                    aggregator.addFilter(MedianFilter(range))
                }
                "-emboss" -> aggregator.addFilter(EmbossFilter())
                "-dither" -> {
                    val redCount = parseInt()
                    val greenCount = parseInt()
                    val blueCount  = parseInt()
                    aggregator.addFilter(FloydDitheringFilter(redCount, greenCount, blueCount))
                }
                "-ordered-dither" -> aggregator.addFilter(OrderedDitherFilter())
                "-sharpen" -> aggregator.addFilter(SharpeningFilter())
                "-watercolor" -> aggregator.addFilter(WatercoloringFilter())
                "-zoom" -> aggregator.addFilter(ZoomFilter())
                else -> outputFileName = currentOption
            }
        }

        if (outputFileName == "")
            throw IllegalArgumentException("No output file name specified")

        if (it.hasNext())
            throw IllegalArgumentException("Some options after output file name detected: ${it.next()}")

        return Config(inputFileName, outputFileName, aggregator)
    }
}