package com.path.dao.omni.client.pull;

import java.util.List;

import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientDeviceCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Client_deviceVO;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.InvitedContactCO;
import com.path.dbmaps.vo.List_destinationsVO;
import com.path.dbmaps.vo.LovVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.ParamLovSC;
import com.path.dbmaps.vo.ParamLovVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.dbmaps.vo.SimVO;
import com.path.lib.common.exception.DAOException;

public interface OmniClientPullDAO {

	/**
	 * @author AlaaAlHek method to return the sim from database
	 * 
	 */

	public SimVO returnSimVO(ClientCO clientCO) throws DAOException;

	/**
	 * method return the sim_id by sim serial
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public SimVO returnSimSerial(ClientCO clientCO) throws DAOException;

	/**
	 * Method return the combination of sim serial and device serial from database
	 * 
	 * @param clientSimDeviceCO
	 * @return Client_sim_device_VO
	 * @throws DAOException
	 */
	public ClientDeviceCO returnCombinationUsernameDevice(ClientCO clientCO) throws DAOException;

	/**
	 * method return if the username put by the client is alraedy used or not
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnUsername(ClientVO clientVO) throws DAOException;

	/**
	 * method that return the username and password from db
	 * 
	 * @param clientVO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnUsernamePassword(ClientCO clientCO) throws DAOException;

	/**
	 * method that check if the combination of mobile_number and device exist in db
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public Client_deviceVO returnCombination(ClientCO clientCO) throws DAOException;

	/**
	 * method return the client infos
	 * 
	 * @param clientVO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnClientInfo(ClientVO clientVO) throws DAOException;

	/**
	 * method return the username of client to use in change password
	 * 
	 * @param clientVO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnUsernamePasswordById(ClientCO clientCO) throws DAOException;

	/**
	 * method that return list of reservations
	 * 
	 * @return
	 * @throws DAOException
	 */
	/*
	 * public List<ReservationSC> returnReservationsList(ReservationVO
	 * reservationVO) throws DAOException;
	 */

	/**
	 * return the saved destinations of client
	 * 
	 * @param list_destinationsVO
	 * @return
	 * @throws DAOException
	 */
	public List<List_destinationsVO> returnListOfDestination(ReservationCO reservationCO) throws DAOException;

	/**
	 * method return list of available machines from nearest to the most far
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<Machine_driverCO> returnListAvailableMachinesNearestMostFar(ReservationCO reservationCO)
			throws DAOException;

	/**
	 * method that return list of unavailable machines form nearest to the most far
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<Machine_driverCO> returnListUnavailableMachines(ReservationCO reservationCO) throws DAOException;

	/**
	 * method return list of devices for a specific username
	 * 
	 * @param clientVO
	 * @return
	 * @throws DAOException
	 */
	public List<ClientCO> returnListDevices(ClientVO clientVO) throws DAOException;

	/**
	 * method that return the last 3 reservations of the client, in order to check
	 * if the reservations are done or not yet
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationCO> returnLastReservations(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the mobile number from a specific username
	 * 
	 * @param clientVO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnMobileNumber(ClientCO clientCO) throws DAOException;

	public int returnUsernameExists(ClientVO clientVO) throws DAOException;

	/**
	 * 
	 * /** method that return the list of last login and devices
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientCO returnLastLogin(ClientCO clientCO) throws DAOException;

//	/**
//	 * method that return the missed reservations of yesterday
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws DAOException
//	 */
//	public List<ReservationListVO> returnOldReservations(ReservationCO reservationCO) throws DAOException;

//	/**
//	 * method that return the reservation that have reservation_list_status pending
//	 * and expired time
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws DAOException
//	 */
//	public List<ReservationListVO> returnOldReservationsToday(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the number of cancel reservation in a specific date
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public int returnNumberOfCancelation(ReservationCO reservationCO) throws DAOException;

	/**
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<Machine_driverCO> returnListDriver(ReservationCO reservationCO) throws DAOException;

	public List<Machine_driverCO> returnListAvailableDriver(ReservationCO reservationCO) throws DAOException;

	public Machine_driverCO returnNearestAvailableMachine(ReservationCO reservationCO) throws DAOException;

	public Machine_driverCO returnDriver(ReservationCO reservationCO) throws DAOException;

//	/**
//	 * method that return reserved driver
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws DAOException
//	 */
//	public List<Machine_driverCO> returnReservedDriver(ReservationCO reservationCO) throws DAOException;

//	/**
//	 * list of all drivers
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws DAOException
//	 */
//	public List<Machine_driverCO> returnAllDriver(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that check if the combination username and mobile number exist
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnUsernameMobileNumber(ClientCO clientCO) throws DAOException;

	/**
	 * method that check the combination of clientid mobile number and device
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientDeviceCO returnClientIdMobileNumberDevice(ClientCO clientCO) throws DAOException;

	/**
	 * method that check the combination of username and device uuid
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientDeviceCO returnCombinationUsernameMobileNumberDevice(ClientCO clientCO) throws DAOException;

	/**
	 * method that check if the client id exist in client device table
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientDeviceCO returnClientFromClientDevice(ClientCO clientCO) throws DAOException;

	/**
	 * method that return available driver in all day for recurring reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnAvailableDriverAllDay(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return available driver in specific date and time
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnAvailableDriverEachDay(ReservationCO reservationCO) throws DAOException;

	/**
	 * will be moved to omniCommonPullDAO
	 * 
	 * method that return the reservation_list inserted in reservation_list table
	 * for recurring reservation by reservation_id
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 *//*
		 * public List<ReservationSC> returnRecurringReservation(ReservationCO
		 * reservationCO) throws DAOException;
		 */

//	/**
//	 * method that return the tlubtuk reservationn not confirmed
//	 * 
//	 * @param reservationCO
//	 * @return
//	 * @throws DAOException
//	 */
//	public List<ReservationCO> returnNotConfirmedTlubtukReservations(ReservationCO reservationCO) throws DAOException;

	/**
	 * return list of invited contact
	 * 
	 * @param invitedContactCO
	 * @return
	 * @throws DAOException
	 */
	public List<String> returnListOfInvitedContact(InvitedContactCO invitedContactCO) throws DAOException;

	/**
	 * method that return the number of reservation of a client
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO returnNbOfReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the list of tlubtuk reservations
	 * 
	 * @param reservationVO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationSC> returnListOfTlubtukReservation(ReservationVO reservationVO) throws DAOException;

	/**
	 * method that return list of brokers
	 * 
	 * @param clientVO
	 * @return
	 * @throws DAOException
	 */
	public List<ParamLovVO> returnListOfBrokers(ParamLovVO paramLovVO) throws DAOException;

	/**
	 * method that return the token of device based on mobile number
	 * 
	 * @param clientVO
	 * @return
	 * @throws DAOException
	 */
	public DeviceSC returnTokenOfMobileNumber(ClientCO clientCO) throws DAOException;

	/**
	 * method that check if the device exist in table client device
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public Client_deviceVO returnIfDeviceExistInClientDevice(ClientCO clientCO) throws DAOException;

	/**
	 * method that return the list of services
	 * 
	 * @param lovVO
	 * @return
	 * @throws DAOException
	 */
	public List<ParamLovSC> returnListOfServices(LovVO lovVO) throws DAOException;

	/**
	 * method that return the mobile number of client
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnMobileNumberById(ClientCO clientCO) throws DAOException;
}
