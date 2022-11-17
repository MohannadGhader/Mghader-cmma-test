package com.path.dbmaps.vo;

public class FactureVO {

	private int facture_id;
	private int reservation_list_id;
	private double cost;
	private int discount;
	private double costAfterDiscount;
	private int numberOfClient;
	private double costByDriver;
	private int totalCostByDriver;
	private String costFormule;

	private double distanceStartFinishTesting;
	private String locationsArray;
	private double costTesting;
	private String costTestingFormule;
	/*
	 * 0: not paid 1: paid
	 */
	private int paid;

	public int getPaid() {
		return paid;
	}

	public void setPaid(int paid) {
		this.paid = paid;
	}

	public int getFacture_id() {
		return facture_id;
	}

	public void setFacture_id(int facture_id) {
		this.facture_id = facture_id;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getCostAfterDiscount() {
		return costAfterDiscount;
	}

	public void setCostAfterDiscount(double costAfterDiscount) {
		this.costAfterDiscount = costAfterDiscount;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getReservation_list_id() {
		return reservation_list_id;
	}

	public void setReservation_list_id(int reservation_list_id) {
		this.reservation_list_id = reservation_list_id;
	}

	public int getNumberOfClient() {
		return numberOfClient;
	}

	public void setNumberOfClient(int numberOfClient) {
		this.numberOfClient = numberOfClient;
	}

	public double getCostByDriver() {
		return costByDriver;
	}

	public void setCostByDriver(double costByDriver) {
		this.costByDriver = costByDriver;
	}

	public int getTotalCostByDriver() {
		return totalCostByDriver;
	}

	public void setTotalCostByDriver(int totalCostByDriver) {
		this.totalCostByDriver = totalCostByDriver;
	}

	public FactureVO(int reservation_list_id, double cost, int numberOfClient, double costByDriver,
			int totalCostByDriver, double costTesting, String costFormule, String costTestingFormule) {
		super();
		this.reservation_list_id = reservation_list_id;
		this.cost = cost;
		this.numberOfClient = numberOfClient;
		this.costByDriver = costByDriver;
		this.totalCostByDriver = totalCostByDriver;
		this.costTesting = costTesting;
		this.costFormule = costFormule;
		this.costTestingFormule = costTestingFormule;

	}

	public FactureVO(int reservation_list_id, double cost, int numberOfClient, double costByDriver,
			int totalCostByDriver, String costFormule) {
		super();
		this.reservation_list_id = reservation_list_id;
		this.cost = cost;
		this.numberOfClient = numberOfClient;
		this.costByDriver = costByDriver;
		this.totalCostByDriver = totalCostByDriver;
		this.costFormule = costFormule;

	}

	public String getCostFormule() {
		return costFormule;
	}

	public void setCostFormule(String costFormule) {
		this.costFormule = costFormule;
	}

	public double getDistanceStartFinishTesting() {
		return distanceStartFinishTesting;
	}

	public void setDistanceStartFinishTesting(double distanceStartFinishTesting) {
		this.distanceStartFinishTesting = distanceStartFinishTesting;
	}

	public String getLocationsArray() {
		return locationsArray;
	}

	public void setLocationsArray(String locationsArray) {
		this.locationsArray = locationsArray;
	}

	public double getCostTesting() {
		return costTesting;
	}

	public void setCostTesting(double costTesting) {
		this.costTesting = costTesting;
	}

	public String getCostTestingFormule() {
		return costTestingFormule;
	}

	public void setCostTestingFormule(String costTestingFormule) {
		this.costTestingFormule = costTestingFormule;
	}

}
