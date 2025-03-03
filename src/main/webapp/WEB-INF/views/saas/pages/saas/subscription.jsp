<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<h1 class="text-center">Une solution flexible adaptée à vos besoins
</h1>
<section>
	<div class="presentation-container">

		<div>
			<div>
				<p>Que vous soyez une petite AMAP ou une organisation établie,
					nos forfaits vous permettent de gérer efficacement vos adhésions,
					commandes, et paiements tout en valorisant vos valeurs. Choisissez
					les services qui répondent à vos besoins !</p>
				<p class="font-weight-bold pt-3">Notre mission ?</p>
				<p>Simplifier la gestion de votre AMAP pour que vous puissiez
					vous concentrer sur l'essentiel : créer du lien entre les
					producteurs et les consommateurs. Grâce à notre plateforme
					intuitive, vous gérez facilement vos adhésions, commandes,
					ateliers, et bien plus encore. Nous vous accompagnons dans la
					valorisation de vos valeurs et le renforcement de votre communauté,
					tout en facilitant la transparence et la traçabilité des échanges.
				</p>
			</div>
		</div>
				<div class="pres-img">
			<img class="shadow text-center"
				src="${pageContext.request.contextPath}/resources/images/saas/configdesign.png"
				alt="" />
		</div>
	</div>
</section>

<section>
	<h2>Nos forfaits</h2>
	<div class="packages">
		<!-- Forfait Essentiel -->

		<div class="package-card shadow-o">
			<p class="package-name bg-o text-center py-4">ESSENTIEL</p>
			<div class="package-card-content">
				<ul>
					<li>Gestion des paniers maraîchers : Organisez simplement la
						distribution de vos paniers maraîchers</li>
					<li>Gestion des adhésions et des cotisations</li>
					<li>Répertoire des producteurs</li>
					<li>Traitement des commandes</li>
				</ul>
				<div>
					<div>
						<p class="pb-1">Idéal pour : Les AMAP cherchant une gestion
							simple et efficace de leurs paniers maraîchers.</p>
						<p class="font-weight-bold">Prix : 9,99 € / mois</p>
					</div>
					<div class="d-flex justify-content-center package-btn">
						<button class="btn btn-o btn-package-card" onclick="window.location.href='${pageContext.request.contextPath}/saasuser/subscription-essential'">Commencez avec
							Essentiel</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Forfait Standard -->

		<div class="package-card shadow-b">
			<p class="package-name bg-b text-center py-4">STANDARD</p>
			<div class="package-card-content">
				<ul>
					<li class="pb-3">Tout le contenu du forfait Essentiel</li>
					<li>Boutique e-commerce : Ajoutez une boutique en ligne pour
						proposer des produits supplémentaires à vos adhérents.</li>
				</ul>
				<div>
					<div>
						<p class="pb-1">Idéal pour : Les AMAP souhaitant développer
							leur offre avec une boutique en ligne.</p>
						<p class="font-weight-bold">Prix : 19,99 € / mois</p>
					</div>

					<div class="d-flex justify-content-center package-btn">
						<button class="btn btn-b btn-package-card" onclick="window.location.href='${pageContext.request.contextPath}/saasuser/subscription-standard'">Commencez avec
							Standard</button>
					</div>
				</div>
			</div>
		</div>

		<!-- Forfait Premium -->

		<div class="package-card shadow-g">
			<p class="package-name bg-g text-center py-4">PREMIUM</p>
			<div class="package-card-content">
				<ul>
					<li class="pb-3">Tout le contenu des forfaits Essentiel et
						Standard</li>
					<li>Gestion des ateliers : Organisez et suivez des ateliers ou
						événements pour vos adhérents.</li>
				</ul>
				<div>
					<div>
						<p class="pb-1">Idéal pour : Les AMAP souhaitant valoriser
							leur communauté à travers des ateliers et événements réguliers.</p>
						<p class="font-weight-bold">Prix : 29,99 € / mois</p>
					</div>
					<div class="d-flex justify-content-center package-btn">
						<button class="btn btn-g btn-package-card" onclick="window.location.href='${pageContext.request.contextPath}/saasuser/subscription-premium'">Commencez avec
							Premium</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<section id="features-table-section">
	<h2>Tableau comparatif</h2>

	<div>
		<table id="features-table" class="table mb-5">
			<thead>
				<tr>
					<th style="background-color: #cfd8dc">Fonctionnalité</th>
					<th class="text-center" style="background-color: #d9a89c">
						Essentiel</th>
					<th class="text-center" style="background-color: #b3d9e2">
						Standard</th>
					<th class="text-center" style="background-color: #a8b5a2">
						Premium</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>Gestion des paniers maraîchers</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
				</tr>
				<tr>
					<td>Gestion des adhésions et des cotisations</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
				</tr>
				<tr>
					<td>Répertoire des producteurs</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
				</tr>
				<tr>
					<td>Traitement des commandes</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
				</tr>
				<tr>
					<td>Boutique e-commerce</td>
					<td class="text-center"><span class="text-danger">&#10007;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
				</tr>
				<tr>
					<td>Gestion des ateliers</td>
					<td class="text-center"><span class="text-danger">&#10007;</span>
					</td>
					<td class="text-center"><span class="text-danger">&#10007;</span>
					</td>
					<td class="text-center"><span class="text-success">&#10003;</span>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</section>
