	public List<$entityName$> findByContaining$upperReferenceField$(
			String $fieldName$Substring) {
			Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM $entityName$ p WHERE LOWER(p.$fieldName$) LIKE CONCAT(CONCAT('%',LOWER(:substring)),'%')");
			query.setParameter("substring", $fieldName$Substring);
			return query.setMaxResults(10).getResultList();
	}