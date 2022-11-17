/**
\ * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OmniCommonPullDAO.java
 *
 * 
 */
package com.path.dao.omni.common.pull;

import java.math.BigDecimal;
import java.util.List;

import com.path.dbmaps.vo.AdminCO;
//import com.path.dao.omni.client.pull.DAOExceptionl;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.DeviceAdminVO;
import com.path.dbmaps.vo.DeviceDriverVO;
import com.path.dbmaps.vo.DeviceSC;
import com.path.dbmaps.vo.DeviceVO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.EdKeysVO;
import com.path.dbmaps.vo.ErrorVO;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.NotificationVO;
import com.path.dbmaps.vo.OC_APP_CHNLVO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationDetailsCO;
import com.path.dbmaps.vo.ReservationDetailsVO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.dbmaps.vo.ReservationVO;
import com.path.dbmaps.vo.StoreVO;
import com.path.dbmaps.vo.TemporaryReservationVO;
import com.path.dbmaps.vo.User;
import com.path.dbmaps.vo.VersionVO;
import com.path.lib.common.exception.DAOException;
import com.path.vo.omni.common.OmniCommonCO;
import com.path.vo.omni.common.base.OmniBaseSC;
import com.path.vo.omni.common.omniuser.OmniCommonUserSC;
import com.path.vo.omni.oadm.omniuser.OmniUserSC;
import com.path.ws.omni.vo.common.ChannelLoginRequest;
import com.path.ws.omni.vo.common.Group_template;
import com.path.ws.omni.vo.common.ReturnMonthlyClientDataResponse;

public interface OmniCommonPullDAO {

	public int returnLastRecordId() throws DAOException;

	public String returnAppCustomerStatus(OmniBaseSC criteria);

	public String returnAppCustomerName(OmniBaseSC criteria);

	public OmniCommonCO returnMappingId(OmniCommonUserSC criteria) throws DAOException;

	public OmniCommonCO returnPackageId(OmniUserSC userSC) throws DAOException;

	public OmniCommonCO returnCommonOperMappingId(OmniCommonUserSC criteria) throws DAOException;

	/**
	 * @author Muhammad.Asif Method used to return appChnlId
	 * @throws DAOException
	 */
	public BigDecimal returnAppChnlId(OmniBaseSC omniBaseSC) throws DAOException;

	/**
	 * @author Muhammad.Asif Method used to return submit action data list
	 * @throws DAOException
	 */

	public OC_APP_CHNLVO returnAppChnlVO(ChannelLoginRequest omniBaseSC) throws DAOException;

	/**
	 * method that return the nearest available machine to the client location
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnNearestAvailableMachine(ReservationCO reservationCO) throws DAOException;

	/**
	 * return the nearest unavailable machine
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnNearestUnavailableMachineTypeTrip(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the nearest unavailable machine if the type of reservation
	 * is machine
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnNearestUnavailableMachineTypeMachine(ReservationCO reservationCO) throws DAOException;

	/**
	 * method return list of machines from nearest to the most far , reservation
	 * type is trip
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<Machine_driverCO> returnListOfMachinesFromNearestToMostFarTrip(ReservationCO reservationCO)
			throws DAOException;

	/**
	 * method return list of machines from nearest to the most far , reservation
	 * type is trip
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<Machine_driverCO> returnListOfMachinesFromNearestToMostFarMachine(ReservationCO reservationCO)
			throws DAOException;

	/**
	 * method that return the available machines from db
	 * 
	 * @param machineVO
	 * @return
	 * @throws DAOException
	 */
	public List<MachineVO> returnAvailableMachines(MachineVO machineVO) throws DAOException;

	/**
	 * method that return the occurrence type of the reservation
	 * 
	 * @param reservationVO
	 * @return
	 * @throws DAOException
	 */
	public ReservationVO returnReservationOccurrenceType(ReservationVO reservationVO) throws DAOException;

	/**
	 * method return the details of one time reservation
	 * 
	 * @param reservationVO
	 * @return
	 * @throws DAOException
	 */
	public ReservationListVO returnOneTimeReservationDetails(ReservationVO reservationVO) throws DAOException;

	/**
	 * method return details of reccurring reservations
	 * 
	 * @param reservationVO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationCO> returnRecurringReservationDetails(ReservationVO reservationVO) throws DAOException;

	/**
	 * method that return the available machine in recurring reservation at each
	 * date and time
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public TemporaryReservationVO returnMaxTemporaryReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return list of temporary reservations for a a specific
	 * reservation to use in validate recurring reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */

	public List<TemporaryReservationVO> returnListOfTmeporaryReservation(TemporaryReservationVO temporaryReservationVO)
			throws DAOException;

	/**
	 * method to check if the store exist or no
	 * 
	 * @param storeVO
	 * @return
	 * @throws DAOException
	 */
	public StoreVO returnStore(StoreVO storeVO) throws DAOException;

	/**
	 * method to calculate the distance of trip
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO returnDistance(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the model and the plate number of machine
	 * 
	 * @param request
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnMachineDriverDetails(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the number of rows of otp trials
	 * 
	 * @param deviceSC
	 * @return
	 * @throws DAOException
	 */
	public int returnNumberOfOTPTrials(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that return the details of reservation, used in update machine infos
	 * and update driver infos
	 * 
	 * @param reservationListVO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO returnReservationDetails(ReservationListVO reservationListVO) throws DAOException;

	/**
	 * method that return the device of client that create the reservation , and the
	 * client device status is login
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<DeviceVO> returnDeviceOfClient(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the token of driver
	 * 
	 * @param driverVO
	 * @return
	 * @throws DAOException
	 */
	public DeviceDriverVO returnDriverToken(Machine_driverCO machine_driverCO) throws DAOException;

	/**
	 * 
	 * Method return the device from database
	 * 
	 * @param ClientRequest
	 * @throws DAOException
	 */
	public DeviceVO returnDevice(User user) throws DAOException;

	/**
	 * method that return the machine driver id
	 * 
	 * @param driverVO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnMachineDriverByDriverUsername(DriverVO driverVO) throws DAOException;

	/**
	 * method that return the number of generated otp at this date for a specific
	 * device
	 * 
	 * @param deviceSC
	 * @return
	 * @throws DAOException
	 */
	public DeviceSC returnNumberOfGeneratedOTP(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that return the token and the secret key of the token from the db
	 * 
	 * @param deviceSC
	 * @return
	 * @throws DAOException
	 */
	public DeviceVO returnSecretKeyAndToken(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that check if the front end public key exist in the db
	 * 
	 * @param keysVO
	 * @return
	 * @throws DAOException
	 */
//	public EdKeysVO returnFrontEndPublicKey (EdKeysVO keysVO) throws DAOException;
	public int returnFrontEndPublicKey(EdKeysVO keysVO) throws DAOException;

	/**
	 * method that return the name of driver , used in notification
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public DriverVO returnDriverName(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the counter of otp
	 * 
	 * @param deviceSC
	 * @return
	 * @throws DAOException
	 */
	public DeviceSC returnInvalidOTP(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that return the version of server
	 * 
	 * @param versionVO
	 * @return
	 * @throws DAOException
	 */
	public VersionVO returnVersion(VersionVO versionVO) throws DAOException;

	/**
	 * method that return the errors and language from db
	 * 
	 * @param errorVO
	 * @return
	 * @throws DAOException
	 */
	public List<ErrorVO> returnLanguageError(ErrorVO errorVO) throws DAOException;

	/**
	 * method that return the notification from db
	 * 
	 * @param notificationVO
	 * @return
	 * @throws DAOException
	 */
	public List<NotificationVO> returnLanguageNotification(NotificationVO notificationVO) throws DAOException;

	/**
	 * method that return property from db
	 * 
	 * @param propertyVO
	 * @return
	 * @throws DAOException
	 */
	public PropertiesVO returnProperty(PropertiesVO propertyVO) throws DAOException;

	/**
	 * method that return list of properties from db
	 * 
	 * @param propertyVO
	 * @return
	 * @throws DAOException
	 */
	public List<PropertiesVO> returnListProperties(PropertiesVO propertyVO) throws DAOException;

	/**
	 * method that return the date and time of create reservation
	 * 
	 * @param reservationListVO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO returnReservationListDateCreateDetails(ReservationListVO reservationListVO)
			throws DAOException;

	/**
	 * return the token of the admin
	 * 
	 * @param adminVO
	 * @return
	 * @throws DAOException
	 */
	public List<DeviceAdminVO> returnAdminToken(AdminVO adminVO) throws DAOException;

	/**
	 * method that return the device token
	 * 
	 * @param deviceSC
	 * @return
	 * @throws DAOException
	 */
	public DeviceSC returnTokenDevice(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that return if the mobile number exist in the bd
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnMobileNumberIfExist(ClientCO clientCO) throws DAOException;

	/**
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnMobileNumberIfExist1(ClientCO clientCO) throws DAOException;

	/**
	 * method that return the reservations list of client
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationCO> retrunIfFirstTwoReservations(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the discount of client
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationCO> returnDismissedAndCanceledReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return tlubtuk reservation details
	 * 
	 * @param reservationDetailsVO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationDetailsVO> returnTlubtukReservationDetails(ReservationDetailsVO reservationDetailsVO)
			throws DAOException;

	/**
	 * method that return the list of client that invite the new mobile number
	 * 
	 * @param deviceSC
	 * @return
	 * @throws DAOException
	 */
	public List<ClientVO> returnClientThatInviteTheNewMobileNumber(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that return the machine driver id
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnMachineDriverId(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the combination of device uuid and mobile number
	 * 
	 * @param deviceSC
	 * @return
	 * @throws DAOException
	 */
	public DeviceSC returnCombinationMobileNumberDeviceUUID(DeviceSC deviceSC) throws DAOException;

	/**
	 * method that return the number of resrvation cancelled and missed within date
	 * of reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public int returnNumberOfCanceledAndMissed(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the number of pending reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOExceptionl
	 */
	public int returnNumberOfPendingReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the number of free reservation of the client
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public ClientVO returnNbOfFreeTrip(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return machine driver id randomly
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnMachineDriverRandomly(ReservationDetailsCO reservationDetailsCO) throws DAOException;

	/**
	 * method that return the driver of broker
	 * 
	 * @param reservationDetailsCO
	 * @return
	 * @throws DAOException
	 */
	public Machine_driverCO returnDriverOfBroker(ReservationDetailsCO reservationDetailsCO) throws DAOException;

	/**
	 * method that return the kilometrages of reservation
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO returnKilometrage(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the tlubtuk reservationn not confirmed
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationCO> returnNotConfirmedTlubtukReservations(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the reservation that have reservation_list_status pending
	 * and expired time
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationListVO> returnOldReservationsToday(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the missed reservations of yesterday
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationListVO> returnOldReservations(ReservationCO reservationCO) throws DAOException;

	/**
	 * list of all drivers
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<Machine_driverCO> returnAllDriver(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return reserved driver
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<Machine_driverCO> returnReservedDriver(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the availability driver in working hours table
	 * 
	 * @param driver_id
	 * @return
	 */
	public DriverCO returnDriverAvailability(DriverVO driverVO) throws DAOException;

	/**
	 * method that return the admin status
	 * 
	 * @param adminVO
	 * @return
	 * @throws DAOException
	 */
	public AdminCO returnAdminStatus(AdminVO adminVO) throws DAOException;

	/**
	 * 
	 * @param adminVO
	 * @return
	 * @throws DAOException
	 */
	public List<DeviceAdminVO> returnCustomerCareToken(AdminVO adminVO) throws DAOException;

	/**
	 * method that return the list of groups
	 * 
	 * @param clients_groupVO
	 * @return
	 * @throws DAOException
	 */
	public List<Clients_groupVO> returnListOfGroups(Clients_groupVO clients_groupVO) throws DAOException;

	/**
	 * method that return the reservation_list inserted in reservation_list table
	 * for recurring reservation by reservation_id
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationSC> returnRecurringReservation(ReservationCO reservationCO) throws DAOException;

	/**
	 * 
	 * @param reservationVO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationSC> returnReservationsList(ReservationVO reservationVO) throws DAOException;

	/**
	 * 
	 * @param groupName
	 * @return
	 * @throws DAOException
	 */
	public Clients_groupVO returnGroupifExist(Clients_groupVO clients_groupVO) throws DAOException;

	/**
	 * 
	 * @param reservationVO
	 * @return
	 * @throws DAOException
	 */
	public Clients_groupVO returnClientsGroup(ReservationCO reservationCO) throws DAOException;

	/**
	 * 
	 * @param clients_groupVO
	 * @return
	 * @throws DAOException
	 */
	public Group_template returnGroupTemplate(Clients_groupVO clients_groupVO) throws DAOException;

	/**
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public List<ClientCO> returnMonthlyClientData(ClientCO clientCO) throws DAOException;

	/**
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public List<ClientCO> returnClientsGroupList(ClientCO clientCO) throws DAOException;

	/**
	 * 
	 * @param clientCO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO retrunReservationData(ClientCO clientCO) throws DAOException;

}
