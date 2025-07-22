package com.aurionpro.model;

public class DeliveryBoy {
	private int id;
	private String name;
	private boolean isAvailable;
	private String timeSlot;

	public DeliveryBoy(int id, String name, boolean isAvailable, String timeSlot) {
		this.id = id;
		this.name = name;
		this.isAvailable = isAvailable;
		this.timeSlot = timeSlot;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}
	
	public String getTimeSlot() {
		return timeSlot;
	}
	
    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

	@Override
	public String toString() {
		return "DeliveryBoy ID: " + id + ", Name: " + name + ", Available: " + (isAvailable ? "Yes" : "No") +
                ", Time Slot: " + timeSlot;
	}
}
