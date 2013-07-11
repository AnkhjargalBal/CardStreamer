package my.exercise.cameraprocess;

import java.util.EventObject;

//import android.util.Log;

public class EventCoordsProcessed extends EventObject {

	private static final long serialVersionUID = 1L;

	private int[] mCoords;

	public EventCoordsProcessed(Object source) {
		super(source);
	}

	public static EventCoordsProcessed CreateCoordsProcessedEvent(
			Object source, int[] data) {
		EventCoordsProcessed event = new EventCoordsProcessed(source);

		synchronized (event) {
			event.mCoords = data;
			/*
			 * if(data[0] != 0) { Log.i("YEAH", "Pikachu"); }
			 */
		}
		return event;
	}

	public int[] getCoords() {
		synchronized (this) {
			return mCoords;
		}
	}
}
