# latitude2gpx

A Clojure library designed to take KML files generated Google Location History (previously known as Google Latitude) and turns them into GPX tracklogs.

## Example

### Input

    <?xml version="1.0" encoding="UTF-8"?>
    <kml xmlns="http://www.opengis.net/kml/2.2" xmlns:gx="http://www.google.com/kml/ext/2.2" xmlns:kml="http://www.opengis.net/kml/2.2" xmlns:atom="http://www.w3.org/2005/Atom">
        <Document>
            <name>Location history from 07/17/2013 to 08/16/2013</name>
            <open>1</open>
            <description/>
            <StyleMap id="multiTrack">
                <Pair>
                    <key>normal</key>
                    <styleUrl>#multiTrack_n</styleUrl>
                </Pair>
                <Pair>
                    <key>highlight</key>
                    <styleUrl>#multiTrack_h</styleUrl>
                </Pair>
            </StyleMap>
            <Style id="multiTrack_n">
                <IconStyle>
                    <Icon>
                        <href>http://earth.google.com/images/kml-icons/track-directional/track-0.png</href>
                    </Icon>
                </IconStyle>
                <LineStyle>
                    <color>99ffac59</color>
                    <width>6</width>
                </LineStyle>
            </Style>
            <Style id="multiTrack_h">
                <IconStyle>
                    <scale>1.2</scale>
                    <Icon>
                        <href>http://earth.google.com/images/kml-icons/track-directional/track-0.png</href>
                    </Icon>
                </IconStyle>
                <LineStyle>
                    <color>99ffac59</color>
                    <width>8</width>
                </LineStyle>
            </Style>
            <Placemark>
                <name>Latitude User</name>
                <description>Location history for Latitude User from 07/17/2013 to 08/16/2013</description>
                <styleUrl>#multiTrack</styleUrl>
                <gx:Track>
                    <altitudeMode>clampToGround</altitudeMode>
                    <when>2013-07-16T16:01:54.272-07:00</when>
                    <gx:coord>-1.121163 52.6527182 0</gx:coord>
                    <when>2013-07-16T16:02:54.544-07:00</when>
                    <gx:coord>-1.122447 52.6536466 0</gx:coord>
                    <when>2013-07-16T16:03:55.214-07:00</when>
                    <gx:coord>-1.1211383 52.6588637 0</gx:coord>
                    <when>2013-07-16T16:05:14.345-07:00</when>
                    <gx:coord>-1.1097693 52.6654192 0</gx:coord>
                </gx:Track>
            </Placemark>
        </Document>
    </kml>

### Output

    <?xml version="1.0"?>
    <gpx creator="latitude2gpx" version="1.0" schemaLocation="http://www.topografix.com/GPX/1/0 http://www.topografix.com/GPX/1/0/gpx.xsd">
    <time>2013-08-19 10:45:00.319+0100</time>
    <trk>
        <trkseg>
            <trkpt lon="-1.121163" lat="52.6527182">
                <ele>0</ele>
                <time>2013-07-16T16:01:54.272-07:00</time>
            </trkpt>
            <trkpt lon="-1.122447" lat="52.6536466">
                <ele>0</ele>
                <time>2013-07-16T16:02:54.544-07:00</time>
            </trkpt>
            <trkpt lon="-1.1211383" lat="52.6588637">
                <ele>0</ele>
                <time>2013-07-16T16:03:55.214-07:00</time>
            </trkpt>
            <trkpt lon="-1.1097693" lat="52.6654192">
                <ele>0</ele>
                <time>2013-07-16T16:05:14.345-07:00</time>
            </trkpt>
        </trkseg>
    </trk>
    </gpx>

## Usage

### Leiningen

If you're using Leinigen then you can clone this repo and just type 
`lein run`

### Standalone
If you want to distribute this standalone then you can use `lein uberjar` to
create a jar file that users can call by typing `java -cp latitude2gpx-VERSION.jar`

### Options


     Switches               Default  Desc                              
     --------               -------  ----                              
     -h, --no-help, --help  false    Show help                         
     -i, --input                     REQUIRED: Input latitude KML FILE 
     -o, --output                    REQUIRED: Output GPX FILE    

## License

Copyright © 2013 FIXME

Distributed under the Eclipse Public License, the same as Clojure.
