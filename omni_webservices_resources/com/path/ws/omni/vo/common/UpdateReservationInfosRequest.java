package com.path.ws.omni.vo.common;

public class UpdateReservationInfosRequest extends OmniCommonRequest {

	private int reservation_id;
	private int reservation_list_id;
	private int driver_id;
	private int cost;
	private int totalCostByDriver;
	private int nbOfClient;

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public int getDriver_id() {
		return driver_id;
	}

	public void setDriver_id(int driver_id) {
		this.driver_id = driver_id;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getTotalCostByDriver() {
		return totalCostByDriver;
	}

	public void setTotalCostByDriver(int totalCostByDriver) {
		this.totalCostByDriver = totalCostByDriver;
	}

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public int getNbOfClient() {
		return nbOfClient;
	}

	public void setNbOfClient(int nbOfClient) {
		this.nbOfClient = nbOfClient;
	}

}
