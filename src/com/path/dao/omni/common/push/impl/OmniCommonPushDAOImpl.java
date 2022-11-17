/**
 * Copyright 2018, Path Solutions
 * Path Solutions retains all ownership rights to this source code 
 * 
 * @author: gilbertandary
 *
 * OmniCommonPushDAOImpl.java
 *
 * 
 */
package com.path.dao.omni.common.push.impl;

import com.path.dao.omni.common.base.OmniBaseDAO;
import com.path.dao.omni.common.push.OmniCommonPushDAO;
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

//@Monitor
public class OmniCommonPushDAOImpl extends OmniBaseDAO implements OmniCommonPushDAO {

	@Override
	public void insertRecurring_template(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertRecurring_template", reservationCO);

	}

	@Override
	public void insertRating(RateVO rateVO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertRating", rateVO);

	}

	@Override
	public void insertTempReservationTrip(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertTempReservationTrip", reservationCO);

	}

	@Override
	public void insertTempReservationTripBefore(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertTempReservationTripBefore", reservationCO);

	}

	@Override
	public void insertTempReservationTripAfter(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertTempReservationTripAfter", reservationCO);

	}

	@Override
	public void deleteTemporaryReservation(ReservationCO reservationCO) throws DAOException {
		getSqlMap().delete("omniCommonPushMapper.deleteTemporaryReservation", reservationCO);

	}

	@Override
	public void updateDriverWorkingHours(WorkingHoursVO workingHoursVO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.updateDriverWorkingHours", workingHoursVO);

	}

	@Override
	public void updateReservationListStatus(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateReservationListStatus", reservationCO);

	}

	public void insertStore(StoreVO storeVO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertStore", storeVO);
	}

	@Override
	public void insertTempReservationMachine(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertTempReservationMachine", reservationCO);

	}

	@Override
	public void insertTempReservationMachineAfter(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertTempReservationMachineAfter", reservationCO);

	}

	@Override
	public void insertTempReservationMachineBefore(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertTempReservationMachineBefore", reservationCO);

	}

	@Override
	public void insertNewInvalidOTP(DeviceSC deviceSC) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertNewInvalidOTP", deviceSC);

	}

//	@Override
//	public void updateMachineDriverStatus(Machine_driverCO machine_driverCO) throws DAOException {
//		getSqlMap().update("omniCommonPushMapper.updateMachineDriverStatus", machine_driverCO);
//
//	}

	@Override
	public void insertReservation(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertReservation", reservationCO);
	}

	@Override
	public void insertReservationList(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertReservationList", reservationCO);

	}

	@Override
	public void insertDevice(User user) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertDevice", user);

	}

	@Override
	public void insertNewOTP(DeviceSC deviceSC) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertNewOTP", deviceSC);

	}

	@Override
	public void updateOTPSecretKey(DeviceSC deviceSC) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.updateOTPSecretKey", deviceSC);

	}

	@Override
	public void updateToken(DeviceKeysSC deviceKeysSC) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateToken", deviceKeysSC);

	}

	@Override
	public void insertKeys(EdKeysVO keysVO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertKeys", keysVO);

	}

	@Override
	public void updateKeys(EdKeysVO keysVO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateKeys", keysVO);

	}

	@Override
	public void updateCounterOTP(DeviceSC deviceSC) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateCounterOTP", deviceSC);

	}

	@Override
	public void updateCounterGeneratedOTP(DeviceSC deviceSC) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateCounterGeneratedOTP", deviceSC);

	}

	@Override
	public void updateDeviceLanguage(DeviceSC deviceSC) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateDeviceLanguage", deviceSC);

	}

	@Override
	public void updateOtpStatus(DeviceSC deviceSC) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateOtpStatus", deviceSC);

	}

	@Override
	public void updateDeviceToken(User user) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateDeviceToken", user);

	}

	@Override
	public void updateRecurringReservationStatus(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateRecurringReservationStatus", reservationCO);

	}

	@Override
	public void updateDeviceManufacturer(User user) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateDeviceManufacturer", user);

	}

	@Override
	public void UpdatePenaltyStatus(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.UpdatePenaltyStatus", reservationCO);

	}

	@Override
	public void updateCostOfTripFromDestinationList(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateCostOfTripFromDestinationList", reservationCO);

	}

	@Override
	public void updateNbOfFreeTrip(ClientVO clientVO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateNbOfFreeTrip", clientVO);

	}

	@Override
	public void updateCancelReservation(ReservationSC reservationSC) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateCancelReservation", reservationSC);

	}

	@Override
	public void updateLocations(ReservationDetailsCO reservationDetailsCO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateLocations", reservationDetailsCO);

	}

	@Override
	public void insertReservationDetails(ReservationDetailsCO reservationDetailsCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertReservationDetails", reservationDetailsCO);

	}

	@Override
	public void updateReservationType(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateReservationType", reservationCO);

	}

	@Override
	public void updateClientGroup(ClientVO clientVO) throws DAOException {
		getSqlMap().update("omniCommonPushMapper.updateClientGroup", clientVO);

	}

	@Override
	public void addMonthlyClient(ClientVO clientVO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.addMonthlyClient", clientVO);

	}

	@Override
	public void insertGroup(Clients_groupVO clients_groupVO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertGroup", clients_groupVO);

	}
	
	@Override
	public void insertUsernameMobileNumber(ClientCO clientCO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertUsernameMobileNumber", clientCO);

	}

	@Override
	public void insertNewFacture(ReservationCO reservationCO) throws DAOException {
		getSqlMap().insert("omniClientPushMapper.insertUsernameMobileNumber", reservationCO);
		
	}
	
	@Override
	public void updateReservationStatus(ReservationVO reservationVO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateReservationStatus", reservationVO);

	}

	@Override
	public void insertGroupTemplate(Clients_groupVO clients_groupVO) throws DAOException {
		getSqlMap().insert("omniCommonPushMapper.insertGroupTemplate", clients_groupVO);
		
	}

	@Override
	public void updateClientsGroupID(ClientVO clientVO) throws DAOException {
		getSqlMap().update("omniClientPushMapper.updateClientsGroupID", clientVO);
		
	}
	
}
