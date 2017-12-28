package cn.et.lesson5.xml;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import redis.clients.jedis.Jedis;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class RedisTest {

	/**
	 * 序列化
	 * @param object
	 * @return
	 * @throws IOException
	 */
	public static byte[] objectToByteArray(Object object) throws IOException{
		ByteOutputStream boStream=new ByteOutputStream();
		ObjectOutputStream oosStream=new ObjectOutputStream(boStream);
		oosStream.writeObject(object);
		return boStream.getBytes();
	}
	
	/**
	 * 反序列化
	 * @param by
	 * @return
	 * @throws Exception
	 */
	public static Object byteArrayToObject(byte[] by) throws  Exception{
		ByteInputStream biStream=new ByteInputStream(by,by.length);
		biStream.read(by);
		ObjectInputStream oisStream=new ObjectInputStream(biStream);
		return oisStream.readObject();
	}
	
	
	public static void main(String[] args) {
		Jedis jedis=new Jedis("localhost",6379);
		
		//String
		jedis.set("name", "ssg");
		jedis.get("name");
		
		//Hash
		jedis.hset("user","age", "19");
		jedis.hget("user", "age");
		
		//List
		jedis.lpush("class", "4","6");
		jedis.rpush("class", "123");
		jedis.lrange("class", 0, 10);
		
		//Set(不支持重复元素)
		jedis.sadd("phone", "pingguo");
		jedis.smembers("phone");
		
		//sorted set
		jedis.zadd("grade", 66, "aa");
		jedis.zrange("grade", 0, 10);
	}

}
