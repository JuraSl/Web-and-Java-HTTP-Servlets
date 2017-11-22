package calc;

public enum OperationType {
	
	ADD("+"),
    SUBSTRACT("-"),
    MULTIPLY("*"),
    DIVIDE("/");
    
    private String stringValue;
    
    private OperationType(String s){
        this.stringValue = s;
    }

    public String getStringValue() {
        return stringValue;
    }

}
