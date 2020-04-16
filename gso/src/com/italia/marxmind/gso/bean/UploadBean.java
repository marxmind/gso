package com.italia.marxmind.gso.bean;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.io.FilenameUtils;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;

import com.italia.marxmind.appUtils.Application;
import com.italia.marxmind.appUtils.DateUtils;
import com.italia.marxmind.appUtils.Numbers;
import com.italia.marxmind.gso.controller.Department;
import com.italia.marxmind.gso.filereader.ExcelAppFields;
import com.italia.marxmind.gso.filereader.ExcelReader;
import com.italia.marxmind.gso.global.GlobalVar;





/**
 * 
 * @author Mark Italia
 * @version 1.0
 * @since 04/09/2020
 *
 */
@Named
@ViewScoped
public class UploadBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 156344343434L;
	private int departmentId;
	private List departments;
	private List<ExcelAppFields> dataList;
	private List<ExcelAppFields> dataListOriginal;
	private String searchParameter;
	
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
	
	@PostConstruct
	public void init() {
		dataList = new ArrayList<ExcelAppFields>();
		dataListOriginal = new ArrayList<ExcelAppFields>();
		departments = new ArrayList<>();
		departments.add(new SelectItem(0, "Select..."));
		for(Department d : Department.retrieve("SELECT * FROM Department", new String[0])) {
			departments.add(new SelectItem(d.getDepid(), d.getDepartmentName()));
		}
	}
	
	public void search() {
		dataList = new ArrayList<ExcelAppFields>();
		int count = 1;
		resetValue();
		for(ExcelAppFields e : getDataListOriginal()) {
			if(e.getDescription().toLowerCase().startsWith(getSearchParameter().toLowerCase())) {
				e.setCount(count);
				dataList.add(e);
				calculateFooterAmount(e);
				count++;
			}
		}
		
		
		if((dataList==null || dataList.size()==0) && (getSearchParameter()==null || getSearchParameter().isBlank())) {
			dataList = getDataListOriginal();
			for(ExcelAppFields e : getDataListOriginal()) {
				calculateFooterAmount(e);
			}
		}
		
		convertDouble();
	}
	
	public void uploadFile(FileUploadEvent event){
		System.out.println("uploading file");
		 try {
			 InputStream stream = event.getFile().getInputStream();
			 String ext = FilenameUtils.getExtension(event.getFile().getFileName());
			 String file = event.getFile().getFileName();
			 
			 if(writeDocToFile(stream,ext)){
				 Application.addMessage(1, "Success", "Data has been successfully uploaded");
			 }else{
				 Application.addMessage(3, "Error", "Error uploading the data " + file);
			 }
			 
	     } catch (Exception e) {
	     
	     }
	}
	
	private boolean writeDocToFile(InputStream stream, String ext){
		System.out.println("writing file");
		try{
			String filename = "uploaded-" + DateUtils.getCurrentDateMMDDYYYYTIMEPlain() + "." + ext;	
			System.out.println("writing... writeDocToFile : " + filename);
			File fileDoc = new File(GlobalVar.UPLOADED_EXCEL_PATH_FOLDER +  filename);
			Path file = fileDoc.toPath();
			Files.copy(stream, file, StandardCopyOption.REPLACE_EXISTING);
			readingExcelFile(GlobalVar.UPLOADED_EXCEL_PATH_FOLDER +  filename, ext);
			return true;
		}catch(IOException e) {}
		return false;
	}
	private void readingExcelFile(String fileName, String ext) {
		System.out.println("readinf file content");
		File file = new File(fileName);
		dataList = new ArrayList<ExcelAppFields>();
		int sheetNo = 0;
		Map<Integer, Map<Integer, ExcelAppFields>> data = ExcelReader.loadFile(file, ext,sheetNo,10);
		int count = 1;
		resetValue();
		for(int row : data.get(sheetNo).keySet()) {
			ExcelAppFields fld = data.get(sheetNo).get(row);
			fld.setCount(count);
			fld.setId(count+"");
			dataList.add(fld);
			calculateFooterAmount(fld);
			count++;
		}
		dataListOriginal = dataList;
		convertDouble();
	}
	
	private void resetValue() {
		totalUnitCost = 0d;
		totalQty = 0d;
		grandTotalUnitCost = 0d;
		firstQtrTotalQty = 0d;
		firstQtrTotalAmnt = 0d;
		secondQtrTotalQty = 0d;
		secondQtrTotalAmnt = 0d;
		thirdQtrTotalQty = 0d;
		thirdQtrTotalAmnt = 0d;
		fourthQtrTotalQty = 0d;
		fourthQtrTotalAmnt = 0d;
	}
	
	private void convertDouble() {
		totalUnitCost = Numbers.formatDouble(getTotalUnitCost());
		totalQty = Numbers.formatDouble(getTotalQty());
		grandTotalUnitCost = Numbers.formatDouble(getGrandTotalUnitCost());
		firstQtrTotalQty = Numbers.formatDouble(getFirstQtrTotalQty());
		firstQtrTotalAmnt = Numbers.formatDouble(getFirstQtrTotalAmnt());
		secondQtrTotalQty = Numbers.formatDouble(getSecondQtrTotalQty());
		secondQtrTotalAmnt = Numbers.formatDouble(getSecondQtrTotalAmnt());
		thirdQtrTotalQty = Numbers.formatDouble(getThirdQtrTotalQty());
		thirdQtrTotalAmnt = Numbers.formatDouble(getThirdQtrTotalAmnt());
		fourthQtrTotalQty = Numbers.formatDouble(getFourthQtrTotalQty());
		fourthQtrTotalAmnt = Numbers.formatDouble(getFourthQtrTotalAmnt());
	}
	private void calculateFooterAmount(ExcelAppFields fld) {
		
		totalUnitCost += fld.getUnitCost();
		totalQty += fld.getQuantity();
		grandTotalUnitCost += fld.getTotalCost();
		
		firstQtrTotalQty += fld.getFirstQtrQty();
		firstQtrTotalAmnt += fld.getFirstQtrAmnt();
		secondQtrTotalQty += fld.getSecondQtrQty();
		secondQtrTotalAmnt += fld.getSecondQtrAmnt();
		thirdQtrTotalQty += fld.getThirdQtrQty();
		thirdQtrTotalAmnt += fld.getThirdQtrAmnt();
		fourthQtrTotalQty += fld.getFourthQtrQty();
		fourthQtrTotalAmnt += fld.getFourthQtrAmnt();
		
	}
	
	public void onRowEdit(RowEditEvent<ExcelAppFields> event) {
		
		ExcelAppFields e = (ExcelAppFields)event.getObject();
		
        Application.addMessage(1, "Sucess", "Successfully saved.");
        
        //if(getDataList().size()!=getDataListOriginal().size()) {
        	//updating data on original list
        	int index = Integer.valueOf(e.getId()) - 1;
        	getDataListOriginal().set(index, e);
        //}
        
    }
     
    public void onRowCancel(RowEditEvent<ExcelAppFields> event) {
        //FacesMessage msg = new FacesMessage("Edit Cancelled", event.getObject().getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
	
	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public List getDepartments() {
		return departments;
	}

	public void setDepartments(List departments) {
		this.departments = departments;
	}

	public List<ExcelAppFields> getDataList() {
		return dataList;
	}

	public void setDataList(List<ExcelAppFields> dataList) {
		this.dataList = dataList;
	}

	public List<ExcelAppFields> getDataListOriginal() {
		return dataListOriginal;
	}

	public void setDataListOriginal(List<ExcelAppFields> dataListOriginal) {
		this.dataListOriginal = dataListOriginal;
	}

	public String getSearchParameter() {
		return searchParameter;
	}

	public void setSearchParameter(String searchParameter) {
		this.searchParameter = searchParameter;
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

}
