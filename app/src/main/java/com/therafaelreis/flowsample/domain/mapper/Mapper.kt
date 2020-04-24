package com.therafaelreis.flowsample.domain.mapper

abstract class Mapper<in T, out E>{
    abstract fun mapFrom(from: T): E
}