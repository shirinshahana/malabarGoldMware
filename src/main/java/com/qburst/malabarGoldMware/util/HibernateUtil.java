package com.qburst.malabarGoldMware.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;

/**
 * @author Brett Meyer
 */

public class HibernateUtil {

	private SessionFactory sf;

	public Session getSession() {
		return getSessionFactory().openSession();
	}

	private SessionFactory getSessionFactory() {
		if ( sf == null ) {
			Bundle thisBundle = FrameworkUtil.getBundle( HibernateUtil.class );
			// Could get this by wiring up OsgiTestBundleActivator as well.
			System.out.println(thisBundle);


			BundleContext context = thisBundle.getBundleContext();			System.out.println(context);

			ServiceReference sr = context.getServiceReference(  SessionFactory.class.getName() );
			System.out.println(sr);

			sf = context.getService( sr );
			System.out.println(sf);

		}
		return sf;
	}
}