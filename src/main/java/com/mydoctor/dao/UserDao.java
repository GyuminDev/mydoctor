package com.mydoctor.dao;

import com.mydoctor.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	public User getUserById(String id, String password) {
		System.out.println("id =" + id);

		Session session = sessionFactory.getCurrentSession();
		// Query query = session.createQuery("select token from User as us where
		// as.username=:username");
		// query.setParameter("username", id);

		User user = (User) session.get(User.class, id);

		if (user != null) { // id�� ������ �����Ӱ�
			if (user.getPassword().equals(password)) { // ��й�ȣ���� ������
				System.out.println(user.getToken());
				user.setIslogin(true);
				session = sessionFactory.getCurrentSession();
				session.saveOrUpdate(user); // islogin�� true�� �ٲ�

				return user;
			} else {
				return new User();
			}
		} else
			return new User();
	}

	public void setIsLogin(User user) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate("User", user);
		session.clear();
	}
}
