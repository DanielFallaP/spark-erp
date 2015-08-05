@Override
public List<$entityName$> findAllActiveBy$upperReferenceField$(EmbeddedField... fields) throws Exception {
	Query query = emFactory.getObject().createEntityManager().createQuery("SELECT p FROM $entityName$ p WHERE p.active = true");
	return query.getResultList();
}

	