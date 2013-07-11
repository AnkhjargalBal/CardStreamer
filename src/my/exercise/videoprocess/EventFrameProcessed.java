package my.exercise.videoprocess;

import java.util.EventObject;

public class EventFrameProcessed extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// TODO speedup and switch conversion between int[] and byte[]
	private int[] mFrameData;
	private int mWidth;
	private int mHeight;

	public EventFrameProcessed(Object source) {
		super(source);
	}

	public static EventFrameProcessed CreateFrameProcessedEvent(Object source,
			int[] data, int width, int height) {
		EventFrameProcessed event = new EventFrameProcessed(source);
		synchronized (event) {
			event.mFrameData = data;
			event.mWidth = width;
			event.mHeight = height;
		}
		return event;
	}

	public int[] getData() {
		synchronized (this) {
			return mFrameData;
		}
	}

	public int getWidth() {
		synchronized (this) {
			return mWidth;
		}
	}

	public int getHeight() {
		synchronized (this) {
			return mHeight;
		}
	}

}
