package org.csc.nsk.kotlin2018.sohich.coursework

import org.csc.nsk.kotlin2018.sohich.coursework.filters.FilterAggregator


data class Config (val inputFileName: String,
                   val outputFileName: String,
                   val aggregator: FilterAggregator)