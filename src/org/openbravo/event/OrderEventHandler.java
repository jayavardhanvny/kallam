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
 * All portions are Copyright (C) 2013 Openbravo SLU 
 * All Rights Reserved. 
 * Contributor(s):  ______________________________________.
 ************************************************************************
 */
package org.openbravo.event;

import java.util.Date;

import javax.enterprise.event.Observes;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.openbravo.base.model.Entity;
import org.openbravo.base.model.ModelProvider;
import org.openbravo.base.model.Property;
import org.openbravo.client.kernel.event.EntityPersistenceEventObserver;
import org.openbravo.client.kernel.event.EntityUpdateEvent;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.order.Order;
import org.openbravo.model.common.order.OrderLine;

public class OrderEventHandler extends EntityPersistenceEventObserver {

  private static Entity[] entities = { ModelProvider.getInstance().getEntity(Order.ENTITY_NAME) };
  protected Logger logger = Logger.getLogger(this.getClass());

  @Override
  protected Entity[] getObservedEntities() {
    return entities;
  }

  public void onUpdate(@Observes
  EntityUpdateEvent event) {
    if (!isValidEvent(event)) {
      return;
    }
    final Entity orderEntity = ModelProvider.getInstance().getEntity(Order.ENTITY_NAME);
    final Property orderDateProperty = orderEntity.getProperty(Order.PROPERTY_ORDERDATE);
    final Property scheduledDateProperty = orderEntity
        .getProperty(Order.PROPERTY_SCHEDULEDDELIVERYDATE);
    String orderId = (String) event.getTargetInstance().getId();
    Date newOrderDate = (Date) event.getCurrentState(orderDateProperty);
    Date oldOrderDate = (Date) event.getPreviousState(orderDateProperty);
    Date newScheduledDate = (Date) event.getCurrentState(scheduledDateProperty);
    Date oldScheduledDate = (Date) event.getPreviousState(scheduledDateProperty);
    OBCriteria<OrderLine> orderLineCriteria = OBDal.getInstance().createCriteria(OrderLine.class);
    orderLineCriteria.add(Restrictions.eq(OrderLine.PROPERTY_SALESORDER,
        OBDal.getInstance().get(Order.class, orderId)));
    if (orderLineCriteria.count() > 0) {
      if (newOrderDate != oldOrderDate) {
        for (OrderLine lines : orderLineCriteria.list()) {
          lines.setOrderDate(newOrderDate);
        }
      }
      if (newScheduledDate != oldScheduledDate) {
        for (OrderLine lines : orderLineCriteria.list()) {
          lines.setScheduledDeliveryDate(newScheduledDate);
        }
      }
    }
  }
}
