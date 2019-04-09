package frc.robot.commands.auto;

public class ShakerPaths {
	public static class FROM_CENTER {
		public static final Path STATION_TO_CENTER_ROCKET = new Path(
            new PathSegment(t -> 
/* {"start":{"x":234,"y":96},"mid1":{"x":164,"y":96},"mid2":{"x":187,"y":45},"end":{"x":117,"y":45}} */
(0 + -306 * t + 306 * Math.pow(t, 2))/ (-210 + 558 * t + -558 * Math.pow(t, 2)) 
, 133));

		public static final Path CENTER_ROCKET_FINISH = new Path(
			new PathSegment(t -> 
			/* {"start":{"x":234,"y":96},"mid1":{"x":302,"y":96},"mid2":{"x":356,"y":84},"end":{"x":316,"y":55}} */
			(0 + -72 * t + -15 * Math.pow(t, 2))/
	(204 + -84 * t + -240 * Math.pow(t, 2)) 
			, 127));
	}
	//----------------------------------------------------------------------------------

	public static class FROM_RIGHT {
		
	}

	public static Path straightPath(double length) {
		return new Path(new PathSegment(t -> 0.0, length));
	}
}