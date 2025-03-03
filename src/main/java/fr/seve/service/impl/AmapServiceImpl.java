package fr.seve.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.AMAP;
import fr.seve.repository.AmapRepository;
import fr.seve.service.AmapService;

@Service
public class AmapServiceImpl implements AmapService{
	
	@Autowired
	private AmapRepository amapRepository;

	@Override
	public List<AMAP> findAll() {
		
		return amapRepository.findAll();
	}

	@Override
	public AMAP save(AMAP amap) {
		
		return amapRepository.save(amap);
	}

	@Override
	public AMAP findById(Long id) {
		
		return amapRepository.findById(id).orElse(null);
	}
	
	@Override
    public AMAP findBySlug(String slug) {
        return amapRepository.findBySlug(slug);
    }
    
	@Override
	public void deleteById(Long Id) {

		amapRepository.deleteById(Id);
		
	}

	@Override
	public Long findSubscriptionIdBySlug(String slug) {
		AMAP amap = amapRepository.findBySlug(slug);
		return amap.getAmapSpace().getSubscription().getId();
	}

}
