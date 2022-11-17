
package com.path.bo.omni.driver;

import java.util.List;

import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.Driver_deviceCO;
import com.path.dbmaps.vo.MachineDriverVO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.ws.omni.vo.common.AddMaintenanceResponse;
import com.path.ws.omni.vo.common.AuthenticateDriverResponse;
import com.path.ws.omni.vo.common.ChangePasswordDriverResponse;
import com.path.ws.omni.vo.common.ChangeProfileDriverResponse;
import com.path.ws.omni.vo.common.CheckRecurringReservationResponse;
import com.path.ws.omni.vo.common.CheckingFutureDriverResponse;
import com.path.ws.omni.vo.common.ForgetPasswordResponse;
import com.path.ws.omni.vo.common.LoginDriverResponse;
import com.path.ws.omni.vo.common.LogoutDriverResponse;
import com.path.ws.omni.vo.common.ResetPasswordResponse;
import com.path.ws.omni.vo.common.ReturnDriverInfoResponse;
import com.path.ws.omni.vo.common.ReturnDriverLocationResponse;
import com.path.ws.omni.vo.common.ReturnDriverStatusResponse;
import com.path.ws.omni.vo.common.ReturnListReservationsDriverResponse;
import com.path.ws.omni.vo.common.ReturnRecurringClientsResponse;
import com.path.ws.omni.vo.common.SaveTokenResponse;
import com.path.ws.omni.vo.common.SwitchingDriverAvailabilityRequest;
import com.path.ws.omni.vo.common.SwitchingDriverAvailabilityResponse;
import com.path.ws.omni.vo.common.UpdateReservationInfosResponse;
import com.path.ws.omni.vo.common.UpdateDriverLocationResponse;
import com.path.ws.omni.vo.common.UpdateDriverLocationsOfflineRequest;
import com.path.ws.omni.vo.common.UpdateDriverLocationsOfflineResponse;
import com.path.ws.omni.vo.common.UpdateReservationStatusResponse;
import com.path.ws.omni.vo.common.UpdateTimeSlotResponse;

public interface OmniDriverBO {

	/**
	 * method that authenticate driver
	 * 
	 * @param driverDeviceCO
	 * @return
	 * @throws Exception
	 */
	public AuthenticateDriverResponse authenticateDriver(Driver_deviceCO driver_deviceCO) throws Exception;

	/**
	 * method that allow the driver to login
	 * 
	 * @param driverCO
	 * @return
	 * @throws Exception
	 */
	public LoginDriverResponse loginDriver(DriverCO driverCO) throws Exception;

	/**
	 * method that allow the driver to logout
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public LogoutDriverResponse logoutDriver(DriverCO driverCO) throws Exception;

	/**
	 * method to get all infos fo driver
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public ReturnDriverInfoResponse returnDriverInfo(DriverVO driverVO) throws Exception;

	/**
	 * method that allow the driver to set the expected time for the trip
	 * 
	 * @param reservationCO
	 * @return
	 * @throws Exception
	 */
	public UpdateTimeSlotResponse updateTimeSlot(ReservationCO reservationCO) throws Exception;

	/**
	 * method that return the list of reservations of the driver
	 * 
	 * @param driverCO
	 * @return
	 * @throws Exception
	 */
	public ReturnListReservationsDriverResponse returnlistReservationsDriver(DriverCO driverCO) throws Exception;

	/**
	 * method that allow the driver to change his password
	 * 
	 * @param driverCO
	 * @return
	 * @throws Exception
	 */
	public ChangePasswordDriverResponse changePasswordDriver(DriverCO driverCO) throws Exception;

	/**
	 * method that return the masked mobile number for the requested username and
	 * generate otp for driver account
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public ForgetPasswordResponse forgetPasswordDriver(DriverCO driverCO) throws Exception;

	/**
	 * method that reset the password of driver
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public ResetPasswordResponse resetPasswordDriver(DriverVO driverVO) throws Exception;

	/**
	 * method that track the trip
	 * 
	 * @param driverRequest
	 * @throws Exception
	 */
	public UpdateReservationStatusResponse updateReservationStatus(DriverCO driverCO) throws Exception;

	/**
	 * method that change the profile of the driver
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public ChangeProfileDriverResponse changeProfileDriver(DriverVO driverVO) throws Exception;

	/**
	 * method that update the driver location
	 * 
	 * @param driverCO
	 * @return
	 * @throws Exception
	 */
	public UpdateDriverLocationResponse updateDriverLocation(DriverCO driverCO) throws Exception;

	/**
	 * method that update the location of driver
	 * 
	 * @param reservationListVO
	 * @return
	 * @throws Exception
	 */
	public ReturnDriverLocationResponse returnDriverLocation(ReservationListVO reservationListVO) throws Exception;

	/**
	 * method that add maintenance
	 * 
	 * @param machine_mntCO
	 * @return
	 * @throws Exception
	 */
	public AddMaintenanceResponse addMaintenance(Machine_mntCO machine_mntCO) throws Exception;

	/**
	 * method that allow the driver to switch his status (on/off)
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public SwitchingDriverAvailabilityResponse switchingDriverStatus(DriverCO driverCO) throws Exception;

	/**
	 * method that return the driver status
	 * 
	 * @param driverVO
	 * @return
	 * @throws Exception
	 */
	public ReturnDriverStatusResponse returnDriverStatus(DriverVO driverVO) throws Exception;

	/**
	 * method that allow the driver to update the cost of reservation
	 * 
	 * @param reservationSC
	 * @return
	 * @throws Exception
	 */
	public UpdateReservationInfosResponse updateReservationInfos(ReservationSC reservationSC) throws Exception;

	/**
	 * thread that check the distance and status of the driver
	 * 
	 * @throws Exception
	 */
//	public void checkingDriverLocation() throws Exception;

	/**
	 * method that check the distance between driver and client in recurring
	 * reservation if the distance more than the parameter , check the distance
	 * between the driver and the another available drivers if distance less than
	 * the parameter stay the same driver , if more than , switch to another driver
	 * 
	 * @param reservationSC
	 * @return
	 * @throws Exception
	 */
	public CheckingFutureDriverResponse checkingFutureDriver(ReservationCO reservationCO) throws Exception;

	/**
	 * thread that check the locations of driver
	 */
	public void checkingDriverLocation() throws Exception;
	
	
	public List<ReturnRecurringClientsResponse> returnRecurringClients() throws Exception;
	
	public CheckRecurringReservationResponse checkRecurringReservation(ReservationCO reservationCO) throws Exception;
	

	/**
	 * method that manage the driver status , if he is turned off , check the future
	 * reservation then cancel the reservation , and create new reservation with
	 * other driver driver, then send notification to the admin that this driver is
	 * turned off , send notification to the driver that this reservation was
	 * cancelled and finally send notification to the client with new reservation
	 * and new driver
	 * 
	 * @param driverCO
	 * @throws Exception
	 */
//	public void manageDriverStatus(DriverCO driverCO) throws Exception;

}
