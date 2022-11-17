package com.path.bo.omni.admin;

import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.CompaniesCO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.MachineCO;
import com.path.dbmaps.vo.MachineDriverSC;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.ws.omni.vo.common.AddAdminResponse;
import com.path.ws.omni.vo.common.AddCompanyResponse;
import com.path.ws.omni.vo.common.AddDriverResponse;
import com.path.ws.omni.vo.common.AddMachineDriverResponse;
import com.path.ws.omni.vo.common.AddMachineResponse;
import com.path.ws.omni.vo.common.AddMonthlyClientResponse;
import com.path.ws.omni.vo.common.AuthenticateAdminResponse;
import com.path.ws.omni.vo.common.CreateDriverRequest;
import com.path.ws.omni.vo.common.CreateDriverResponse;
import com.path.ws.omni.vo.common.CreateGroupResponse;
import com.path.ws.omni.vo.common.CreateRecurringReservationAdminResponse;
import com.path.ws.omni.vo.common.KillDriverResponse;
import com.path.ws.omni.vo.common.LoginAdminResponse;
import com.path.ws.omni.vo.common.LogoutResponse;
import com.path.ws.omni.vo.common.ReturnAllDriversResponse;
import com.path.ws.omni.vo.common.ReturnCompaniesListResponse;
import com.path.ws.omni.vo.common.ReturnListOfFacturesResponse;
import com.path.ws.omni.vo.common.ReturnListOfGroupsResponse;
import com.path.ws.omni.vo.common.ReturnListOfMachinesResponse;
import com.path.ws.omni.vo.common.ReturnListPropertiesResponse;
import com.path.ws.omni.vo.common.ReturnListReservationsAdminResponse;
import com.path.ws.omni.vo.common.ReturnMachineInfoResponse;
import com.path.ws.omni.vo.common.ReturnMaintenanceResponse;
import com.path.ws.omni.vo.common.UpdateDriverInfoResponse;
import com.path.ws.omni.vo.common.UpdateMachineInfoResponse;
import com.path.ws.omni.vo.common.UpdatePropertyRequest;
import com.path.ws.omni.vo.common.UpdatePropertyResponse;

public interface OmniAdminBO {

	/**
	 * method to add a driver
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public AddDriverResponse addDriver(DriverVO driverVO) throws Exception;

	/**
	 * meethod to add new machine
	 * 
	 * @param machineVO
	 * @return
	 * @throws Exception
	 */
	public AddMachineResponse addMachine(MachineVO machineVO) throws Exception;

	/**
	 * method to get machine infos
	 * 
	 * @param machineVO
	 * @return
	 * @throws Exception
	 */
	public ReturnMachineInfoResponse returnMachineInfo(MachineVO machineVO) throws Exception;

	/**
	 * method that add admin
	 * 
	 * @param adminVO
	 * @return
	 * @throws Exception
	 */
	public AddAdminResponse addAdmin(AdminVO adminVO) throws Exception;

	/**
	 * method to login admin
	 * 
	 * @param adminCO
	 * @return
	 * @throws Exception
	 */
	public LoginAdminResponse loginAdmin(AdminCO adminCO) throws Exception;

	/**
	 * service that authenticate the combination of username and device in order to
	 * auto login
	 * 
	 * @param adminCO
	 * @return
	 * @throws Exception
	 */
	public AuthenticateAdminResponse authenticateAdmin(AdminCO adminCO) throws Exception;

	/**
	 * method to update the driver infos
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public UpdateDriverInfoResponse updateDriverInfo(Machine_driverCO machine_driverCO) throws Exception;

	/**
	 * method to update the machine infos
	 * 
	 * @param machineVO
	 * @return
	 * @throws Exception
	 */
	public UpdateMachineInfoResponse updateMachineInfo(MachineCO machineCO) throws Exception;

	/**
	 * method that insert a machine and driver
	 * 
	 * @param machine_driverCO
	 * @return
	 * @throws Exception
	 */
	public AddMachineDriverResponse addMachineDriver(Machine_driverCO machine_driverCO) throws Exception;

	/**
	 * method that allowed the admin to update the properties in the db
	 * 
	 * @param propertyCO
	 * @return
	 * @throws Exception
	 */
	public UpdatePropertyResponse updateProperty(UpdatePropertyRequest updatePropertyRequest) throws Exception;

	/**
	 * method that return the list of properties
	 * 
	 * @return
	 */
	public ReturnListPropertiesResponse returnListProperties() throws Exception;

	/**
	 * method to logout admin
	 * 
	 * @param adminCO
	 * @return
	 * @throws Exception
	 */
	public LogoutResponse logoutAdmin(AdminCO adminCO) throws Exception;

	/**
	 * method that return all resrevations in a specific date with details
	 * 
	 * @param reservationSC
	 * @return
	 * @throws Exception
	 */
	public ReturnListReservationsAdminResponse returnListReservationsAdmin(ReservationSC reservationSC)
			throws Exception;

	/**
	 * method that return maintenance list
	 * 
	 * @param machine_mntCO
	 * @return
	 * @throws Exception
	 */
	public ReturnMaintenanceResponse returnMaintenance(Machine_mntCO machine_mntCO) throws Exception;

	/**
	 * method that add Company to companies table
	 * 
	 * @param companiesCO
	 * @return
	 * @throws Exception
	 */
	public AddCompanyResponse addCompany(CompaniesCO companiesCO) throws Exception;

	/**
	 * method that return the companies list
	 * 
	 * @return
	 * @throws Exception
	 */
	public ReturnCompaniesListResponse returnCompaniesList() throws Exception;

	/**
	 * method that return the list of reservations and there factures
	 * 
	 * @param reservationSC
	 * @return
	 * @throws Exception
	 */
	public ReturnListOfFacturesResponse returnListOfFactures(ReservationSC reservationSC) throws Exception;

	/**
	 * method that return all driver for admin screen
	 * 
	 * @return
	 * @throws Exception
	 */
	public ReturnAllDriversResponse returnAllDrivers(ReservationCO reservationCO) throws Exception;

	/**
	 * method that kill driver
	 * 
	 * @param killDriverRequest
	 * @return
	 * @throws Exception
	 */
	public KillDriverResponse killDriver(DriverCO driverCO) throws Exception;

	/**
	 * method that return the list of machines
	 * 
	 * @param machineVO
	 * @return
	 * @throws Exception
	 */
	public ReturnListOfMachinesResponse returnListOfMachines(MachineVO machineVO) throws Exception;

	/**
	 * method that create driver with combination machine driver and working hours
	 * 
	 * @param machine_driverCO
	 * @return
	 * @throws Exception
	 */
	public CreateDriverResponse createDriver(MachineDriverSC machineDriverSC) throws Exception;
	
	
	/**
	 * My first service
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public CreateRecurringReservationAdminResponse CreateRecurringReservationAdmin(ReservationCO reservationCO) throws Exception;
	
	
	public UpdateFactureStatusResponse updateFactureStatus(ReservationCO reservationCO) throws Exception;

}
