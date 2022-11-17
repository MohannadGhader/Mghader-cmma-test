package com.path.dao.omni.driver.push;

import java.util.List;

import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.FactureVO;
import com.path.dbmaps.vo.MachineDriverLocationVO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.Sended_notificationVO;
import com.path.lib.common.exception.DAOException;

public interface OmniDriverPushDAO {

	/**
	 * when the driver reach the destination and the reservation is done , insert in
	 * the db the current location of the machine
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
//	public void updateMachineLocation(DriverCO driverCO) throws DAOException;

	/**
	 * method that insert the combination of driver and device
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void insertDriverDeviceCombination(DriverCO driverCO) throws DAOException;

	/**
	 * method that update the combination of driver and device the driver cannot
	 * login from multiple devices
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updateDriverDeviceCombination(DriverCO driverCO) throws DAOException;

	/**
	 * method that update the status of driver
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updateDriverDeviceStatus(DriverCO driverCO) throws DAOException;

	/**
	 * method to set the time slot expected for the trip
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateTimeSlot(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the token of client
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateDeviceToken(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the token of driver
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updateDeviceDriverToken(DriverCO driverCO) throws DAOException;

	/**
	 * method that change the password of driver
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updatePasswordDriver(DriverCO driverCO) throws DAOException;

	/**
	 * method that update the password
	 * 
	 * @param driverVO
	 * @throws DAOException
	 */
	public void resetPasswordDriver(DriverVO driverVO) throws DAOException;

	/**
	 * method that update the last login driver
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updateLastLoginDriver(DriverCO driverCO) throws DAOException;

	/**
	 * method that update the driver's profile
	 * 
	 * @param driverVO
	 * @throws DAOException
	 */
	public void updateProfileDriver(DriverVO driverVO) throws DAOException;

	/**
	 * method that update the longitude and latitude of client when he reach his
	 * destination
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateLocationToClient(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the longitude and latitude of machine in reservation table
	 * in order to user it in calculate cost
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updateMachineDriverReservationLocations(DriverCO driverCO) throws DAOException;

	/**
	 * method that insert the facture into facture table
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertFacture(FactureVO factureVO) throws DAOException;

	/**
	 * update the location in machine_driver_location table
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updateMachineDriverLocation(DriverCO driverCO) throws DAOException;

	/**
	 * method that update the distances in reservation list
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateDistanceStartFinish(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the distance take wait
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateTakeWait(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that insert maintenance
	 * 
	 * @param machine_mntCO
	 * @throws DAOException
	 */
	public void insertMaintenance(Machine_mntCO machine_mntCO) throws DAOException;

	/**
	 * method that update the status of driver
	 * 
	 * @param driverVO
	 * @throws DAOException
	 */
	public void updateDriverStatus(DriverCO driverCO) throws DAOException;

	/**
	 * method that update the nb of client
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void updateNbOfClient(DriverCO driverCO) throws DAOException;

	/**
	 * method that update the cost of reservation based of driver recommendation
	 * 
	 * @param reservationSC
	 * @throws DAOException
	 */
	public void updateCostByDriver(ReservationSC reservationSC) throws DAOException;

	/**
	 * method that insert new location of driver
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void insertNewLocationDriver(DriverCO driverCO) throws DAOException;

	/**
	 * insert the sended notification into a table
	 * 
	 * @param sended_notificationVO
	 * @throws DAOException
	 */
	public void insertSendedNotification(Sended_notificationVO sended_notificationVO) throws DAOException;

	/**
	 * insert the date and time when change the availability of driver
	 * 
	 * @param driverCO
	 * @throws DAOException
	 */
	public void insertTrackingAvailabily(DriverCO driverCO) throws DAOException;

	/**
	 * update the kilometrage in the db
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateKilometrage(ReservationCO reservationCO) throws DAOException;

}
