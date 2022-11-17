package com.path.dao.omni.client.push;

import org.apache.catalina.connector.ClientAbortException;

import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.InvitedContactVO;
import com.path.dbmaps.vo.List_destinationsVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.lib.common.exception.DAOException;

public interface OmniClientPushDAO {

	/**
	 * method to insert the username and password in client table
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void insertUsernamePass(ClientCO clientCO) throws DAOException;

	/**
	 * Method insert sim into database
	 * 
	 * @param ClientRequest
	 * @throws DAOException
	 */
	public void insertSim(ClientCO clientCO) throws DAOException;

	/**
	 * Method insert combination sim and device and client into database
	 * 
	 * @param ClientRequest
	 * @throws DAOException
	 */
	public void insertCombination(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the status of client 0:login , 1:logout
	 * 
	 * @param deviceVO
	 * @throws DAOException
	 */
	public void updateClientStatus(ClientCO clientCO) throws DAOException;

	/**
	 * method to insert list of reservations in reservation_list table with all
	 * details from temporary reservation table when the reservation is recurrent
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertListReservationList(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the name of client in client table
	 * 
	 * @param request
	 * @throws DAOException
	 */

	public void updateClient(ClientCO clientCO) throws DAOException;

	/**
	 * method that delete the reservation
	 * 
	 * @param request
	 * @throws DAOException
	 */
	public void deleteReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method to update the combination for saving the device
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateCombination(ClientCO clientCO) throws DAOException;

	/**
	 * method to update client infos
	 * 
	 * @param clientVO
	 * @throws DAOException
	 */
	public void updateClientInfos(ClientVO clientVO) throws DAOException;

	/**
	 * method to update the password
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updatePassword(ClientCO clientCO) throws DAOException;

	/**
	 * method to update the profile of the {@link ClientAbortException} in db
	 * 
	 * @param clientVO
	 * @throws DAOException
	 */
	public void updateProfile(ClientVO clientVO) throws DAOException;

	/**
	 * insert into the reservation list the new machine reserved
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertReservationListMachineInfos(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that add destination for client
	 * 
	 * @param list_destinationsVO
	 * @throws DAOException
	 */
	public void insertDestination(List_destinationsVO list_destinationsVO) throws DAOException;

	/**
	 * method that reset the password
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void resetPassword(ClientVO clientVO) throws DAOException;

	/**
	 * method that delete the combination of device and username
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void deleteCombinationDeviceUsername(ClientCO clientCO) throws DAOException;

	/**
	 * method that insert in the db the last login date in client table
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateLastLoginClient(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the last login date for the combination username and
	 * device
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateLastLoginClientDevice(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the token of client
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateDeviceToken(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the status of the device (available or blocked)
	 * 
	 * @param deviceSC
	 * @throws DAOException
	 */
	public void updateDeviceStatus(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that insert in cancel_reservation table
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertCancelReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the combination username mobile number and device UUID
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateCombinationUsernameMobileNumberDevice(ClientCO clientCO) throws DAOException;

	/**
	 * method that insert in reservation_list table the recurring reservation in
	 * each day
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void insertRecurringReservationEachDay(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the name of destination
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateDestinationName(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that update the resrevation status
	 * 
	 * @param reservationVO
	 * @throws DAOException
	 */
	/*
	 * public void updateReservationStatus(ReservationVO reservationVO) throws
	 * DAOException;
	 */
	/**
	 * method that insert new account (username and password)
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void insertUsernameMobileNumber(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the name of mobile number if exist
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateUsernameMobileNumber(ClientCO clientCO) throws DAOException;

	/**
	 * method that insert into invited contact
	 * 
	 * @param invitedContactVO
	 * @throws DAOException
	 */
	public void insertIntoInvitedContact(InvitedContactVO invitedContactVO) throws DAOException;

	/**
	 * method that update the number of reservation
	 * 
	 * @param reservationCO
	 * @throws DAOException
	 */
	public void updateNbOfReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that add the number of free trip
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void addNbOfFreeTrip(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the reference name in client table
	 * 
	 * @param clientCO
	 * @throws DAOException
	 */
	public void updateReferenceName(ClientCO clientCO) throws DAOException;

	/**
	 * method that update the client inactive
	 * 
	 * @param clientVO
	 * @throws DAOException
	 */
	public void updateInactiveClient(ClientVO clientVO) throws DAOException;

	/**
	 * method that update the reservation list status to cancelled, add the broker
	 * id and the service id
	 * 
	 * @param reservationSC
	 */
	public void updateReservationListStatusToCanceledAndBrokerReservation(ReservationSC reservationSC)
			throws DAOException;

	public void addUser(ClientVO clientVO) throws DAOException;

}
