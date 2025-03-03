package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Activity;
import fr.seve.entities.Product;
import fr.seve.repository.ActivityRepository;
import fr.seve.service.ActivityService;

@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public List<Activity> findAll() {
		return activityRepository.findAll();
	}

	@Override
	public Activity save(Activity activity) {
		return activityRepository.save(activity);
	}

	@Override
	public Activity findById(Long id) {
		return activityRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Activity> findByAmapSpaceId(Long amapSpaceId) {
        return activityRepository.findByAmapSpaceId(amapSpaceId);
    }

	@Override
	public void deletebyId(Long Id) {
		activityRepository.deleteById(Id);
	}

	@Override
	public List<Activity> findByProducerId(Long id) {
		return activityRepository.findByProducerId(id);
	}
}
