package gruppenarbeit3_bankomatsimulator;

import java.util.LinkedList;
import java.util.List;

public class KontoDAO {

	List<Konto> all = new LinkedList<Konto>();

	public KontoDAO() {
		all.add(new Konto(1000000, 10000.35));
		all.add(new Konto(2000000, 1.00));
		all.add(new Konto(1000001, 98989.15));
		all.add(new Konto(1000002, 100000000.10));
		all.add(new Konto(1000003, 1222.20));
		all.add(new Konto(4000000, 98888.00));
		all.add(new Konto(1999999, 12304.65));
	}

}
