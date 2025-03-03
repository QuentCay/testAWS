package fr.seve.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.seve.entities.Function;
import fr.seve.entities.Subscription;
import fr.seve.repository.FunctionRepository;
import fr.seve.repository.SubscriptionRepository;
import fr.seve.service.SubscriptionService;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
	
	@Autowired 
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired 
	private FunctionRepository functionRepository;

	@Override
	public List<Subscription> findAll() {
		return subscriptionRepository.findAll();
	}

	@Override
	public Subscription findById(Long id) {
	
		return subscriptionRepository.findById(id).orElse(null);
	}

	@Override
	public Subscription save(Subscription subscription) {
		
		return subscriptionRepository.save(subscription);
	}

	@Override
	public List<Subscription> initialize(List<Subscription> subscriptions) {
		
		Subscription essential = new Subscription();
		Subscription standard = new Subscription();
		Subscription premium = new Subscription();
		
		Function basket = new Function();
		Function product = new Function();
		Function activity =  new Function();
		
		basket.setId(1l);
		basket.setName("Panier");
		product.setId(2l);
		product.setName("Produits");
		activity.setId(3l);
		activity.setName("Ateliers");
		
		functionRepository.save(basket);
		System.out.println("Basket créé");
		functionRepository.save(product);
		System.out.println("Product créé");
		functionRepository.save(activity);
		System.out.println("activity créé");
		
		List<Long> essentialFunctionsIds = new ArrayList<Long>();
		List<Long> standardFunctionsIds = new ArrayList<Long>();
		List<Long> premiumFunctionsIds = new ArrayList<Long>();
		
		essentialFunctionsIds.add(basket.getId());
		
		
		standardFunctionsIds.add(basket.getId());
		standardFunctionsIds.add(product.getId());
		
		premiumFunctionsIds.add(basket.getId());
		premiumFunctionsIds.add(product.getId());
		premiumFunctionsIds.add(activity.getId());
		
		essential.setName("Essentiel");
		essential.setDescription("Gestion des Paniers");
		essential.setPrice("9.99€/mois");
		essential.setSubscribeDate(LocalDateTime.now().toString());
		essential.getFunctionsList().addAll(functionRepository.findAllById(essentialFunctionsIds));
		
		standard.setName("Standard");
		standard.setDescription("Gestion des Paniers et boutique en ligne");
		standard.setPrice("19.99€/mois");
		standard.setSubscribeDate(LocalDateTime.now().toString());
		standard.getFunctionsList().addAll(functionRepository.findAllById(standardFunctionsIds));
		
		premium.setName("Premium");
		premium.setDescription("Gestion des Paniers, boutique en ligne et ateliers");
		premium.setPrice("29.99€/mois");
		premium.setSubscribeDate(LocalDateTime.now().toString());
		premium.getFunctionsList().addAll(functionRepository.findAllById(premiumFunctionsIds));
		
		subscriptionRepository.save(essential);
		System.out.println("Initialisation essential faite");
		subscriptionRepository.save(standard);
		System.out.println("Initialisation standard faite");
		subscriptionRepository.save(premium);
		System.out.println("Initialisation premium faite");
		return null;
		
	}

//	@Override
//	public Subscription linkFunctionsListToSubscription(Long subscriptionId, List<Long> functionIds) {
//		
//		Subscription subscription = subscriptionRepository.findById(subscriptionId).orElseThrow();
//		
//		List<Function> functionsList = functionRepository.findAllById(functionIds);
//		
//		subscription.getFunctionsList().addAll(functionsList);
//		
//		return subscriptionRepository.save(subscription);
//	}

	

}
