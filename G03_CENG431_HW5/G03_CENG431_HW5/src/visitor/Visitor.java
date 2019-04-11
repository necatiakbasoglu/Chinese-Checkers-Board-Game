package visitor;

import boardElements.Hole;

public class Visitor implements IVisitor {

	@Override
	public boolean visit(Hole hole) {
		//hole de birsey varsa true
		return hole.getHoleElement()!=null;
	}

}
