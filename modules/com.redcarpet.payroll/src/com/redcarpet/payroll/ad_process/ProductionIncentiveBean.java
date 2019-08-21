package com.redcarpet.payroll.ad_process;

import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
import com.redcarpet.payroll.data.RCPLLoom;
import  com.redcarpet.payroll.data.RCPLProdIncentive;
public class ProductionIncentiveBean {
	public Organization organization;
	public Client client;
	public String efficiency;
	public Boolean dyed;
	public Boolean grey;
	public RCPLProdIncentive productionIncentiveHeaderObject;
	public RCPLLoom loomObject;
	public Organization getOrganization() {
		return organization;
	}
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(String efficiency) {
		this.efficiency = efficiency;
	}
	public Boolean getDyed() {
		return dyed;
	}
	public void setDyed(Boolean dyed) {
		this.dyed = dyed;
	}
	public Boolean getGrey() {
		return grey;
	}
	public void setGrey(Boolean grey) {
		this.grey = grey;
	}
	public RCPLProdIncentive getProductionIncentiveHeaderObject() {
		return productionIncentiveHeaderObject;
	}
	public void setProductionIncentiveHeaderObject(
			RCPLProdIncentive productionIncentiveHeaderObject) {
		this.productionIncentiveHeaderObject = productionIncentiveHeaderObject;
	}
	public RCPLLoom getLoomObject() {
		return loomObject;
	}
	public void setLoomObject(RCPLLoom loomObject) {
		this.loomObject = loomObject;
	}
}