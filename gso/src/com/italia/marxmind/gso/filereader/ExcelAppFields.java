package com.italia.marxmind.gso.filereader;

import com.italia.marxmind.gso.controller.Department;
/**
 * 
 * @author Mark Italia
 * @version 1.0
 * @since 04/09/2020
 *
 */
public class ExcelAppFields {
	
	private int count;
	private String id;
	private String type;
	private String description;
	private double quantity;
	private String unit;
	private double unitCost;
	private double totalCost;
	private double firstQtrQty;
	private double firstQtrAmnt;
	private double secondQtrQty;
	private double secondQtrAmnt;
	private double thirdQtrQty;
	private double thirdQtrAmnt;
	private double fourthQtrQty;
	private double fourthQtrAmnt;
	
	private double totalUnitCost;
	private double totalQty;
	private double grandTotalUnitCost;
	
	private double firstQtrTotalQty;
	private double firstQtrTotalAmnt;
	private double secondQtrTotalQty;
	private double secondQtrTotalAmnt;
	private double thirdQtrTotalQty;
	private double thirdQtrTotalAmnt;
	private double fourthQtrTotalQty;
	private double fourthQtrTotalAmnt;
	
	private Department department;
	private String uploadedDate;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public double getUnitCost() {
		return unitCost;
	}
	public void setUnitCost(double unitCost) {
		this.unitCost = unitCost;
	}
	public double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	public double getFirstQtrQty() {
		return firstQtrQty;
	}
	public void setFirstQtrQty(double firstQtrQty) {
		this.firstQtrQty = firstQtrQty;
	}
	public double getFirstQtrAmnt() {
		return firstQtrAmnt;
	}
	public void setFirstQtrAmnt(double firstQtrAmnt) {
		this.firstQtrAmnt = firstQtrAmnt;
	}
	public double getSecondQtrQty() {
		return secondQtrQty;
	}
	public void setSecondQtrQty(double secondQtrQty) {
		this.secondQtrQty = secondQtrQty;
	}
	public double getSecondQtrAmnt() {
		return secondQtrAmnt;
	}
	public void setSecondQtrAmnt(double secondQtrAmnt) {
		this.secondQtrAmnt = secondQtrAmnt;
	}
	public double getThirdQtrQty() {
		return thirdQtrQty;
	}
	public void setThirdQtrQty(double thirdQtrQty) {
		this.thirdQtrQty = thirdQtrQty;
	}
	public double getThirdQtrAmnt() {
		return thirdQtrAmnt;
	}
	public void setThirdQtrAmnt(double thirdQtrAmnt) {
		this.thirdQtrAmnt = thirdQtrAmnt;
	}
	public double getFourthQtrQty() {
		return fourthQtrQty;
	}
	public void setFourthQtrQty(double fourthQtrQty) {
		this.fourthQtrQty = fourthQtrQty;
	}
	public double getFourthQtrAmnt() {
		return fourthQtrAmnt;
	}
	public void setFourthQtrAmnt(double fourthQtrAmnt) {
		this.fourthQtrAmnt = fourthQtrAmnt;
	}
	public double getTotalUnitCost() {
		return totalUnitCost;
	}
	public void setTotalUnitCost(double totalUnitCost) {
		this.totalUnitCost = totalUnitCost;
	}
	public double getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(double totalQty) {
		this.totalQty = totalQty;
	}
	public double getGrandTotalUnitCost() {
		return grandTotalUnitCost;
	}
	public void setGrandTotalUnitCost(double grandTotalUnitCost) {
		this.grandTotalUnitCost = grandTotalUnitCost;
	}
	public double getFirstQtrTotalQty() {
		return firstQtrTotalQty;
	}
	public void setFirstQtrTotalQty(double firstQtrTotalQty) {
		this.firstQtrTotalQty = firstQtrTotalQty;
	}
	public double getFirstQtrTotalAmnt() {
		return firstQtrTotalAmnt;
	}
	public void setFirstQtrTotalAmnt(double firstQtrTotalAmnt) {
		this.firstQtrTotalAmnt = firstQtrTotalAmnt;
	}
	public double getSecondQtrTotalQty() {
		return secondQtrTotalQty;
	}
	public void setSecondQtrTotalQty(double secondQtrTotalQty) {
		this.secondQtrTotalQty = secondQtrTotalQty;
	}
	public double getSecondQtrTotalAmnt() {
		return secondQtrTotalAmnt;
	}
	public void setSecondQtrTotalAmnt(double secondQtrTotalAmnt) {
		this.secondQtrTotalAmnt = secondQtrTotalAmnt;
	}
	public double getThirdQtrTotalQty() {
		return thirdQtrTotalQty;
	}
	public void setThirdQtrTotalQty(double thirdQtrTotalQty) {
		this.thirdQtrTotalQty = thirdQtrTotalQty;
	}
	public double getThirdQtrTotalAmnt() {
		return thirdQtrTotalAmnt;
	}
	public void setThirdQtrTotalAmnt(double thirdQtrTotalAmnt) {
		this.thirdQtrTotalAmnt = thirdQtrTotalAmnt;
	}
	public double getFourthQtrTotalQty() {
		return fourthQtrTotalQty;
	}
	public void setFourthQtrTotalQty(double fourthQtrTotalQty) {
		this.fourthQtrTotalQty = fourthQtrTotalQty;
	}
	public double getFourthQtrTotalAmnt() {
		return fourthQtrTotalAmnt;
	}
	public void setFourthQtrTotalAmnt(double fourthQtrTotalAmnt) {
		this.fourthQtrTotalAmnt = fourthQtrTotalAmnt;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(String uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
		
}
