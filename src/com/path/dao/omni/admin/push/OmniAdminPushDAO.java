package com.path.dao.omni.admin.push;

import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.CompaniesCO;
import com.path.dbmaps.vo.CompaniesSC;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.MachineCO;
import com.path.dbmaps.vo.MachineDriverSC;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.lib.common.exception.DAOException;

public interface OmniAdminPushDAO {

	/**
	 * method to insert a new driver
	 * 
	 * @param driverVO
	 * @throws DAOException
	 */
	public void insertDriver(DriverVO driverVO) throws DAOException;

	/**
	 * method to insert a new machine
	 * 
	 * @param machineVO
	 * @throws DAOException
	 */
	public void insertMachine(MachineVO machineVO) throws DAOException;

	/**
	 * method that insert an admin
	 * 
	 * @param adminVO
	 * @throws DAOException
	 */
	public void insertAdmin(AdminVO adminVO) throws DAOException;

	/**
	 * method that insert the device of admin
	 * 
	 * @param adminCO
	 * @throws DAOException
	 */
	public void insertDeviceAdmin(AdminCO adminCO) throws DAOException;

	/**
	 * method that insert the combination admin and device
	 * 
	 * @param adminCO
	 * @throws DAOException
	 */
	public void insertAdminDeviceCombination(AdminCO adminCO) throws DAOException;

	/**
	 * method that update the combination device and admin
	 * 
	 * @param adminCO
	 * @throws DAOException
	 */
	public void updateAdminDeviceCombination(AdminCO adminCO) throws DAOException;

	/**
	 * method that update the lastlogin admin
	 * 
	 * @param adminCO
	 * @throws DAOException
	 */
	public void updateLastLoginAdmin(AdminCO adminCO) throws DAOException;

	/**
	 * method to update driver infos
	 * 
	 * @param driverVO
	 * @throws DAOException
	 */
	public void updateDriverInfos(Machine_driverCO machine_driverCO) throws DAOException;

	/**
	 * method to update machine infos
	 * 
	 * @param machineVO
	 * @throws DAOException
	 */
	public void updateMachineInfos(MachineCO machineCO) throws DAOException;

	/**
	 * method to insert into machine maintenance table
	 * 
	 * @param machineCO
	 * @throws DAOException
	 */
	public void insertMachineMnt(MachineCO machineCO) throws DAOException;

	/**
	 * method that insert in machine_driver table
	 * 
	 * @param machine_driverCO
	 * @throws DAOException
	 */
	public void insertMachineDriver(Machine_driverCO machine_driverCO) throws DAOException;

	/**
	 * method that update machine status
	 * 
	 * @param machineCO
	 * @throws DAOException
	 */
	public void updateMachineStatus(MachineCO machineCO) throws DAOException;

	/**
	 * method that update the property in db
	 * 
	 * @param propertyVO
	 * @throws DAOException
	 */
	public void updateProperty(PropertiesVO propertyVO) throws DAOException;

	/**
	 * method that update the status of admin device
	 * 
	 * @param adminCO
	 * @throws DAOException
	 */
	public void updateAdminStatus(AdminCO adminCO) throws DAOException;

	/**
	 * method that add company
	 * 
	 * @param companiesCO
	 * @throws DAOException
	 */
	public void insertCompany(CompaniesCO companiesCO) throws DAOException;

	/**
	 * method that add mobile number and company id as foreign key
	 * 
	 * @param companiesSC
	 * @throws DAOException
	 */
	public void insertMobileNumberCompany(CompaniesSC companiesSC) throws DAOException;

	/**
	 * method that update the company id for the mobile number
	 * 
	 * @param companiesSC
	 * @throws DAOException
	 */
	public void updateMobileNumberCompany(CompaniesSC companiesSC) throws DAOException;

	/**
	 * method that update the driver availability
	 * 
	 * @param driverVO
	 * @throws DAOException
	 */
	public void updateDriverAvailabilty(DriverCO driverCO) throws DAOException;

	/**
	 * method that insert the working hours of a driver
	 * 
	 * @param machineDriverSC
	 * @throws DAOException
	 */
	public void insertWorkingHours(MachineDriverSC machineDriverSC) throws DAOException;

	/**
	 * method that update the machine driver id
	 * 
	 * @param machineDriverSC
	 * @throws DAOException
	 */
	public void updateMachineDriver(MachineDriverSC machineDriverSC) throws DAOException;

	/**
	 * method that update the working hours of driver
	 * 
	 * @param machineDriverSC
	 * @throws DAOException
	 */
	public void updateDriverWorkingHours(MachineDriverSC machineDriverSC) throws DAOException;
	
	/**
	 * 
	 * @throws DAOException
	 */
	public void updateFactureReceipt(ReservationCO reservationCO)throws DAOException;

}
