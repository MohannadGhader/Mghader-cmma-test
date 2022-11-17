/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OmniCommonPullDAOImpl.java
 *
 * 
 */
package com.path.dao.omni.common.pull.impl;

import java.math.BigDecimal;
import java.util.List;

import com.path.dao.omni.common.base.OmniBaseDAO;
import com.path.dao.omni.common.pull.OmniCommonPullDAO;
import com.path.dbmaps.vo.AdminCO;
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

//@Monitor
public class OmniCommonPullDAOImpl extends OmniBaseDAO implements OmniCommonPullDAO {

	public int returnLastRecordId() throws DAOException {
		return (int) getSqlMap().queryForObject("omniCommonPullMapper.returnLastRecordId", null);
	}

	public String returnAppCustomerStatus(OmniBaseSC criteria) {
		String result = "";
		try {
			result = (String) getSqlMap().queryForObject("omniCommonPullMapper.returnAppCustomerStatus", criteria);
		} catch (DAOException e) {
			// pathlog.error(e,"Message " + e.getMessage());
		}
		return result;
	}

	public String returnAppCustomerName(OmniBaseSC criteria) {
		String result = "";
		try {
			result = (String) getSqlMap().queryForObject("omniCommonPullMapper.returnAppCustomerName", criteria);
		} catch (DAOException e) {
			// pathlog.error(e,"Message " + e.getMessage());
		}
		return result;
	}

	@Override
	public OmniCommonCO returnMappingId(OmniCommonUserSC criteria) throws DAOException {
		return (OmniCommonCO) getSqlMap().queryForObject("omniCommonPullMapper.returnMappingId", criteria);
	}

	@Override
	public OmniCommonCO returnPackageId(OmniUserSC userSC) throws DAOException {
		return (OmniCommonCO) getSqlMap().queryForObject("omniCommonPullMapper.returnPackageId", userSC);
	}

	/**
	 * @author mohammadshour added for services having same oper with different
	 *         mapping id
	 */

	@Override
	public OmniCommonCO returnCommonOperMappingId(OmniCommonUserSC criteria) throws DAOException {
		return (OmniCommonCO) getSqlMap().queryForObject("omniCommonPullMapper.returnCommonOperMappingId", criteria);
	}

	@Override
	public BigDecimal returnAppChnlId(OmniBaseSC omniBaseSC) throws DAOException {
		return (BigDecimal) getSqlMap().queryForObject("omniCommonPullMapper.returnAppChnlId", omniBaseSC);
	}

	@Override
	public OC_APP_CHNLVO returnAppChnlVO(ChannelLoginRequest omniBaseSC) throws DAOException {
		return (OC_APP_CHNLVO) getSqlMap().queryForObject("omniCommonPullMapper.returnAppChnlVO", omniBaseSC);
	}

	@Override
	public Machine_driverCO returnMachineDriverDetails(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniCommonPullMapper.returnMachineDriverDetails",
				reservationCO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MachineVO> returnAvailableMachines(MachineVO machineVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnAvailableMachines", machineVO);
	}

	@Override
	public TemporaryReservationVO returnMaxTemporaryReservation(ReservationCO reservationCO) throws DAOException {
		return (TemporaryReservationVO) getSqlMap().queryForObject("omniCommonPullMapper.returnMaxTemporaryReservation",
				reservationCO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TemporaryReservationVO> returnListOfTmeporaryReservation(TemporaryReservationVO temporaryReservationVO)
			throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnListOfTmeporaryReservation",
				temporaryReservationVO);
	}

	@Override
	public ReservationVO returnReservationOccurrenceType(ReservationVO reservationVO) throws DAOException {
		return (ReservationVO) getSqlMap().queryForObject("omniCommonPullMapper.returnReservationOccurrenceType",
				reservationVO);
	}

	@Override
	public ReservationListVO returnOneTimeReservationDetails(ReservationVO reservationVO) throws DAOException {
		return (ReservationListVO) getSqlMap().queryForObject("omniCommonPullMapper.returnOneTimeReservationDetails",
				reservationVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationCO> returnRecurringReservationDetails(ReservationVO reservationVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnRecurringReservationDetails", reservationVO);
	}

	@Override
	public StoreVO returnStore(StoreVO storeVO) throws DAOException {
		return (StoreVO) getSqlMap().queryForObject("omniCommonPullMapper.returnStore", storeVO);
	}

	@Override
	public Machine_driverCO returnNearestAvailableMachine(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniCommonPullMapper.returnNearestAvailableMachine",
				reservationCO);
	}

	@Override
	public Machine_driverCO returnNearestUnavailableMachineTypeTrip(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap()
				.queryForObject("omniCommonPullMapper.returnNearestUnavailableMachineTypeTrip", reservationCO);
	}

	@Override
	public Machine_driverCO returnNearestUnavailableMachineTypeMachine(ReservationCO reservationCO)
			throws DAOException {
		return (Machine_driverCO) getSqlMap()
				.queryForObject("omniCommonPullMapper.returnNearestUnavailableMachineTypeMachine", reservationCO);
	}

	@Override
	public ReservationCO returnDistance(ReservationCO reservationCO) throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniCommonPullMapper.returnDistance", reservationCO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine_driverCO> returnListOfMachinesFromNearestToMostFarTrip(ReservationCO reservationCO)
			throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnListOfMachinesFromNearestToMostFarTrip",
				reservationCO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine_driverCO> returnListOfMachinesFromNearestToMostFarMachine(ReservationCO reservationCO)
			throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnListOfMachinesFromNearestToMostFarMachine",
				reservationCO);

	}

	@Override
	public int returnNumberOfOTPTrials(DeviceSC deviceSC) throws DAOException {
		return (int) getSqlMap().queryForObject("omniCommonPullMapper.returnNumberOfOTPTrials", deviceSC);

	}

	@Override
	public ReservationCO returnReservationDetails(ReservationListVO reservationListVO) throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniCommonPullMapper.returnReservationDetails",
				reservationListVO);
	}

	@Override
	public List<DeviceVO> returnDeviceOfClient(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnDeviceOfClient", reservationCO);
	}

	@Override
	public DeviceDriverVO returnDriverToken(Machine_driverCO machine_driverCO) throws DAOException {
		return (DeviceDriverVO) getSqlMap().queryForObject("omniCommonPullMapper.returnDriverToken", machine_driverCO);

	}

	@Override
	public DeviceVO returnDevice(User user) throws DAOException {
		return (DeviceVO) getSqlMap().queryForObject("omniCommonPullMapper.returnDevice", user);
	}

	@Override
	public Machine_driverCO returnMachineDriverByDriverUsername(DriverVO driverVO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniCommonPullMapper.returnMachineDriverByDriverUsername",
				driverVO);

	}

	@Override
	public DeviceSC returnNumberOfGeneratedOTP(DeviceSC deviceSC) throws DAOException {
		return (DeviceSC) getSqlMap().queryForObject("omniCommonPullMapper.returnNumberOfGeneratedOTP", deviceSC);

	}

	@Override
	public DeviceVO returnSecretKeyAndToken(DeviceSC deviceSC) throws DAOException {
		return (DeviceVO) getSqlMap().queryForObject("omniCommonPullMapper.returnSecretKeyAndToken", deviceSC);
	}

//	@Override
//	public EdKeysVO returnFrontEndPublicKey(EdKeysVO keysVO) throws DAOException {
//		return (EdKeysVO) getSqlMap().queryForObject("omniCommonPullMapper.returnFrontEndPublicKey", keysVO);
//	}

	@Override
	public DriverVO returnDriverName(ReservationCO reservationCO) throws DAOException {
		return (DriverVO) getSqlMap().queryForObject("omniCommonPullMapper.returnDriverName", reservationCO);
	}

	@Override
	public DeviceSC returnInvalidOTP(DeviceSC deviceSC) throws DAOException {
		return (DeviceSC) getSqlMap().queryForObject("omniCommonPullMapper.returnInvalidOTP", deviceSC);
	}

	@Override
	public VersionVO returnVersion(VersionVO versionVO) throws DAOException {
		return (VersionVO) getSqlMap().queryForObject("omniCommonPullMapper.returnVersion", versionVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ErrorVO> returnLanguageError(ErrorVO errorVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnLanguageError", errorVO);

	}

	@Override
	public List<NotificationVO> returnLanguageNotification(NotificationVO notificationVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnLanguageNotification", notificationVO);

	}

	@Override
	public PropertiesVO returnProperty(PropertiesVO propertyVO) throws DAOException {
		return (PropertiesVO) getSqlMap().queryForObject("omniCommonPullMapper.returnProperty", propertyVO);
	}

	@Override
	public List<PropertiesVO> returnListProperties(PropertiesVO propertyVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnListProperties", propertyVO);

	}

	@Override
	public int returnFrontEndPublicKey(EdKeysVO keysVO) throws DAOException {
		return (int) getSqlMap().queryForObject("omniCommonPullMapper.returnFrontEndPublicKey", keysVO);
	}

	@Override
	public ReservationCO returnReservationListDateCreateDetails(ReservationListVO reservationListVO)
			throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniCommonPullMapper.returnReservationListDateCreateDetails",
				reservationListVO);
	}

	@Override
	public List<DeviceAdminVO> returnAdminToken(AdminVO adminVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnAdminToken", adminVO);
	}

	@Override
	public DeviceSC returnTokenDevice(DeviceSC deviceSC) throws DAOException {
		return (DeviceSC) getSqlMap().queryForObject("omniCommonPullMapper.returnTokenDevice", deviceSC);
	}

	@Override
	public ClientVO returnMobileNumberIfExist(ClientCO clientCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniCommonPullMapper.returnMobileNumberIfExist", clientCO);
	}

	@Override
	public ClientVO returnMobileNumberIfExist1(ClientCO clientCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniCommonPullMapper.returnMobileNumberIfExist1", clientCO);
	}

	@Override
	public List<ReservationCO> retrunIfFirstTwoReservations(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.retrunIfFirstTwoReservations", reservationCO);
	}

	@Override
	public List<ReservationCO> returnDismissedAndCanceledReservation(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnDismissedAndCanceledReservation", reservationCO);

	}

	@Override
	public List<ReservationDetailsVO> returnTlubtukReservationDetails(ReservationDetailsVO reservationDetailsVO)
			throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnTlubtukReservationDetails", reservationDetailsVO);

	}

	@Override
	public List<ClientVO> returnClientThatInviteTheNewMobileNumber(DeviceSC deviceSC) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnClientThatInviteTheNewMobileNumber", deviceSC);

	}

	@Override
	public Machine_driverCO returnMachineDriverId(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniCommonPullMapper.returnMachineDriverId",
				reservationCO);

	}

	@Override
	public DeviceSC returnCombinationMobileNumberDeviceUUID(DeviceSC deviceSC) throws DAOException {
		return (DeviceSC) getSqlMap().queryForObject("omniCommonPullMapper.returnCombinationMobileNumberDeviceUUID",
				deviceSC);
	}

	@Override
	public int returnNumberOfCanceledAndMissed(ReservationCO reservationCO) throws DAOException {
		return (int) getSqlMap().queryForObject("omniCommonPullMapper.returnNumberOfCanceledAndMissed", reservationCO);

	}

	@Override
	public int returnNumberOfPendingReservation(ReservationCO reservationCO) throws DAOException {
		return (int) getSqlMap().queryForObject("omniCommonPullMapper.returnNumberOfPendingReservation", reservationCO);
	}

	@Override
	public ClientVO returnNbOfFreeTrip(ReservationCO reservationCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniCommonPullMapper.returnNbOfFreeTrip", reservationCO);
	}

	@Override
	public Machine_driverCO returnMachineDriverRandomly(ReservationDetailsCO reservationDetailsCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniCommonPullMapper.returnMachineDriverRandomly",
				reservationDetailsCO);

	}

	@Override
	public Machine_driverCO returnDriverOfBroker(ReservationDetailsCO reservationDetailsCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniCommonPullMapper.returnDriverOfBroker",
				reservationDetailsCO);
	}

	@Override
	public ReservationCO returnKilometrage(ReservationCO reservationCO) throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniCommonPullMapper.returnKilometrage", reservationCO);

	}

	@Override
	public List<ReservationCO> returnNotConfirmedTlubtukReservations(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnNotConfirmedTlubtukReservations", reservationCO);

	}

	@Override
	public List<ReservationListVO> returnOldReservationsToday(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnOldReservationsToday", reservationCO);

	}

	@Override
	public List<ReservationListVO> returnOldReservations(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnOldReservations", reservationCO);

	}

	@Override
	public List<Machine_driverCO> returnAllDriver(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnAllDriver", reservationCO);

	}

	@Override
	public List<Machine_driverCO> returnReservedDriver(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnReservedDriver", reservationCO);

	}

	@Override
	public DriverCO returnDriverAvailability(DriverVO driverVO) throws DAOException {
		return (DriverCO) getSqlMap().queryForObject("omniCommonPullMapper.returnDriverAvailability", driverVO);
	}

	@Override
	public AdminCO returnAdminStatus(AdminVO adminVO) throws DAOException {
		return (AdminCO) getSqlMap().queryForObject("omniCommonPullMapper.returnAdminStatus", adminVO);
	}

	@Override
	public List<DeviceAdminVO> returnCustomerCareToken(AdminVO adminVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnCustomerCareToken", adminVO);

	}

	@Override
	public List<Clients_groupVO> returnListOfGroups(Clients_groupVO clients_groupVO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnListOfGroups", clients_groupVO);

	}

	@Override
	public List<ReservationSC> returnRecurringReservation(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnRecurringReservation", reservationCO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationSC> returnReservationsList(ReservationVO reservationVO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnReservationsList", reservationVO);
	}

	@Override
	public Clients_groupVO returnGroupifExist(Clients_groupVO clients_groupVO) throws DAOException {
		return (Clients_groupVO) getSqlMap().queryForObject("omniCommonPullMapper.returnGroupifExist", clients_groupVO);
	}

	@Override
	public Clients_groupVO returnClientsGroup(ReservationCO reservationCO) throws DAOException {
		return (Clients_groupVO) getSqlMap().queryForObject("omniCommonPullMapper.returnClientsGroup", reservationCO);
	}

	@Override
	public Group_template returnGroupTemplate(Clients_groupVO clients_groupVO) throws DAOException {
		return (Group_template) getSqlMap().queryForObject("omniCommonPullMapper.returnGroupTemplate", clients_groupVO);
	}

	@Override
	public List<ClientCO> returnMonthlyClientData(ClientCO clientCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnMonthlyClientData",clientCO);
	}

	@Override
	public List<ClientCO> returnClientsGroupList(ClientCO clientCO) throws DAOException {
		return getSqlMap().queryForList("omniCommonPullMapper.returnClientsGroupList",clientCO);
	}

	@Override
	public ReservationCO retrunReservationData(ClientCO clientCO) throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniCommonPullMapper.retrunReservationData", clientCO);
	}

}
