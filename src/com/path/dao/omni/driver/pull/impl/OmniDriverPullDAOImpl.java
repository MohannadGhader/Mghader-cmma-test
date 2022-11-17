package com.path.dao.omni.driver.pull.impl;

import java.util.List;

import com.path.dao.omni.common.base.OmniBaseDAO;
import com.path.dao.omni.driver.pull.OmniDriverPullDAO;
import com.path.dbmaps.vo.ClientCO;
import com.path.dbmaps.vo.CompaniesSC;
import com.path.dbmaps.vo.DeviceDriverVO;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.Driver_deviceCO;
import com.path.dbmaps.vo.Driver_deviceVO;
import com.path.dbmaps.vo.MachineDriverLocationVO;
import com.path.dbmaps.vo.MachineDriverVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.lib.common.exception.DAOException;
import com.path.ws.omni.vo.common.ReturnRecurringClientsResponse;

//@Monitor
public class OmniDriverPullDAOImpl extends OmniBaseDAO implements OmniDriverPullDAO {

	@Override
	public DriverVO returnDriverInfo(DriverVO driverVO) throws DAOException {
		return (DriverVO) getSqlMap().queryForObject("omniDriverPullMapper.returnDriverInfo", driverVO);
	}

	@Override
	public Driver_deviceCO returnCombinationDriverDevice(Driver_deviceCO driver_deviceCO) throws DAOException {
		return (Driver_deviceCO) getSqlMap().queryForObject("omniDriverPullMapper.returnCombinationDriverDevice",
				driver_deviceCO);

	}

	@Override
	public Driver_deviceVO returnCombDriverDevice(DriverCO driverCO) throws DAOException {
		return (Driver_deviceVO) getSqlMap().queryForObject("omniDriverPullMapper.returnCombDriverDevice", driverCO);
	}

	@Override
	public DriverVO returnDriverUsername(DriverCO driverCO) throws DAOException {
		return (DriverVO) getSqlMap().queryForObject("omniDriverPullMapper.returnDriverUsername", driverCO);

	}

	@Override
	public List<ReservationSC> returnListReservationsNotFinishedDriver(DriverCO driverCO) throws DAOException {
		return getSqlMap().queryForList("omniDriverPullMapper.returnListReservationsNotFinishedDriver", driverCO);

	}

	@Override
	public DriverVO returnUsernamePassDriverById(DriverCO driverCO) throws DAOException {
		return (DriverVO) getSqlMap().queryForObject("omniDriverPullMapper.returnUsernamePassDriverById", driverCO);

	}

	@Override
	public DriverVO returnMobileNumberDriver(DriverCO driverCO) throws DAOException {
		return (DriverVO) getSqlMap().queryForObject("omniDriverPullMapper.returnMobileNumberDriver", driverCO);
	}

	@Override
	public Driver_deviceVO returnDriverUsernameComb(DriverCO driverCO) throws DAOException {
		return (Driver_deviceVO) getSqlMap().queryForObject("omniDriverPullMapper.returnDriverUsernameComb", driverCO);
	}

	@Override
	public MachineDriverVO returnDriverLocation(ReservationListVO reservationListVO) throws DAOException {
		return (MachineDriverVO) getSqlMap().queryForObject("omniDriverPullMapper.returnDriverLocation",
				reservationListVO);

	}

	@Override
	public ReservationCO returnDistanceOfTrip(ReservationCO reservationCO) throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniDriverPullMapper.returnDistanceOfTrip", reservationCO);

	}

	@Override
	public DeviceDriverVO returnDriverToken(DriverCO driverCO) throws DAOException {
		return (DeviceDriverVO) getSqlMap().queryForObject("omniDriverPullMapper.returnDriverToken", driverCO);

	}

	@Override
	public ReservationCO returnDistanceFromToClient(ReservationCO reservationCO) throws DAOException {
		return (ReservationCO) getSqlMap().queryForObject("omniDriverPullMapper.returnDistanceFromToClient",
				reservationCO);

	}

	@Override
	public MachineDriverLocationVO returnMachineDriverLocation(DriverCO driverCO) throws DAOException {
		return (MachineDriverLocationVO) getSqlMap().queryForObject("omniDriverPullMapper.returnMachineDriverLocation",
				driverCO);
	}

	@Override
	public CompaniesSC returnDiscountOfClient(DriverCO driverCO) throws DAOException {
		return (CompaniesSC) getSqlMap().queryForObject("omniDriverPullMapper.returnDiscountOfClient", driverCO);

	}

	@Override
	public DriverVO returnDriverStatus(DriverVO driverVO) throws DAOException {
		return (DriverVO) getSqlMap().queryForObject("omniDriverPullMapper.returnDriverStatus", driverVO);
	}

	@Override
	public List<ReservationSC> returnlistOfFinishedReservationsDriver(DriverCO driverCO) throws DAOException {
		return getSqlMap().queryForList("omniDriverPullMapper.returnlistOfFinishedReservationsDriver", driverCO);

	}

	@Override
	public List<MachineDriverLocationVO> returnListOfDriverLocations(DriverCO driverCO) throws DAOException {
		return getSqlMap().queryForList("omniDriverPullMapper.returnListOfDriverLocations", driverCO);

	}

	@Override
	public List<ReservationSC> returnListOfFinishedReservationsWithAddress(DriverCO driverCO) throws DAOException {
		return getSqlMap().queryForList("omniDriverPullMapper.returnListOfFinishedReservationsWithAddress", driverCO);

	}

	@Override
	public List<ReservationSC> returnListOfNotFinishedReservationsWithAddress(DriverCO driverCO) throws DAOException {
		return getSqlMap().queryForList("omniDriverPullMapper.returnListOFNotFinishedReservationsWithAddress", driverCO);

	}

	@Override
	public List<ReservationCO> returnListOfFutureReservation(DriverCO driverCO) throws DAOException {
		return getSqlMap().queryForList("omniDriverPullMapper.returnListOfFutureReservation", driverCO);

	}

	@Override
	public List<ReturnRecurringClientsResponse> returnRecurringClients() throws DAOException {
			// is it right to be null??!!
			return getSqlMap().queryForList("omniDriverPullMapper.returnRecurringClients",null);

	}

}
