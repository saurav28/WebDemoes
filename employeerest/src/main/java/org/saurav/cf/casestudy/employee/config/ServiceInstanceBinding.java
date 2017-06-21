package org.saurav.cf.casestudy.employee.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Service;

/**
 * A binding to a service instance
 *
 * @author Saurav Sarkar
 */
@Entity
public class ServiceInstanceBinding {
	@Id
	private String id;
	
	private String serviceInstanceId;
	//private Map<String,Object> credentials = new HashMap<>();
	private String syslogDrainUrl;
	private String appGuid;

	public ServiceInstanceBinding(String id,
								  String serviceInstanceId,
								  Map<String,Object> credentials,
								  String syslogDrainUrl, String appGuid) {
		this.id = id;
		this.serviceInstanceId = serviceInstanceId;
		//setCredentials(credentials);
		this.syslogDrainUrl = syslogDrainUrl;
		this.appGuid = appGuid;
	}

	public String getId() {
		return id;
	}

	public String getServiceInstanceId() {
		return serviceInstanceId;
	}

//	public Map<String, Object> getCredentials() {
//		return credentials;
//	}
//
//	private void setCredentials(Map<String, Object> credentials) {
//		if (credentials == null) {
//			this.credentials = new HashMap<>();
//		} else {
//			this.credentials = credentials;
//		}
//	}

	public String getSyslogDrainUrl() {
		return syslogDrainUrl;
	}

	public String getAppGuid() {
		return appGuid;
	}

}