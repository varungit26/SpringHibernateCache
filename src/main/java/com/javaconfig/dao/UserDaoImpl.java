package com.javaconfig.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javaconfig.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger log = Logger.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	private static void printStats(Statistics stats, int i) {
		System.out.println("*****" + i + "**********");
		System.out.println("Fetch Count = " + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count" + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count" + stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count" + stats.getSecondLevelCachePutCount());

	}

	private static void printData(User user, Statistics stats, int count) {
		System.out.println(count + ":: Name=" + user.getUsername());
		printStats(stats, count);
	}

	@Transactional
	public List<User> list() {
		log.info("User List.");
		List<User> userList = (List<User>) sessionFactory.getCurrentSession()
				.createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		log.info("test : " + userList.toString());
		return userList;
	}

	@Transactional
	public User get(int id) {
		log.info("Get User with userId " + id);
		List<User> userList = (List<User>) sessionFactory.getCurrentSession()
				.createQuery("from User where id = " + id).list();

		if (userList != null && !userList.isEmpty())
			return userList.get(0);
		log.info(userList.toString());
		return null;
	}

	@Transactional
	public void saveOrUpdate(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
	}

	@Transactional
	public void delete(int id) {

		User userToDelete = new User();

		userToDelete.setId(id);
		sessionFactory.getCurrentSession().delete(userToDelete);
	}

	public void test() {

//		Statistics stats = sessionFactory.getStatistics();
//		System.out.println("Statistics enabled = " + stats.isStatisticsEnabled());
//		stats.setStatisticsEnabled(true);
//		System.out.println("Statistics enabled = " + stats.isStatisticsEnabled());
//
//		printStats(stats, 0);
//
//		Session session = sessionFactory.openSession();
//		Session otherSession = sessionFactory.openSession();
//
//		Transaction transaction = session.beginTransaction();
//		Transaction otherTransaction = otherSession.beginTransaction();
//
//		User user = (User) session.load(User.class, 1L);
//		printData(user, stats, 1);
//
//		user = (User) session.load(User.class, 1L);
//		printData(user, stats, 2);
//
//		// clearing first level cache, so second level cache can be used.
//		session.evict(user);
//		user = (User) session.load(User.class, 1L);
//		printData(user, stats, 3);
//
//		user = (User) session.load(User.class, 1L);
//		printData(user, stats, 4);
//
//		user = (User) otherSession.load(User.class, 1L);
//		printData(user, stats, 5);
//
//		transaction.commit();
//		otherTransaction.commit();

	}

}
