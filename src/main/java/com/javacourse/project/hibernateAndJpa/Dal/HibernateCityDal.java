package com.javacourse.project.hibernateAndJpa.Dal;

import java.util.List;


import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.javacourse.project.hibernateAndJpa.Entities.City;

import jakarta.persistence.EntityManager;



@Repository
public class HibernateCityDal implements ICityDal {
	
	EntityManager entityManager;
	
	@Autowired
	
	public HibernateCityDal(EntityManager entityManager) {
	
		this.entityManager = entityManager;
	}

    //AOP -> Aspect Oriented programming
	@Override
	@Transactional
	public List<City> getAll() {
		Session session =entityManager.unwrap(Session.class);
		List<City> cities = session.createQuery("from City",City.class).getResultList();
		return cities;
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void add(City city) {
		Session session =entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void update(City city) {
		Session session =entityManager.unwrap(Session.class);
		session.saveOrUpdate(city);
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void delete(City city) {
		Session session =entityManager.unwrap(Session.class);
		City cityToDelete=session.get(City.class,city.getId());
		session.delete(cityToDelete);
		
	}

	@Override
	public City getById(int id) {
		Session session =entityManager.unwrap(Session.class);
		City city=session.get(City.class,id);
		return city;
	}
}
