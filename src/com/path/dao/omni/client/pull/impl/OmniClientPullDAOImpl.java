package com.path.dao.omni.client.pull.impl;

import java.util.List;

import com.path.dao.omni.client.pull.OmniClientPullDAO;
import com.path.dao.omni.common.base.OmniBaseDAO;
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

//@Monitor
public class OmniClientPullDAOImpl extends OmniBaseDAO implements OmniClientPullDAO {

	@Override
	public SimVO returnSimVO(ClientCO clientCO) throws DAOException {
		// TODO Auto-generated method stub
		return (SimVO) getSqlMap().queryForObject("omniClientPullMapper.returnSimVO", clientCO);
	}

	@Override
	public SimVO returnSimSerial(ClientCO clientCO) throws DAOException {
		return (SimVO) getSqlMap().queryForObject("omniClientPullMapper.returnSimSerial", clientCO);
	}

	@Override
	public ClientDeviceCO returnCombinationUsernameDevice(ClientCO clientCO) throws DAOException {
		return (ClientDeviceCO) getSqlMap().queryForObject("omniClientPullMapper.returnCombinationUsernameDevice",
				clientCO);
	}

	@Override
	public ClientVO returnUsernamePassword(ClientCO clientCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniClientPullMapper.returnUsernamePassword", clientCO);
	}

	@Override
	public ClientVO returnUsername(ClientVO clientVO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniClientPullMapper.returnUsername", clientVO);
	}

	@Override
	public Client_deviceVO returnCombination(ClientCO clientCO) throws DAOException {
		return (Client_deviceVO) getSqlMap().queryForObject("omniClientPullMapper.returnCombination", clientCO);
	}

	@Override
	public ClientVO returnClientInfo(ClientVO clientVO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniClientPullMapper.returnClientInfo", clientVO);
	}

	@Override
	public ClientVO returnUsernamePasswordById(ClientCO clientCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniClientPullMapper.returnUsernamePasswordById", clientCO);
	}

	/*
	 * @SuppressWarnings("unchecked")
	 * 
	 * @Override public List<ReservationSC> returnReservationsList(ReservationVO
	 * reservationVO) throws DAOException { return
	 * getSqlMap().queryForList("omniClientPullMapper.returnReservationsList",
	 * reservationVO); }
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<List_destinationsVO> returnListOfDestination(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListOfDestination", reservationCO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine_driverCO> returnListAvailableMachinesNearestMostFar(ReservationCO reservationCO)
			throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListAvailableMachinesNearestMostFar",
				reservationCO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationCO> returnLastReservations(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnLastReservations", reservationCO);
	}

	@Override
	public ClientVO returnMobileNumber(ClientCO clientCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniClientPullMapper.returnMobileNumber", clientCO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Machine_driverCO> returnListUnavailableMachines(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListUnavailableMachines", reservationCO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ClientCO> returnListDevices(ClientVO clientVO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListDevices", clientVO);

	}

	@Override
	public int returnUsernameExists(ClientVO clientVO) throws DAOException {
		return (int) getSqlMap().queryForObject("omniClientPullMapper.returnUsernameExists", clientVO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public ClientCO returnLastLogin(ClientCO clientCO) throws DAOException {
		return (ClientCO) getSqlMap().queryForObject("omniClientPullMapper.returnLastLogin", clientCO);
	}

//	@Override
//	public List<ReservationListVO> returnOldReservations(ReservationCO reservationCO) throws DAOException {
//		return getSqlMap().queryForList("omniClientPullMapper.returnOldReservations", reservationCO);
//
//	}

//	@Override
//	public List<ReservationListVO> returnOldReservationsToday(ReservationCO reservationCO) throws DAOException {
//		return getSqlMap().queryForList("omniClientPullMapper.returnOldReservationsToday", reservationCO);
//
//	}

	@Override
	public int returnNumberOfCancelation(ReservationCO reservationCO) throws DAOException {
		return (int) getSqlMap().queryForObject("omniClientPullMapper.returnNumberOfCancelation", reservationCO);

	}

	@Override
	public List<Machine_driverCO> returnListDriver(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListDriver", reservationCO);

	}

	@Override
	public List<Machine_driverCO> returnListAvailableDriver(ReservationCO reservationCO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListAvailableDriver", reservationCO);

	}

	@Override
	public Machine_driverCO returnNearestAvailableMachine(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniClientPullMapper.returnNearestAvailableMachine",
				reservationCO);
	}

	@Override
	public Machine_driverCO returnDriver(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniClientPullMapper.returnDriver", reservationCO);
	}

//	@Override
//	public List<Machine_driverCO> returnReservedDriver(ReservationCO reservationCO) throws DAOException {
//		return getSqlMap().queryForList("omniClientPullMapper.returnReservedDriver", reservationCO);
//
//	}

//	@Override
//	public List<Machine_driverCO> returnAllDriver(ReservationCO reservationCO) throws DAOException {
//		return getSqlMap().queryForList("omniClientPullMapper.returnAllDriver", reservationCO);
//
//	}

	@Override
	public ClientVO returnUsernameMobileNumber(ClientCO clientCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniClientPullMapper.returnUsernameMobileNumber", clientCO);

	}

	@Override
	public ClientDeviceCO returnClientIdMobileNumberDevice(ClientCO clientCO) throws DAOException {
		return (ClientDeviceCO) getSqlMap().queryForObject("omniClientPullMapper.returnClientIdMobileNumberDevice",
				clientCO);

	}

	@Override
	public ClientDeviceCO returnCombinationUsernameMobileNumberDevice(ClientCO clientCO) throws DAOException {
		return (ClientDeviceCO) getSqlMap()
				.queryForObject("omniClientPullMapper.returnCombinationUsernameMobileNumberDevice", clientCO);

	}

	@Override
	public ClientDeviceCO returnClientFromClientDevice(ClientCO clientCO) throws DAOException {
		return (ClientDeviceCO) getSqlMap().queryForObject("omniClientPullMapper.returnClientFromClientDevice",
				clientCO);

	}

	@Override
	public Machine_driverCO returnAvailableDriverAllDay(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniClientPullMapper.returnAvailableDriverAllDay",
				reservationCO);

	}

	@Override
	public Machine_driverCO returnAvailableDriverEachDay(ReservationCO reservationCO) throws DAOException {
		return (Machine_driverCO) getSqlMap().queryForObject("omniClientPullMapper.returnAvailableDriverEachDay",
				reservationCO);
	}

	
//	@Override
//	public List<ReservationCO> returnNotConfirmedTlubtukReservations(ReservationCO reservationCO) throws DAOException {
//		return getSqlMap().queryForList("omniClientPullMapper.returnNotConfirmedTlubtukReservations", reservationCO);
//
//	}

	@Override
	public List<String> returnListOfInvitedContact(InvitedContactCO invitedContactCO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListOfInvitedContact", invitedContactCO);

	}

	@Override
	public ReservationCO returnNbOfReservation(ReservationCO reservationCO) throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniClientPullMapper.returnNbOfReservation", reservationCO);
	}

	@Override
	public List<ReservationSC> returnListOfTlubtukReservation(ReservationVO reservationVO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListOfTlubtukReservation", reservationVO);

	}

	@Override
	public List<ParamLovVO> returnListOfBrokers(ParamLovVO paramLovVO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListOfBrokers", paramLovVO);

	}

	@Override
	public DeviceSC returnTokenOfMobileNumber(ClientCO clientCO) throws DAOException {
		return (DeviceSC) getSqlMap().queryForObject("omniClientPullMapper.returnTokenOfMobileNumber", clientCO);

	}

	@Override
	public Client_deviceVO returnIfDeviceExistInClientDevice(ClientCO clientCO) throws DAOException {
		return (Client_deviceVO) getSqlMap().queryForObject("omniClientPullMapper.returnIfDeviceExistInClientDevice",
				clientCO);

	}

	@Override
	public List<ParamLovSC> returnListOfServices(LovVO lovVO) throws DAOException {
		return getSqlMap().queryForList("omniClientPullMapper.returnListOfServices", lovVO);

	}

	@Override
	public ClientVO returnMobileNumberById(ClientCO clientCO) throws DAOException {
		return (ClientVO) getSqlMap().queryForObject("omniClientPullMapper.returnMobileNumberById", clientCO);
	}

}
