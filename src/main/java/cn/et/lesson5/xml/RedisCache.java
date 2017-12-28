package cn.et.lesson5.xml;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;

import redis.clients.jedis.Jedis;

public class RedisCache implements Cache {
	/**
	 * ����redis����
	 */
	Jedis jedis=new Jedis("localhost",6379);
	
	/**
	 * �����id
	 */
	private String cacheId;
	public RedisCache(String cacheId){
		this.cacheId=cacheId;
	}
	@Override
	public void clear() {
		//jedis.flushDB();
	}

	@Override
	public String getId() {
		return cacheId;
	}
	/**
	 * mybatis�Զ�����getObject����Ƿ񻺴��д���
	 */
	@Override
	public Object getObject(Object key) {
		try {
			byte[] bt=jedis.get(RedisTest.objectToByteArray(key));
			if(bt==null){
				return null;
			}
			return RedisTest.byteArrayToObject(bt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		return new ReentrantReadWriteLock();
	}

	@Override
	public int getSize() {
		return 1;
	}
	/**
	 * mybatis��ȡ����ʱ �����ݿ��ж�ȡ������ ͨ��
	 *   putObject���õ�������
	 */
	@Override
	public void putObject(Object key, Object value) {
		//д��redis
		try {
			jedis.set(RedisTest.objectToByteArray(key), RedisTest.objectToByteArray(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * mybatis������� �Զ��ж��ڴ�Ĵ�С �����Ƿ�ɾ��ĳЩ���� ��Զ������
	 * @param key
	 * @return
	 */
	@Override
	public Object removeObject(Object key) {
		Object obj=getObject(key);
		try {
			jedis.del(RedisTest.objectToByteArray(key));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return obj;
	}
}
