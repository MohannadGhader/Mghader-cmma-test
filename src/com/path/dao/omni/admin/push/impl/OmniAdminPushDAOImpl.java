package com.path.dao.omni.admin.push.impl;

import com.path.dao.omni.admin.push.OmniAdminPushDAO;
import com.path.dao.omni.common.base.OmniBaseDAO;
import com.path.dbmaps.vo.AdminCO;
import com.path.dbmaps.vo.AdminVO;
import com.path.dbmaps.vo.ClientVO;
import com.path.dbmaps.vo.Clients_groupVO;
import com.path.dbmaps.vo.CompaniesCO;
import com.path.dbmaps.vo.CompaniesSC;
import com.path.dbmaps.vo.DriverCO;
import com.path.dbmaps.vo.DriverVO;
import com.path.dbmaps.vo.MachineCO;
import com.path.dbmaps.vo.MachineDriverSC;
import com.path.dbmaps.vo.MachineVO;
import com.path.dbmaps.vo.Machine_driverCO;
import com.path.dbmaps.vo.PropertiesVO;
import com.path.dbmaps.vo.ReservationCO;
import com.path.lib.common.exception.DAOException;

//@Monitor
public class OmniAdminPushDAOImpl extends OmniBaseDAO implements OmniAdminPushDAO {

	@Override
	public void insertDriver(DriverVO driverVO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertDriver", driverVO);

	}

	@Override
	public void insertMachine(MachineVO machineVO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertMachine", machineVO);
	}

	@Override
	public void insertAdmin(AdminVO adminVO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertAdmin", adminVO);

	}

	@Override
	public void insertDeviceAdmin(AdminCO adminCO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertDeviceAdmin", adminCO);

	}

	@Override
	public void insertAdminDeviceCombination(AdminCO adminCO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertAdminDeviceCombination", adminCO);

	}

	@Override
	public void updateAdminDeviceCombination(AdminCO adminCO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateAdminDeviceCombination", adminCO);

	}

	@Override
	public void updateLastLoginAdmin(AdminCO adminCO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateLastLoginAdmin", adminCO);

	}

	@Override
	public void updateDriverInfos(Machine_driverCO machine_driverCO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateDriverInfos", machine_driverCO);
	}

	@Override
	public void updateMachineInfos(MachineCO machineCO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateMachineInfos", machineCO);

	}

	@Override
	public void insertMachineMnt(MachineCO machineCO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertMachineMnt", machineCO);
	}

	@Override
	public void insertMachineDriver(Machine_driverCO machine_driverCO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertMachineDriver", machine_driverCO);
	}

	@Override
	public void updateMachineStatus(MachineCO machineCO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateMachineStatus", machineCO);

	}

	@Override
	public void updateProperty(PropertiesVO propertyVO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateProperty", propertyVO);

	}

	@Override
	public void updateAdminStatus(AdminCO adminCO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateAdminStatus", adminCO);

	}

	@Override
	public void insertCompany(CompaniesCO companiesCO) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertCompany", companiesCO);

	}

	@Override
	public void insertMobileNumberCompany(CompaniesSC companiesSC) throws DAOException {
		getSqlMap().insert("omniAdminPushMapper.insertMobileNumberCompany", companiesSC);

	}

	@Override
	public void updateMobileNumberCompany(CompaniesSC companiesSC) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateMobileNumberCompany", companiesSC);

	}

	@Override
	public void updateDriverAvailabilty(DriverCO driverCO) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateDriverAvailabilty", driverCO);

	}

	@Override
	public void insertWorkingHours(MachineDriverSC machineDriverSC) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.insertWorkingHours", machineDriverSC);

	}

	@Override
	public void updateMachineDriver(MachineDriverSC machineDriverSC) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateMachineDriver", machineDriverSC);

	}

	@Override
	public void updateDriverWorkingHours(MachineDriverSC machineDriverSC) throws DAOException {
		getSqlMap().update("omniAdminPushMapper.updateDriverWorkingHours", machineDriverSC);

	}

	@Override
	public void updateFactureReceipt(ReservationCO reservationCO) throws DAOException {
		//to do the query then call it here
		getSqlMap().update("omniAdminPushMapper.updateFactureReceipt", reservationCO);
		
	}

}
