package com.path.dao.omni.admin.pull;

import java.util.List;

import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.Admin_deviceVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.CompaniesCO;
import com.path.dbmaps.vo.CompaniesVO;
import com.path.dbmaps.vo.DeviceAdminVO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.MachineDriverSC;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.PropertiesSC;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.lib.common.exception.DAOException;

public interface OmniAdminPullDAO {

	/**
	 * method return the driver id and and in order to check if the driver exist in
	 * db or no to use in addDriver service
	 * 
	 * @param driverVO
	 * @return
	 * @throws DAOException
	 */
	public DriverVO returnDriver(DriverVO driverVO) throws DAOException;

	/**
	 * method return the machine infos
	 * 
	 * @param machineVO
	 * @return
	 * @throws DAOException
	 */
	public MachineVO returnMachineInfo(MachineVO machineVO) throws DAOException;

	/**
	 * method return the machine id in order to check if the machine exist in db or
	 * no to use it in addMachine service
	 * 
	 * @param machineVO
	 * @return
	 * @throws DAOException
	 */
	public MachineVO returnMachineByPlateNumber(MachineVO machineVO) throws DAOException;

	/**
	 * method that return the admin
	 * 
	 * @param adminVO
	 * @return
	 * @throws DAOException
	 */
	public AdminVO returnAdmin(AdminVO adminVO) throws DAOException;

	/**
	 * check if the username exist
	 * 
	 * @param adminCO
	 * @return
	 * @throws DAOException
	 */
	public AdminVO returnAdminUsername(AdminCO adminCO) throws DAOException;

	/**
	 * method that return the deviceAdmin
	 * 
	 * @param adminCO
	 * @return
	 * @throws DAOException
	 */
	public DeviceAdminVO returnDeviceAdmin(AdminCO adminCO) throws DAOException;

	/**
	 * check if the device exist in driver_device table
	 * 
	 * @param adminCO
	 * @return
	 * @throws DAOException
	 */
	public Admin_deviceVO returnAdminUsernameComb(AdminCO adminCO) throws DAOException;

	/**
	 * method that return the combination of admin username and device
	 * 
	 * @param adminCO
	 * @return
	 * @throws DAOException
	 */
	public Admin_deviceVO returnCombAdminDevice(AdminCO adminCO) throws DAOException;

	/**
	 * method that return if the machine is reserved or no , used when we need to
	 * change the status of machine to unvailable in order to reserve another one
	 * and give the client the new one
	 * 
	 * @param machineDriverSC
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationListVO> returnIfMachineDriverIsReserved(MachineDriverSC machineDriverSC) throws DAOException;

	/**
	 * return the combination of admin id and the device uuid
	 * 
	 * @param adminCO
	 * @return
	 * @throws DAOException
	 */
	public AdminCO returnCombinationUsernameDeviceAdmin(AdminCO adminCO) throws DAOException;

	/**
	 * return the list of reservations with details by date
	 * 
	 * @param reservationSC
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationSC> returnListReservationsDiffFinishAdmin(ReservationSC reservationSC) throws DAOException;

	public List<ReservationSC> returnListReservationsFinishAdmin(ReservationSC reservationSC) throws DAOException;

	public List<Machine_mntCO> returnMaintenance(Machine_mntCO machine_mntCO) throws DAOException;

	/**
	 * method that return the list of companies
	 * 
	 * @param companiesCO
	 * @return
	 * @throws DAOException
	 */
	public List<CompaniesVO> returnCompaniesList(CompaniesCO companiesCO) throws DAOException;

	/**
	 * method that return the company by name
	 * 
	 * @param companiesCO
	 * @return
	 * @throws DAOException
	 */
	public CompaniesVO returnCompany(CompaniesCO companiesCO) throws DAOException;

	/**
	 * method that return list of reservations and fatures
	 * 
	 * @param reservationSC
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationSC> returnListOfFactures(ReservationSC reservationSC) throws DAOException;

	/**
	 * method that return the properties with categories
	 * 
	 * @param propertyVO
	 * @return
	 * @throws DAOException
	 */
	public List<PropertiesSC> returnListPropertiesWithCategories(PropertiesVO propertyVO) throws DAOException;

	/**
	 * method that return the list of machines
	 * 
	 * @param machineVO
	 * @return
	 * @throws DAOException
	 */
	public List<MachineVO> returnListOfMachines(MachineVO machineVO) throws DAOException;

}
