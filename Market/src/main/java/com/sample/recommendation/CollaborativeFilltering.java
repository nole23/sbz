package com.sample.recommendation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import com.sample.model.Article;
import com.sample.model.OrderArticle;
import com.sample.model.User;

public class CollaborativeFilltering {

	private User user;
	private List<User> users;

	public CollaborativeFilltering() {

	}

	public CollaborativeFilltering(User user, List<User> users) {
		super();
		this.user = user;
		this.users = users;
	}

	/**
	 * Find articles which user would like to buy
	 * 
	 * @return
	 */
	public List<Article> recommendation() {

		// list of userOrders
		HashMap<Article, Long> userOrders = getOrders(user);

		HashMap<Article, Long> compareOrders;
		double dotProduct;
		double lengthProd;
		double cos;
		List<Article> diff;
		double maxCosine = -1;

		List<Article> resultList = new ArrayList<Article>();


		for (User us : users) {
			if (us.getId() != user.getId()) {
				// get order for other user
				compareOrders = getOrders(us);
				dotProduct = dotProduct(userOrders, compareOrders);
				lengthProd = lengthVectorProduct(userOrders, compareOrders);
				cos = dotProduct / lengthProd;

				diff = getDiff(compareOrders, userOrders);
				if (cos > maxCosine && !diff.isEmpty()){
					resultList = diff;
					maxCosine = cos;
				}
					
				
			}
		}

		return resultList;
	}

	/**
	 * get HashMap with all user articles
	 * 
	 * @param user
	 * @return
	 */
	private HashMap<Article, Long> getOrders(User user) {

		HashMap<Article, Long> userOrders = new HashMap<Article, Long>();
		Set<OrderArticle> uOrder = user.getOrders();

		for (OrderArticle a : uOrder) {
			Article ar = a.getArticle();
			// if value allready in hashmap, add the amount you bought
			if (userOrders.containsKey(ar)) {
				userOrders.put(ar, userOrders.get(ar) + a.getAmount());
			} else

				userOrders.put(ar, a.getAmount());

		}

		return userOrders;

	}

	/**
	 * dot product of two HashMaps contaning Articles
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private double dotProduct(HashMap<Article, Long> a, HashMap<Article, Long> b) {
		double sum = 0;
		for (HashMap.Entry<Article, Long> entry : a.entrySet()) {
			if (b.containsKey(entry.getKey())) {
				sum += entry.getValue() * b.get(entry.getKey());
			}
		}

		return sum;
	}

	/**
	 * Calculate the length product of each vector
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private double lengthVectorProduct(HashMap<Article, Long> a, HashMap<Article, Long> b) {
		double sumA = 0;
		double sumB = 0;

		for (HashMap.Entry<Article, Long> entry : a.entrySet()) {
			sumA += entry.getValue() * entry.getValue();
		}
		for (HashMap.Entry<Article, Long> entry : b.entrySet()) {
			sumB += entry.getValue() * entry.getValue();
		}

		return Math.sqrt(sumA * sumB);
	}

	/**
	 * return all articles from a which are not included into b
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private List<Article> getDiff(HashMap<Article, Long> a, HashMap<Article, Long> b) {
		List<Article> diff = new ArrayList<Article>();

		for (HashMap.Entry<Article, Long> entry : a.entrySet()) {
			if (!b.containsKey(entry.getKey())) {
				// if b list doesnt have this article
				diff.add(entry.getKey());
			}
		}

		return diff;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

}
