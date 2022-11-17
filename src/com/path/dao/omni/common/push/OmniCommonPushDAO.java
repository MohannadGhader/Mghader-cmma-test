/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: Alaa Al Hek
 *
 * OmniCommonPushDAOImpl.java
 *
 * 
 */
package com.path.dao.omni.common.push;

import org.springframework.dao.DataAccessException;

import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.DeviceKeysSC;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.EdKeysVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.RateVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.dbmaps.vo.StoreVO;
import com.path.dbmaps.vo.User;
import com.path.dbmaps.vo.WorkingHoursVO;
import com.path.lib.common.exception.DAOException;

public interface OmniCommonPushDAO {

	/**
	 * method to insert the rating in the db
	 * 
	 * @param request
	 * @throws DAOException
	 */
	public void insertRating(RateVO rateVO) throws DAOException;

	/**
	 * method to insert into recurring template table the recuring reservation
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertRecurring_template(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that insert into temporary table the available machine to reserve it ,
	 * in order to avoid conflict reservation in processing time if the reservation
	 * type is trip
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertTempReservationTrip(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that insert into temporary table the available machine to reserve it ,
	 * in order to avoid conflict reservation in processing time if the reservation
	 * type is machine (the client determine the time_slot )
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertTempReservationMachine(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that reserve a machine temporarly , in case no available machine at
	 * this time insert into temporary table to reserve a machine before the time
	 * reservation is trip
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertTempReservationTripBefore(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that reserve a machine temporarly , in case no available machine at
	 * this time insert into temporary table to reserve a machine before the time
	 * reservation is machine
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertTempReservationMachineBefore(ReservationCO reservationCO) throws DAOException;

	/**
	 * method to insert into temporary reservation , in order to reserve a machine
	 * later than the time mentioned and the reservation is trip
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertTempReservationTripAfter(ReservationCO reservationCO) throws DAOException;

	/**
	 * method to insert into temporary reservation , in order to reserve a machine
	 * later than the time mentioned and the reservation is machine
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertTempReservationMachineAfter(ReservationCO reservationCO) throws DAOException;

	/**
	 * after confirm the reservation , delete a temprorary reservation from
	 * temporary_reservation table
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void deleteTemporaryReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the working hours of drivers
	 * 
	 * @param workingHoursVO
	 * @throws DAOException
	 */
	public void updateDriverWorkingHours(WorkingHoursVO workingHoursVO) throws DAOException;

	/**
	 * method to update the status of reservation list
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateReservationListStatus(ReservationCO reservationCO) throws DAOException;

	/**
	 * insert into the store table in db
	 * 
	 * @param storeVO
	 * @throws DAOException
	 */
	public void insertStore(StoreVO storeVO) throws DAOException;

	/**
	 * method that insert a new invalid otp
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void insertNewInvalidOTP(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that update the machine_driver_status , used in update driver status
	 * and update machine status , if one not available,so the machine_driver is not
	 * available
	 * 
	 * @param machine_driverCO
	 * @throws DAOException
	 */
//	public void updateMachineDriverStatus(Machine_driverCO machine_driverCO) throws DAOException;

	/**
	 * method that insert into reservation where type is trip
	 * 
	 * @param request
	 * @throws DAOException
	 */
	public void insertReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method to insert reservation in reservation_list table when the reservation
	 * is one time
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertReservationList(ReservationCO reservationCO) throws DAOException;

	/**
	 * Method insert device into database
	 * 
	 * @param ClientRequest
	 * @throws DAOException
	 */
	public void insertDevice(User user) throws DAOException;

	/**
	 * method that insert new row when generate new otp
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void insertNewOTP(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that insert the secret key of otp in the db
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void updateOTPSecretKey(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that insert the token and the secret key of the token in the db
	 * 
	 * @param deviceKeysSC
	 * @throws DAOException
	 */
	public void updateToken(DeviceKeysSC deviceKeysSC) throws DAOException;

	/**
	 * insert the keys in the db
	 * 
	 * @param keysVO
	 * @throws DAOException
	 */
	public void insertKeys(EdKeysVO keysVO) throws DAOException;

	/**
	 * update the data if the front end public key
	 * 
	 * @param keysVO
	 * @throws DAOException
	 */
	public void updateKeys(EdKeysVO keysVO) throws DAOException;

	/**
	 * update the counter of validate otp
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void updateCounterOTP(DeviceSC deviceSC) throws DAOException;

	/**
	 * update the counter of generated otp
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void updateCounterGeneratedOTP(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that update the language of the device
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void updateDeviceLanguage(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that update the otp status
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void updateOtpStatus(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that update the device token
	 * 
	 * @param user
	 * @throws DAOException
	 */
	public void updateDeviceToken(User user) throws DAOException;

	/**
	 * method that update the recurring reservation status to cancel it
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateRecurringReservationStatus(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the device manufacturer
	 * 
	 * @param user
	 * @throws DAOException
	 */
	public void updateDeviceManufacturer(User user) throws DAOException;

	/**
	 * method that update penalty status
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void UpdatePenaltyStatus(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the cost of trip reserved from list destination with
	 * location to
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateCostOfTripFromDestinationList(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that reduce the nb of free trip
	 * 
	 * @param clientVO
	 * @throws DAOException
	 */
	public void updateNbOfFreeTrip(ClientVO clientVO) throws DAOException;

	/**
	 * method that update the cancel type of reservation
	 * 
	 * @param reservationSC
	 * @throws DAOException
	 */
	public void updateCancelReservation(ReservationSC reservationSC) throws DAOException;

	/**
	 * method that update the location from and location to
	 * 
	 * @param reservationDetailsCO
	 * @throws DAOException
	 */
	public void updateLocations(ReservationDetailsCO reservationDetailsCO) throws DAOException;

	/**
	 * method that insert tlubtuk reservation
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertReservationDetails(ReservationDetailsCO reservationDetailsCO) throws DAOException;

	/**
	 * method that update the resrevation type of reservation
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateReservationType(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the group of client
	 * 
	 * @param clientVO
	 * @throws DAOException
	 */
	public void updateClientGroup(ClientVO clientVO) throws DAOException;

	/**
	 * method that add client
	 * 
	 * @param clientVO
	 * @throws DAOException
	 */
	public void addMonthlyClient(ClientVO clientVO) throws DAOException;

	/**
	 * method that insert new group
	 * @param clients_groupVO
	 * @throws DAOException
	 */
	public void insertGroup(Clients_groupVO clients_groupVO) throws DAOException;

	/**
	 * 
	 * Added By Mohannad
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void insertUsernameMobileNumber(ClientCO clientCO) throws DAOException ;
	
	/**
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertNewFacture (ReservationCO reservationCO)throws DAOException;
	
	

	/**
	 * method that update the resrevation status
	 * 
	 * @param reservationVO
	 * @throws DAOException
	 */
	public void updateReservationStatus(ReservationVO reservationVO) throws DAOException;
	
	/**
	 * 
	 * @param clients_groupVO
	 * @throws DAOException
	 */
	public void insertGroupTemplate(Clients_groupVO clients_groupVO) throws DAOException;
	
	/**
	 * 
	 * @param clientVO
	 * @throws DAOException
	 */
	public void updateClientsGroupID(ClientVO clientVO) throws DAOException;

}
