package com.path.dao.omni.admin.pull.impl;

import java.util.List;

import com.path.dao.omni.admin.pull.OmniAdminPullDAO;
import com.path.dao.omni.common.base.OmniBaseDAO;
import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.Admin_deviceVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.CompaniesCO;
import com.path.dbmaps.vo.CompaniesVO;
import com.path.dbmaps.vo.DeviceAdminVO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.MachineDriverSC;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_mntCO;
import com.path.dbmaps.vo.PropertiesSC;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationListVO;
import com.path.dbmaps.vo.ReservationSC;
import com.path.lib.common.exception.DAOException;
import com.path.ws.omni.vo.common.ReturnListOfFacturesResponse;

//@Monitor
public class OmniAdminPullDAOImpl extends OmniBaseDAO implements OmniAdminPullDAO {

	@Override
	public DriverVO returnDriver(DriverVO driverVO) throws DAOException {
		return (DriverVO) getSqlMap().queryForObject("omniAdminPullMapper.returnDriver", driverVO);
	}

	@Override
	public MachineVO returnMachineInfo(MachineVO machineVO) throws DAOException {
		return (MachineVO) getSqlMap().queryForObject("omniAdminPullMapper.returnMachineInfo", machineVO);

	}

	@Override
	public MachineVO returnMachineByPlateNumber(MachineVO machineVO) throws DAOException {
		return (MachineVO) getSqlMap().queryForObject("omniAdminPullMapper.returnMachineByPlateNumber", machineVO);
	}

	@Override
	public AdminVO returnAdmin(AdminVO adminVO) throws DAOException {
		return (AdminVO) getSqlMap().queryForObject("omniAdminPullMapper.returnAdmin", adminVO);

	}

	@Override
	public AdminVO returnAdminUsername(AdminCO adminCO) throws DAOException {
		return (AdminVO) getSqlMap().queryForObject("omniAdminPullMapper.returnAdminUsername", adminCO);

	}

	@Override
	public DeviceAdminVO returnDeviceAdmin(AdminCO adminCO) throws DAOException {
		return (DeviceAdminVO) getSqlMap().queryForObject("omniAdminPullMapper.returnDeviceAdmin", adminCO);

	}

	@Override
	public Admin_deviceVO returnAdminUsernameComb(AdminCO adminCO) throws DAOException {
		return (Admin_deviceVO) getSqlMap().queryForObject("omniAdminPullMapper.returnAdminUsernameComb", adminCO);

	}

	@Override
	public Admin_deviceVO returnCombAdminDevice(AdminCO adminCO) throws DAOException {
		return (Admin_deviceVO) getSqlMap().queryForObject("omniAdminPullMapper.returnCombAdminDevice", adminCO);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ReservationListVO> returnIfMachineDriverIsReserved(MachineDriverSC machineDriverSC)
			throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnIfMachineDriverIsReserved", machineDriverSC);
	}

	@Override
	public AdminCO returnCombinationUsernameDeviceAdmin(AdminCO adminCO) throws DAOException {
		return (AdminCO) getSqlMap().queryForObject("omniAdminPullMapper.returnCombinationUsernameDeviceAdmin",
				adminCO);

	}

	@Override
	public List<ReservationSC> returnListReservationsDiffFinishAdmin(ReservationSC reservationSC) throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnListReservationsDiffFinishAdmin", reservationSC);

	}

	@Override
	public List<ReservationSC> returnListReservationsFinishAdmin(ReservationSC reservationSC) throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnListReservationsFinishAdmin", reservationSC);

	}

	@Override
	public List<Machine_mntCO> returnMaintenance(Machine_mntCO machine_mntCO) throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnMaintenance", machine_mntCO);

	}

	@Override
	public List<CompaniesVO> returnCompaniesList(CompaniesCO companiesCO) throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnreturnListOfMachinesCompaniesList", companiesCO);

	}

	@Override
	public CompaniesVO returnCompany(CompaniesCO companiesCO) throws DAOException {
		return (CompaniesVO) getSqlMap().queryForObject("omniAdminPullMapper.returnCompany", companiesCO);
	}

	@Override
	public List<ReservationSC> returnListOfFactures(ReservationSC reservationSC) throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnListOfFactures", reservationSC);

	}

	@Override
	public List<PropertiesSC> returnListPropertiesWithCategories(PropertiesVO propertyVO) throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnListPropertiesWithCategories", propertyVO);

	}

	@Override
	public List<MachineVO> returnListOfMachines(MachineVO machineVO) throws DAOException {
		return getSqlMap().queryForList("omniAdminPullMapper.returnListOfMachines", machineVO);

	}

}
