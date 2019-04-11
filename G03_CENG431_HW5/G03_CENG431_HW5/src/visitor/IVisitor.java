package visitor;

import boardElements.Hole;

public interface IVisitor {
	public boolean visit(Hole hole); 
}
