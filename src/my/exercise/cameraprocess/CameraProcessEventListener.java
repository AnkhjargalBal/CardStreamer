package my.exercise.cameraprocess;

import java.util.EventListener;

import org.datacontract.schemas.BusinessObjects.User;

public interface CameraProcessEventListener extends EventListener {

	public void onCoordsProcessed(EventCoordsProcessed evt);
	
	public void onOCRProcessed(User user);
}
