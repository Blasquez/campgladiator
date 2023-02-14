package com.campgladiator.trainermanagement.database;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

public class CustomIdGenerator extends SequenceStyleGenerator{

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return "trainer-id-" + String.format("%06d", super.generate(session, object));
	}
	
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		super.configure(LongType.INSTANCE, params, serviceRegistry);
	}
	
	/*@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		return "trainer-id-" + String.format("%06d", super.generate(session, object));
	}
	
	@Override
	public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
		NamedBasicTypeImpl<Long> customType = new NamedBasicTypeImpl<>(LongJavaType.INSTANCE, LongVarcharJdbcType.INSTANCE, "long"); 
		super.configure(customType, params, serviceRegistry);
	}*/
}
