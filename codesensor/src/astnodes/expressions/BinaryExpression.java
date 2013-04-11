package astnodes.expressions;

import astnodes.ASTNode;


public class BinaryExpression extends Expression
{
	Expression subExpressions [] = new Expression[2];
		
	
	public Expression getLeft() { return subExpressions[0]; }
	public Expression getRight() { return subExpressions[1];}
	public void setLeft(Expression aLeft) { subExpressions[0] = aLeft; }
	public void setRight(Expression aRight) {subExpressions[1] = aRight; }
	
	@Override
	public void addChild(ASTNode item)
	{	
		Expression expression = (Expression) item;
		if(getLeft() == null)
			setLeft(expression);
		else if(getRight() == null)
			setRight(expression);
		else
			throw new RuntimeException("Error: attempting to add third child to binary expression");
	}
	
	@Override
	public int getChildCount()
	{
		int childCount = 0;
		if(getLeft() != null) childCount++;
		if(getRight() != null) childCount++;
		return childCount;
	}
	@Override
	public ASTNode getChild(int i)
	{
		return subExpressions[i];
	}
	
}