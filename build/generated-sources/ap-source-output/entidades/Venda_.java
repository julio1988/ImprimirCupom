package entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Venda.class)
public abstract class Venda_ {

	public static volatile SingularAttribute<Venda, BigDecimal> total;
	public static volatile ListAttribute<Venda, ItemVenda> itens;
	public static volatile SingularAttribute<Venda, Date> data;
	public static volatile SingularAttribute<Venda, String> usuario;
	public static volatile SingularAttribute<Venda, Long> id;
	public static volatile SingularAttribute<Venda, Festa> festa;

}

