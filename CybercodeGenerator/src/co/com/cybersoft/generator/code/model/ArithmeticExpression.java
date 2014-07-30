package co.com.cybersoft.generator.code.model;

public class ArithmeticExpression {
	private String operation;
	private ArithmeticExpression lhs;
	private ArithmeticExpression rhs;
	private String leftOperand;
	private String rightOperand;

	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public ArithmeticExpression getLhs() {
		return lhs;
	}
	public void setLhs(ArithmeticExpression lhs) {
		this.lhs = lhs;
	}
	public ArithmeticExpression getRhs() {
		return rhs;
	}
	public void setRhs(ArithmeticExpression rhs) {
		this.rhs = rhs;
	}
	public String getLeftOperand() {
		return leftOperand;
	}
	public void setLeftOperand(String leftOperand) {
		this.leftOperand = leftOperand;
	}
	public String getRightOperand() {
		return rightOperand;
	}
	public void setRightOperand(String rightOperand) {
		this.rightOperand = rightOperand;
	}
	
}
