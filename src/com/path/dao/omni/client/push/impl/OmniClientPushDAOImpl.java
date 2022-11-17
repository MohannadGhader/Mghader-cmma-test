package com.path.dao.omni.client.push.impl;

import com.path.dao.omni.client.push.OmniClientPushDAO;
import com.path.dao.omni.common.base.OmniBaseDAO;
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

//@Monitor
public class OmniClientPushDAOImpl extends OmniBaseDAO implements OmniClientPushDAO {

	@Override
	public void insertSim(ClientCO clientCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertSim", clientCO);
	}

	@Override
	public void insertCombination(ClientCO clientCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertCombination", clientCO);

	}

	@Override
	public void updateClient(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateClient", clientCO);
	}

	@Override
	public void deleteReservation(ReservationCO reservationCO) throws DAOException {
		getSqlMap().delete("omniClientPushMapper.deleteReservation", reservationCO);
	}

	@Override
	public void insertUsernamePass(ClientCO clientCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertUsernamePass", clientCO);

	}

	@Override
	public void updateCombination(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateCombination", clientCO);

	}

	@Override
	public void insertListReservationList(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertListReservationList", reservationCO);

	}

	@Override
	public void updateClientInfos(ClientVO clientVO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateClientInfos", clientVO);

	}

	@Override
	public void updatePassword(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updatePassword", clientCO);

	}

	@Override
	public void updateProfile(ClientVO clientVO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateProfile", clientVO);

	}

	@Override
	public void updateClientStatus(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateClientStatus", clientCO);

	}

	@Override
	public void insertDestination(List_destinationsVO list_destinationsVO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertDestination", list_destinationsVO);

	}

	public void resetPassword(ClientVO clientVO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.resetPassword", clientVO);

	}

	@Override
	public void deleteCombinationDeviceUsername(ClientCO clientCO) throws DAOException {
		getSqlMap().delete("omniClientPushMapper.deleteCombinationDeviceUsername", clientCO);
	}

	@Override
	public void updateLastLoginClient(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateLastLoginClient", clientCO);

	}

	@Override
	public void updateLastLoginClientDevice(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateLastLoginClientDevice", clientCO);

	}

	@Override
	public void insertReservationListMachineInfos(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertReservationListMachineInfos", reservationCO);

	}

	@Override
	public void updateDeviceToken(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateDeviceToken", clientCO);

	}

	@Override
	public void updateDeviceStatus(DeviceSC deviceSC) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateDeviceStatus", deviceSC);

	}

	@Override
	public void insertCancelReservation(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertCancelReservation", reservationCO);

	}

	@Override
	public void updateCombinationUsernameMobileNumberDevice(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateCombinationUsernameMobileNumberDevice", clientCO);

	}

	@Override
	public void insertRecurringReservationEachDay(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertRecurringReservationEachDay", reservationCO);

	}

	@Override
	public void updateDestinationName(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateDestinationName", reservationCO);

	}

	/*
	 * @Override public void updateReservationStatus(ReservationVO reservationVO)
	 * throws DAOException {
	 * getSqlMap().update("omniClientPushMapper.updateReservationStatus",
	 * reservationVO);
	 * 
	 * }
	 */

//	@Override
//	public void insertUsernameMobileNumber(ClientCO clientCO) throws DAOException {
//		getSqlMap().insert("omniClientPushMapper.insertUsernameMobileNumber", clientCO);
//
//	}

	@Override
	public void updateUsernameMobileNumber(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateUsernameMobileNumber", clientCO);

	}

	@Override
	public void insertIntoInvitedContact(InvitedContactVO invitedContactVO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertIntoInvitedContact", invitedContactVO);

	}

	@Override
	public void updateNbOfReservation(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateNbOfReservation", reservationCO);

	}

	@Override
	public void addNbOfFreeTrip(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.addNbOfFreeTrip", clientCO);

	}

	@Override
	public void updateReferenceName(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateReferenceName", clientCO);

	}

	@Override
	public void updateInactiveClient(ClientVO clientVO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateInactiveClient", clientVO);

	}

	@Override
	public void updateReservationListStatusToCanceledAndBrokerReservation(ReservationSC reservationSC)
			throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateReservationListStatusToCanceledAndBrokerReservation",
				reservationSC);

	}

	@Override
	public void addUser(ClientVO clientVO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.addUser", clientVO);
		
	}

	@Override
	public void insertUsernameMobileNumber(ClientCO clientCO) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
