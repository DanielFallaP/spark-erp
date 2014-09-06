package co.com.cybersoft.generator.code.model;

public class JavaArithmeticExpression {

	public static String getJavaArithmeticExpression(ArithmeticExpression expression){
		if (expression.getLeftOperand()==null && expression.getRightOperand()==null){
			return "("+getJavaArithmeticExpression(expression.getLhs())+" "+expression.getOperation()+" "+getJavaArithmeticExpression(expression.getRhs())+")";
		}
		else{
			if (expression.getLeftOperand()!=null && expression.getRightOperand()!=null)
				return "("+expression.getLeftOperand()+" "+expression.getOperation()+" "+expression.getRightOperand()+")";
			else if (expression.getRhs()!=null)
				return "("+expression.getLeftOperand()+" "+expression.getOperation()+" "+getJavaArithmeticExpression(expression.getRhs())+")";
			else
				return "("+getJavaArithmeticExpression(expression.getLhs())+" "+expression.getOperation()+" "+expression.getRightOperand()+")";
		}
	}

	public static String getNonNullCondition(ArithmeticExpression expression) {
		if (expression.getLeftOperand()==null && expression.getRightOperand()==null){
			return getNonNullCondition(expression.getLhs())+" && "+getNonNullCondition(expression.getRhs());
		}
		else{
			if (expression.getLeftOperand()!=null && expression.getRightOperand()!=null)
				return expression.getLeftOperand()+"!=null && "+expression.getRightOperand()+"!=null";
			else if (expression.getRhs()!=null)
				return expression.getLeftOperand()+"!=null && "+getNonNullCondition(expression.getRhs());
			else
				return getNonNullCondition(expression.getLhs())+" && "+expression.getRightOperand()+"!=null";
		}
	}
}
