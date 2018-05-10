package com.tajv.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tajv.model.File;

@Repository
public class FileDaoImpl implements FileDao {
	@Autowired
	private SessionFactory sessionFactory;

	public FileDaoImpl() {
	}

	public FileDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	@Transactional
	public void addFile(File uploadFile) {
		sessionFactory.getCurrentSession().save(uploadFile);
	}

	@Override
	@Transactional
	public void deleteFile(int fileId) {
		File fileToDelete = new File();
		fileToDelete.setFileId(fileId);
		sessionFactory.getCurrentSession().delete(fileToDelete);
	}

	@Override
	@Transactional
	public File getFileById(int id) {
		String hql = "from File where fileId=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<File> listFile = (List<File>) query.list();

		if (listFile != null && !listFile.isEmpty()) {
			return listFile.get(0);
		}

		return null;
	}

	@Override
	@Transactional
	public List<File> getFileListByClientId(int clientId) {
		String hql = "from File where clientId=" + clientId;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<File> listFile = (List<File>) query.list();

		if (listFile != null && !listFile.isEmpty()) {
			return listFile;
		}

		return null;
	}
}