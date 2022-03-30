package com.example.k2022_03_08_rv.model

import kotlin.collections.MutableList

var stations: MutableList<RadioStation> = mutableListOf(RadioStation())


class RadioStations() {

    init {
        stations.add(RadioStation("Radio Caroline", "http://sc6.radiocaroline.net:8040/mp3"))
        stations.add(RadioStation("WHUS", "http://stream.whus.org:8000/whusfm"))
        stations.add(RadioStation("The Krush Wine Countr Netherlands", "https://19213.live.streamtheworld.com/KRSHFM.mp3"))
        stations.add(RadioStation("Haarlem 105 Germany", "http://stream.haarlem105.nl:8000/haarlem105low.mp3"))
        stations.add(RadioStation("rbb Radio Eins Germany", "http://www.radioeins.de/livemp3"))
        stations.add(RadioStation("Nativa FM Brazil", "https://servidor28.brlogic.com:8264/live"))
        stations.add(RadioStation("The Current", "https://current.stream.publicradio.org/kcmp.mp3"))
        stations.add(RadioStation("Classic MPR", "https://cms.stream.publicradio.org/cms.mp3"))
        stations.add(RadioStation("WNYC", "http://sgrewind.streamguys1.com/wnycfm/wnycfm.aac/playlist.m3u8"))
        stations.add(RadioStation("KMOJ", "https://kmojfm.streamguys1.com/live-mp3"))
    }

    public fun getStations() : MutableList<RadioStation> {

        return stations
    }

    public fun size() : Int {
        return stations.size
    }
}

