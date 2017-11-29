package entidades;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ItemVenda.class)
public abstract class ItemVenda_ {

	public static volatile SingularAttribute<ItemVenda, BigDecimal> precoUnitario;
	public static volatile SingularAttribute<ItemVenda, Venda> venda;
	public static volatile SingularAttribute<ItemVenda, String> produto;
	public static volatile SingularAttribute<ItemVenda, BigDecimal> vlUnitario;
	public static volatile SingularAttribute<ItemVenda, Long> id;
	public static volatile SingularAttribute<ItemVenda, Integer> quantidade;

}

