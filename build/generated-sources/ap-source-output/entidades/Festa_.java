package entidades;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Festa.class)
public abstract class Festa_ {

	public static volatile SingularAttribute<Festa, Date> data;
	public static volatile SingularAttribute<Festa, BigDecimal> valorInicial;
	public static volatile SingularAttribute<Festa, String> nome;
	public static volatile SingularAttribute<Festa, Long> id;

}

