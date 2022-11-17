package com.path.dao.omni.driver.push.impl;

import java.util.List;

import com.path.dao.omni.common.base.OmniBaseDAO;
import com.path.dao.omni.driver.push.OmniDriverPushDAO;
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

//@Monitor
public class OmniDriverPushDAOImpl extends OmniBaseDAO implements OmniDriverPushDAO {

//	@Override
//	public void updateMachineLocation(DriverCO driverCO) throws DAOException {
//		getSqlMap().update("omniDriverPushMapper.updateMachineLocation", driverCO);
//
//	}

	@Override
	public void insertDriverDeviceCombination(DriverCO driverCO) throws DAOException {
		getSqlMap().insert("omniDriverPushMapper.insertDriverDeviceCombination", driverCO);

	}

	@Override
	public void updateDriverDeviceStatus(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateDriverDeviceStatus", driverCO);

	}

	@Override
	public void updateTimeSlot(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateTimeSlot", reservationCO);

	}

	@Override
	public void updateDeviceToken(ClientCO clientCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateDeviceToken", clientCO);

	}

	@Override
	public void updateDeviceDriverToken(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateDeviceDriverToken", driverCO);

	}

	@Override
	public void updatePasswordDriver(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updatePasswordDriver", driverCO);

	}

	@Override
	public void updateDriverDeviceCombination(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateDriverDeviceCombination", driverCO);

	}

	@Override
	public void resetPasswordDriver(DriverVO driverVO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.resetPasswordDriver", driverVO);

	}

	@Override
	public void updateLastLoginDriver(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateLastLoginDriver", driverCO);

	}

	@Override
	public void updateProfileDriver(DriverVO driverVO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateProfileDriver", driverVO);

	}

	@Override
	public void updateLocationToClient(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateLocationToClient", reservationCO);

	}

	@Override
	public void updateMachineDriverReservationLocations(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateMachineDriverReservationLocations", driverCO);

	}

	@Override
	public void insertFacture(FactureVO factureVO) throws DAOException {
		getSqlMap().insert("omniDriverPushMapper.insertfacture", factureVO);

	}

	@Override
	public void updateMachineDriverLocation(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateMachineDriverLocation", driverCO);

	}

	@Override
	public void updateDistanceStartFinish(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateDistanceStartFinish", reservationCO);

	}

	@Override
	public void updateTakeWait(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateTakeWait", reservationCO);

	}

	@Override
	public void insertMaintenance(Machine_mntCO machine_mntCO) throws DAOException {
		getSqlMap().insert("omniDriverPushMapper.insertMaintenance", machine_mntCO);

	}

	@Override
	public void updateDriverStatus(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateDriverStatus", driverCO);

	}

	@Override
	public void updateNbOfClient(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateNbOfClient", driverCO);

	}

	@Override
	public void updateCostByDriver(ReservationSC reservationSC) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateCostByDriver", reservationSC);

	}

	@Override
	public void insertNewLocationDriver(DriverCO driverCO) throws DAOException {
		getSqlMap().insert("omniDriverPushMapper.insertNewLocationDriver", driverCO);
		
	}

	@Override
	public void insertSendedNotification(Sended_notificationVO sended_notificationVO) throws DAOException {
		getSqlMap().insert("omniDriverPushMapper.insertSendedNotification", sended_notificationVO);
		
	}

	@Override
	public void insertTrackingAvailabily(DriverCO driverCO) throws DAOException {
		getSqlMap().insert("omniDriverPushMapper.insertTrackingAvailabily", driverCO);
		
	}

	@Override
	public void updateKilometrage(ReservationCO reservationCO) throws DAOException {
		getSqlMap().update("omniDriverPushMapper.updateKilometrage", reservationCO);
		
	}

}

