package com.path.ws.omni.vo.common;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

public class CostResponse extends ResponseBaseObject {

	private double cost;
	private double totalCost;
	private double costOfEachReserv;
	private HashMap<Date, Double> listOfCost;
	private double costAfterDiscount;
	private int discount;
	private int penalty;
	private String costFormule;

	private double costTesting;
	private String costTestingFormule;

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getCostOfEachReserv() {
		return costOfEachReserv;
	}

	public void setCostOfEachReserv(double costOfEachReserv) {
		this.costOfEachReserv = costOfEachReserv;
	}

	public HashMap<Date, Double> getListOfCost() {
		return listOfCost;
	}

	public void setListOfCost(HashMap<Date, Double> listOfCost) {
		this.listOfCost = listOfCost;
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

	public int getPenalty() {
		return penalty;
	}

	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	public String getCostFormule() {
		return costFormule;
	}

	public void setCostFormule(String costFormule) {
		this.costFormule = costFormule;
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
