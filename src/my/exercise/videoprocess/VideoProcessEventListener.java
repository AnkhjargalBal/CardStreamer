package my.exercise.videoprocess;

import java.util.EventListener;

public interface VideoProcessEventListener extends EventListener {

	public void onFrameProcessed(EventFrameProcessed evt);

}
