package output.csvast;

import java.util.Stack;

import astnodes.ASTNode;
import astwalking.ASTNodeVisitor;

public class ASTToCSVConverter extends ASTNodeVisitor
{
	private final String SEPARATOR = "\t";
	Stack<Object> levelStack = new Stack<Object>();
	Object o = new Object();
	
	StringBuilder builder = new StringBuilder();
	
	public void reset()
	{
		builder = new StringBuilder();
	}

	public String getResult()
	{
		return builder.toString();
	} 
	
	public void visit(ASTNode node)
	{
		int level = levelStack.size();
		defaultOut(node, level);		
		levelStack.push(o);
		visitChildren(node);
		levelStack.pop();
	}
	
	private void defaultOut(ASTNode node, int level)
	{    	
		 if(node == null)
			 return;
		 
		 String nodeTypeName = node.getClass().getSimpleName();
		 String output = nodeTypeName + SEPARATOR +
				 node.getLocationString() + SEPARATOR + level;

		 String codeStr = null;
		 
		 if((node.getChildCount() == 0) && !NodeBlacklist.isBlackListed(nodeTypeName))
			 codeStr = node.getCodeStr();
		 
		 if(codeStr != null)
			 output += SEPARATOR + escapeCodeStr(codeStr);
	     else
	    	 output += SEPARATOR + "";
		 builder.append(output + "\n");
	 }
	 
	 private String escapeCodeStr(String codeStr)
	 {
		 String retval = codeStr;
		 retval = retval.replace("\n", "\\n");
		 retval = retval.replace("\t", "\\t");
		 return retval;
	 }
	 
}