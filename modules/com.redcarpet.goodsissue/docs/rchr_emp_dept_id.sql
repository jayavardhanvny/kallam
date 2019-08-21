alter table m_requisition add column rchr_emp_dept_id character varying(32)


alter table m_requisition add constraint m_requis_dept 
 FOREIGN KEY (rchr_emp_dept_id)
      REFERENCES rchr_emp_dept (rchr_emp_dept_id) 