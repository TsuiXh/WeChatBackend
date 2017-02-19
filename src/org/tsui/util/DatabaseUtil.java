package org.tsui.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * ���ݿ����ӹ�����
 * @author TsuiXh
 */
public class DatabaseUtil {
	
	// MySQL database configuration
	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/wechat_backend?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456";
	
	//C3P0 database connection pool configuration
	private static final int INIT_POOL_SIZE = 3;
	private static final int MAX_POOL_SIZE = 20;
	private static final int MIN_POOL_SIZE = 0;
	private static final int MAX_IDLE_TIME = 60;
	private static final int ACQUIRE_INCREMENT = 3;
	private static final int ACQUIRE_RETRY_ATTEMPS = 15;
	private static final int CHECKOUT_TIMEOUT = 100;
	private static final int IDLE_CONNECTION_TEST_PERIOD = 60;
	private static Connection conn = null;
	private static ComboPooledDataSource c3p0 = null;
	static {
		try {
			//Initialize(configuration) c3p0 database connection pool
			c3p0 = new ComboPooledDataSource();
			c3p0.setDriverClass(DRIVER_CLASS);
			c3p0.setJdbcUrl(URL);
			c3p0.setUser(USERNAME);
			c3p0.setPassword(PASSWORD);
			//���ӹر�ʱĬ�Ͻ�����δ�ύ�Ĳ����ع�
			c3p0.setAutoCommitOnClose(false);
			//��ʼ��ʱ��ȡ�������ӣ�ȡֵ��min��max֮��
			c3p0.setInitialPoolSize(INIT_POOL_SIZE);
			//���ӳ��б��������������
			c3p0.setMaxPoolSize(MAX_POOL_SIZE);
			//���ӳ��б�������С������
			c3p0.setMinPoolSize(MIN_POOL_SIZE);
			//������ʱ�䣬60����δʹ�������ӱ�����
			c3p0.setMaxIdleTime(MAX_IDLE_TIME);
			//�����ӳ��е����Ӻľ���ʱ��c3p0һ��ͬʱ��ȡ��������
			c3p0.setAcquireIncrement(ACQUIRE_INCREMENT);
			//��������ݿ��ȡ������ʧ�ܺ��ظ����ԵĴ���
			c3p0.setAcquireRetryAttempts(ACQUIRE_RETRY_ATTEMPS);
			//�����ӳ�����ʱ�ͻ��˵���getConnection()��ȴ���ȡ�����ӵ�ʱ�䣬��ʱ���׳�SQL Exception
			c3p0.setCheckoutTimeout(CHECKOUT_TIMEOUT);
			//ÿ��TEST_PERIOD����������ӳ��еĿ�������
			c3p0.setIdleConnectionTestPeriod(IDLE_CONNECTION_TEST_PERIOD);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ���ݿ�����
	 * @return	���ݿ�����
	 * @throws SQLException
	 */
	public static Connection getConn() throws SQLException {
		if (conn == null || conn.isClosed()) {
			conn = c3p0.getConnection();
		}
		return conn;
	}
	
}
