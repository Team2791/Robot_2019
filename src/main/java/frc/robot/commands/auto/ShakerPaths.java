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
	public static class FROM_RIGHT_HP{
		public static final Path HP_TO_FAR_SIDE_ROCKET = new Path(new PathSegment(t -> 
			/* {"start":{"x":102,"y":304},"mid1":{"x":195,"y":246},"mid2":{"x":287,"y":270},"end":{"x":315,"y":286}} */
			(-174 + 492 * t + -270 * Math.pow(t, 2))/ (279 + -6 * t + -189 * Math.pow(t, 2)) 
			, 223));

		public static final Path FAR_SIDE_ROCKET_TO_SCORE = new Path(new PathSegment(t -> 
			/* {"start":{"x":315,"y":286},"mid1":{"x":289,"y":282},"mid2":{"x":287,"y":295},"end":{"x":266,"y":306}} */
			(-12 + 102 * t + -57 * Math.pow(t, 2))/ (-78 + 144 * t + -129 * Math.pow(t, 2)) 
			, 55));

		//-----------------------------------------------------
		public static final Path HP_TO_NEAR_CARGSHIP_BAY1 = new Path(new PathSegment(t -> 
			/* {"start":{"x":101,"y":309},"mid1":{"x":189,"y":265},"mid2":{"x":252,"y":252},"end":{"x":269,"y":277}} */
			(-132 + 186 * t + 21 * Math.pow(t, 2))/ (264 + -150 * t + -63 * Math.pow(t, 2)) 
			, 180));
		public static final Path NEAR_CARGOSHIP_BAY1_TO_SCORE = new Path(new PathSegment(t -> 
			/* {"start":{"x":269,"y":277},"mid1":{"x":241,"y":240},"mid2":{"x":273,"y":236},"end":{"x":269,"y":214}} */
			(-111 + 198 * t + -153 * Math.pow(t, 2))/ (-84 + 360 * t + -288 * Math.pow(t, 2)) 
			, 69));
	}

	public static class FROM_RIGHT {
		
	}

	public static Path straightPath(double length) {
		return new Path(new PathSegment(t -> 0.0, length));
	}
}