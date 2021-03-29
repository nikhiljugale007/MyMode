package com.example.pdat_old;

public class Mode_Model {
	String mode_name;
	int mode_image;

	public Mode_Model(String mode_name, int mode_image) {
		this.mode_name = mode_name;
		this.mode_image = mode_image;
	}

	public String getMode_name() {
		return mode_name;
	}

	public void setMode_name(String mode_name) {
		this.mode_name = mode_name;
	}

	public int getMode_image() {
		return mode_image;
	}

	public void setMode_image(int mode_image) {
		this.mode_image = mode_image;
	}
}
