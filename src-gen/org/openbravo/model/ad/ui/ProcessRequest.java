/*
 *************************************************************************
 * The contents of this file are subject to the Openbravo  Public  License
 * Version  1.1  (the  "License"),  being   the  Mozilla   Public  License
 * Version 1.1  with a permitted attribution clause; you may not  use this
 * file except in compliance with the License. You  may  obtain  a copy of
 * the License at http://www.openbravo.com/legal/license.html
 * Software distributed under the License  is  distributed  on  an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific  language  governing  rights  and  limitations
 * under the License.
 * The Original Code is Openbravo ERP.
 * The Initial Developer of the Original Code is Openbravo SLU
 * All portions are Copyright (C) 2008-2011 Openbravo SLU
 * All Rights Reserved.
 * Contributor(s):  ______________________________________.
 ************************************************************************
*/
package org.openbravo.model.ad.ui;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openbravo.base.structure.ActiveEnabled;
import org.openbravo.base.structure.BaseOBObject;
import org.openbravo.base.structure.ClientEnabled;
import org.openbravo.base.structure.OrganizationEnabled;
import org.openbravo.base.structure.Traceable;
import org.openbravo.model.ad.access.User;
import org.openbravo.model.ad.system.Client;
import org.openbravo.model.common.enterprise.Organization;
/**
 * Entity class for entity ProcessRequest (stored in table AD_Process_Request).
 *
 * NOTE: This class should not be instantiated directly. To instantiate this
 * class the {@link org.openbravo.base.provider.OBProvider} should be used.
 */
public class ProcessRequest extends BaseOBObject implements Traceable, ClientEnabled, OrganizationEnabled, ActiveEnabled {
    private static final long serialVersionUID = 1L;
    public static final String TABLE_NAME = "AD_Process_Request";
    public static final String ENTITY_NAME = "ProcessRequest";
    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_CLIENT = "client";
    public static final String PROPERTY_ORGANIZATION = "organization";
    public static final String PROPERTY_ACTIVE = "active";
    public static final String PROPERTY_CREATIONDATE = "creationDate";
    public static final String PROPERTY_CREATEDBY = "createdBy";
    public static final String PROPERTY_UPDATED = "updated";
    public static final String PROPERTY_UPDATEDBY = "updatedBy";
    public static final String PROPERTY_PROCESS = "process";
    public static final String PROPERTY_DESCRIPTION = "description";
    public static final String PROPERTY_USERCONTACT = "userContact";
    public static final String PROPERTY_SECURITYBASEDONROLE = "securityBasedOnRole";
    public static final String PROPERTY_OPENBRAVOCONTEXT = "openbravoContext";
    public static final String PROPERTY_STATUS = "status";
    public static final String PROPERTY_NEXTEXECUTION = "nextExecution";
    public static final String PROPERTY_PREVIOUSEXECUTION = "previousExecution";
    public static final String PROPERTY_FINISH = "finish";
    public static final String PROPERTY_CHANNEL = "channel";
    public static final String PROPERTY_TIMING = "timing";
    public static final String PROPERTY_STARTTIME = "startTime";
    public static final String PROPERTY_STARTDATE = "startDate";
    public static final String PROPERTY_FREQUENCY = "frequency";
    public static final String PROPERTY_INTERVALINSECONDS = "intervalInSeconds";
    public static final String PROPERTY_INTERVALINMINUTES = "intervalInMinutes";
    public static final String PROPERTY_HOURLYINTERVAL = "hourlyInterval";
    public static final String PROPERTY_DAILYINTERVAL = "dailyInterval";
    public static final String PROPERTY_REPETITIONS = "repetitions";
    public static final String PROPERTY_NUMREPETITIONS = "numRepetitions";
    public static final String PROPERTY_NUMBEROFREPETITIONS = "numberOfRepetitions";
    public static final String PROPERTY_MONDAY = "monday";
    public static final String PROPERTY_TUESDAY = "tuesday";
    public static final String PROPERTY_WEDNESDAY = "wednesday";
    public static final String PROPERTY_THURSDAY = "thursday";
    public static final String PROPERTY_FRIDAY = "friday";
    public static final String PROPERTY_SATURDAY = "saturday";
    public static final String PROPERTY_SUNDAY = "sunday";
    public static final String PROPERTY_MONTHLYOPTION = "monthlyOption";
    public static final String PROPERTY_DAYINMONTH = "dayInMonth";
    public static final String PROPERTY_DAYOFTHEWEEK = "dayOfTheWeek";
    public static final String PROPERTY_FINISHES = "finishes";
    public static final String PROPERTY_FINISHTIME = "finishTime";
    public static final String PROPERTY_FINISHDATE = "finishDate";
    public static final String PROPERTY_DAILYOPTION = "dailyOption";
    public static final String PROPERTY_CRONEXPRESSION = "cronExpression";
    public static final String PROPERTY_PROCESSSET = "processSet";
    public static final String PROPERTY_SCHEDULEPROCESS = "scheduleProcess";
    public static final String PROPERTY_RESCHEDULEPROCESS = "rescheduleProcess";
    public static final String PROPERTY_UNSCHEDULEPROCESS = "unscheduleProcess";
    public static final String PROPERTY_PARAMS = "params";
    public static final String PROPERTY_PROCESSEXECUTIONLIST = "processExecutionList";
    public static final String PROPERTY_PROCESSRUNLIST = "processRunList";

    public ProcessRequest() {
        setDefaultValue(PROPERTY_ACTIVE, true);
        setDefaultValue(PROPERTY_CREATIONDATE, new Date());
        setDefaultValue(PROPERTY_UPDATED, new Date());
        setDefaultValue(PROPERTY_SECURITYBASEDONROLE, true);
        setDefaultValue(PROPERTY_CHANNEL, "Process Scheduler");
        setDefaultValue(PROPERTY_TIMING, "I");
        setDefaultValue(PROPERTY_FREQUENCY, "4");
        setDefaultValue(PROPERTY_DAILYINTERVAL, (long) 1);
        setDefaultValue(PROPERTY_MONDAY, false);
        setDefaultValue(PROPERTY_TUESDAY, false);
        setDefaultValue(PROPERTY_WEDNESDAY, false);
        setDefaultValue(PROPERTY_THURSDAY, false);
        setDefaultValue(PROPERTY_FRIDAY, false);
        setDefaultValue(PROPERTY_SATURDAY, false);
        setDefaultValue(PROPERTY_SUNDAY, false);
        setDefaultValue(PROPERTY_MONTHLYOPTION, "S");
        setDefaultValue(PROPERTY_FINISHES, false);
        setDefaultValue(PROPERTY_DAILYOPTION, "N");
        setDefaultValue(PROPERTY_SCHEDULEPROCESS, false);
        setDefaultValue(PROPERTY_RESCHEDULEPROCESS, false);
        setDefaultValue(PROPERTY_UNSCHEDULEPROCESS, false);
        setDefaultValue(PROPERTY_PROCESSEXECUTIONLIST, new ArrayList<Object>());
        setDefaultValue(PROPERTY_PROCESSRUNLIST, new ArrayList<Object>());
    }

    @Override
    public String getEntityName() {
        return ENTITY_NAME;
    }

    public String getId() {
        return (String) get(PROPERTY_ID);
    }

    public void setId(String id) {
        set(PROPERTY_ID, id);
    }

    public Client getClient() {
        return (Client) get(PROPERTY_CLIENT);
    }

    public void setClient(Client client) {
        set(PROPERTY_CLIENT, client);
    }

    public Organization getOrganization() {
        return (Organization) get(PROPERTY_ORGANIZATION);
    }

    public void setOrganization(Organization organization) {
        set(PROPERTY_ORGANIZATION, organization);
    }

    public Boolean isActive() {
        return (Boolean) get(PROPERTY_ACTIVE);
    }

    public void setActive(Boolean active) {
        set(PROPERTY_ACTIVE, active);
    }

    public Date getCreationDate() {
        return (Date) get(PROPERTY_CREATIONDATE);
    }

    public void setCreationDate(Date creationDate) {
        set(PROPERTY_CREATIONDATE, creationDate);
    }

    public User getCreatedBy() {
        return (User) get(PROPERTY_CREATEDBY);
    }

    public void setCreatedBy(User createdBy) {
        set(PROPERTY_CREATEDBY, createdBy);
    }

    public Date getUpdated() {
        return (Date) get(PROPERTY_UPDATED);
    }

    public void setUpdated(Date updated) {
        set(PROPERTY_UPDATED, updated);
    }

    public User getUpdatedBy() {
        return (User) get(PROPERTY_UPDATEDBY);
    }

    public void setUpdatedBy(User updatedBy) {
        set(PROPERTY_UPDATEDBY, updatedBy);
    }

    public Process getProcess() {
        return (Process) get(PROPERTY_PROCESS);
    }

    public void setProcess(Process process) {
        set(PROPERTY_PROCESS, process);
    }

    public String getDescription() {
        return (String) get(PROPERTY_DESCRIPTION);
    }

    public void setDescription(String description) {
        set(PROPERTY_DESCRIPTION, description);
    }

    public User getUserContact() {
        return (User) get(PROPERTY_USERCONTACT);
    }

    public void setUserContact(User userContact) {
        set(PROPERTY_USERCONTACT, userContact);
    }

    public Boolean isSecurityBasedOnRole() {
        return (Boolean) get(PROPERTY_SECURITYBASEDONROLE);
    }

    public void setSecurityBasedOnRole(Boolean securityBasedOnRole) {
        set(PROPERTY_SECURITYBASEDONROLE, securityBasedOnRole);
    }

    public String getOpenbravoContext() {
        return (String) get(PROPERTY_OPENBRAVOCONTEXT);
    }

    public void setOpenbravoContext(String openbravoContext) {
        set(PROPERTY_OPENBRAVOCONTEXT, openbravoContext);
    }

    public String getStatus() {
        return (String) get(PROPERTY_STATUS);
    }

    public void setStatus(String status) {
        set(PROPERTY_STATUS, status);
    }

    public Date getNextExecution() {
        return (Date) get(PROPERTY_NEXTEXECUTION);
    }

    public void setNextExecution(Date nextExecution) {
        set(PROPERTY_NEXTEXECUTION, nextExecution);
    }

    public Date getPreviousExecution() {
        return (Date) get(PROPERTY_PREVIOUSEXECUTION);
    }

    public void setPreviousExecution(Date previousExecution) {
        set(PROPERTY_PREVIOUSEXECUTION, previousExecution);
    }

    public Date getFinish() {
        return (Date) get(PROPERTY_FINISH);
    }

    public void setFinish(Date finish) {
        set(PROPERTY_FINISH, finish);
    }

    public String getChannel() {
        return (String) get(PROPERTY_CHANNEL);
    }

    public void setChannel(String channel) {
        set(PROPERTY_CHANNEL, channel);
    }

    public String getTiming() {
        return (String) get(PROPERTY_TIMING);
    }

    public void setTiming(String timing) {
        set(PROPERTY_TIMING, timing);
    }

    public Timestamp getStartTime() {
        return (Timestamp) get(PROPERTY_STARTTIME);
    }

    public void setStartTime(Timestamp startTime) {
        set(PROPERTY_STARTTIME, startTime);
    }

    public Date getStartDate() {
        return (Date) get(PROPERTY_STARTDATE);
    }

    public void setStartDate(Date startDate) {
        set(PROPERTY_STARTDATE, startDate);
    }

    public String getFrequency() {
        return (String) get(PROPERTY_FREQUENCY);
    }

    public void setFrequency(String frequency) {
        set(PROPERTY_FREQUENCY, frequency);
    }

    public Long getIntervalInSeconds() {
        return (Long) get(PROPERTY_INTERVALINSECONDS);
    }

    public void setIntervalInSeconds(Long intervalInSeconds) {
        set(PROPERTY_INTERVALINSECONDS, intervalInSeconds);
    }

    public Long getIntervalInMinutes() {
        return (Long) get(PROPERTY_INTERVALINMINUTES);
    }

    public void setIntervalInMinutes(Long intervalInMinutes) {
        set(PROPERTY_INTERVALINMINUTES, intervalInMinutes);
    }

    public Long getHourlyInterval() {
        return (Long) get(PROPERTY_HOURLYINTERVAL);
    }

    public void setHourlyInterval(Long hourlyInterval) {
        set(PROPERTY_HOURLYINTERVAL, hourlyInterval);
    }

    public Long getDailyInterval() {
        return (Long) get(PROPERTY_DAILYINTERVAL);
    }

    public void setDailyInterval(Long dailyInterval) {
        set(PROPERTY_DAILYINTERVAL, dailyInterval);
    }

    public Long getRepetitions() {
        return (Long) get(PROPERTY_REPETITIONS);
    }

    public void setRepetitions(Long repetitions) {
        set(PROPERTY_REPETITIONS, repetitions);
    }

    public Long getNumRepetitions() {
        return (Long) get(PROPERTY_NUMREPETITIONS);
    }

    public void setNumRepetitions(Long numRepetitions) {
        set(PROPERTY_NUMREPETITIONS, numRepetitions);
    }

    public Long getNumberOfRepetitions() {
        return (Long) get(PROPERTY_NUMBEROFREPETITIONS);
    }

    public void setNumberOfRepetitions(Long numberOfRepetitions) {
        set(PROPERTY_NUMBEROFREPETITIONS, numberOfRepetitions);
    }

    public Boolean isMonday() {
        return (Boolean) get(PROPERTY_MONDAY);
    }

    public void setMonday(Boolean monday) {
        set(PROPERTY_MONDAY, monday);
    }

    public Boolean isTuesday() {
        return (Boolean) get(PROPERTY_TUESDAY);
    }

    public void setTuesday(Boolean tuesday) {
        set(PROPERTY_TUESDAY, tuesday);
    }

    public Boolean isWednesday() {
        return (Boolean) get(PROPERTY_WEDNESDAY);
    }

    public void setWednesday(Boolean wednesday) {
        set(PROPERTY_WEDNESDAY, wednesday);
    }

    public Boolean isThursday() {
        return (Boolean) get(PROPERTY_THURSDAY);
    }

    public void setThursday(Boolean thursday) {
        set(PROPERTY_THURSDAY, thursday);
    }

    public Boolean isFriday() {
        return (Boolean) get(PROPERTY_FRIDAY);
    }

    public void setFriday(Boolean friday) {
        set(PROPERTY_FRIDAY, friday);
    }

    public Boolean isSaturday() {
        return (Boolean) get(PROPERTY_SATURDAY);
    }

    public void setSaturday(Boolean saturday) {
        set(PROPERTY_SATURDAY, saturday);
    }

    public Boolean isSunday() {
        return (Boolean) get(PROPERTY_SUNDAY);
    }

    public void setSunday(Boolean sunday) {
        set(PROPERTY_SUNDAY, sunday);
    }

    public String getMonthlyOption() {
        return (String) get(PROPERTY_MONTHLYOPTION);
    }

    public void setMonthlyOption(String monthlyOption) {
        set(PROPERTY_MONTHLYOPTION, monthlyOption);
    }

    public Long getDayInMonth() {
        return (Long) get(PROPERTY_DAYINMONTH);
    }

    public void setDayInMonth(Long dayInMonth) {
        set(PROPERTY_DAYINMONTH, dayInMonth);
    }

    public String getDayOfTheWeek() {
        return (String) get(PROPERTY_DAYOFTHEWEEK);
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        set(PROPERTY_DAYOFTHEWEEK, dayOfTheWeek);
    }

    public Boolean isFinishes() {
        return (Boolean) get(PROPERTY_FINISHES);
    }

    public void setFinishes(Boolean finishes) {
        set(PROPERTY_FINISHES, finishes);
    }

    public Timestamp getFinishTime() {
        return (Timestamp) get(PROPERTY_FINISHTIME);
    }

    public void setFinishTime(Timestamp finishTime) {
        set(PROPERTY_FINISHTIME, finishTime);
    }

    public Date getFinishDate() {
        return (Date) get(PROPERTY_FINISHDATE);
    }

    public void setFinishDate(Date finishDate) {
        set(PROPERTY_FINISHDATE, finishDate);
    }

    public String getDailyOption() {
        return (String) get(PROPERTY_DAILYOPTION);
    }

    public void setDailyOption(String dailyOption) {
        set(PROPERTY_DAILYOPTION, dailyOption);
    }

    public String getCronExpression() {
        return (String) get(PROPERTY_CRONEXPRESSION);
    }

    public void setCronExpression(String cronExpression) {
        set(PROPERTY_CRONEXPRESSION, cronExpression);
    }

    public String getProcessSet() {
        return (String) get(PROPERTY_PROCESSSET);
    }

    public void setProcessSet(String processSet) {
        set(PROPERTY_PROCESSSET, processSet);
    }

    public Boolean isScheduleProcess() {
        return (Boolean) get(PROPERTY_SCHEDULEPROCESS);
    }

    public void setScheduleProcess(Boolean scheduleProcess) {
        set(PROPERTY_SCHEDULEPROCESS, scheduleProcess);
    }

    public Boolean isRescheduleProcess() {
        return (Boolean) get(PROPERTY_RESCHEDULEPROCESS);
    }

    public void setRescheduleProcess(Boolean rescheduleProcess) {
        set(PROPERTY_RESCHEDULEPROCESS, rescheduleProcess);
    }

    public Boolean isUnscheduleProcess() {
        return (Boolean) get(PROPERTY_UNSCHEDULEPROCESS);
    }

    public void setUnscheduleProcess(Boolean unscheduleProcess) {
        set(PROPERTY_UNSCHEDULEPROCESS, unscheduleProcess);
    }

    public String getParams() {
        return (String) get(PROPERTY_PARAMS);
    }

    public void setParams(String params) {
        set(PROPERTY_PARAMS, params);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessExecution> getProcessExecutionList() {
        return (List<ProcessExecution>) get(PROPERTY_PROCESSEXECUTIONLIST);
    }

    public void setProcessExecutionList(List<ProcessExecution> processExecutionList) {
        set(PROPERTY_PROCESSEXECUTIONLIST, processExecutionList);
    }

    @SuppressWarnings("unchecked")
    public List<ProcessRun> getProcessRunList() {
        return (List<ProcessRun>) get(PROPERTY_PROCESSRUNLIST);
    }

    public void setProcessRunList(List<ProcessRun> processRunList) {
        set(PROPERTY_PROCESSRUNLIST, processRunList);
    }

}
