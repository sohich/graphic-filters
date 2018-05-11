package org.csc.nsk.kotlin2018.sohich.coursework.filters

import java.awt.image.BufferedImage


class FilterAggregator {
    private var filters = arrayOf<Filter>()

    fun addFIlter(filter: Filter) {
        filters += filter
    }

    fun apply(source: BufferedImage): BufferedImage {
        var image = source
        for (filter in filters) {
            image = filter.apply(image)
        }
        return image
    }
}