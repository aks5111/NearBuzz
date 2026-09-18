package com.Java.NearBuzz.geo.dto;

import java.util.List;

public record DirectionsResult(
        boolean available,
        String deepLinkUrl,
        String overviewPolyline,
        List<double[]> waypoints) {
}
