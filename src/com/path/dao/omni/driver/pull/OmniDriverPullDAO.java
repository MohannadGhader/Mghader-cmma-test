package com.path.dao.omni.driver.pull;

import java.util.List;

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

public interface OmniDriverPullDAO {

	/**
	 * method that return the infos of driver from db
	 * 
	 * @param driverVO
	 * @return
	 * @throws DAOException
	 */
	public DriverVO returnDriverInfo(DriverVO driverVO) throws DAOException;

	/**
	 * method that return the combination device and driver with driver_id and
	 * device_UUID as param
	 * 
	 * @param driverDeviceCO
	 * @return
	 * @throws DAOException
	 */
	public Driver_deviceCO returnCombinationDriverDevice(Driver_deviceCO driver_deviceCO) throws DAOException;

	/**
	 * method that return the combination device and driver with username and
	 * device_UUID as param
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public Driver_deviceVO returnCombDriverDevice(DriverCO driverCO) throws DAOException;

	/**
	 * method that check if the username exist or no
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public DriverVO returnDriverUsername(DriverCO driverCO) throws DAOException;

	/**
	 * method that return if the driver_id exist in driver_device table
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public Driver_deviceVO returnDriverUsernameComb(DriverCO driverCO) throws DAOException;

	/**
	 * method that return the list of reservations of the driver
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationSC> returnListReservationsNotFinishedDriver(DriverCO driverCO) throws DAOException;

	/**
	 * method that return the username and password of driver by id
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public DriverVO returnUsernamePassDriverById(DriverCO driverCO) throws DAOException;

	/**
	 * method that return the mobile number of driver by username
	 * 
	 * @param driverVO
	 * @return
	 * @throws DAOException
	 */
	public DriverVO returnMobileNumberDriver(DriverCO driverCO) throws DAOException;

	public MachineDriverVO returnDriverLocation(ReservationListVO reservationListVO) throws DAOException;

	/**
	 * method that return the distance of trip in order to calcule the cost
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO returnDistanceOfTrip(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the distance from the machine where it is reserved to the
	 * client
	 * 
	 * @param reservationCO
	 * @return
	 * @throws DAOException
	 */
	public ReservationCO returnDistanceFromToClient(ReservationCO reservationCO) throws DAOException;

	/**
	 * method that return the details and locations of machine driver
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public MachineDriverLocationVO returnMachineDriverLocation(DriverCO driverCO) throws DAOException;

	/**
	 * method that return the token of driver
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public DeviceDriverVO returnDriverToken(DriverCO driverCO) throws DAOException;

	public CompaniesSC returnDiscountOfClient(DriverCO driverCO) throws DAOException;

	/**
	 * method that return the driver status
	 * 
	 * @param driverVO
	 * @return
	 * @throws DAOException
	 */
	public DriverVO returnDriverStatus(DriverVO driverVO) throws DAOException;

	/**
	 * method that return the finished reservations
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationSC> returnlistOfFinishedReservationsDriver(DriverCO driverCO) throws DAOException;

	/**
	 * method that return the latest locations of each driver
	 * 
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public List<MachineDriverLocationVO> returnListOfDriverLocations(DriverCO driverCO) throws DAOException;

	public List<ReservationSC> returnListOfFinishedReservationsWithAddress(DriverCO driverCO) throws DAOException;

	public List<ReservationSC> returnListOfNotFinishedReservationsWithAddress(DriverCO driverCO) throws DAOException;
	
	/**
	 * method that return the future reservation of driver
	 * @param driverCO
	 * @return
	 * @throws DAOException
	 */
	public List<ReservationCO> returnListOfFutureReservation(DriverCO driverCO) throws DAOException;
	
	
	public List<ReturnRecurringClientsResponse> returnRecurringClients() throws DAOException;
	
	
}
