package lambdasinaction.optionals.refacter;

import java.util.Optional;

public class DisplayFeatures {

	private String size; // In inches
	private Optional<ScreenResolution> resolution;
	
	public DisplayFeatures(String size, Optional<ScreenResolution> resolution){
		this.size = size;
		this.resolution = resolution;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Optional<ScreenResolution> getResolution() {
		return resolution;
	}

	public void setResolution(Optional<ScreenResolution> resolution) {
		this.resolution = resolution;
	}
}
