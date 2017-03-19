package app.project.donate.ngolocator;

/**
 * Created by ArupPc on 18-03-2017.
 */

public class AlgorithmGIS {
    public static PointFloating derivePos(PointFloating point, double range, double bearing) {
        double earthRad = 6371000;//m
        double latA = Math.toRadians(point.x);
        double lonA = Math.toRadians(point.y);
        double angularDistance = range / earthRad;
        double trueCourse = Math.toRadians(bearing);
        double lat = Math.asin(
                Math.sin(latA) * Math.cos(angularDistance) +
                        Math.cos(latA) * Math.sin(angularDistance)
                                * Math.cos(trueCourse));

        double dlon = Math.atan2(
                Math.sin(trueCourse) * Math.sin(angularDistance)
                        * Math.cos(latA),
                Math.cos(angularDistance) - Math.sin(latA) * Math.sin(lat));

        double lon = ((lonA + dlon + Math.PI) % (Math.PI * 2)) - Math.PI;
        lat = Math.toDegrees(lat);
        lon = Math.toDegrees(lon);

        PointFloating newPoint = new PointFloating(lat, lon);

        return newPoint;

    }

    public static boolean pointIsInCircle(PointFloating pointForCheck, PointFloating center,
                                          double radius) {
        if (getDistanceBetweenTwoPoints(pointForCheck, center) <= radius) {
            return true;
        }
        return false;
    }

    public static double getDistanceBetweenTwoPoints(PointFloating p1, PointFloating p2) {
        double R = 6371000; // m
        double dLat = Math.toRadians(p2.x - p1.x);
        double dLon = Math.toRadians(p2.y - p1.y);
        double lat1 = Math.toRadians(p1.x);
        double lat2 = Math.toRadians(p2.x);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.sin(dLon / 2)
                * Math.sin(dLon / 2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }
}
