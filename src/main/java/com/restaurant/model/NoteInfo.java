package com.restaurant.model;

import lombok.Data;
import lombok.NonNull;

@Data
public class NoteInfo {

	@NonNull
	private String description;

	public NoteInfo() {
		this.description = "";
	}

	public NoteInfo(String description) {
		this.description = description;
	}
}
