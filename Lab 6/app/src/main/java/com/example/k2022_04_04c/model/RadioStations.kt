package com.example.k2022_03_08_rv.model

var stations: MutableList<RadioStation> =
    mutableListOf(RadioStation("name", "http://stream.whus.org:8000/whusfm"))


class RadioStations {

    init {
        stations.add(
            RadioStation(
                "WHUS",
                "http://stream.whus.org:8000/whusfm"
            )
        )
        stations.add(
            RadioStation(
                "Classic MPR",
                "https://cms.stream.publicradio.org/cms.mp3"
            )
        )
        stations.add(
            RadioStation(
                "Radio Caroline",
                "http://sc6.radiocaroline.net:8040/mp3"
            )
        )
    }

    fun getStations(): MutableList<RadioStation> {

        return stations
    }

    fun size(): Int {
        return stations.size
    }
}

