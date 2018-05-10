package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.Client;

@Repository
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * public ClientDAOImpl() {
	 * 
	 * }
	 * 
	 * public ClientDAOImpl(SessionFactory sessionFactory) { this.sessionFactory =
	 * sessionFactory; }
	 */

	@Override
	@Transactional
	public Client getClientById(int id) {
		String hql = "from Client where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Client> listClient = (List<Client>) query.list();

		if (listClient != null && !listClient.isEmpty()) {
			return listClient.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public Client getClientByEmail(String email) {

		String hql = "FROM Client WHERE EMAIL = :emailParam";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setParameter("emailParam", email);

		@SuppressWarnings("unchecked")
		List<Client> listClient = query.list();

		/*
		 * @SuppressWarnings("unchecked") List<Client> listClient = (List<Client>)
		 * query.list();
		 */

		if (listClient != null && !listClient.isEmpty()) {
			return listClient.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public Client getClientByPhone(String phone) {
		String hql = "from Client where phone=" + phone;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Client> listClient = (List<Client>) query.list();

		if (listClient != null && !listClient.isEmpty()) {
			return listClient.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public void saveOrUpdateClient(Client client) {
		sessionFactory.getCurrentSession().saveOrUpdate(client);
	}
}
